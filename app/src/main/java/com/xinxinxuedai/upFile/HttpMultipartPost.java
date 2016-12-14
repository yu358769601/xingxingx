package com.xinxinxuedai.upFile;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.webkit.URLUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * 文件上传
 * 
 * @author zhaoyong
 * 
 */
public class HttpMultipartPost extends AsyncTask<HttpMultipartPost.Param, Integer, JSONObject> {

	private static final String TAG = HttpMultipartPost.class.getSimpleName();
	private String uploadURL;
	private long totalSize;
	private InputStream file;
	private String fileName;
	private CallBack mCallBack;
	private CallBackMsg mCallBackMsg;
	private Context context;
	private String encode = HTTP.UTF_8;


	/**
	 * 
	 * @param url
	 *            文件路径
	 *            其他参数
	 * 
	 */
	public HttpMultipartPost(Context context, String url, InputStream file, String fileName) {
		super();
		this.context = context;
		this.uploadURL = url;
		this.file = file;
		this.fileName = fileName;
	}

	@Override
	protected void onPreExecute() {
		if (!URLUtil.isNetworkUrl(uploadURL)) {
			throw new IllegalArgumentException("unvalid url for post!");
		}
	}

	@Override
	protected JSONObject doInBackground(Param... params) {
		String end = "\r\n";
		String twoHyphens = "--";
		String boundary = "******";
		try {
			URL url = new URL(uploadURL);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url
					.openConnection();
			httpURLConnection.setDoInput(true);
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setUseCaches(false);
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setConnectTimeout(12 * 1000);
			httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
			httpURLConnection.setRequestProperty("Charset", "UTF-8");
			httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);


			DataOutputStream dos = new DataOutputStream(httpURLConnection
					.getOutputStream());
			if(params!=null) {
				StringBuffer sbf = new StringBuffer();
				for (Param param  : params) {
					sbf.append(twoHyphens + boundary + end);
					sbf.append("Content-Disposition: form-data; name=\"").append(param.getKey()).append("\"").append(end).append(end).append(param.getValue()).append(end).append(end);
				}
				dos.writeBytes(sbf.toString());
			}
			dos.writeBytes(twoHyphens + boundary + end);
			//文件名字 重要需要限定
			dos
					.writeBytes("Content-Disposition: form-data; name=\"file\"; filename=\""
							+ fileName
							+ "\"" + end);
			dos.writeBytes(end);

			long total = file.available();
			String totalstr = String.valueOf(total);
			Log.d("文件大小", totalstr);
			byte[] buffer = new byte[8192]; // 8k
			int count = 0;
			int length = 0;
			while ((count = file.read(buffer)) != -1) {
				dos.write(buffer, 0, count);
				length += count;
				publishProgress((int) ((length / (float) total) * 100));
				//为了演示进度,休眠500毫秒
				//Thread.sleep(500);
			}
			file.close();
			dos.writeBytes(end);
			dos.writeBytes(twoHyphens + boundary + twoHyphens + end);
			dos.flush();
			Log.i("发出去的流文件",dos.toString());
			//这里出现了 异常

			InputStream is = httpURLConnection.getInputStream();

			InputStreamReader isr = new InputStreamReader(is, "utf-8");
			BufferedReader br = new BufferedReader(isr);
			@SuppressWarnings("unused")
			String result = br.readLine();
			dos.close();
			is.close();
			JSONObject ret = JSON.parseObject(result);



			//LogUtils.i("上传网络之后反过来的"+ret.toString());
//			Log.i("ret",ret.toString());
//			if(ret.getIntValue("error_code")==-999){
//				Log.i("ret发现999",ret.toString());
//				Toast.makeText(context, "登录信息已过期 请重新登录", Toast.LENGTH_SHORT).show();
//
//				Share.saveToken(context,"");
//			}
			return ret;
			//return jsonObject;
		}catch (Exception e) {
			e.printStackTrace();
			String err = "{'error_code':-1,'error_msg':'"+e.getMessage()+"'}";
			return JSON.parseObject(err);
		}
	}

	@Override
	protected void onProgressUpdate(Integer... progress) {
		if (mCallBack != null) {
			mCallBack.update(progress[0]);
		}
	}

	@Override
	protected void onPostExecute(JSONObject param) {
		Log.d(TAG, param + "");
		if (mCallBackMsg != null) {
			mCallBackMsg.msg(param);
		}
	}

	public void setCallBack(CallBack mCallBack) {
		this.mCallBack = mCallBack;
	}

	public void setCallBackMsg(CallBackMsg mCallBackMsg) {
		this.mCallBackMsg = mCallBackMsg;
	}

	public interface CallBack {
		public void update(Integer i);
	}

	public interface CallBackMsg {
		public void msg(JSONObject msg);
	}

	public static class Param{
		private String key;
		private String value;
		public Param(String key,String value){
			this.key = key;
			this.value = value;
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}
}
