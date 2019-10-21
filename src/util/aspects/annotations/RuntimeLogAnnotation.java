package util.aspects.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *  TYPE ->  Class, interface (including annotation type), or enum declaration
 *  METHOD ->  Method declaration
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RuntimeLogAnnotation {

    String value() default "";

    String description() default "";

    boolean ignore() default false;
}