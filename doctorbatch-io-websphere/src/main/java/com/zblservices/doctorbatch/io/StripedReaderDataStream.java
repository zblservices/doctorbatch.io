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

package com.zblservices.doctorbatch.io;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import com.ibm.websphere.batch.BatchContainerDataStreamException;
import com.ibm.websphere.batch.BatchDataStream;
import com.ibm.websphere.batch.BatchDataStreamMgr;
import com.zblservices.doctorbatch.io.websphere.AbstractBatchDataStream;

public class StripedReaderDataStream<T> extends AbstractBatchDataStream implements Reader<T>, BatchDataStream {
	int index = 0;
	Reader<T> current = null;
	private List<Reader<T>> readers = new LinkedList<Reader<T>>();
	
	@Override
	public String externalizeCheckpointInformation() {
		return Integer.toString(index);
	}

	@Override
	public void intermediateCheckpoint() {
	}

	@Override
	public void internalizeCheckpointInformation(String arg0) {
		if ( arg0 == null )
			index = 0;
		
		try {
			index = Integer.parseInt(arg0);
		} catch (NumberFormatException nfe ) {
			nfe.printStackTrace();
		}
		
		current = readers.get(index);
	}

	@Override
	public void open() throws BatchContainerDataStreamException {
	}

	@Override
	public void positionAtCurrentCheckpoint() throws BatchContainerDataStreamException {
	}

	@Override
	public void positionAtInitialCheckpoint() throws BatchContainerDataStreamException {
	}

	@Override
	public void open(Serializable args) {
	}

	@Override
	public void close() {
	}

	@Override
	public T read() {
		T value = null;

		if ( current != null ) {
			value = current.read();
			
			if ( value == null && ++index < readers.size() ) {
				current = readers.get(index);
				value = current.read();
			}
		}
		
		return value;
	}

	@Override
	public Serializable getState() {
		return externalizeCheckpointInformation();
	}

	@Override
	protected void initialize(Properties props) {
		int index = 0;

		StringBuilder builder = new StringBuilder("STRIPED_DATASTREAM_");
		builder.append(index);
		while ( props.contains( builder.toString() ) ) {
			String name = props.getProperty( builder.toString() );
			
			try {
				@SuppressWarnings("unchecked")
				Reader<T> reader = (Reader<T>) BatchDataStreamMgr.getBatchDataStream(name, getJobStepId() );
				readers.add(reader);
			} catch (BatchContainerDataStreamException e) {
				e.printStackTrace();
			}
			
			builder.setLength(18); // Trim off the number.
		}
	}


}
