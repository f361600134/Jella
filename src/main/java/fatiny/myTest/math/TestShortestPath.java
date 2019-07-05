package fatiny.myTest.math;
public class TestShortestPath {//hapjin test
    public static void main(String[] args) {
//        String graphFilePath = "";
//        if(args.length == 0)
//            graphFilePath = "F:\\xxx";
//        else
//            graphFilePath = args[0];
//        
//        String graphContent = FileUtil.read(graphFilePath, null);
    	String graphContent = "0,0,1,4\r\n" + 
    			"1,0,2,7\r\n" + 
    			"2,0,3,3\r\n" + 
    			"3,1,2,3\r\n" + 
    			"4,1,4,2\r\n" + 
    			"5,3,4,3\r\n" + 
    			"6,2,5,2\r\n" + 
    			"7,4,5,2";
        NonDirectedGraph graph = new NonDirectedGraph(graphContent);
        graph.unweightedShortestPath();
        graph.showDistance();
    }
}