package base.IOC;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yshi
 *
 */
@Slf4j
public final class ReflectionUtils {

	/**
	 * @param clazz
	 * @return
	 */
	public static Object newInstance(Class<?> clazz) {
		Object instance = null;
		try {
			instance = clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			log.error(e.getMessage());
		}
		return instance;
	}
	
	/**
	 * @param obj
	 * @param method
	 * @param args
	 * @return
	 */
	public static Object invokeMethod(Object obj, Method method, Object...args) {
		Object result = null;
		method.setAccessible(true);
		try {
			result = method.invoke(obj, args);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			log.error(e.getMessage());
		}
		return result;
	}
	
	/**
	 * Used to inject the target Object to the field.
	 * @param obj
	 * @param field
	 * @param value
	 */
	public static void setField(Object obj, Field field, Object value) {
		field.setAccessible(true);
		try {
			field.set(obj, value);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			log.error(e.getMessage());
		}
	}
}
