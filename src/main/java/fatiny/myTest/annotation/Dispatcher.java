package fatiny.myTest.annotation;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.Lists;

import fatiny.myTest.annotation.handler.BagHandler;
import fatiny.myTest.annotation.handler.WeaponHandler;

/**
 * @author Jeremy Feng
 */
public class Dispatcher {
	
	private Map<Short, Commander> commanders = new HashMap<>();

	public void load(Collection<Class<?>> classes) throws Exception{
		if (classes == null)
			throw new NullPointerException("classes is null");
		
		for (Class<?> clazz : classes) {
			Object o = clazz.newInstance();
			Method[] methods = clazz.getDeclaredMethods();
			
			for (Method method : methods) {
				Command command = method.getAnnotation(Command.class);
				if (command != null){
					commanders.put(command.id(), new Commander(o, command.mustLogin(), method));
				}
			}
		}
	}
	
	public void invoke(Short id) throws Exception{
		Commander commander = getCommander(id);
		if (commander == null) 
			throw new NullPointerException("Not found commander");
		
		
		if (commander.IsMustLogin()) {
			commander.method.invoke(commander.object);
		
		}else{
			
			System.out.println("");
			
		}
		
	}
	
	public Commander getCommander(short id){
		return commanders.get(id);
	}

	public static class Commander{
		
		private final Object object;
		private final Method method;
		private final boolean mustLogin;
		
		public Commander(Object object, Boolean mustLogin, Method method) throws Exception{
			this.object = object;
			this.method = method;
			this.mustLogin = mustLogin;
		}
		
		/**
		 * @return true: 必须登录, false: 无需登录执行
		 */
		public boolean IsMustLogin(){
			return mustLogin == true;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Dispatcher dispatcher = new Dispatcher();
		try {
			/*
			 * 可以做一个扫描类,把相关模块的Handler,在游戏启动的时候全部加载到dispatcher里面. 
			 */
			dispatcher.load(Lists.newArrayList(BagHandler.class, WeaponHandler.class));
			dispatcher.invoke((short) 2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
