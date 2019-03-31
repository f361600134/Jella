package fatiny.myTest.design.state;
public class GumballMachine{
    //all state
    final static int SOLD_OUT = 0;
    final static int NO_QUARTER = 1;
    final static int HAS_QUARTER = 2;
    final static int SOLD = 3;
    
    //default sold out
    int state = SOLD_OUT;
    int count  = 0;//gumbal count 
    
    public GumballMachine(int count){
        this.count = count;
        if(count >0){
            state = NO_QUARTER;
        }
    }
    
    //投入25分钱 
    public void insertQuarter(){
        if (state == HAS_QUARTER){
            System.out.print("- you have insert a quarter -\n");
        }else if (state == NO_QUARTER){
            state = HAS_QUARTER;
            System.out.print("- you inserted a quarter -\n");
        }else if (state == SOLD_OUT){
            System.out.print("- the machine is sold out -\n");
        }else if (state == SOLD){
            System.out.print("- please wait, we are giving you a gumbal -\n");
        }
    }
    
    //退回25分钱
    public void ejectQuarter(){
        if (state == HAS_QUARTER){
            System.out.print("- quarter returned -\n");
            state = NO_QUARTER;
        }else if (state == NO_QUARTER){
            System.out.print("- you have not inserted a quarter -\n");
        }else if (state == SOLD_OUT){
            System.out.print("- do not this, you have not inserted a quarter -\n");
        }else if (state == SOLD){
            System.out.print("- Sorry, you already turned the crank -\n");
        }
    }
    
    //转动曲柄
    public void turnCrank(){
        if (state == HAS_QUARTER){
            System.out.print("- turned -\n");
            state = SOLD;
            dispense();
        }else if (state == NO_QUARTER){
            System.out.print("- you turned, but you have not inserted a quarter -\n");
        }else if (state == SOLD_OUT){
            System.out.print("- gumbal has sold out -\n");
        }else if (state == SOLD){
            System.out.print("- turning twice no use -\n");
        }
    }
    
    //发放糖果
    public void dispense(){
        if (state == HAS_QUARTER){
            System.out.print("- No gumbal dispensed -\n");
        }else if (state == NO_QUARTER){
            System.out.print("- you need pay first -\n");
        }else if (state == SOLD_OUT){
            System.out.print("- No gumbal dispensed -\n");
        }else if (state == SOLD){
            System.out.print("- gumbal is comming -\n");
            count = count - 1;
            if (count == 0){
                System.out.print("- Sorry, out of gumbals -\n");
                state = SOLD_OUT;
            }else{
                state = NO_QUARTER;
            }
        }
    }
    
    //other methods
    public String toString() {
        String str = "";
        if (state == HAS_QUARTER){
            str = "- Machine state: " + "HAS_QUARTER, count = " +count+ "-\n";
        }else if (state == NO_QUARTER){
            str = "- Machine state: " + "NO_QUARTER, count = " +count + "-\n";
        }else if (state == SOLD_OUT){
            str = "- Machine state: " + "SOLD_OUT, count = " +count + "-\n";
        }else if (state == SOLD){
            str = "- Machine state: " + "SOLD, count = " +count + "-\n";
        }
        return str;
    }
}
