package digital_ocean.nevezano_vezbe.patterns.Composite;

/**
 * @author д06ри, dobri7@gmail.com
 */
public class iPhone7Product implements Product {

	@Override
	public String getName() {
		return "iPhone7";
	}

	@Override
	public int getPrice() {
		return 1_000;
	}
}
