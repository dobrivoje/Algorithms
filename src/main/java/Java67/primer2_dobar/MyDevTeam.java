package Java67.primer2_dobar;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class MyDevTeam extends Thread implements RunnableWithLatch {

	CountDownLatch countDownLatch;
	final String name;
	final long   workDuration;

	public MyDevTeam(String name, long workDuration) {
		this.name = name;
		this.workDuration = workDuration;
	}

	@Override
	public void setLatch(CountDownLatch countDownLatch) {
		this.countDownLatch = countDownLatch;
	}

	@Override
	public void run() {
		System.out.println( "Task assigned to development team : " + name );
		try {
			TimeUnit.MILLISECONDS.sleep( workDuration );
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		countDownLatch.countDown();
		System.out.println( "Task finished by development team : " + name );
	}
}
