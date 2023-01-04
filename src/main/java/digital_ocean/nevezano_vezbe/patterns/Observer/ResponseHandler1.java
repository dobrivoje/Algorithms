package digital_ocean.nevezano_vezbe.patterns.Observer;

import java.util.Observable;
import java.util.Observer;

public class ResponseHandler1 implements Observer {

	@Override
	public void update(Observable observable, Object arg) {
		if (arg instanceof String) {
			String resp = (String) arg;
			System.err.println( ">>> Received response : " + resp );
		}
	}
}
