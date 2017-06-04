package junit.tutorialspoint.com;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author root
 */
public class Test1 {

    private String msg = "dobri";

    @Test
    public void testtirajPoruku() {
        Assert.assertEquals("dobri", msg);
    }
}
