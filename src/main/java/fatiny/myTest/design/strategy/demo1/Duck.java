package fatiny.myTest.design.strategy.demo1;

import fatiny.myTest.design.strategy.demo1.impl.FlyBehavior;
import fatiny.myTest.design.strategy.demo1.impl.QuackBehavior;

public abstract class Duck {

	FlyBehavior flyBehavior;
	QuackBehavior quackBehavior;

	public Duck(FlyBehavior flyBehavior, QuackBehavior quackBehavior) {
		super();
		this.flyBehavior = flyBehavior;
		this.quackBehavior = quackBehavior;
	}

	/**
	 * 飞
	 * 
	 * @return void
	 * @date 2018年11月28日下午12:56:29
	 */
	public void fly() {
		flyBehavior.fly();
	}

	/**
	 * 叫
	 * 
	 * @return void
	 * @date 2018年11月28日下午12:56:37
	 */
	public void quack() {
		quackBehavior.quack();
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
	 * 外形
	 * 
	 * @return void
	 * @date 2018年11月28日下午12:56:37
	 */
	public void display() {
		System.out.println("普通外形");
	}

}
