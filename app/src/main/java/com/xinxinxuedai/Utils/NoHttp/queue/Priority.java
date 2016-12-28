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
 * Created in Mar 27, 2016 8:41:19 PM.
 * 
 * @author Yolanda;
 */
public enum Priority {

	/**
	 * 优先级最低
	 */
	A,

	/**
	 * 默认优先级
	 */
	B,

	/**
	 * 优先级最高
	 */
	C,

	/**
	 * 一般情况下不用；特殊情况下，请求假如到到队列后立即执行
	 */
	D

}
