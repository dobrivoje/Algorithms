package algs.intervju2022.tredovi.BoundedBuffer;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class example {

	public static void main(String[] args) {
		BoundedBuffer<String> buffer = new BoundedBuffer<>( 2 );
		System.out.println( "Is buffer empty? " + buffer.isEmpty() );

		/*buffer.put( "abc" );
		buffer.put( "def" );

		System.out.println( "Is buffer full? " + buffer.isFull() );

		String value = buffer.take();
		System.out.println( "element taken from buffer : " + value );

		value = buffer.take();
		System.out.println( "another element taken from buffer : " + value );*/

		Thread producer = new Thread( () -> {
			try {
				TimeUnit.MILLISECONDS.sleep( 200 );
			} catch (InterruptedException e) {
				System.err.println( e.getMessage() );
			}

			buffer.put( String.valueOf( new Random().nextInt( 10 ) ) );

		}, "producer-1" );

		Thread consumer = new Thread( () -> {
			while (true) {
				System.err.println( "-----" );
				System.err.println( buffer.take() );
				System.err.println( "-----" );
			}
		}, "consumer-1" );

		producer.start();
		consumer.start();
	}

	public void compute(BoundedBuffer<String> buffer) {
		while (true) {
			synchronized (buffer) {
			}
		}
	}
}
