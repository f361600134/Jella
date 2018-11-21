package fatiny.myTest.httpclient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import fatiny.myTest.httpclient.HttpConnection.CallbackListener;

public class HttpClientConn {

	private static final int defaultTimeout = 10000; // 10s超时

	private final String url;
	private final List<NameValuePair> params = new ArrayList<NameValuePair>();
	private CallbackListener callbackListener;
	private final CloseableHttpClient httpClient;
	private RequestConfig config;
	private final int timeout;
	// private String result;
	// private int responseCode;

	public HttpClientConn(String url, int timeout) {
		httpClient = HttpClients.createDefault();
		this.url = url;
		this.timeout = timeout;
	}

	public HttpClientConn(String url) {
		httpClient = HttpClients.createDefault();
		this.url = url;
		timeout = defaultTimeout;
		// config = RequestConfig.custom().
		// setSocketTimeout(defaultTimeout).setConnectTimeout(defaultTimeout).build();//设置超时
	}

	public HttpClientConn build() {
		config = RequestConfig.custom().setSocketTimeout(defaultTimeout).setConnectTimeout(defaultTimeout).setConnectionRequestTimeout(defaultTimeout).build();// 设置超时
		return this;
	}

	public void doGet() throws Exception {
		HttpGet httpGet = new HttpGet(url);
		httpGet.setConfig(config);
		try {
			CloseableHttpResponse response = httpClient.execute(httpGet);
			int resultCode = response.getStatusLine().getStatusCode();
			String resultInfo = EntityUtils.toString(response.getEntity(), "UTF-8");
			System.out.println(resultCode + ", " + resultInfo);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			httpClient.close();
		}
	}

	public static void doGet1(String url) throws Exception {
		HttpGet httpGet = new HttpGet(url);
		RequestConfig config = RequestConfig.custom().setSocketTimeout(defaultTimeout).setConnectTimeout(defaultTimeout).setConnectionRequestTimeout(defaultTimeout).build();// 设置超时
		httpGet.setConfig(config);
		CloseableHttpClient httpClient = HttpClients.createDefault();
		try {
			CloseableHttpResponse response = httpClient.execute(httpGet);
			int resultCode = response.getStatusLine().getStatusCode();
			String resultInfo = EntityUtils.toString(response.getEntity(), "UTF-8");
			System.out.println(resultCode + ", " + resultInfo);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			httpClient.close();
		}
	}

	public static void doPost1(String url, List<BasicNameValuePair> params) throws Exception {
		HttpPost httpPost = new HttpPost(url);
		RequestConfig config = RequestConfig.custom().setSocketTimeout(defaultTimeout).setConnectTimeout(defaultTimeout).setConnectionRequestTimeout(defaultTimeout).build();// 设置超时
		httpPost.setConfig(config);
		if (params != null)
			httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

		CloseableHttpClient httpClient = HttpClients.createDefault();
		try {
			CloseableHttpResponse response = httpClient.execute(httpPost);
			int resultCode = response.getStatusLine().getStatusCode();
			String resultInfo = EntityUtils.toString(response.getEntity(), "UTF-8");
			System.out.println(resultCode + ", " + resultInfo);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			httpClient.close();
		}
	}

	public static void main(String[] args) {
		// HttpClientConn conn = new
		// HttpClientConn("http://47.92.122.53:8080/youxifan_auth/youxifan/auth");
		try {
			// conn.build().doGet();
			doGet1("http://47.92.122.53:8080/youxifan_auth/youxifan/auth");

			doPost1("http://47.92.122.53:8080/youxifan_auth/youxifan/auth", Arrays.asList(new BasicNameValuePair("a", "b")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
