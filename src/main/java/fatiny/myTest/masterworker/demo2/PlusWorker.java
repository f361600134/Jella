package fatiny.myTest.masterworker.demo2;
public class PlusWorker extends Worker { //求立方和
    @Override
    public Object handle(Object input) {
        int i = (Integer)input;
        return i * i * i;
    }
}