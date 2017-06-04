package Other;

import java.util.Date;
import org.junit.After;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import algs.TIJ4th.collections.Person;

/**
 *
 * @author root
 */
public class test1 {

    private Person dobri;
    private Person dobri1;
    private Person krme;

    @Before
    public void setup() {
        dobri = new Person("Dobrivoje", 41, new Date(1975, 9, 7));
        dobri1 = new Person("Dobrivoje", 41, new Date(1975, 9, 7));
        krme = new Person("Krme", 37, new Date(1979, 4, 15));
        System.err.println("setup");
    }

    @Test(timeout = 1000)
    public void testJednakosti() {
        assertTrue(dobri.equals(dobri1));
        System.err.println("assertTrue(dobri.equals(dobri1))");
        assertTrue(dobri.hashCode() == dobri1.hashCode());
        System.err.println("assertTrue(dobri.hashCode() == dobri1.hashCode())");
    }

    @Test(timeout = 1000)
    public void testNejednakosti() {
        assertFalse(dobri.hashCode() == krme.hashCode());
        System.err.println("testNejednakosti()");
    }

    @After
    public void teardown() {
        dobri = null;
        dobri1 = null;
        krme = null;
        System.err.println("teardown");
    }
}
