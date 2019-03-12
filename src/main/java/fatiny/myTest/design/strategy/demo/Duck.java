package fatiny.myTest.design.strategy.demo;

public abstract class Duck {

	/**
	 * 飞
	 * 
	 * @return void
	 * @date 2018年11月28日下午12:56:29
	 */
	public void fly() {
		System.out.println("普通飞");
	}

	/**
	 * 游泳
	 * 
	 * @return void
	 * @date 2018年11月28日下午12:56:37
	 */
	public void swim() {
		System.out.println("普通游泳");
	}

	/**
	 * 叫
	 * 
	 * @return void
	 * @date 2018年11月28日下午12:56:37
	 */
	public void quack() {
		System.out.println("普通叫");
	}

	/**
	 * 外形
	 * 
	 * @return void
	 * @date 2018年11月28日下午12:56:37
	 */
	public void display() {
		System.out.println("普通外形");
	}

}
