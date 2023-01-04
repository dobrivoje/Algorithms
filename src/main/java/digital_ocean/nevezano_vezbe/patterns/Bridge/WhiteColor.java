package digital_ocean.nevezano_vezbe.patterns.Bridge;

/**
 * @author д06ри, dobri7@gmail.com
 */
public class WhiteColor implements Color {

	@Override
	public String name() {
		return "White";
	}

	@Override
	public void apply() {
		System.err.println( "White is applying..." );
	}
}
