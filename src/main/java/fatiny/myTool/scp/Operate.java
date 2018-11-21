package fatiny.myTool.scp;

import java.io.IOException;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import fatiny.myTool.scp.base.AuthInfo;
import fatiny.myTool.scp.base.IAuth;
import fatiny.myTool.scp.util.ScpLog;

public enum Operate {
	
	PUT{
		@Override
		public boolean execute(Connection conn, IAuth auth){
			SCPClient client = null;
			try {
				AuthInfo authInfo = (AuthInfo)auth;
				client = new SCPClient(conn);
			    client.put(authInfo.getSource(), authInfo.getTarget());
			} catch (IOException ex) {
			    ScpLog.error("",ex);
			}finally {
				conn.close();
			}
			return false;
		}
	},
	GET{
		
	},
	COMAND{
		
	};
	
	public boolean execute(Connection conn, IAuth auth){
		return false;
	}

}
