package fatiny.myTest.design.template.operation.event;

/**
 * @author Jeremy Feng
 */
public class UserResourceEvent {
	
	private int type;
	private int num;
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public UserResourceEvent(int type, int num) {
		super();
		this.type = type;
		this.num = num;
	}
	public UserResourceEvent() {}
	
	

}
