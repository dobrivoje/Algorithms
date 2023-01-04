package digital_ocean.nevezano_vezbe.patterns.Composite;

/**
 * @author д06ри, dobri7@gmail.com
 */
public class iPhone11Product implements Product {

	@Override
	public String getName() {
		return "iPhone11";
	}

	@Override
	public int getPrice() {
		return 1_400;
	}
}
