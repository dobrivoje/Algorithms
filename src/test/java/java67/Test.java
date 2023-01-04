package java67;

import algs.intervju2022.java67.ReverseArray;
import org.junit.Assert;
import org.junit.Before;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.util.Comparator;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@State(Scope.Benchmark)
public class Test {

	ReverseArray revArray;

	@Before
	public void setup() {
	}

	@org.junit.Test
	public void testSymetric() {
		char[] chars = "123456".toCharArray();
		ReverseArray.reverseArray( chars );

		char[] expected = new char[] { '6', '5', '4', '3', '2', '1' };
		Assert.assertArrayEquals( expected, chars );
	}

	@org.junit.Test
	public void testOdd() {
		char[] chars = "123456789".toCharArray();
		ReverseArray.reverseArray( chars );

		char[] expected = new char[] { '9', '8', '7', '6', '5', '4', '3', '2', '1' };
		Assert.assertArrayEquals( expected, chars );
	}

	@org.junit.Test
	//	@Benchmark
	//	@BenchmarkMode( Mode.AverageTime )
	//	@OutputTimeUnit(TimeUnit.NANOSECONDS)
	public void testOddNumbers() {
		int noOfElements = 100_000_000;
		Integer[] ints = IntStream.range( 1, noOfElements )/*.parallel()*/.boxed().toArray( Integer[]::new );

		//		ReverseArray.reverseArray( ints );

		Integer[] expected = IntStream.range( 1, noOfElements ).boxed()
									  .sorted( Comparator.comparingInt( v -> v )/*.reversed()*/ )
//									  .parallel()
									  .toArray( Integer[]::new );

		Assert.assertArrayEquals( expected, ints );
		System.err.println();
	}

}
