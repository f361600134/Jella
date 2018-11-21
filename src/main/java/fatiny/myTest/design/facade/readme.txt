门面模式（Facade Pattern）也叫做外观模式，是一种比较常用的封装模式，其定义如下：
      要求一个子系统的外部与其内部的通信必须通过一个统一的对象进行。门面模式提供一个高层次的接口，使得子系统更易于使用。
门面模式注重“统一的对象”，也就是提供一个访问子系统的接口，除了这个接口不允许有任何访问子系统的行为发生.如图
	-----------
	|  Facade |
	|		  |
	-----------
		||
		||
		VV
  ----------------
  |	Subsystem	 |		
  |	Classes	     |
  ----------------

SubSystem Classes是代表所有的类对象简称, 包含至少一个类对象.

门面模式的优点
1. 减少了系统的相互依赖, 提高了灵活性
2. 所有的子系统业务都开通在门面上,提高了安全性.

 门面模式的缺点
 门面模式最大的缺点就是不符合开闭原则，对修改关闭，对扩展开放。
 
demo:
public class ShapeMaker {
   private Shape circle;
   private Shape rectangle;
   private Shape square;

   public ShapeMaker() {
      circle = new Circle();
      rectangle = new Rectangle();
      square = new Square();
   }

   public void drawCircle(){
     circle.draw();
   }
   public void drawRectangle(){
     rectangle.draw();
   }
   public void drawSquare(){
     square.draw();
   }
}
