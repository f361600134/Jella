package fatiny.myTest.httpclient;

import java.util.List;

import org.apache.http.NameValuePair;

public class HttpTool {

	/**
	 * 发送url请求
	 * 
	 * @param url
	 * @return
	 */
	public static String submit(String url) {
		CallBackListener callback = new CallBackListener();
		HttpConnection httpConn = HttpConnection.create(url, callback);
		try {
			httpConn.exec(false);
		} catch (Exception e) {
			return null;
		}
		return callback.getResult();
	}

	/**
	 * 发送url请求
	 * 
	 * @param url
	 * @return
	 */
	public static String submit(String url, List<NameValuePair> params) {
		String result = "";
		CallBackListener callback = new CallBackListener();
		HttpConnection httpConn = HttpConnection.create(url, params, callback);
		try {
			httpConn.exec(false);
			result = callback.getResult();
		} catch (Exception e) {
			return result;
		}
		return result;
	}

	// public static String getRemoteAddress(HttpServletRequest request) {
	// String ip = request.getHeader("x-forwarded-for");
	// if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
	// ip = request.getHeader("Proxy-Client-IP");
	// }
	// if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
	// ip = request.getHeader("WL-Proxy-Client-IP");
	// }
	// if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
	// ip = request.getRemoteAddr();
	// }
	// return ip;
	// }

	public static void main(String[] args) {
		// youxifan();
		baidu();
	}

	public static void baidu() {
		String url = "http://www.baidu.com";
		String result = submit(url, null);
		System.out.println(result);
	}

	public static void youxifan() {
		String url = "http://47.92.122.53:8080/youxifan_auth/youxifan/auth";
		String result = submit(url, null);
		System.out.println(result);
	}

	public static void quick() {
		String url = "http://47.92.122.53:8180/quick2_auth/quick2/auth";
		String result = submit(url, null);
		System.out.println(result);
	}

}
