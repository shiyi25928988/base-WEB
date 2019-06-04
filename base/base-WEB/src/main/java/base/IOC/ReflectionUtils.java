package base.IOC;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class ReflectionUtils {

	public static Object newInstance(Class<?> clazz) {
		Object instance = null;
		try {
			instance = clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return instance;
	}
	
	public static Object invokeMethod(Object obj, Method method, Object...args) {
		Object result = null;
		method.setAccessible(true);
		try {
			result = method.invoke(obj, args);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static void setField(Object obj, Field field, Object value) {
		field.setAccessible(true);
		try {
			field.set(obj, value);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
