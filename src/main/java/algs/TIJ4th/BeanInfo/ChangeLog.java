package algs.TIJ4th.BeanInfo;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ChangeLog {

    boolean propagateToSubClass() default false;

    boolean includeAllFields() default true;

    boolean skip() default false;

    @Target({ElementType.FIELD, ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @interface Exclude {
    }

    @Target({ElementType.FIELD, ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @interface Include {

        String name() default "";
    }
}
