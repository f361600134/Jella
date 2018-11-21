package fatiny.myTest.design.template.operation.event;

import java.util.Collection;

import fatiny.myTest.design.template.operation.Control;
import fatiny.myTest.design.template.operation.IHandler;

/**
 * @author Jeremy Feng
 *
 */
public class OptJob {
	
	public void run(){
		try {
			Collection<IHandler> handlers = Control.instance(1).values();
			for (IHandler iHandler : handlers)
				iHandler.reset();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
