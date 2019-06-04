package base.IOC;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ClassHelper {

	public static Set<Class<?>> getClassSet(String packageName) throws ClassNotFoundException, IOException {
		return Collections.unmodifiableSet(ClassUtils.getClassSet(packageName));
	}

	public static Set<Class<?>> getAnnotationClass(Set<Class<?>> classSet,
			Class<? extends Annotation> annotationClass) {
		
		Set<Class<?>> cs = new HashSet<>();

		classSet.stream()
		.filter(clazz -> clazz.isAnnotationPresent(annotationClass))
		.forEach(c -> cs.add(c));
		return cs;
	}
}
