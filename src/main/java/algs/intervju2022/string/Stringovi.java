package algs.intervju2022.string;

import lombok.Getter;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Stringovi {

	public static void main(String[] args) {
		int[][] twoD = {
				{ 100, 200, 300, 400, 500 },
				{ 300, 600, 900, 700, 800 } };

		System.out.println( Arrays.deepToString( twoD ) );

		int[][][] threeD = {
				{
						{ 100, 200, 300, 400, 500 },
						{ 300, 600, 900, 700, 800 } },

				{
						{ 222, 333, 444 },
						{ 555, 666, 777, 888 } }
		};

		System.err.println( "threeD : " + threeD );
		System.err.println( "---------------------------------------------" );
		System.err.println( "threeD : " + Arrays.deepToString( threeD ) );
		System.err.println( "+++++++++++++++++++++" );

		for (int[][] level_1 : threeD) {
			System.err.println( "level - 1" );

			for (int[] level2 : level_1) {
				System.err.println( "level-2" );

				for (int elem : level2) {
					System.err.println( elem );
				}
			}
		}

		System.err.println( "------------------------" );

		Comparator<Integer> ci = (o1, o2) -> o1 - o2;
		Comparator<Integer> ci2 = Comparator.comparingInt( o -> o );
		System.err.println( ci.compare( 1, 3 ) );

		System.err.println( "------------------------" );

		Comparator<BigInteger> cbi = (o1, o2) -> o1.compareTo( o2 );
		System.err.println( cbi.compare( BigInteger.TEN, BigInteger.ONE ) );

		@Getter
		class AI implements Comparable<AI> {

			private final Integer val;
			private final String  user;

			AI(Integer val, String user) {
				this.val = val;
				this.user = user;
			}

			@Override
			public int compareTo(AI other) {
				return this.val == null ? other == null || other.val == null ? 0 : -1
						: this.val.compareTo( other.val );
			}

			@Override
			public String toString() {
				String valRes = this.val == null ? "n/a" : String.valueOf( val );
				String userRes = this.user == null ? "n/a" : user;
				return val + " : " + userRes;
			}
		}

		AI ai1 = new AI( 11, "user-1" ), ai2 = new AI( 11, "user-2" ), ai3 = new AI( 11, "user-0" );
		System.err.println( ai1.compareTo( ai2 ) );

		Collection<AI> aiElems = Arrays.asList( ai1, ai2, ai3 );
		Comparator<AI> comparator1 = Comparator.naturalOrder();
		Comparator<AI> comparator2 = Comparator.comparing( AI::getVal ).thenComparing( AI::getUser )
											   .reversed();

		List<AI> res = aiElems.stream().sorted( comparator2 ).collect( Collectors.toList() );
		System.err.println( res );
	}
}
