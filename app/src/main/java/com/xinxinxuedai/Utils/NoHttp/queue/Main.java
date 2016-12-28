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

/**
 * <p>
 * 队列
 * </p>
 * Created in Mar 27, 2016 7:56:06 PM.
 * 
 * @author Yolanda;
 */
public class Main {

	/**
	 * 程序入口
	 */
	public void start() {
		// 第一种，先进先出的队列
//		 YolandaLinkedQueue queue = new YolandaLinkedQueue(3);
//		 queue.start();

//		// 第二种，没有顺序的队列
//		YolandaQueue queue = new YolandaQueue(1);
//		queue.start();

		// 往队列中添加请求
//		for (int i = 0; i < 20; i++) {
//			Request request = new Request("请求" + i);
//			if (i == 10)
//				request.setPriority(Priority.C);
//			if (i == 15)
//				request.setPriority(Priority.D);
//			queue.add(request);
//		}
	}

	public static void main(String[] args) {
		Main main = new Main();
		main.start();
	}

}
