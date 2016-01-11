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

import java.util.Properties;

/**
 * ProcessorChain is a composite record processor that chains two RecordProcessors together for complex 
 * workflow management.
 * 
 * @author Timothy C. Fanelli (tfanelli@zblservices.com)
 *
 * @param <InputRecordType> The input record type.
 * @param <IntermediateRecordType> The record type which is the output of the first processor, and input to the second processor
 * @param <OutputRecordType> The output record type.
 */
public class ProcessorChain<InputRecordType, IntermediateRecordType, OutputRecordType> implements RecordProcessor<InputRecordType, OutputRecordType> {
	private RecordProcessor< InputRecordType, IntermediateRecordType > firstProcessor;
	private RecordProcessor< IntermediateRecordType, OutputRecordType > secondProcessor;

	/**
	 * The property name that specifies the first record processor class type.
	 */
	public final static String FIRST_RECORD_PROCESSOR_CLASSNAME = "FIRST_RECORD_PROCESSOR_CLASSNAME";

	/**
	 * The property name that specifies the second record processor class type.
	 */
	public final static String SECOND_RECORD_PROCESSOR_CLASSNAME = "SECOND_RECORD_PROCESSOR_CLASSNAME";
	
	@Override
	@SuppressWarnings("unchecked")
	public void initialize(Properties jobStepProperties) {
		String firstClass = jobStepProperties.getProperty( FIRST_RECORD_PROCESSOR_CLASSNAME );
		String secondClass = jobStepProperties.getProperty( SECOND_RECORD_PROCESSOR_CLASSNAME );
		
		firstProcessor = (RecordProcessor<InputRecordType, IntermediateRecordType>) ClassUtil.getInstanceForClass( firstClass );
		secondProcessor = (RecordProcessor<IntermediateRecordType, OutputRecordType>) ClassUtil.getInstanceForClass( secondClass );
	}

	@Override
	public OutputRecordType process(InputRecordType record) {
		return secondProcessor.process( 
				firstProcessor.process( record ) 
			);
	}

	/**
	 * Returns the max of the first and second processor's return codes.
	 */
	@Override
	public int getReturnCode() {
		return Math.max( firstProcessor.getReturnCode(), secondProcessor.getReturnCode() );
	}

	/**
	 * Tear's down both the first and second processors.
	 */
	@Override
	public void tearDown() {
		firstProcessor.tearDown();
		secondProcessor.tearDown();
	}

}
