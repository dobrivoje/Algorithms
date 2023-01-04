package digital_ocean.nevezano_vezbe;

import lombok.val;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class JLKJK {

	public enum Operator implements BiFunction<Integer, Integer, Integer> {
		ADD {
			@Override
			public Integer apply(Integer a, Integer b) {
				return a + b;
			}
		},
		MULTIPLY {
			@Override
			public Integer apply(Integer a, Integer b) {
				return a * b;
			}
		},
		SUBTRACT {
			@Override
			public Integer apply(Integer a, Integer b) {
				return a - b;
			}
		},
		DIVIDE {
			@Override
			public Integer apply(Integer a, Integer b) {
				return a / b;
			}
		};
	}

	public static void main(String[] args) {
		String name1 = new ArrayList<Integer>().getClass().getName();
		String name2 = new ArrayList<String>().getClass().getName();
		System.err.println( name1 + ", " + name2 );

		int x = Integer.MAX_VALUE + 12;
		System.err.println( x );

		LocalDateTime l2 = LocalDateTime.now().minusDays( 2 ).plusHours( 12 );
		long between = ChronoUnit.DAYS.between( l2, LocalDateTime.now() );
		System.err.println( "between = " + between );

		Function<Integer, String> is = in -> "".concat( in == null ? "0" : in.toString() );
		Function<String, String> ss = in -> "".concat( in ).concat( "111.5465467" );
		Function<String, Double> sd = Double::parseDouble;
		Double res = is.andThen( ss ).andThen( sd ).apply( 8 );
		System.err.format( "res = %.4f", res );
		System.err.println();
		System.err.println();

		abstract class Comm implements Supplier<Integer> {

			protected final Integer[] elements;

			public Comm(Integer... elements) {
				this.elements = elements;
			}

			abstract Integer execute();
		}

		class AddComm extends Comm {

			public AddComm(Integer... elements) {
				super( elements );
			}

			@Override
			Integer execute() {
				return get();
			}

			@Override
			public Integer get() {
				return elements == null ? null : Arrays.stream( elements ).reduce( 0, Integer::sum );
			}
		}

		class MultiComm extends Comm {

			public MultiComm(Integer... elements) {
				super( elements );
			}

			@Override
			Integer execute() {
				return get();
			}

			@Override
			public Integer get() {
				return elements == null ? null : Arrays.stream( elements ).reduce( 1, (a, b) -> a * b );
			}
		}

		Comm addComm1 = new AddComm( 2, 3, 5 );
		System.err.println( "addComm1 =" + addComm1.get() );

		Comm multiComm = new MultiComm( 1, 3, 5 );
		System.err.println( "multiComm =" + multiComm.get() );

		val add = Operator.ADD.apply( 11, 2 );
		System.err.println( "add : " + add );
		Integer reducedEnumAddRes = Stream.of( 3, 4, 7 ).reduce( 0, Operator.ADD::apply );
		System.err.println( "reducedEnumRes : " + reducedEnumAddRes );
		Integer reducedEnumMultiRes = Stream.of( 3, 4, 7 ).reduce( 1, Operator.MULTIPLY::apply );
		System.err.println( "reducedEnumMultiRes : " + reducedEnumMultiRes );
	}
}
