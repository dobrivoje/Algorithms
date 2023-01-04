package algs.intervju2022.goran_todorovic.zadatak2;

public class Car {

	Engine engine;

	String   steeringWheel;
	String[] wheels;
	String[] windows;

	public String getPower() {
		return String.format( "Engine power: %3.2f", engine.getHorsePower() );
	}

	public String getRPM() {
		return String.format( "Engine power: %3.2f", engine.getHorsePower() );
	}
}
