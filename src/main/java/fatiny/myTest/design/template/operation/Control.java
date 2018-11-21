package fatiny.myTest.design.template.operation;

import java.util.Collection;
import java.util.Map;

import com.google.common.collect.Maps;

import fatiny.myTest.design.template.operation.domain.OptCaptain;
import fatiny.myTest.design.template.operation.domain.OptRecharge;
import fatiny.myTest.design.template.operation.domain.OptRobotman;

/**
 * @author Jeremy Feng
 *
 */
public class Control {
	
	public Map<String,IHandler> handlers;
	
	public IHandler getHandler(String clazz){
		return handlers.get(clazz);
	}
	
	public IHandler getHandler(Class<?> clazz){
		return getHandler(clazz.getName());
	}
	
	public Collection<IHandler> values(){
		return handlers.values();
	}
	
	public Control putClazz(String clazz, IHandler handler){
		handlers.put(clazz, handler);
		return this;
	}
	
	public Control putClass(Class<?> clazz, IHandler handler){
		return putClazz(clazz.getName(), handler);
	}
	
	public Control(){
		handlers = Maps.newHashMap();
	}
	public static Control control;
	public static Control instance(int playerId){
		if (control == null) {
			control = new Control();
			control.putClass(OptCaptain.class, new OptCaptain(playerId));
			control.putClass(OptRecharge.class, new OptRecharge(playerId));
			control.putClass(OptRobotman.class, new OptRobotman(playerId));
		}
		return control;
	}
	
	
	
}
