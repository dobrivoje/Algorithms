package digital_ocean.nevezano_vezbe.patterns.Bridge;

/**
 * @author д06ри, dobri7@gmail.com
 */
public class Square extends Shape {

	public Square(Color color) {
		super( color );
	}

	@Override
	public String applyColor() {
		color.apply();
		return "Square, color : " + color.name();
	}
}
