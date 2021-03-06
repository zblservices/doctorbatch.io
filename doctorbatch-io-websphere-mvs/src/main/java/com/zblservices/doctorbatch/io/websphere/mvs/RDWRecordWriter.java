/*
 * Copyright 2016 ZBL Services, Inc.
 * Copyright 2015 IBM Corp.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zblservices.doctorbatch.io.websphere.mvs;


import static com.zblservices.doctorbatch.io.Constants.RECORD_PARSER_CLASSNAME;
import static com.zblservices.doctorbatch.io.mvs.Constants.FILE_NAME;
import static com.zblservices.doctorbatch.io.mvs.Constants.RECORD_LENGTH;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.List;
import java.util.Properties;

import com.ibm.etools.marshall.RecordBytes;
import com.ibm.jzos.RDWOutputRecordStream;
import com.ibm.websphere.batch.BatchDataStream;
import com.zblservices.doctorbatch.io.BatchException;
import com.zblservices.doctorbatch.io.ClassUtil;
import com.zblservices.doctorbatch.io.Writer;
import com.zblservices.doctorbatch.io.mvs.RecordBytesParser;
import com.zblservices.doctorbatch.io.websphere.AbstractBatchDataStream;

/**
 * This batch data stream writes record structures to a flat on Unix, Linux, or Windows platforms. The 
 * output file is written as a native z/OS variable-block record format. 
 * 
 * @author Timothy C. Fanelli (tfanelli@zblservices.com, tim@fanel.li)
 *
 * @param <T> The record type written by this batch data stream implementation.
 */
public class RDWRecordWriter<T extends RecordBytes> extends AbstractBatchDataStream implements Writer<T>, BatchDataStream {

	private RecordBytesParser<T> recordParser;
	
	private RandomAccessFile outputFile;
	private RDWOutputRecordStream rdwOutputStream;
//	private BufferedOutputStream bufferedOutStream = null;
	private FileOutputStream fileOut = null;
	private String fileName;
	private int recordLength;
	private long currentRecordInd;
	
	public RDWRecordWriter() {
		
	}
	
	/*
	 * Constructs an RDWRecordWriter based on the given output stream, record length, and
	 * parser. This constructor is intended only for use with the frameworks unit test 
	 * suites, and is not for public use. When using this constructor, do not invoke
	 * initialize(props).
	 * 
	 * @param is
	 * @param recordLength
	 * @param parser
	 */
	RDWRecordWriter( OutputStream outputstream, int recordLength, RecordBytesParser<T> parser ) {
		this.rdwOutputStream = new RDWOutputRecordStream(outputstream);
		this.recordLength = recordLength;
		this.recordParser = parser;
	}
	
	
	/*
	 * Used for unit testing... not intended for public use.
	 */
	void setRecordBytesParser( RecordBytesParser<T> parser ) {
		this.recordParser = parser;
	}

	
	@Override
	protected void initialize(Properties props) {
		fileName = props.getProperty(FILE_NAME);
		
		recordLength = Integer.parseInt(props.getProperty(RECORD_LENGTH, "-1"));		
		if(recordLength == -1) {
			throw new BatchException(this.getName() + " " + this.getJobStepId() + " Record Length not specified");
		}
		
		String recordParserClassName = props.getProperty( RECORD_PARSER_CLASSNAME );
		recordParser = ClassUtil.getInstanceForClass(recordParserClassName);
		recordParser.initialize(props);
	}
	
	
	@Override
	public void open() {
		this.open( null );
	}

	@Override
	public void close() {
		try {
			this.rdwOutputStream.close();
		} catch (IOException ioe) {
			throw new BatchException("Unexpected error while closing the stream", ioe.getCause());
		}

	}

	@Override
	public String externalizeCheckpointInformation() {
		String retval = String.valueOf(getCurrentPosition());
		return retval;
	}


	@Override
	public void internalizeCheckpointInformation(String token) {
		setPosition(Integer.parseInt(token));
	}


	@Override
	public void positionAtInitialCheckpoint() {
		// Nothing to do here as open keeps the file at initial checkpoint
	}

	@Override
	public void positionAtCurrentCheckpoint() {
		// Nothing to do here as internalizeCheckpointInformation sets the current record and write method takes care of 
		// adjusting to that offset while writing
	}

	@Override
	public void intermediateCheckpoint() {
		// Nothing to do here
	}
	
	@Override
	public void write(T record) {
		writeRecord( this.recordParser, record, this.rdwOutputStream );
    	currentRecordInd++;
	}
	
	protected void writeRecord( RecordBytesParser<T> recordProcessor, T record, RDWOutputRecordStream bos ) {
    	try {
        	byte[] recordBytes = this.recordParser.parseObjectToRecord(record);
    		bos.write( recordBytes );
		} catch (IOException e) {
			throw new BatchException( e );
		}		
	}
	
	
	public long getCurrentPosition() {
		return currentRecordInd;
	}
	
	protected void setPosition(long position) {
		int currentPos = 0;
		int reclen = -1;
		
		// Calculate correct offset
		for ( int i = 0; i < position; ++i ) {
			try {
				reclen = outputFile.readShort();

				currentPos += reclen;
				outputFile.seek( currentPos );
			} catch (IOException e) {
				throw new BatchException(e);
			}			
		}

		// Truncate at that offset
		try {
			outputFile.getChannel().truncate( currentPos );
		} catch (IOException e) {
			throw new BatchException(e);
		}

	}

	@Override
	public void write(List<? extends T> records) {
		for ( T t : records )
			write(t);
	}

	@Override
	public void open(Serializable state) {
		try {
			outputFile = new RandomAccessFile( fileName, "rw" );
		    fileOut = new FileOutputStream(outputFile.getFD());
			rdwOutputStream = new RDWOutputRecordStream(fileOut);
			
			internalizeCheckpointInformation( state.toString() );
		} catch (FileNotFoundException e) {
			throw new BatchException(e);
		} catch(IOException ioEx) {
			throw new BatchException("Unexpected error while opening file", ioEx.getCause());
		}		
	}
	
	@Override
	public Serializable getState() {
		return externalizeCheckpointInformation();
	}


}
