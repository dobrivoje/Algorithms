package algs.intervju2022.java67.TypeErasue;

/**
 * @author д06ри, dobri7@gmail.com
 */
public class Triangle implements LinearShape {

	private final int side;
	private final int height;

	public Triangle(int side, int height) {
		this.side = side;
		this.height = height;
	}

	@Override
	public boolean hasDiagonal() {
		return false;
	}

	@Override
	public String getName() {
		return getClass().getSimpleName();
	}

	@Override
	public double getArea() {
		return 0.5 * side * height;
	}

	@Override
	public double getCircumference() {
		return 3 * side;
	}
}
