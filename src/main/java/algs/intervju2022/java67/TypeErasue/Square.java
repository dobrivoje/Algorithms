package algs.intervju2022.java67.TypeErasue;

/**
 * @author д06ри, dobri7@gmail.com
 */
public class Square implements LinearShape {

	private final int side;

	public Square(int side) {
		this.side = side;
	}

	@Override
	public boolean hasDiagonal() {
		return true;
	}

	@Override
	public String getName() {
		return getClass().getSimpleName();
	}

	@Override
	public double getArea() {
		return side*side;
	}

	@Override
	public double getCircumference() {
		return 4*side;
	}
}
