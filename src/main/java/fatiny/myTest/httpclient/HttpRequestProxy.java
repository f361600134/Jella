package fatiny.myTest.httpclient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HTTP请求代理类
 * 
 * @author 何春节
 * @version 1.0
 * @since 1.6
 *
 */
public class HttpRequestProxy {

	protected static final Logger logger = LoggerFactory.getLogger(HttpRequestProxy.class);

	private HttpRequestProxy() {
	}

	/**
	 * 将参数组装成字符串
	 * 
	 * @param parameters
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private static String buildQueryStr(Map<String, String> paramMap, String encoding) throws UnsupportedEncodingException {
		StringBuilder params = new StringBuilder("");

		if (paramMap != null && !paramMap.isEmpty()) {
			for (Map.Entry<String, String> entry : paramMap.entrySet()) {
				// if (StringUtils.hasText(params)) {
				params.append("&");
				// }

				String val = URLEncoder.encode(entry.getValue(), encoding);
				params.append(entry.getKey()).append("=").append(val);
			}
		}

		return params.toString();
	}

	/**
	 * 将参数组装成字符串
	 * 
	 * @param paramMap
	 * @param encoding
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static byte[] buildParamter(Map<String, String> paramMap, String encoding) throws UnsupportedEncodingException {
		return buildQueryStr(paramMap, encoding).getBytes(encoding);
	}

	public static String postJson(String reqUrl, String json, String encoding) {
		return postJson(reqUrl, json, encoding, 4000);
	}

	/**
	 * <pre>
	 * 发送JSON请求
	 * </pre>
	 * 
	 * @param reqUrl
	 *            HTTP请求URL
	 * @param json
	 *            JSON内容
	 * @return HTTP响应的字符串
	 */
	public static String postJson(String reqUrl, String json, String encoding, int timeout) {

		HttpURLConnection conn = null;
		ByteArrayOutputStream bs = null;
		OutputStream os = null;
		InputStream is = null;
		byte[] bytes = new byte[1024];
		try {
			conn = (HttpURLConnection) new URL(reqUrl).openConnection();
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			// 设置连接超时时间。在人员注册修改的时候，多次调用连接校验引擎。如果引擎不存在，页面会长时间没反应。
			conn.setConnectTimeout(timeout);
			conn.setRequestMethod("POST");
			conn.setInstanceFollowRedirects(false);
			conn.setRequestProperty("Content-Type", "application/json");
			conn.connect();
			// if (StringUtils.hasText(json)) {
			byte[] b = json.getBytes(encoding);
			os = conn.getOutputStream();
			os.write(b, 0, b.length);
			os.flush();
			// }
			is = conn.getInputStream();
			bs = new ByteArrayOutputStream();
			int len = 0;
			while ((len = is.read(bytes)) != -1) {
				bs.write(bytes, 0, len);
			}
			return new String(bs.toByteArray(), encoding);
		} catch (IOException e) {
			logger.error("", e);
		} finally {
			if (bs != null) {
				try {
					bs.close();
				} catch (IOException e) {
					logger.error("", e);
				}
			}

			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					logger.error("", e);
				}
			}

			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					logger.error("", e);
				}
			}
			if (conn != null) {
				conn.disconnect();
			}
		}
		return null;
	}

	public static String post(String reqUrl, Map<String, String> parameters, String encoding) {
		return post(reqUrl, parameters, encoding, 4000);
	}

	/**
	 * <pre>
	 * 发送带参数的POST的HTTP请求
	 * </pre>
	 * 
	 * @param reqUrl
	 *            HTTP请求URL
	 * @param parameters
	 *            参数映射表
	 * @return HTTP响应的字符串
	 */
	public static String post(String reqUrl, Map<String, String> parameters, String encoding, int timeout) {

		HttpURLConnection conn = null;
		ByteArrayOutputStream bs = null;
		OutputStream os = null;
		InputStream is = null;
		byte[] bytes = new byte[1024];
		try {
			conn = (HttpURLConnection) new URL(reqUrl).openConnection();
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			// 设置连接超时时间。在人员注册修改的时候，多次调用连接校验引擎。如果引擎不存在，页面会长时间没反应。
			conn.setConnectTimeout(timeout);
			conn.setRequestMethod("POST");
			conn.setInstanceFollowRedirects(false);
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.connect();
			byte[] b = buildParamter(parameters, encoding);
			if (b != null) {
				os = conn.getOutputStream();
				os.write(b, 0, b.length);
				os.flush();
			}
			is = conn.getInputStream();
			bs = new ByteArrayOutputStream();
			int len = 0;
			while ((len = is.read(bytes)) != -1) {
				bs.write(bytes, 0, len);
			}
			return new String(bs.toByteArray(), encoding);
		} catch (IOException e) {
			logger.error("", e);
		} finally {
			if (bs != null) {
				try {
					bs.close();
				} catch (IOException e) {
					logger.error("", e);
				}
			}

			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					logger.error("", e);
				}
			}

			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					logger.error("", e);
				}
			}
			if (conn != null) {
				conn.disconnect();
			}
		}
		return null;
	}

	public static String post(String reqUrl, Map<String, String> parameters, String encoding, int timeout, String ContentType) {

		HttpURLConnection conn = null;
		ByteArrayOutputStream bs = null;
		OutputStream os = null;
		InputStream is = null;
		byte[] bytes = new byte[1024];
		try {
			conn = (HttpURLConnection) new URL(reqUrl).openConnection();
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			// 设置连接超时时间。在人员注册修改的时候，多次调用连接校验引擎。如果引擎不存在，页面会长时间没反应。
			conn.setConnectTimeout(timeout);
			conn.setRequestMethod("POST");
			conn.setInstanceFollowRedirects(false);
			conn.setRequestProperty("Content-Type", ContentType);
			conn.connect();
			byte[] b = buildParamter(parameters, encoding);
			if (b != null) {
				os = conn.getOutputStream();
				os.write(b, 0, b.length);
				os.flush();
			}
			is = conn.getInputStream();
			bs = new ByteArrayOutputStream();
			int len = 0;
			while ((len = is.read(bytes)) != -1) {
				bs.write(bytes, 0, len);
			}
			return new String(bs.toByteArray(), encoding);
		} catch (IOException e) {
			logger.error("", e);
		} finally {
			if (bs != null) {
				try {
					bs.close();
				} catch (IOException e) {
					logger.error("", e);
				}
			}

			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					logger.error("", e);
				}
			}

			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					logger.error("", e);
				}
			}
			if (conn != null) {
				conn.disconnect();
			}
		}
		return null;
	}

	public static void main(String[] args) {
		String url = "http://47.92.122.53:8180/quick2_auth/quick2/auth";
		System.out.println(post(url, null, "utf-8"));
	}

}
