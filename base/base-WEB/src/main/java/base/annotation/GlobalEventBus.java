package base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.google.inject.BindingAnnotation;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@BindingAnnotation
public @interface GlobalEventBus {

}
