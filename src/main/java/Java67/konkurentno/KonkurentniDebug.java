package Java67.konkurentno;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KonkurentniDebug {

	public static final List<Integer> a = Collections.synchronizedList( new ArrayList<>() );

	public static void main(String[] args) throws InterruptedException {
		Thread t = new Thread( () -> addIfAbsent( 17 ) );
		t.start();

		addIfAbsent( 17 );
		t.join();

		System.err.println( "a : " + a );
	}

	private static void addIfAbsent(Integer x) {
		System.err.println( "addIfAbsent begin : " + Thread.currentThread().getName() );

		if (!a.contains( x )) {
			a.add( x );
		}

		System.err.println( "addIfAbsent end : " + Thread.currentThread().getName() );
	}
}
