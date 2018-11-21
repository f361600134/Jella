package fatiny.myTool.scp.base;

public class AuthInfo extends AuthBase{
	
	private String source;
	private String target;
	
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

}
