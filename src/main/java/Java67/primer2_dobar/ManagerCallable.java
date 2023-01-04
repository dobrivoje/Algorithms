package Java67.primer2_dobar;

import algs.Util.Utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ManagerCallable<T> extends Thread {

	// final int                     cpus = 15;
	final List<CallableWithLatch<T>> callableWithLatches;
	CountDownLatch latch;

	public void setLatch(CountDownLatch latch) {
		this.latch = latch;
	}

	public ManagerCallable(List<CallableWithLatch<T>> callableWithLatches) {
		this.callableWithLatches = callableWithLatches;
		this.latch = new CountDownLatch( callableWithLatches.size() );
		callableWithLatches.forEach( t -> t.setLatch( latch ) );
	}

	private void awaitForExecution() {
		try {
			latch.await();
		} catch (InterruptedException e) {
			System.err.println( "Error: " + e.getMessage() );
			throw new RuntimeException( e );
		}
	}

	public synchronized List<T> startAllAndAwaitExecution() {
		List<Future<T>> res = new LinkedList<>();

		for (CallableWithLatch<T> cwl : callableWithLatches) {
			try {
//				res.add(  );
			} catch (Exception e) {
				throw new RuntimeException( e );
			}
		}

		awaitForExecution();

//		return res;
		return new ArrayList<>();
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
		Utils.executionTime( () -> {
			List<CallableWithLatch<Integer>> runnableTasks =
					IntStream.range( 0, 100 ).mapToObj( i -> new CallableWorker( "Task-" + i, 500 ) {
							 } )
							 .collect( Collectors.toCollection( LinkedList::new ) );

			ManagerCallable<Integer> manager = new ManagerCallable<>( runnableTasks );
			List<Integer> finalResult = manager.startAllAndAwaitExecution();
			System.err.println( finalResult );

			System.err.println( "gotovo !!" );
		}, "test 2" );

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
