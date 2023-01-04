package Java67.konkurentno;

import algs.TIJ4th.ForkJoinTasks.CompletableFuture.primer2.kast.ImportResultDto;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author д06ри, dobri7@gmail.com
 */
public class Latches {

	private static final int threadCount = 12;

	public static void main(String[] args) throws InterruptedException {
		CountDownLatch startLatch = new CountDownLatch( 1 );
		CountDownLatch endLatch = new CountDownLatch( threadCount );

		for (int i = 0; i < threadCount; i++) {
			new Thread( () -> {
				try {
					startLatch.await();
				} catch (InterruptedException e) {
					System.err.println( "thread : " + Thread.currentThread().getName() + ", error." );
				} finally {
					endLatch.countDown();
				}

				System.err.println( "thread : " + Thread.currentThread().getName() );
				System.err.println( "thread end." );
			} ).start();
		}

		startLatch.countDown();
		endLatch.await();

		System.err.println( "-------------------" );
		System.err.println( "end." );

		LocalDateTime now = LocalDateTime.now().truncatedTo( ChronoUnit.DAYS ).plusHours( 17 ).plusMinutes( 12 );
		System.err.println( "now = " + now );
		LocalDateTime incidentStartingTime = now.plusDays( 2 ).minusHours( 4 );
		System.err.println( "incidentStartingTime = " + incidentStartingTime );

		long daysBetweenIncidentAndNow = 1 + Math.abs( ChronoUnit.DAYS.between( incidentStartingTime.toLocalDate(),
																				now.toLocalDate() ) );

		System.err.println( "daysBetweenIncidentAndNow = " + daysBetweenIncidentAndNow );

		ImportResultDto<String> dto = ImportResultDto.<String>builder()
													 .result( "pocetak" )
													 .build();
		Function<String, String> p1 = s -> s.concat( ", korak1" );
		Function<String, String> p2 = s -> s.concat( ", korak2" );
		Function<String, String> p3 = s -> s.concat( ", korak3" );

		processBeanField( dto::getResult, dto::setResult, p1, p2, p3 );
		System.err.println( dto.getResult() );
	}

	@SafeVarargs
	public static <T, R> void processBeanField(Supplier<T> fieldSupplier, Consumer<R> fieldConsumer,
											   Function<T, R>... processors) {
		if (fieldSupplier == null || fieldConsumer == null || processors == null || processors.length == 0) {
			return;
		}
		Arrays.stream( processors ).map( p -> p.apply( fieldSupplier.get() ) ).forEach( fieldConsumer );
	}

}
