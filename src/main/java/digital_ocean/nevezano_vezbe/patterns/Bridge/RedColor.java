package digital_ocean.nevezano_vezbe.patterns.Bridge;

/**
 * @author д06ри, dobri7@gmail.com
 */
public class RedColor implements Color {

	@Override
	public String name() {
		return "Red";
	}

	@Override
	public void apply() {
		System.err.println( "RedColor is appling..." );
	}
}
