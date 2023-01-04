package digital_ocean.nevezano_vezbe.patterns.Bridge;

/**
 * @author д06ри, dobri7@gmail.com
 */
public abstract class Shape {

	protected final Color color;

	public Shape(Color color) {
		this.color = color;
	}

	abstract public String applyColor();

	@Override
	public String toString() {
		return color.name();
	}
}
