package fatiny.myTest.thread.masterworker.demo1;
import java.util.Map;
import java.util.Set;

public class Application {
    public static void main(String[] args) {
        //固定使用5个Worker
        Master master = new Master(new PlusWorker(), 5);
        for(int i=1; i<=10; i++) //提交100个子任务
            master.submit(i);
        master.execute(); //开始计算
        int re = 0;  //最终计算结果保存在此
        Map<String, Object> resultMap = master.getResultMap();
        
        while(true) {//不需要等待所有Worker都执行完成，即可开始计算最终结果
            Set<String> keys = resultMap.keySet();  //开始计算最终结果
            String key = null;
            for(String k : keys) {
                key = k;
                break;
            }
            Integer i = null;
            if(key != null)
                i = (Integer)resultMap.get(key);
            if(i != null)
                re += i; //最终结果
            if(key != null)
                resultMap.remove(key); //移除已被计算过的项目
            if(master.isComplete() && resultMap.size()==0)
                break;
        }
        System.out.println(re);
    }
}