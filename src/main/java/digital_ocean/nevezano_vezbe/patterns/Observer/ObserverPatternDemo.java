package digital_ocean.nevezano_vezbe.patterns.Observer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 <a href="https://www.javatpoint.com/observer-pattern">...</a>
 */
public class ObserverPatternDemo {

	public static void main(String[] args) {
		ExecutorService E = Executors.newCachedThreadPool();
		EventSource eventSource = new EventSource( E );
		E.submit( eventSource );

		ResponseHandler1 handler1 = new ResponseHandler1();
		ResponseHandler2 handler2 = new ResponseHandler2();
		eventSource.addObserver( handler1 );
		eventSource.addObserver( handler2 );

		//        Thread thread = new Thread(eventSource);
		//        thread.start();
	}
}
