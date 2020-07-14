package core.service.http;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
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

import core.annotation.RequestBody;
import core.exception.EmptyPathParameterException;
import core.exception.ReduplicativeMathodPathException;
import core.exception.SingleRequestBodyRequiredException;
import core.http.RestHelper;
import core.ioc.ReflectionUtils;
import core.servlet.GuiceServletCustomContextListener;
import core.servlet.ServletHelper;
import lombok.extern.slf4j.Slf4j;

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
	private Map<Method, Class<?>> requestBodyMap = new ConcurrentHashMap<>();

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
					// NO ELSE to ensure that we can add multi-annotation on the same method
					if (m.isAnnotationPresent(GET.class)) {
						try {
							setMethodAndClassMap(clazz, m, methodMap_GET);
						} catch (ReduplicativeMathodPathException | EmptyPathParameterException
								| SingleRequestBodyRequiredException e) {
							log.error(e.getLocalizedMessage());
						}
					}

					if (m.isAnnotationPresent(PUT.class)) {
						try {
							setMethodAndClassMap(clazz, m, methodMap_PUT);
						} catch (ReduplicativeMathodPathException | EmptyPathParameterException
								| SingleRequestBodyRequiredException e) {
							log.error(e.getLocalizedMessage());
						}
					}

					if (m.isAnnotationPresent(POST.class)) {
						try {
							setMethodAndClassMap(clazz, m, methodMap_POST);
						} catch (ReduplicativeMathodPathException | EmptyPathParameterException
								| SingleRequestBodyRequiredException e) {
							log.error(e.getLocalizedMessage());
						}
					}

					if (m.isAnnotationPresent(DELETE.class)) {
						try {
							setMethodAndClassMap(clazz, m, methodMap_DELETE);
						} catch (ReduplicativeMathodPathException | EmptyPathParameterException
								| SingleRequestBodyRequiredException e) {
							log.error(e.getLocalizedMessage());
						}
					}

					if (m.isAnnotationPresent(OPTIONS.class)) {
						try {
							setMethodAndClassMap(clazz, m, methodMap_OPTIONS);
						} catch (ReduplicativeMathodPathException | EmptyPathParameterException
								| SingleRequestBodyRequiredException e) {
							log.error(e.getLocalizedMessage());
						}
					}

					if (m.isAnnotationPresent(HEAD.class)) {
						try {
							setMethodAndClassMap(clazz, m, methodMap_HEAD);
						} catch (ReduplicativeMathodPathException | EmptyPathParameterException
								| SingleRequestBodyRequiredException e) {
							log.error(e.getLocalizedMessage());
						}
					}
				});
			});
		}
	}

	/**
	 * @param clazz
	 * @param method
	 * @param methodMap
	 * @throws ReduplicativeMathodPathException
	 * @throws SingleRequestBodyRequiredException
	 * @throws EmptyPathParameterException
	 */
	private void setMethodAndClassMap(Class<?> clazz, Method method, Map<String, Method> methodMap)
			throws ReduplicativeMathodPathException, EmptyPathParameterException, SingleRequestBodyRequiredException {
		if (method.isAnnotationPresent(Path.class)) {
			Path path = method.getAnnotation(Path.class);
			if (!Strings.isNullOrEmpty(path.value())) {
				if (Objects.nonNull(methodMap.get(path.value()))) {
					throw new ReduplicativeMathodPathException("Request Path is already exist with : " + path.value());
				}
				methodMap.put(path.value(), method);
				classMap.put(path.value(), clazz);
				addMethodParameter(method);
			}
		}
	}

	/**
	 * @param method
	 * @throws EmptyPathParameterException
	 * @throws SingleRequestBodyRequiredException
	 */
	private void addMethodParameter(final Method method)
			throws EmptyPathParameterException, SingleRequestBodyRequiredException {
		if (method.getParameterCount() <= 0)
			return;
		List<String> list = new LinkedList<>();

		for (Parameter p : method.getParameters()) {
			if (p.isAnnotationPresent(PathParam.class)) {
				PathParam pathParam = (PathParam) p.getAnnotation(PathParam.class);
				if (Strings.isNullOrEmpty(pathParam.value())) {
					throw new EmptyPathParameterException();
				} else {
					list.add(pathParam.value());
				}
			} else

			if (p.isAnnotationPresent(RequestBody.class)) {
				if (Objects.nonNull(requestBodyMap.get(method))) {
					throw new SingleRequestBodyRequiredException(method.getName() + " required single parameter!!");
				}
				requestBodyMap.put(method, p.getType());
			}
		}
		parameterMap.put(method, list);
	}

	/**
	 * @param methodMap
	 * @throws Exception
	 */
	private void invoke(final Map<String, Method> methodMap) throws Exception {
		List<String> args = new ArrayList<>();
		var req = ServletHelper.getRequest();
		var path = StringUtils.remove(req.getRequestURI(), req.getContextPath());
		var method = methodMap.get(path);
		var clazz = classMap.get(path);
		List<String> parameters = parameterMap.get(method);

		Class<?> requestBodyClass = requestBodyMap.get(method);

		if (Objects.nonNull(parameters) && !parameters.isEmpty()) {

			parameters.forEach(p -> {
				args.add(ServletHelper.getRequest().getParameter(p));
			});
			invoke(clazz, method, args.toArray());
			return;
		}
		if (Objects.nonNull(requestBodyClass)) {
			invoke(clazz, method, RestHelper.getRequestPostBody(requestBodyClass));
			return;

		}
		invoke(clazz, method);
	}

	/**
	 * @param clazz
	 * @param method
	 * @throws Exception
	 */
	private void invoke(Class<?> clazz, Method method, Object... args) throws Exception {
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
				RestHelper.sendResponseData(ReflectionUtils.invokeMethod(obj, method, args));
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
