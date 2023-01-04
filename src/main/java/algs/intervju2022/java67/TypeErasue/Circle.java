package algs.intervju2022.java67.TypeErasue;

/**
 * @author д06ри, dobri7@gmail.com
 */
public class Circle implements RoundShape {

	private final int radius;

	public Circle(int radius) {
		this.radius = radius;
	}

	@Override
	public double getRadius() {
		return radius;
	}

	@Override
	public String getName() {
		return getClass().getSimpleName();
	}

	@Override
	public double getArea() {
		return Math.PI * radius * radius;
	}

	@Override
	public double getCircumference() {
		return Math.PI * radius;
	}
}
