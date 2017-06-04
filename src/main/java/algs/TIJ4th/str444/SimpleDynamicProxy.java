package algs.TIJ4th.str444;

import java.lang.reflect.Proxy;

/**
 *
 * @author root
 */
public class SimpleDynamicProxy {

    public static void cunsumer(Interface iface) {
        iface.doSomething();
        iface.domethingElse("Banooboo");
    }

    public static void main(String[] args) {
        RealObject real = new RealObject();
        cunsumer(real);

        Interface proxy = (Interface) Proxy.newProxyInstance(
                Interface.class.getClassLoader(),
                new Class[]{Interface.class}, new DynamicProxyHandler(real)
        );
        
        SimpleDynamicProxy.cunsumer(proxy);
    }
}
