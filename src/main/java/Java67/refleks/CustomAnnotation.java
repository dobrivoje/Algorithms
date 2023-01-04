package Java67.refleks;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@interface CustomAnnotation {

	String name();

	String value();
}
