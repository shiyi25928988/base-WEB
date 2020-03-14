package lego.ioc;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Strings;
import com.google.inject.Injector;

import lego.rest.utils.RestHelper;
import lego.servlet.GuiceServletCustomContextListener;
import lego.servlet.ServletHelper;
import lombok.extern.slf4j.Slf4j;
import mq.base.utils.JsonUtils;

/**
 * @author yshi
 *
 */
@Slf4j
public class RestApiServiceImpl implements RestApiService {

	private Set<Class<?>> classSet;

	private Map<String, Class<?>> classMap = new ConcurrentHashMap<>();

	private Map<String, Method> methodMap_GET = new ConcurrentHashMap<>();
	private Map<String, Method> methodMap_PUT = new ConcurrentHashMap<>();
	private Map<String, Method> methodMap_POST = new ConcurrentHashMap<>();
	private Map<String, Method> methodMap_DELETE = new ConcurrentHashMap<>();
	private Map<String, Method> methodMap_OPTIONS = new ConcurrentHashMap<>();
	private Map<String, Method> methodMap_HEAD = new ConcurrentHashMap<>();

	private Map<Method, List<String>> parameterMap = new ConcurrentHashMap<>();

	/**
	 * Constructor
	 * 
	 * @param classSet
	 */
	public RestApiServiceImpl(final Set<Class<?>> classSet) {
		this.classSet = classSet;
		if (Objects.nonNull(this.classSet) && this.classSet.size() >= 1) {
			classSet.stream().forEach(clazz -> {
				Method[] methods = clazz.getDeclaredMethods();

				Stream.of(methods).forEach(m -> {

					if (m.isAnnotationPresent(GET.class)) {
						if (m.isAnnotationPresent(Path.class)) {
							Path path = m.getAnnotation(Path.class);
							if (!Strings.isNullOrEmpty(path.value())) {
								methodMap_GET.put(path.value(), m);
								classMap.put(path.value(), clazz);
								addMethodParameter(m);
							}
						}
					}
					if (m.isAnnotationPresent(PUT.class)) {
						if (m.isAnnotationPresent(Path.class)) {
							Path path = m.getAnnotation(Path.class);
							if (!Strings.isNullOrEmpty(path.value())) {
								methodMap_PUT.put(path.value(), m);
								classMap.put(path.value(), clazz);
								addMethodParameter(m);
							}
						}
					}
					if (m.isAnnotationPresent(POST.class)) {
						if (m.isAnnotationPresent(Path.class)) {
							Path path = m.getAnnotation(Path.class);
							if (!Strings.isNullOrEmpty(path.value())) {
								methodMap_POST.put(path.value(), m);
								classMap.put(path.value(), clazz);
								addMethodParameter(m);
							}
						}
					}
					if (m.isAnnotationPresent(DELETE.class)) {
						if (m.isAnnotationPresent(Path.class)) {
							Path path = m.getAnnotation(Path.class);
							if (!Strings.isNullOrEmpty(path.value())) {
								methodMap_DELETE.put(path.value(), m);
								classMap.put(path.value(), clazz);
								addMethodParameter(m);
							}
						}
					}
					if (m.isAnnotationPresent(OPTIONS.class)) {
						if (m.isAnnotationPresent(Path.class)) {
							Path path = m.getAnnotation(Path.class);
							if (!Strings.isNullOrEmpty(path.value())) {
								methodMap_OPTIONS.put(path.value(), m);
								classMap.put(path.value(), clazz);
								addMethodParameter(m);
							}
						}
					}
					if (m.isAnnotationPresent(HEAD.class)) {
						if (m.isAnnotationPresent(Path.class)) {
							Path path = m.getAnnotation(Path.class);
							if (!Strings.isNullOrEmpty(path.value())) {
								methodMap_HEAD.put(path.value(), m);
								classMap.put(path.value(), clazz);
								addMethodParameter(m);
							}
						}
					}
				});
			});
		}
	}

	/**
	 * @param method
	 */
	private void addMethodParameter(final Method method) {
		if (method.getParameterCount() <= 0)
			return;
		List<String> list = new LinkedList<>();

		Stream.of(method.getParameters()).forEach(p -> {
			if (p.isAnnotationPresent(PathParam.class)) {
				PathParam pathParam = (PathParam) p.getAnnotation(PathParam.class);
				if (Strings.isNullOrEmpty(pathParam.value())) {
					// throw new InvalidPathParameterException();
				} else {
					list.add(pathParam.value());
				}
			}
		});
		parameterMap.put(method, list);
	}

	/**
	 * @param methodMap
	 * @throws Exception
	 */
	private void invoke(final Map<String, Method> methodMap) throws Exception {
		var req = ServletHelper.getRequest();
		var path = StringUtils.remove(req.getRequestURI(), req.getContextPath());
		var method = methodMap.get(path);
		var clazz = classMap.get(path);
		List<String> parameters = parameterMap.get(method);
		if (Objects.nonNull(parameters) && !parameters.isEmpty()) {
			List<String> args = new ArrayList<>();
			parameters.forEach(p -> {
				args.add(ServletHelper.getRequest().getParameter(p));
			});
			invoke(clazz, method, args.toArray());
		} else {
			invoke(clazz, method);
		}
	}

	/**
	 * @param clazz
	 * @param method
	 * @throws Exception
	 */
	private void invoke(Class<?> clazz, Method method, Object...args) throws Exception {
		if (Objects.nonNull(clazz)) {
			if (Objects.nonNull(method)) {
				Object obj = ReflectionUtils.newInstance(clazz);
				Field[] fields = clazz.getDeclaredFields();
				Injector injector = GuiceServletCustomContextListener.getInjectorInstance();
				Stream.of(fields).forEach(field -> {
					if (field.isAnnotationPresent(com.google.inject.Inject.class)
							|| field.isAnnotationPresent(javax.inject.Inject.class)) {
						try {
							ReflectionUtils.setField(obj, field, injector.getInstance(field.getType()));
						} catch (Exception e) {
							e.printStackTrace();
							log.error(e.getMessage());
						}
					}
				});
				RestHelper.sendResponseData(ReflectionUtils.invokeMethod(obj, method, args),
						ServletHelper.getResponse());
			}
		}
	}

	@Override
	public void doGet() {
		try {
			invoke(methodMap_GET);
		} catch (Exception e) {
			log.error(e.toString());
		}
	}

	@Override
	public void doPut() {
		try {
			invoke(methodMap_PUT);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	@Override
	public void doPost() {
		try {
			invoke(methodMap_POST);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	@Override
	public void doHead() {
		try {
			invoke(methodMap_HEAD);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	@Override
	public void doDelete() {
		try {
			invoke(methodMap_DELETE);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	@Override
	public void doOptions() {
		try {
			invoke(methodMap_OPTIONS);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	@Override
	public void doTrace() {
		log.error("Not support TRACE http method!!");
	}

}
