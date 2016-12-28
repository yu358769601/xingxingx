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

import java.util.Hashtable;

/**
 * <p>
 * 要去执行的任务。
 * </p>
 * Created in Mar 27, 2016 8:15:20 PM.
 * 
 * @author Yolanda;
 */
public class Request{

	private String url;

	private Priority mPriority = Priority.B;

	private int order;

	private Hashtable<String,String> mHashtable;



}
