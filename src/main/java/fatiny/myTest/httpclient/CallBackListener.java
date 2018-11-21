package fatiny.myTest.httpclient;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;

import fatiny.myTest.httpclient.HttpConnection.CallbackListener;

public class CallBackListener implements CallbackListener {

	String result = "";

	@Override
	public void callBack(int responseCode, String result) {
		if (responseCode == HttpStatus.SC_OK && !StringUtils.isEmpty(result)) {
			this.result = result;
		}
	}

	public String getResult() {
		return result;
	}

}
