package cache.base.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.google.inject.BindingAnnotation;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@BindingAnnotation
public @interface Cache {

	String key();
}
