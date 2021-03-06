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

package com.zblservices.doctorbatch.io.websphere.etl;

import static com.zblservices.doctorbatch.io.Constants.RECORD_PROCESSOR;
import static com.zblservices.doctorbatch.io.websphere.Constants.READER;
import static com.zblservices.doctorbatch.io.websphere.Constants.UNIT_OF_WORK_SIZE;
import static com.zblservices.doctorbatch.io.websphere.Constants.WRITER;

import java.util.ArrayList;
import java.util.List;

import com.ibm.batch.api.BatchJobStepInterface;
import com.ibm.websphere.batch.BatchConstants;
import com.zblservices.doctorbatch.io.BatchException;
import com.zblservices.doctorbatch.io.ClassUtil;
import com.zblservices.doctorbatch.io.Reader;
import com.zblservices.doctorbatch.io.RecordProcessor;
import com.zblservices.doctorbatch.io.Writer;
import com.zblservices.doctorbatch.io.websphere.AbstractSkipRecordJobStep;

/**
 * <p>ETLBatchJobStep is a batch job step implementations providing and Extract, 
 * Transform, Load pattern implementation. It provides skip record resiliency 
 * through the AbstractBatchSkipRecordJobStep base class.</p> 
 * 
 * <p>This job step implementation requires a single AbstractBatchDataStreamReader 
 * and AbstractBatchDataStreamWriter, with logical names "reader" and "writer" 
 * respectively, defined in the xJCL.</p> 
 * 
 * <p>The transform logic is encapsulated in an instance of RecordProcessBehavior,
 * and defined to this job step by setting the RecordProcessBehavior 
 * class' fully qualified class-name in the job step's RECORD_PROCESS_BEHAVIOR
 * property.</p>
 * 
 * <p>Additionally, you can control the size of a unit of work - the number of 
 * record processed per invocation of doUnitOfWork (or processJobStep), by 
 * setting the UNIT_OF_WORK_SIZE job step property. NOTE that setting this 
 * value greater than one is effectively a multiplier of your recordCount 
 * value when using RecordBased check-pointing. For example, if you set 
 * UNIT_OF_WORK_SIZE to 100, and specify a RecordBased checkpoint algorithm
 * with recordCount of 100, this job step will process 10,000 records from 
 * the reader before the container commits the global transaction.</p>
 * 
 * <p>Here is an example xJCL snippet showing this job step (note: the bds sections
 * are templated out, and classes referenced under com.customer.* are user-provided)</p>
 * <pre>{@code
 *   <job-step name="RemoveZWords">
 *    <classname>com.ibm.websphere.batch.framework.etl.ETLBatchJobStep</classname>
 *    <checkpoint-algorithm-ref name="recordbased"/>
 *    <batch-data-streams>
 *      <bds>
 *        <logical-name>reader</logical-name>
 *        <impl-class> ... </impl-class>
 *        <props>
 *           ...
 *        </props>
 *      </bds>
 *      <bds>
 *        <logical-name>writer</logical-name>
 *        <impl-class> ... </impl-class>
 *        <props>
 *			 ...
 *        </props>
 *      </bds>
 *    </batch-data-streams>
 *    <props>
 *      <prop name=UNIT_OF_WORK_SIZE value="1000"/>
 *      <prop name=RECORD_PROCESS_BEHAVIOR value="com.customer.batch.JobStepProcessBehavior"/>
 *      <prop name="SKIP_RECORD_OBSERVER_1" value="com.customer.batch.SkipRecordLogger"/>
 *      <prop name="SKIP_RECORD_OBSERVER_2" value="com.customer.batch.SkipRecordEMailAlert"/>
 *      
 *      <!-- optional: you can override the default reader and writer names here -->
 *      <prop name="READER" value="reader"/>
 *      <prop name="WRITER" value="writer"/>
 *    </props>
 *    <results-ref name="maxRC"/>
 *  </job-step>
 * }</pre>
 * 
 * @author Timothy C. Fanelli (tfanelli@zblservices.com, tim@fanel.li)
 *
 * @param <R> The type of input record object to process
 * @param <P> The type of the returned, processed record object.
 */

public class ETLJobStep<R,P> extends AbstractSkipRecordJobStep implements BatchJobStepInterface
{
	private Reader<R> reader;
	private Writer<P> writer;
	private int unitOfWorkSize = 1;
	private RecordProcessor<R,P> recordProcessor;
	
	@Override
	public void createJobStep() {
		super.createJobStep();
		
		String readerStream = getProperty( READER, "reader" );
		String writerStream = getProperty( WRITER, "writer" );

		reader = getBatchDataStream( readerStream );
		writer = getBatchDataStream( writerStream );

		try {
			recordProcessor = ClassUtil.getInstanceForClass( getProperty( RECORD_PROCESSOR ) );
			recordProcessor.initialize( getProperties() );
		} catch ( Throwable t ) {
			t.printStackTrace();
			throw new BatchException(t);
		}
		unitOfWorkSize = Integer.parseInt(getProperty(UNIT_OF_WORK_SIZE, "1"));
	}
	
	@Override
	public int destroyJobStep() {
		if ( recordProcessor != null ) {
			recordProcessor.tearDown();
			return recordProcessor.getReturnCode();
		}
		
		return 0;
	}

	@Override
	protected int doUnitOfWork() {
		List<P> records = new ArrayList<P>( unitOfWorkSize );
		for ( int i = 0; i < unitOfWorkSize; ++i ) 
		{
			R input = reader.read();
			
			if ( input == null ) {
				return BatchConstants.STEP_COMPLETE;
			}

			records.add(recordProcessor.process( input ));
		}
	
		writer.write(records);

		
		
		return BatchConstants.STEP_CONTINUE;
	}
	
	
	/*
	 * For unit testing support only.
	 */
	RecordProcessor<R, P> getRecordProcessBehavior() {
		return recordProcessor;
	}
}
