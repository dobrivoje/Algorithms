package junit.tutorialspoint.com;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author root
 */
public class Test3 {

    private String str1;
    private String str2;
    private String str3;
    private String str4;
    private String str5;

    private MessageUtil messageUtil;

    private String[] expectedArray = {"one", "two", "three"};
    private String[] resultArray = {"one", "two", "three"};

    @Before
    public void setup() {
        str1 = new String("abc");
        str2 = new String("abc");
        str3 = null;
        str4 = "abc";
        str5 = "abc";

        System.err.println("@Before !");
    }

    @BeforeClass
    public static void beforeClass() {
        System.out.println("in before class");
    }

    @Test
    public void testtirajPoruku() {
        Assert.assertSame(str4, str5);
    }

    @Test
    public void testtirajStringove() {
//        String s1 = str4 += ".";
//        String s2 = str5 += ".";

        String s1 = str4;
        String s2 = str5;

        System.err.println(s1);
        System.err.println(s2);

        System.err.println(s1.hashCode());
        System.err.println(s2.hashCode());

        System.err.println(s1.equals(s2));

        Assert.assertSame(s1, s2);
    }

    @Test(timeout = 1000)
    public void testtirajVreme() {
        String s1 = str4;
        String s2 = str5;

        Assert.assertSame(s1, s2);
    }

    @Test
    public void testtirajNizove() {
        Assert.assertArrayEquals(expectedArray, resultArray);
    }

    @Test(expected = NullPointerException.class)
    public void testirajPrintMessage() {
        messageUtil.printMessage();
    }

}
