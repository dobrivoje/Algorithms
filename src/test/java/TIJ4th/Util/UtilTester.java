package TIJ4th.Util;

import algs.Util.Utils;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import algs.examples.recursion.FibonacciCalculator;

/**
 *
 * @author root
 */
public class UtilTester {

    private final Map<Integer, String> m = new HashMap<>();

    @Mock
    private Utils.MyMap<Integer, String> M;

    @Mock
    private FibonacciCalculator FC;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        m.put(1, "String 1");
        m.put(2, "String 2");
        m.put(3, "Dobri");
        m.put(4, "Nina");
    }

    @Test
    public void test_Univerzalna_Mapa() {
        when(M.getMap()).thenReturn(new HashMap<>(m));

        System.err.println("---------------------------");
        System.err.println(M.getMap().values());
        System.err.println(M.getMap().keySet());
        System.err.println("---------------------------");

        assertEquals(m, M.getMap());
    }

    @Test
    public void test_Fibbonaci() {
        when(FC.fibonacci2(6)).thenReturn(13L);

        long f = FC.fibonacci2(6);
        assertEquals(13, f);
    }
}
