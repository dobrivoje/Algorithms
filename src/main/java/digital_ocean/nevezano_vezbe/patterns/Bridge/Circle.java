package digital_ocean.nevezano_vezbe.patterns.Bridge;

/**
 * @author д06ри, dobri7@gmail.com
 */
public class Circle extends Shape {

	public Circle(Color color) {
		super( color );
	}

	@Override
	public String applyColor() {
		color.apply();
		return "Circle, color : " + color.name();
	}
}
