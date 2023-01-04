package algs.intervju2022.goran_todorovic.zadatak2;

import lombok.Data;

@Data
public class Engine implements Runnable {

	double horsePower;
	double rpm;
	String egrValve;

	@Override
	public void run() {
		System.err.println( "Engine is working. Current RPM = " + rpm );
	}
}
