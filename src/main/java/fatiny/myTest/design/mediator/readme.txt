 public class Mediator {
     Welcome welcome;
     Browse browse;
     Purchase purchase;
     Exit exit;

    public Mediator() {
      welcome = new Welcome(this);
      browse = new Browse(this);
      purchase = new Purchase(this);
      exit = new Exit(this);
   }

  public void handle(String state) {
      if (state.equals("welcome.shop")) {
        browse.execute();
  } else if (state.equals("shop.purchase")) {
        purchase.execute();
  } else if (state.equals("purchase.exit")) {
         exit.execute();
  }
}

调停器模式
有多个组件组成, 然后在调停器里面进行业务处理
  
  
  
  
  
  
  
  
  
  