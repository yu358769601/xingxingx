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

/**
 * Created in Mar 27, 2016 8:12:37 PM.
 * 
 * @author Yolanda;
 */
public class TaskExecutor <T>extends Thread {

	private BlockingQueue<T> blockingQueue;

	private boolean isRunning = true;

	public TaskExecutor(BlockingQueue<T> blockingQueue) {
		this.blockingQueue = blockingQueue;
	}

	/**
	 * @param isRunning the isRunning to set
	 */
	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	@Override
	public void run() {
		while (isRunning) {
			T t = null;
			try {
				// take方法是一个阻塞的方法，每次调用会拿到队列中的第一个任务，如果队列为空，这个方法将一直阻塞，知道队列中有任务再次返回
				t = blockingQueue.take();
			} catch (InterruptedException e) {
				return;
			}



		}
	}

}
