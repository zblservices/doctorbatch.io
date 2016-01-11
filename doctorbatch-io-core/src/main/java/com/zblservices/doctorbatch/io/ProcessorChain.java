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
