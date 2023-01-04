package Java67.konkurentno;

import algs.Util.Utils;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ParallelStreams {

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		int CPUs = 16;
		List<Integer> primes = Collections.synchronizedList( new LinkedList<>() );

		/*Utils.executionTime( () -> {
			int elements = 1000_000 / CPUs;
			ForkJoinPool threadPool = new ForkJoinPool( CPUs );

			for (int i = 0; i < CPUs; i++) {
				int from = i * elements;
				int to = (1 + i) * elements;

				ForkJoinTask<Long> task = threadPool.submit( () -> primesOf( from, to, primes ) );
				try {
					Long howManyPrimes = task.get();
					System.err.println( "ukupno [" + from + "," + to + "] :" + howManyPrimes );
				} catch (InterruptedException | ExecutionException e) {
					throw new RuntimeException( e );
				}
			}

			threadPool.shutdown();
		}, "blokovi po 100.000 elemenata." );*/

		Utils.executionTime( () -> {
			ForkJoinPool customThreadPool = new ForkJoinPool( CPUs );
			try {
				ForkJoinTask<Long> task =
						customThreadPool.submit( () -> primesOf( 0, 1_000_000, primes ) );
				Long count = task.get();
				System.out.println( "\nTotal: " + count );
			} catch (InterruptedException | ExecutionException e) {
				throw new RuntimeException( e );
			}

			customThreadPool.shutdown();
		}, "allPrimes" );

		System.err.println( primes.stream().limit( 1000 ).sorted( Comparator.comparing( e -> e ) ).collect( Collectors.toList() ) );
	}

	private static Long getHowManyPrimes(int forHowMany) {
		long count = Stream.iterate( 0, n -> n + 1 )
						   .limit( forHowMany )
						   .parallel()
						   .peek( elemInd -> {
							   if (elemInd % 100_000 == 0) {
								   System.err.println( elemInd );
							   }
						   } )
						   .filter( ParallelStreams::isPrime )
						   // .peek( x -> System.out.format( "%s\t", x ) )
						   .count();
		return count;
	}

	private static Long primesOf(int from, int to, Collection<Integer> primes) {
		long count = Stream.iterate( from, n -> n + 1 )
						   .limit( to )
						   .parallel()
						   .peek( elemInd -> {
							   if (elemInd % 100_000 == 0) {
								   System.err.println( elemInd );
							   }
						   } )
						   .filter( ParallelStreams::isPrime )
						   .peek( primes::add )
						   .count();
		return count;
	}

	static boolean isPrime(int number) {
		if (number <= 1) {
			return false;
		}

		return IntStream.rangeClosed( 2, number / 2 ).noneMatch( i -> number % i == 0 );
	}
}
