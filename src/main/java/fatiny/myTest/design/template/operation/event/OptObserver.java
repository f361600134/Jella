package fatiny.myTest.design.template.operation.event;

import com.google.common.eventbus.Subscribe;

import fatiny.myTest.design.template.operation.Control;
import fatiny.myTest.design.template.operation.IHandler;
import fatiny.myTest.design.template.operation.domain.OptCaptain;
import fatiny.myTest.design.template.operation.domain.OptRecharge;

/**
 * @author Jeremy Feng
 *
 */
public class OptObserver {
	
	@Subscribe
	public void userResourceEvent(UserResourceEvent event) {
		
		IHandler iHandler = null;
		
		Control control = Control.instance(1);
		iHandler = control.getHandler(OptCaptain.class);
		iHandler.onTrigger(event.getType(), event.getNum());
		
		iHandler = control.getHandler(OptRecharge.class);
		iHandler.onTrigger(event.getType(), event.getNum());
		
		
	}

}
