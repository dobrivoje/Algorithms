package algs.TIJ4th.str785;

import algs.TIJ4th.str785.annotations.UseCase;
import java.lang.reflect.Method;

/**
 *
 * @author root
 */
public class UseCaseTracker {

    public static void TrackUseCases(Class<?> clazz) {
        for (Method m : clazz.getDeclaredMethods()) {
            UseCase uc = m.getAnnotation(UseCase.class);
            if (uc != null) {
                System.out.println("Found use case id: " + uc.id()
                        + (uc.description() != null || !uc.description().isEmpty() ? ", with description :" + uc.description() : "")
                );
            } else {
                System.err.println("Method without annotation : " + m.getName());
            }
        }
    }

    public static void main(String[] args) {
        TrackUseCases(PassUtil.class);
    }

}
