package Java67.primer2_dobar;

import algs.Util.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Manager extends Thread {

	private final List<RunnableWithLatch> runnableLatchMembers;
	private       CountDownLatch          latch;

	public void setLatch(CountDownLatch latch) {
		this.latch = latch;
	}

	public Manager(List<RunnableWithLatch> runnableLatchMembers) {
		this.runnableLatchMembers = runnableLatchMembers;
		this.latch = new CountDownLatch( runnableLatchMembers.size() );
		runnableLatchMembers.forEach( t -> t.setLatch( latch ) );
	}

	private void awaitForExecution() {
		try {
			latch.await();
		} catch (InterruptedException e) {
			System.err.println( "Error: " + e.getMessage() );
			throw new RuntimeException( e );
		}
	}

	public synchronized void startAllAndAwaitExecution() {
		runnableLatchMembers.stream().map( Thread::new ).forEach( Thread::start );
		awaitForExecution();
	}

	//<editor-fold desc="experimental">
	/*
	private void executeInParallel(List<RunnableWithLatch> elements) {
		int elementsSize = elements.size();
		int inParallel = elementsSize / cpus;
		int remainInOneThread = elementsSize % cpus;

		// napravi blokove izvršavanja u paraleli :
		int startIndex;
		int endIndex;
		for (int parallelChunk = 0; parallelChunk < inParallel; parallelChunk++) {
			startIndex = cpus * parallelChunk;
			endIndex = cpus * (1 + parallelChunk);

			// performAction( startIndex, endIndex, elements );
			performActionInParallel( startIndex, endIndex, elements );
		}
		if (remainInOneThread > 0) {
			startIndex = elementsSize - remainInOneThread;
			endIndex = elementsSize;

			// performAction( startIndex, endIndex, elements );
			performActionInParallel( startIndex, endIndex, elements );
		}
	}

	private static void performAction(int startIndex, int endIndex, List<?> elements) {
		System.err.println( "-------------------------------" );
		System.err.println( startIndex + " : " + endIndex );
		// System.err.println( "-------------------------------" );

		*//*
		for (int i = startIndex; i < endIndex; i++) {
			Object element = elements.get( i );
			System.err.println( element );
		}
		System.err.println();
		*//*
	}

	private void performActionInParallel(int startIndex, int endIndex, List<RunnableWithLatch> elements) {
		System.err.println( "-------------------------------" );
		System.err.println( startIndex + " : " + endIndex );

		for (int i = startIndex; i < endIndex; i++) {
			RunnableWithLatch element = elements.get( i );
			new Thread( element ).start();
		}

		awaitForExecution();
	}
	*/
	//</editor-fold>

	public static void main(String[] args) {
		/*
		Utils.executionTime( () -> {
			MyDevTeam dev = new MyDevTeam( "Dev tim", 3500 );
			MyQATeam qa1 = new MyQATeam( "QA tim 1", 1000 );
			MyQATeam qa2 = new MyQATeam( "QA tim 2", 1200 );
			MyQATeam qa3 = new MyQATeam( "QA tim 3", 1000 );
			List<RunnableWithLatch> teams = Arrays.asList( dev, qa1, qa2, qa3 );

			Manager manager = new Manager( teams );
			manager.startAllAndAwaitExecution();
		}, "test 1" );
		*/

		Utils.executionTime( () -> {
			List<RunnableWithLatch> runnableTasks =
					IntStream.range( 0, 1000 ).mapToObj( i -> new MyDevTeam( "Task-" + i, 1000 ) )
							 .collect( Collectors.toCollection( LinkedList::new ) );

			Manager manager = new Manager( runnableTasks );
			manager.startAllAndAwaitExecution();

			System.err.println( "gotovo !!" );
		}, "test 2" );

		Function<String, List<String>> URLProcessor = in -> Arrays.stream( in.trim().split( "," ) ).map( String::trim )
																  .collect( Collectors.toList() );

		String xxx = "/eSurvey/2021-11-12/thumbnail_211112101976567557.jpg,/eSurvey/2021-11-12/thumbnail_211112101976567559.jpg,/eSurvey/2021-11-12/thumbnail_211112101976567560.jpg,/eSurvey/2021-11-12/thumbnail_211112101976567562.jpg";
		Optional.of( xxx ).map( URLProcessor ).orElseGet( ArrayList::new )
				.forEach( System.err::println );

		/*Utils.executionTime( () -> {
			for (int i = 0; i < 100_000; i++) {
				try {
					TimeUnit.MILLISECONDS.sleep( 500 );
				} catch (InterruptedException e) {
					throw new RuntimeException( e );
				}
				System.err.println( *//*100 **//* (double) i / 100_0*//*00*//* + " %" );
			}

			System.err.println( "gotovo !!" );
			System.err.println( "-------------" );
		}, "test 3" );*/
	}

}
