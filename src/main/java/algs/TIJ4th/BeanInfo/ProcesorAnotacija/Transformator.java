package algs.TIJ4th.BeanInfo.ProcesorAnotacija;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.TYPE, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Transformator {

	boolean skip() default false;

	String process() default "";

	@Target({ ElementType.FIELD, ElementType.METHOD })
	@Retention(RetentionPolicy.RUNTIME)
	@interface Exclude {

	}

	@Target({ ElementType.FIELD, ElementType.METHOD })
	@Retention(RetentionPolicy.RUNTIME)
	@interface Include {

		String name() default "";
	}
}
