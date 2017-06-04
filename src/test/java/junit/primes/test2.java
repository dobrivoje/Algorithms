package junit.primes;

import java.util.LinkedList;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author root
 */
public class test2 {

    @Mock
    LinkedList mockedList;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test1() {
        when(mockedList.get(0)).thenReturn("first");
        when(mockedList.get(1)).thenReturn(2);
        when(mockedList.get(2)).thenThrow(new RuntimeException());

        System.err.println("------------------------");
        System.err.println(mockedList.get(0));
        System.err.println(mockedList.get(1));
        System.err.println("------------------------");

        verify(mockedList).get(0);
        verify(mockedList).get(1);
    }

}
