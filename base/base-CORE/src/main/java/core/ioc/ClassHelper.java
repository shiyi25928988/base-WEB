package core.ioc;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import core.annotation.Controller;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yshi
 *
 */
@Slf4j
public final class ClassHelper {
	
	/**
	 * 
	 */
	private ClassHelper() {}
	
	/**
	 * @param scanPackageNames
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public static Set<Class<?>> getControllers(String...scanPackageNames) throws ClassNotFoundException, IOException {
		Set<Class<?>> classSet = new HashSet<>();
		Arrays.asList(scanPackageNames).stream().forEach(name->{
			try {
				classSet.addAll(getControllers(name));
			} catch (ClassNotFoundException | IOException e) {
				log.error(e.getMessage());
			}
		});
		return classSet;
	}

	/**
	 * @param scanPackageName
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public static Set<Class<?>> getControllers(String scanPackageName) throws ClassNotFoundException, IOException {
		Set<Class<?>> classSet = getAnnotationClass(ClassHelper.getClassSet(scanPackageName), Controller.class);
		classSet.forEach(clazz -> log.info(clazz.getCanonicalName()));
		return classSet;
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
		Set<Class<?>> set = new HashSet<>();
		classSet.stream()
		.filter(clazz -> clazz.isAnnotationPresent(annotationClass))
		.forEach(c -> set.add(c));
		return set;
	}
	
}
