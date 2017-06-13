package com.flink;


/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.GroupReduceFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.api.scala.ExecutionEnvironment;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.windowing.AllWindowFunction;
import org.apache.flink.streaming.api.windowing.assigners.GlobalWindows;
import org.apache.flink.streaming.api.windowing.windows.GlobalWindow;
import org.apache.flink.streaming.api.windowing.windows.Window;
import org.apache.flink.util.Collector;

/**
 * Implements the "WordCount" program that computes a simple word occurrence
 * histogram over text files in a streaming fashion.
 *
 * <p>
 * The input is a plain text file with lines separated by newline characters.
 *
 * <p>
 * Usage: <code>WordCount --input &lt;path&gt; --output &lt;path&gt;</code><br>
 * If no parameters are provided, the program is run with default data from
 * {@link WordCountData}.
 *
 * <p>
 * This example shows how to:
 * <ul>
 * <li>write a simple Flink Streaming program,
 * <li>use tuple data types,
 * <li>write and use user-defined functions.
 * </ul>
 */

class MyReducer implements ReduceFunction<Tuple2<String, Long>> {

	@Override
	public Tuple2<String, Long> reduce(Tuple2<String, Long> arg0, Tuple2<String, Long> arg1) throws Exception {
		return null;
	}
	
}

class MyGroupReducer implements GroupReduceFunction<Tuple2<String, Long>, Long> {

	private static final long serialVersionUID = 1L;

	@Override
	public void reduce(Iterable<Tuple2<String, Long>> values, Collector<Long> out) throws Exception {
		
	}

	
}

class MyWindowFunction implements AllWindowFunction<Tuple2<String, Long>, Long, GlobalWindow> {

	private static final long serialVersionUID = 1L;

	@Override
	public void apply(GlobalWindow window, Iterable<Tuple2<String, Long>> values, Collector<Long> out) throws Exception {
		Long sum = 0L;
		for(Tuple2<String, Long> value : values) {
			System.err.println(value);
			sum += 1;
		}
		out.collect(sum);
	}


	
}

public class WordCount {

	// *************************************************************************
	// PROGRAM
	// *************************************************************************

	public static void main(String[] args) throws Exception {

		// set up the execution environment
		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		// get input data
		DataStream<String> text;

		text = env.fromElements(WordCountData.WORDS);

		//DataStream<Tuple2<String, Long>> counts = text.flatMap(new Tokenizer()).keyBy(0).sum(1);
		DataStream<Long> counts = text.flatMap(new Tokenizer()).windowAll(GlobalWindows.create()).trigger(new SessionTrigger<>()).apply(new MyWindowFunction());
		//windowAll(GlobalWindows.create())
//				.reduce(new ReduceFunction<Tuple2<String, Long>> {
//				      public Tuple2<String, Long> reduce(Tuple2<String, Long> v1, Tuple2<String, Long> v2) {
//				        return new Tuple2<>(v1.f0, v1.f1 + v2.f1);
//				      }
//				    });
//				.trigger(new SessionTrigger<>()).sum(1);
		// emit result
		counts.print();
		
		// execute program
		env.execute("Streaming WordCount");
	}

	// *************************************************************************
	// USER FUNCTIONS
	// *************************************************************************

	/**
	 * Implements the string tokenizer that splits sentences into words as a
	 * user-defined FlatMapFunction. The function takes a line (String) and
	 * splits it into multiple pairs in the form of "(word,1)"
	 * ({@code Tuple2<String,
	 * Integer>}).
	 */
	public static final class Tokenizer implements FlatMapFunction<String, Tuple2<String, Long>> {
		private static final long serialVersionUID = 1L;
		private static int counts = 0;

		@Override
		public void flatMap(String value, Collector<Tuple2<String, Long>> out) throws Exception {
			// normalize and split the line
			String[] tokens = value.toLowerCase().split("\\W+");
			counts +=  tokens.length;
			System.err.println(value + ", token size= "  + counts);

			// emit the pairs
			for (String token : tokens) {
				if (token.length() > 0) {
					//Thread.sleep(1000);
					out.collect(new Tuple2<String, Long>(token, 1L));
				}
			}
		}
	}

}