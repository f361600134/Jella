package fatiny.myTest.rubbishbin;

/**
 * @author Jeremy Feng
 *
 */
public class rubbishBin {
	
	private String bookName;

    public rubbishBin(String bookName) {
        this.bookName = bookName;
        System.out.println(bookName+" is created");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println(bookName+" is freed");
    }
    
   public static class test {
   	 public static void main(String args[]) {
   	    	rubbishBin l1 = new rubbishBin("book1");
   	    	rubbishBin l2 = new rubbishBin("book2");
   	        l1 = l2 = null;
   	        System.gc();
   	 }
   }
   
   

}

