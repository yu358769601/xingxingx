/*
 * Copyright © Yolanda. All Rights Reserved
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
package com.xinxinxuedai.Utils.NoHttp.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created in Mar 27, 2016 8:10:58 PM.
 * 
 * @author Yolanda;
 */
public class YolandaLinkedQueue<T> {

	private BlockingQueue<T> blockingQueue;

	private TaskExecutor[] taskExecutors;

	public YolandaLinkedQueue(int poolSize) {
		// LinkedBlockingQueue是一个先进先出的队列
		blockingQueue = new LinkedBlockingQueue<>();
		taskExecutors = new TaskExecutor[poolSize];
	}

	public void add(T t) {
		blockingQueue.add(t);
	}

	public void start() {
		for (int i = 0; i < taskExecutors.length; i++) {
			taskExecutors[i] = new TaskExecutor(blockingQueue);
			taskExecutors[i].start();
		}
	}

	public void stop() {
		for (TaskExecutor taskExecutor : taskExecutors) {
			taskExecutor.setRunning(false);
			taskExecutor.interrupt();
		}
	}

}
