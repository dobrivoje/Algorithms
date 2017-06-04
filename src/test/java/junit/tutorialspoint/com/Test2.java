package junit.tutorialspoint.com;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author root
 */
public class Test2 {
    private String msg = "xxx";

    @Test
    public void testtirajPoruku() {
        Assert.assertEquals("xxx", msg);
    }
}
