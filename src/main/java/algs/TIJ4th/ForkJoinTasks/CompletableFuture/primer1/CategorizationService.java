package algs.TIJ4th.ForkJoinTasks.CompletableFuture.primer1;

import algs.Util.Utils;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CategorizationService {

	public static Category categorizeTransaction(Transaction transaction) {
		simulateWorkWhichTakesTime( 1 );

		String category = "Category-" + transaction.getId();
		System.err.println( category );

		return new Category( category );
	}

	public static void simulateWorkWhichTakesTime(int seconds) {
		try {
			TimeUnit.SECONDS.sleep( seconds );
		} catch (InterruptedException e) {
			throw new RuntimeException( e );
		}
	}

	public static void main(String[] args) {
		List<Transaction> transactions = IntStream.rangeClosed( 1, 10_000 ).boxed().map( Transaction::new )
												  .collect( Collectors.toList() );

		/*
		Utils.executionTime( () -> {
			List<Category> categories =
					transactions.stream().map( CategorizationService::categorizeTransaction )
								.collect( Collectors.toList() );
		}, "Linear execution." );
		*/

		/*
		Utils.executionTime( () -> {
			List<Category> categories = transactions.stream().parallel()
													.map( CategorizationService::categorizeTransaction )
													.collect( Collectors.toList() );
		}, "Linear execution." );
		*/

		Utils.executionTime( () -> {
			ExecutorService eService = Executors.newFixedThreadPool( 2_500 /*transactions.size()*/ );

			List<CompletableFuture<Category>> categories =
					transactions.stream().map(
							trans -> CompletableFuture.supplyAsync(
									() -> CategorizationService.categorizeTransaction( trans ), eService )
							// () -> CategorizationService.categorizeTransaction( trans ) )
					).collect( Collectors.toList() );

			List<Category> result = categories.stream().map( CompletableFuture::join )
											  .collect( Collectors.toList() );

			eService.shutdown();
		}, "CompletableFuture execution." );
	}
}
