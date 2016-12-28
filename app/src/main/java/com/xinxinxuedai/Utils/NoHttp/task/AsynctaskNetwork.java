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

import android.os.AsyncTask;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created in Mar 27, 2016 9:14:56 PM.
 * 
 * @author Yolanda;
 */
public class AsynctaskNetwork extends AsyncTask<Void, Void, String> {

	private NetworkInterface mInterface;

	public AsynctaskNetwork(NetworkInterface networkInterface) {
		this.mInterface = networkInterface;
	}

	@Override
	protected String doInBackground(Void... params) {
		try {
			URL url = new URL("http://blog.csdn.net/yanzhenjie1003");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			int responseCode = connection.getResponseCode();
			if (responseCode == 200) {
				return "请求网络成功";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "请求网络失败";
	}

	@Override
	protected void onPostExecute(String result) {
		mInterface.onResult(result);
	}

}
