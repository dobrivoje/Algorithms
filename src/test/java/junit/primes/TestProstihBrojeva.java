package junit.primes;

import java.util.Iterator;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import algs.junit.primes.core.IPrimeGen;
import algs.junit.primes.service.IMathService;
import algs.junit.primes.service.MathServiceImpl;

/**
 *
 * @author root
 */
//@RunWith(Parameterized.class)
public class TestProstihBrojeva {

    private IMathService mathService;

    @Mock
    private IPrimeGen iPrimeGen;

    @Mock
    private Iterator iteratorTest;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        mathService = new MathServiceImpl(iPrimeGen);
    }

    //<editor-fold defaultstate="collapsed" desc="parametarski test">
    /*
    @Parameterized.Parameters
    public static Collection returnPrimes() {
    return Arrays.asList(
    new Object[][]{
    {2, true},
    {3, true},
    {5, true},
    {7, true},
    {11, true},
    {13, true},
    {17, true},
    {19, true},
    {23, true},
    {24, false},
    {1000, false},
    {1000000000, false}
    }
    );
    }
     */
    //</editor-fold>

    @Test
    public void test_Broj_Jeste_Prost() {
        when(iPrimeGen.isPrime(11L)).thenReturn(false);

        assertTrue(mathService.isPrime(11L));
    }

    @Test
    public void iterator_test() {
        when(iteratorTest.next()).thenReturn("hello").thenReturn("world");
        String res = iteratorTest.next().toString() + iteratorTest.next().toString();

        assertEquals("helloworld", res);
    }

}
