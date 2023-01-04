package algs.intervju2022.tredovi.InterThreadCommunication;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InterThreadCommunicationExample {

	public static void main(String[] args) {
		AtomicInteger counter = new AtomicInteger( 0 );

		Queue<Integer> sharedQ = new LinkedList<>();

		Thread producer = new Producer( sharedQ, counter );
		List<Thread> consumers = IntStream.rangeClosed( 1, 10 ).boxed()
										  .map( i -> new Consumer( "Konzumer " + i, sharedQ ) )
										  .collect( Collectors.toList() );

		producer.start();
		consumers.forEach( Thread::start );
	}
}
