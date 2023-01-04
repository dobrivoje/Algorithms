package digital_ocean.nevezano_vezbe.patterns.Bridge;

import java.util.Arrays;
import java.util.List;

public class HJIZI {

	public static void main(String[] args) {
		Color redColor = new RedColor();
		Color whiteColor = new WhiteColor();
		Color blueColor = new BlueColor();

		Shape circle1 = new Circle( blueColor );
		Shape circle2 = new Circle( redColor );
		Shape square = new Square( whiteColor );

		List<Shape> shapes = Arrays.asList( circle1,circle2,square );
		shapes.stream().map( Shape::applyColor ). forEach( System.err::println );
	}
}
