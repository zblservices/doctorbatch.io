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

package com.zblservices.doctorbatch.io.patterns;

import java.io.BufferedWriter;
import java.util.Arrays;
import java.util.List;

import com.zblservices.doctorbatch.io.BatchException;
import com.zblservices.doctorbatch.io.Writer;

/**
 * StringWriter writes string records, one per line, to an
 * output writer.
 * 
 * @author Timothy C. Fanelli (tfanelli@zblservices.com, tim@fanel.li)
 */
public abstract class SingleLineRecordWriter implements Writer<String> {
	private int lineNumber;
	private BufferedWriter writer;
	
	
	@Override
	public void write(String record) {
		try {
			write( Arrays.asList( new String[]{record} ) );
		} catch ( Exception e ) {
			throw new BatchException(e);
		}
	}

	@Override
	public void write(List<? extends String> records) {
		try {
			for (Object line : records) {
				writer.write(line + "\n");
			}
		} catch ( Exception e ) {
			throw new BatchException(e);
		}
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public BufferedWriter getWriter() {
		return writer;
	}

	public void setWriter(BufferedWriter writer) {
		this.writer = writer;
	}
	
	
}














