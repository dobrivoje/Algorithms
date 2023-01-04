package digital_ocean.nevezano_vezbe.patterns.Observer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Observable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class EventSource extends Observable implements Runnable {

	private final ExecutorService executorService;

	public EventSource(ExecutorService executorService) {
		this.executorService = executorService;
	}

	@Override
	public void run() {
		try {
			InputStreamReader isr = new InputStreamReader( System.in );
			BufferedReader br = new BufferedReader( isr );
			while (true) {
				String response = br.readLine();
				if (response.equalsIgnoreCase( "exit!" )) {
					executorService.shutdown();
					executorService.awaitTermination( 100, TimeUnit.MILLISECONDS );

					throw new RuntimeException( "Exiting..." );
				}
				setChanged();
				notifyObservers( response );
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println( "Exiting app..." );
		}
	}
}
