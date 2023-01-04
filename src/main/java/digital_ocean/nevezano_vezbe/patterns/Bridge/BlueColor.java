package digital_ocean.nevezano_vezbe.patterns.Bridge;

/**
 * @author д06ри, dobri7@gmail.com
 */
public class BlueColor implements Color {

	@Override
	public String name() {
		return "Blue";
	}

	@Override
	public void apply() {
		System.err.println( "Blue is applying..." );
	}
}
