package algs.TIJ4th.str444;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 *
 * @author root
 */
public class DynamicProxyHandler implements InvocationHandler {

    private final Object proxied;

    public DynamicProxyHandler(Object proxied) {
        this.proxied = proxied;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("proxy : " + proxy.getClass().getSimpleName()
                + ", method: " + method.getName() + " , args : " + Arrays.toString(args)
        );

        if (args != null) {
            for (Object a : args) {
                System.out.print("args : " + Arrays.toString(args));
            }
        }

        return method.invoke(proxied, args);
    }

}
