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

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created in Mar 27, 2016 9:29:54 PM.
 * 异步操作类
 * @author Yolanda;
 */
public class MultiAsynctaskNetwork extends MultiAsynctask<Void, Integer, String> {

	private NetworkInterface mInterface;

	public MultiAsynctaskNetwork(NetworkInterface networkInterface) {
		this.mInterface = networkInterface;
	}

	@Override
	protected String onExecuteTask(Void... params) {
		HttpURLConnection connection = null;
		try {
			URL url = new URL("http://blog.csdn.net/yanzhenjie1003");
			connection = (HttpURLConnection) url.openConnection();
			int responseCode = connection.getResponseCode();
			if (responseCode == 200) {
				int len = 0;
				byte[] buffer = new byte[1024];
				ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
				InputStream inputStream = new BufferedInputStream(connection.getInputStream());
				while ((len = inputStream.read(buffer)) != -1) {
					arrayOutputStream.write(buffer, 0, len);
				}
				inputStream.close();
				arrayOutputStream.flush();
				inputStream.close();
				return new String(arrayOutputStream.toByteArray());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null)
				connection.disconnect();
		}
		return "请求网络失败";
	}

	@Override
	public void onResult(String result) {// 拿到执行结果，直接更新UI
		mInterface.onResult(result);
	}

}
