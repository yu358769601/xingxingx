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
package com.xinxinxuedai.Utils.NoHttp.task;

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

	/**
	 * 如果默认形式参数创建的对象的话就 设置一个,默认的线程池大小为5
	 */
	public MultiAsynctask() {
		this(getDufaultExecutor());
	}

	/**
	 * 如果是传进来一个线程池就设置它为 异步框架的线程池
	 * @param executorService
     */
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
	 * 除了构造方法以外的设置线程池
	 * 
	 * @param executorService
	 */
	public static void setDefaultExecutor(ExecutorService executorService) {
		synchronized (MultiAsynctask.class) {
			sExecutorService = executorService;
		}
	}

	/**
	 * 获取一个单例 主线程handler
	 * @return
     */
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
	 * @param params 子线程获取的 参数 其实也是 给抽象方法 让后面的继承者去实现
	 */
	public final void execute(Param... params) {
		mExecutorService.execute(new Tasker<Param,Update,Result>(this,params ));
	}

	/**
	 * 子类去实现的方法 子线程
	 * @param params 这个可变参数 不一定用 但是得有
	 * @return 这个返回值 是 给  结果的 那个方法的
     */
	protected abstract Result onExecuteTask(Param[] params);

	/**
	 * 发送进度更新到主线程
	 * 
	 * @param update
	 */
	public final void onPostUpdate(Update update) {
		Message.obtain();
		//获取一个单例的handler
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
	protected abstract void onUpdate(Update update);

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
	protected abstract void onResult(Result result);



	/**
	 * <p>自定义handler
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



}
