package fatiny.myTest.other.mavencon;

/**
 * Update code to the specified server.
 * @author Jeremy Feng
 */
public class ControlUpload {
	
	/**
	 * The files that on the local are copied to your directories on the remote server.
	 * <p>
	 * Source and destination path must be different.
	 * @param source The path to the local resource.
	 * @param target this is destination path.
	 */
	 public static void main(String[] args) {
	    	String ip = "192.168.75.128";
	    	int port = 22;
	    	String username = "root";
	    	String password = "Jeremy2oe5";
	    	Scpclient scpclient = new Scpclient(ip, port, username, password);
	    	scpclient.putFile("F:/a.txt", "/home/Jeremy/");
	}
	
	
}
