package digital_ocean.nevezano_vezbe.patterns.Bridge;

/**
 * @author д06ри, dobri7@gmail.com
 */
public class Triangle extends Shape {

	public Triangle(Color color) {
		super( color );
	}

	@Override
	public String applyColor() {
		color.apply();
		return "Triangle, color : " + color.name();
	}
}
