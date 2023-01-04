package Java67.konkurentno;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Upperer extends ConverterTask {

	protected Upperer(CountDownLatch latch, String value) {
		super( latch, value );
		System.err.println( "Upperer - init." );
	}

	@Override
	protected String convert(String value) {
		System.err.println( ">>> Upperer - begin." );
		try {
			TimeUnit.SECONDS.sleep( 3 );
		} catch (InterruptedException e) {
		}
		System.err.println( "<<< Upperer - end." );

		return value == null ? "" : value.toUpperCase();
	}

	public static void main(String[] args) {
		//<editor-fold desc="Solution 1">
		String commandArray[] = { "cmd", "/c", "dir", /*"C:\\Program Files", */"C:\\" };

		try {
			Process process3 = Runtime.getRuntime().exec( commandArray );
			printCommandResult( process3 );
		} catch (IOException e) {
			System.err.println( e );
		}
		//</editor-fold>
	}

	private static void printCommandResult(Process process) throws IOException {
		BufferedReader reader = new BufferedReader( new InputStreamReader( process.getInputStream() ) );
		String line;
		while ((line = reader.readLine()) != null) {
			System.out.println( line );
		}

		reader.close();
	}
}
