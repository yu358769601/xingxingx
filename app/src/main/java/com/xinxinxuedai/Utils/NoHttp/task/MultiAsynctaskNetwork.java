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

import android.os.SystemClock;
import android.util.Log;
import android.webkit.URLUtil;

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
	private String urlNetwork;
	public MultiAsynctaskNetwork(String url,NetworkInterface networkInterface) {
		this.mInterface = networkInterface;
		this.urlNetwork = url;
		//创建就调用开始工作
		execute();
	}

	@Override
	protected void onUpdate(Integer integer) {
		mInterface.onUpData(integer);
	}

	@Override
	protected String onExecuteTask(Void... params) {
		HttpURLConnection connection = null;
		try {
			if (!URLUtil.isNetworkUrl(urlNetwork)){
				return "";
			}
			URL url = new URL(urlNetwork);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(false);
			connection.setConnectTimeout(10 * 1000);
			connection.setReadTimeout(10 * 1000);
			connection.setRequestProperty("Connection", "Keep-Alive");
			connection.setRequestProperty("Charset", "UTF-8");
		//	connection.setRequestProperty("Accept-Encoding", "gzip, deflate");
			connection .setRequestProperty("Accept-Encoding", "identity");
			connection.connect();

			int responseCode = connection.getResponseCode();
			if (responseCode == 200) {
				ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
				InputStream inputStream = new BufferedInputStream(connection.getInputStream());
				//获取网络文件的大小   .setRequestProperty("Accept-Encoding", "identity");
				//这样设置获取到了正常的 网络文件大小  不能要压缩的
				int total = connection.getContentLength();
				//获取本地文件的大小
				//long total = inputStream.available();
				String totalstr = String.valueOf(total);
				Log.d("文件大小", totalstr);
				int len = 0;
				int length = 0;
				byte[] buffer = new byte[512];
				while ((len = inputStream.read(buffer)) != -1) {
					arrayOutputStream.write(buffer, 0, len);
					length += len;
					//演示进度
					SystemClock.sleep(1*200);
					//算出来的值
					int progress = (int) (length * 100L / total);
					onPostUpdate(progress);

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

	/**
	 * 形式参数是 执行结果 也就是 onExecuteTask 方法的返回值
	 * @param result
     */
	@Override
	public void onResult(String result) {
		mInterface.onResult(result);
	}

}
