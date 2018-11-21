package fatiny.myTool.scp.base;

public class AuthBase implements IAuth{
	
	private String serverId;
	private String ip;
	private int port;
	private String username;
	private String password;
	private String source;
	private String target;
	
	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}
	
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

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public String getUsername() {
	    return username;
	}
	
	public void setUsername(String username) {
	    this.username = username;
	}
	
	public String getPassword() {
	    return password;
	}
	
	public void setPassword(String password) {
	    this.password = password;
	}
	
	public int getPort() {
	    return port;
	}
	
	public void setPort(int port) {
	    this.port = port;
	}
	
	public AuthBase() {}
	
	public AuthBase(String serverId, String ip, int port, String username, String password, String source) {
		super();
		this.serverId = serverId;
		this.ip = ip;
		this.port = port;
		this.username = username;
		this.password = password;
		this.source = source;
	}

	public AuthBase(String ip, int port, String username, String password) {
		super();
		this.ip = ip;
		this.port = port;
		this.username = username;
		this.password = password;
	}

	public AuthBase( String ip, int port, String username, String password, String target) {
		super();
		this.ip = ip;
		this.port = port;
		this.username = username;
		this.password = password;
		this.target = target;
	}

	
}
