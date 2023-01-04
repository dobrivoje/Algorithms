package Java67.primer1_nijedobro;

import Java67.primer2_dobar.MyDevTeam;
import Java67.primer2_dobar.MyQATeam;
import Java67.primer2_dobar.RunnableWithLatch;
import algs.Util.Utils;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ManagerRunnable extends Thread {

	final List<RunnableWithLatch> runnableLatchMembers;
	final int                     runnableLatchMembersNumber;
	final CountDownLatch          latch;

	public ManagerRunnable(List<RunnableWithLatch> runnableLatchMembers) {
		this.runnableLatchMembers = runnableLatchMembers;
		this.runnableLatchMembersNumber = runnableLatchMembers.size();
		this.latch = new CountDownLatch( runnableLatchMembersNumber );
		runnableLatchMembers.forEach( t -> t.setLatch( latch ) );
	}

	public synchronized void startAllAndWaitForExecution() {
		runnableLatchMembers.forEach( Runnable::run );
		awaitForExecution();
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
