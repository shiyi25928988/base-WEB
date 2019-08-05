package base.rest;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Strings;
import com.google.inject.Injector;

import base.IOC.ReflectionUtils;
import base.config.GuiceServletConfig;
import base.servlet.ServletHelper;

/**
 * @author yshi
 *
 */
public class RestServiceImpl implements RestService {

	private Set<Class<?>> classSet;
	
	private Map<String, Class<?>> classMap = new ConcurrentHashMap<>();

	private Map<String, Method> methodMap_GET     = new ConcurrentHashMap<>();
	private Map<String, Method> methodMap_PUT     = new ConcurrentHashMap<>();
	private Map<String, Method> methodMap_POST    = new ConcurrentHashMap<>();
	private Map<String, Method> methodMap_DELETE  = new ConcurrentHashMap<>();
	private Map<String, Method> methodMap_OPTIONS = new ConcurrentHashMap<>();
	private Map<String, Method> methodMap_HEAD    = new ConcurrentHashMap<>();
	
	public RestServiceImpl(final Set<Class<?>> classSet) {
		this.classSet = classSet;
		if (Objects.nonNull(this.classSet) && this.classSet.size() >= 1) {
			classSet.stream().forEach(clazz -> {
				Method[] methods = clazz.getDeclaredMethods();
				Stream.of(methods).forEach(m -> {

					if (m.isAnnotationPresent(GET.class)) {
						if(m.isAnnotationPresent(Path.class)) {
							Path path = m.getAnnotation(Path.class);
							if(!Strings.isNullOrEmpty(path.value())) {
								methodMap_GET.put(path.value(), m);
								classMap.put(path.value(), clazz);
							}
						}
					}
					if (m.isAnnotationPresent(PUT.class)) {
						if(m.isAnnotationPresent(Path.class)) {
							Path path = m.getAnnotation(Path.class);
							if(!Strings.isNullOrEmpty(path.value())) {
								methodMap_PUT.put(path.value(), m);
								classMap.put(path.value(), clazz);
							}
						}
					}
					if (m.isAnnotationPresent(POST.class)) {
						if(m.isAnnotationPresent(Path.class)) {
							Path path = m.getAnnotation(Path.class);
							if(!Strings.isNullOrEmpty(path.value())) {
								methodMap_POST.put(path.value(), m);
								classMap.put(path.value(), clazz);
							}
						}
					}
					if (m.isAnnotationPresent(DELETE.class)) {
						if(m.isAnnotationPresent(Path.class)) {
							Path path = m.getAnnotation(Path.class);
							if(!Strings.isNullOrEmpty(path.value())) {
								methodMap_DELETE.put(path.value(), m);
								classMap.put(path.value(), clazz);
							}
						}
					}
					if (m.isAnnotationPresent(OPTIONS.class)) {
						if(m.isAnnotationPresent(Path.class)) {
							Path path = m.getAnnotation(Path.class);
							if(!Strings.isNullOrEmpty(path.value())) {
								methodMap_OPTIONS.put(path.value(), m);
								classMap.put(path.value(), clazz);
							}
						}
					}
					if (m.isAnnotationPresent(HEAD.class)) {
						if(m.isAnnotationPresent(Path.class)) {
							Path path = m.getAnnotation(Path.class);
							if(!Strings.isNullOrEmpty(path.value())) {
								methodMap_HEAD.put(path.value(), m);
								classMap.put(path.value(), clazz);
							}
						}
					}
				});
			});
		}
	}
	
	private void invoke(Map<String, Method> methodMap) {
		HttpServletRequest req = ServletHelper.getRequest();
		HttpServletResponse resp = ServletHelper.getResponse();
		String restPath = StringUtils.remove(req.getRequestURI(), req.getContextPath());
		Method method = methodMap.get(restPath);
		Class<?> clazz = classMap.get(restPath);
		invoke(clazz, method);
	}
	
	private void invoke(Class<?> clazz, Method method) {
		if(Objects.nonNull(clazz)) {
			if(Objects.nonNull(method)) {
				Object obj = ReflectionUtils.newInstance(clazz);
				Field[] fields = clazz.getDeclaredFields();
				Injector injector = GuiceServletConfig.getInjectorInstance();
				Stream.of(fields).forEach(field->{
					if(field.isAnnotationPresent(com.google.inject.Inject.class) || field.isAnnotationPresent(javax.inject.Inject.class)) {
						ReflectionUtils.setField(obj, field, injector.getInstance(field.getType()));
					}
				});
				ReflectionUtils.invokeMethod(obj, method);
			}
		}
	}
	
	@Override
	public void doGet() {
		invoke(methodMap_GET);
	}

	@Override
	public void doPut() {
		invoke(methodMap_PUT);
	}

	@Override
	public void doPost() {
		invoke(methodMap_POST);
	}

	@Override
	public void doHead() {
		invoke(methodMap_HEAD);
	}

	@Override
	public void doDelete() {
		invoke(methodMap_DELETE);
	}

	@Override
	public void doOptions() {
		invoke(methodMap_OPTIONS);
	}
	
	@Override
	public void doTrace() {
		// not support yet
	}

}
