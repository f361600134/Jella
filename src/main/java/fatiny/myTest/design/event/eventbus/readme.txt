  在软件开发过程中，对象信息的分享以及相互直接的协作是必须的，困难在于确保对象之间的沟通是有效完成的，而不是拥有成本高度耦合的组件。
  当对象对其他组件的责任有太多的细节时，它被认为是高度耦合的。当一个应用程序有高度的耦合，维护将变得非常具有挑战，任何变化都将带来涟漪效应。
  为了解决这一类的软件设计问题，我们就需要基于事件的编程。
  
Eventbus: 内存级别的事件分发,事件驱动

@Subscribe 事件的订阅,加了此函数就是事件源.受监听的对象
@AllowConcurrentEvents 同步的方式去处理

