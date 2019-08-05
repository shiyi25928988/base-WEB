package base.IOC;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import base.annotation.Controller;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yshi
 *
 */
@Slf4j
public class ClassHelper {
	
	/**
	 * @param scanPackageNames
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public static Set<Class<?>> getControllers(String...scanPackageNames) throws ClassNotFoundException, IOException {
		Set<Class<?>> set = new HashSet<>();
		Arrays.asList(scanPackageNames).stream().forEach(name->{
			try {
				set.addAll(getControllers(name));
			} catch (ClassNotFoundException | IOException e) {
				log.error(e.getMessage());
			}
		});
		return set;
	}

	/**
	 * @param scanPackageName
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public static Set<Class<?>> getControllers(String scanPackageName) throws ClassNotFoundException, IOException {
		Set<Class<?>> set = getAnnotationClass(ClassHelper.getClassSet(scanPackageName), Controller.class);
		set.forEach(clazz -> log.info(clazz.getCanonicalName()));
		return set;
	}

	/**
	 * @param packageName
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public static Set<Class<?>> getClassSet(String packageName) throws ClassNotFoundException, IOException {
		return Collections.unmodifiableSet(ClassUtils.getClassSet(packageName));
	}

	/**
	 * @param classSet
	 * @param annotationClass
	 * @return
	 */
	public static Set<Class<?>> getAnnotationClass(Set<Class<?>> classSet,
			Class<? extends Annotation> annotationClass) {
		Set<Class<?>> cs = new HashSet<>();
		classSet.stream()
		.filter(clazz -> clazz.isAnnotationPresent(annotationClass))
		.forEach(c -> cs.add(c));
		return cs;
	}
	
}
