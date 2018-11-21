package fatiny.myTest.httpclient;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

/**
 * @see https://github.com/linux-china/spring-boot-starter-httpclient.git
 * @see https://github.com/pixmob/httpclient.git
 * @see https://github.com/JourWon/httpclientutil.git important
 * @see https://github.com/li5454yong/springboot-httpclient.git important
 * 
 */
public final class HttpConnection {

	private static final int HttpConnnectTimeout = 10 * 1000;
	private static final int HttpSoTimeout = 10 * 1000;

	private String url;
	private String method;
	private final List<NameValuePair> params = new ArrayList<NameValuePair>();
	private CallbackListener callbackListener;
	private final HttpClient httpClient;
	private String result;
	private int responseCode;

	private HttpConnection() {
		HttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, HttpConnnectTimeout);
		HttpConnectionParams.setSoTimeout(httpParams, HttpSoTimeout);
		httpClient = new DefaultHttpClient(httpParams);
		// httpClient = HttpClients.createDefault();
	}

	/** 创建一个GET方式的http连接 */
	public static HttpConnection create(String url, CallbackListener callbackListener) {
		HttpConnection httpConnection = new HttpConnection();
		httpConnection.method = "GET";
		httpConnection.url = url;
		// 补一个时间参数. 避免http的缓存问题..
		if (httpConnection.url.indexOf("?") > 0) {
			httpConnection.url += "&_t=" + System.currentTimeMillis();
		} else {
			httpConnection.url += "?_t=" + System.currentTimeMillis();
		}
		httpConnection.callbackListener = callbackListener;
		return httpConnection;
	}

	/** 创建一个POST方式的http连接 */
	public static HttpConnection create(String url, List<NameValuePair> params, CallbackListener callbackListener) {
		HttpConnection httpConnection = new HttpConnection();
		httpConnection.url = url;
		httpConnection.method = "POST";
		if (params != null)
			httpConnection.params.addAll(params);
		httpConnection.callbackListener = callbackListener;
		return httpConnection;
	}

	/**
	 * 开始执行Http请求
	 * 
	 * @param async
	 *            为true时将会在另一个线程中执行http请求和回调,为false时在当前线程中执行
	 */
	public void exec(boolean async) {
		if (async) {
			Thread t = new Thread(new Runnable() {

				@Override
				public void run() {
					_connect();
				}
			});

			t.start();
		} else {
			_connect();
		}
	}

	private void _connect() {
		HttpResponse httpResponse = null;
		HttpPost httpPost = null;
		try {
			if ("GET".equals(method)) {
				httpResponse = httpClient.execute(new HttpGet(url));
			} else {
				httpPost = new HttpPost(url);
				httpPost.setEntity(new UrlEncodedFormEntity(this.params, "UTF-8"));
				httpResponse = httpClient.execute(httpPost);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			callbackListener.callBack(-1, "");
			return;
		}

		responseCode = httpResponse.getStatusLine().getStatusCode();

		result = "";
		try {
			result = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			callbackListener.callBack(responseCode, "");
			return;
		}

		callbackListener.callBack(responseCode, result);
	}

	public String getResult() {
		return result;
	}

	/**
	 * 成功时返回 HttpStatus.SC_OK, 其他状态参考{@link HttpStatus }
	 * 
	 * @return responseCode 成功时返回 HttpStatus.SC_OK, 其他状态参考{@link HttpStatus }
	 */
	public int getResponseCode() {
		return responseCode;
	}

	public interface CallbackListener {
		/**
		 * Http请求的返回时被回调
		 * 
		 * @param responseCode
		 *            成功时返回 HttpStatus.SC_OK, 其他状态参考{@link HttpStatus }
		 * @param result
		 */
		public void callBack(final int responseCode, final String result);
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		// params.add(new BasicNameValuePair("test", "bb"));
		// params.add(new BasicNameValuePair("msg1", "测试字符"));

		String url = "http://47.92.122.53:8180/quick2_auth/quick2/auth";
		HttpConnection http = HttpConnection.create(url, params, new CallbackListener() {
			@Override
			public void callBack(int responseCode, String result) {
				System.out.println("result:" + result);
			}
		});

		http.exec(false);
	}

	public static void test() {
		// List<NameValuePair> params = new ArrayList<NameValuePair>();
		// params.add(new BasicNameValuePair("test", "bb"));
		// params.add(new BasicNameValuePair("msg1", "测试字符"));
		//
		// String url =
		// "http://server.pay.gzyouai.com/2/hucn/notify_excharge?test2=aa&msg1=卡密校验成功&msg2="
		// + URLEncoder.encode(new String("卡密校验成功".getBytes(), "UCS-2"),
		// "UTF-8") + "&msg3=" + URLEncoder.encode("卡密校验成功", "ISO-8859-1") +
		// "&msg4=" + URLEncoder.encode("卡密校验成功", "GBK") + "&msg5="
		// + URLEncoder.encode("卡密校验成功", "GB18030") + "&msg6=" +
		// URLEncoder.encode("卡密校验成功", "GB2312");
		// HttpConnection http = HttpConnection.create(url, new
		// CallbackListener() {
		// @Override
		// public void callBack(int responseCode, String result) {
		// }
		// });
		//
		// http.exec(false);
	}

}
