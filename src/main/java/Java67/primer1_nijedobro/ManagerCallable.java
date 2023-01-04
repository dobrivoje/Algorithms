package Java67.primer1_nijedobro;

import Java67.primer2_dobar.CallableWithLatch;
import Java67.primer2_dobar.MyDevTeam;
import Java67.primer2_dobar.MyQATeam;
import Java67.primer2_dobar.RunnableWithLatch;
import algs.Util.Utils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ManagerCallable<T> {

	final List<CallableWithLatch<T>> callableWithLatches;
	final int                        callableLatchMembersNumber;
	final CountDownLatch             latch;

	public ManagerCallable(List<CallableWithLatch<T>> callableWithLatches) {
		this.callableWithLatches = callableWithLatches;
		this.callableLatchMembersNumber = callableWithLatches.size();
		this.latch = new CountDownLatch( callableLatchMembersNumber );
		callableWithLatches.forEach( t -> t.setLatch( latch ) );
	}

	public synchronized List<T> startAllAndWaitForExecution() {
		List<T> res = new LinkedList<>();

		callableWithLatches.forEach( e -> {
			try {
				res.add( e.call() );
			} catch (Exception ex) {
				throw new RuntimeException( ex );
			}
		} );

		awaitForExecution();
		return res;
	}

	private void awaitForExecution() {
		try {
			latch.await();
		} catch (InterruptedException e) {
			System.err.println( "Error: " + e.getMessage() );
			throw new RuntimeException( e );
		}
	}

	public static void main(String[] args) {
		Utils.executionTime( () -> {
			MyDevTeam dev = new MyDevTeam( "Dev tim", 1500 );
			MyQATeam qa1 = new MyQATeam( "QA tim 1", 1000 );
			MyQATeam qa2 = new MyQATeam( "QA tim 2", 1200 );
			MyQATeam qa3 = new MyQATeam( "QA tim 3", 1000 );
			List<RunnableWithLatch> teams = Arrays.asList( dev, qa1, qa2, qa3 );
			ManagerRunnable managerRunnable = new ManagerRunnable( teams );
			managerRunnable.startAllAndWaitForExecution();

			// manager.awaitForExecution();
			System.err.println( "gotovo !!" );
		}, "test 1" );
	}
}
