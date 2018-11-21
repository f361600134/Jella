package fatiny.myTest.design.event.simple.police;

/**
 * 警察, 实现观察者接口
 * @author Administrator
 *
 */
public class Policemen implements Observer{
	
	private String name;
	
//	@Override
//	public void update(String message, String name) {
//		StringBuilder buider = new StringBuilder();
//		buider.append("报告总部,我是").append(this.name).append(",");
//		buider.append(name).append("这里有新情况,").append(message);
//		System.out.println(buider.toString());
//	}
	
	@Override
	public void update(EventMsg message) {
		StringBuilder buider = new StringBuilder();
		buider.append("报告总部,我是").append(this.name).append(",");
		buider.append(message.getName()).append("这里有新情况,").append(message.getMessage());
		System.out.println(buider.toString());
	}
	
	public Policemen() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Policemen(String name) {
		super();
		this.name = name;
	}

}
