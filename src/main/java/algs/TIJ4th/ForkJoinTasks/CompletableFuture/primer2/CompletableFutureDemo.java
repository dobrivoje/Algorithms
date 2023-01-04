package algs.TIJ4th.ForkJoinTasks.CompletableFuture.primer2;

import java.util.concurrent.*;

public class CompletableFutureDemo {

	static ExecutorService executorService = Executors.newCachedThreadPool();

	public Future<String> calculateAsync() throws InterruptedException {
		CompletableFuture<String> completableFuture = new CompletableFuture<>();
		executorService.submit( () -> {
			/*try {
				TimeUnit.MILLISECONDS.sleep( 500 );
			} catch (InterruptedException e) {
			}*/
			completableFuture.complete( "rezultat...kraj" );
			// return null;
		} );

		return completableFuture;
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		CompletableFutureDemo demo = new CompletableFutureDemo();
		Future<String> completableFuture = demo.calculateAsync();

		long t01 = System.currentTimeMillis();
		String result = completableFuture.get();
		System.err.println( "result : " + result );

		long t02 = System.currentTimeMillis();
		System.out.println( "1. slučaj za \"Future\" time required : " + (t02 - t01) );

		executorService.shutdown();

		CompletableFuture<String> cf = CompletableFuture.supplyAsync(
				() -> "rezultat \"CompletableFuture-a : \"" );

		long t11 = System.currentTimeMillis();
		String result2 = completableFuture.get();
		System.err.println( "result2 : " + result2 );

		long t12 = System.currentTimeMillis();
		System.out.println( "1. slučaj za \"completableFuture\" time required : " + (t12 - t11) );
	}
}
