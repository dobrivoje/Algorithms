package digital_ocean.nevezano_vezbe.patterns.Composite;

/**
 * @author д06ри, dobri7@gmail.com
 */
public class TVTeslaProduct implements Product {

	@Override
	public String getName() {
		return "TV Tesla";
	}

	@Override
	public int getPrice() {
		return 2_000;
	}
}
