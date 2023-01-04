package algs.TIJ4th.ForkJoinTasks.CompletableFuture.primer1;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.*;

public class demo_3_2_2022 {

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		ExecutorService es = Executors.newFixedThreadPool( 4 );
		// Future<String> stringFuture = es.submit( demo_3_2_2022::neverEndingComputation );
		// System.err.println( "Future result : " + stringFuture.get() );

		/* CompletableFuture<String> cf = CompletableFuture.supplyAsync( demo_3_2_2022::neverEndingComputation );
		cf.complete( "ubij ga !" );
		System.err.println( "cf.isCancelled() ? " + cf.isCancelled() );
		System.err.println( "cf.isCompletedExceptionally() ? " + cf.isCompletedExceptionally() );
		System.err.println( "cf.isDone() ? " + cf.isDone() );
		*/

		CompletableFuture<Void> res = CompletableFuture.supplyAsync( () -> firstTask( "početna vrednost" ) )
													   .thenApply( demo_3_2_2022::secondTask )
													   .thenAccept( System.err::println );

		System.err.println( "konačniRezultat : " + res.get() );

		List<Runnable> runnables = es.shutdownNow();
		System.err.println( "runnable(s) : " + String.join( ", ", runnables.toString() ) );
	}

	private static String neverEndingComputation() {
		System.err.println( "-------> neverEndingComputation begin." );
		try {
			TimeUnit.SECONDS.sleep( 2 );
		} catch (InterruptedException e) {
			System.err.println( "greška : " + e );
		}
		System.err.println( "-------< neverEndingComputation end." );

		return "rezultat...";
	}

	private static String firstTask(String input) {
		System.err.println( LocalDateTime.now() + " -------> firstTask begin." );
		try {
			TimeUnit.SECONDS.sleep( 3 );
		} catch (InterruptedException e) {
			System.err.println( "greška : " + e );
		}
		System.err.println( LocalDateTime.now() + " <------- firstTask end." );

		return "firstTask = " + input + ", ";
	}

	private static String secondTask(String input) {
		System.err.println( LocalDateTime.now() + " -------> secondTask begin." );
		try {
			TimeUnit.SECONDS.sleep( 2 );
		} catch (InterruptedException e) {
			System.err.println( "greška : " + e );
		}
		System.err.println( LocalDateTime.now() + " <------- secondTask end." );

		return "secondTask = " + input;
	}
}
