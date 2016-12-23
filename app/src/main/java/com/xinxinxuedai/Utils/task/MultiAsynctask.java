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
package com.xinxinxuedai.Utils.task;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created in Mar 27, 2016 9:22:22 PM.
 * 
 * @author Yolanda;
 */
public abstract class MultiAsynctask<Param, Update, Result> {

	/**
	 * 更新的what
	 */
	private static final int WHAT_UPDATE = 0x01;

	/**
	 * 发送结果的what
	 */
	private static final int WHAT_RESULT = 0x02;

	/**
	 * 默认的线程池
	 */
	private static ExecutorService sExecutorService;

	/**
	 * 默认并发大小
	 */
	private static final int DEFAULT_POOL_SIZE = 5;

	/**
	 * 发送结果的Handler
	 */
	private static Handler sHandler;

	/**
	 * Handler的锁
	 */
	private static Object HANDLER_LOCK = new Object();

	/**
	 * 本地异步任务的执行器
	 */
	private ExecutorService mExecutorService = null;

	public MultiAsynctask() {
		this(getDufaultExecutor());
	}

	public MultiAsynctask(ExecutorService executorService) {
		mExecutorService = executorService;
	}

	/**
	 * 拿到默认的线程池
	 * 
	 * @return
	 */
	private static ExecutorService getDufaultExecutor() {
		synchronized (MultiAsynctask.class) {
			if (sExecutorService == null)
				sExecutorService = Executors.newFixedThreadPool(DEFAULT_POOL_SIZE);
			return sExecutorService;
		}
	}

	/**
	 * 设置默认的线程池
	 * 
	 * @param executorService
	 */
	public static void setDefaultExecutor(ExecutorService executorService) {
		synchronized (MultiAsynctask.class) {
			sExecutorService = executorService;
		}
	}

	public static Handler getDefaultPoster() {
		synchronized (HANDLER_LOCK) {
			if (sHandler == null)
				sHandler = new Poster();
			return sHandler;
		}
	}

	/**
	 * 开始执行任务
	 * 
	 * @param params
	 */
	public final void execute(Param... params) {
		mExecutorService.execute(new Tasker(params));
	}

	protected abstract Result onExecuteTask(Param... params);

	/**
	 * 发送进度更新到主线程
	 * 
	 * @param update
	 */
	public final void onPostUpdate(Update update) {
		Message.obtain();
		Message message = getDefaultPoster().obtainMessage();
		message.what = WHAT_UPDATE;
		message.obj = new Messager<Param, Update, Result>(this, update, null);
		message.sendToTarget();
	}

	/**
	 * 当返回进度更新的时候
	 * 
	 * @param update
	 */
	protected void onUpdate(Update update) {
	}

	/**
	 * 发送进度执行结果到主线程
	 * 
	 * @param result
	 */
	public final void onPostResult(Result result) {
		Message.obtain();
		Message message = getDefaultPoster().obtainMessage();
		message.what = WHAT_RESULT;
		message.obj = new Messager<Param, Update, Result>(this, null, result);
		message.sendToTarget();
	}

	/**
	 * 当返回执行结果的时候
	 * 
	 * @param result
	 */
	protected void onResult(Result result) {

	}

	private static class Messager<Param, Update, Result> {

		private final MultiAsynctask<Param, Update, Result> asynctask;

		private final Update update;

		private final Result result;

		public Messager(MultiAsynctask<Param, Update, Result> asynctask, Update update, Result result) {
			this.asynctask = asynctask;
			this.update = update;
			this.result = result;
		}

		/**
		 * 调用当前MultiAsynctask的主线程更新方法
		 */
		public void onUpdate() {
			asynctask.onUpdate(update);
		}

		/**
		 * 调用当前MultiAsynctask的主线程结果方法
		 */
		public void onResult() {
			asynctask.onResult(result);
		}

	}

	/**
	 * <p>
	 * 线程间通信使者
	 * </p>
	 * Created in Mar 27, 2016 10:00:03 PM.
	 * 
	 * @author Yolanda;
	 */
	private static class Poster extends Handler {

		public Poster() {
			super(Looper.getMainLooper());
		}

		@Override
		public void handleMessage(Message msg) {
			Messager<?, ?, ?> messageer = (Messager<?, ?, ?>) msg.obj;
			if (msg.what == WHAT_RESULT) {
				messageer.onResult();
			} else if (msg.what == WHAT_UPDATE) {
				messageer.onUpdate();
			}
		}
	}

	/**
	 * <p>
	 * 任务执行器
	 * </p>
	 * Created in Mar 27, 2016 10:03:44 PM.
	 * 
	 * @author Yolanda;
	 */
	private class Tasker implements Runnable {

		private Param[] params;

		public Tasker(Param... params) {
			this.params = params;
		}

		@Override
		public void run() {
			Result result = onExecuteTask(params);
			onPostResult(result);
		}
	}

}
