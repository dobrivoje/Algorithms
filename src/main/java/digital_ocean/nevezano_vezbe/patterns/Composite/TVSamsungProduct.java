package digital_ocean.nevezano_vezbe.patterns.Composite;

/**
 * @author д06ри, dobri7@gmail.com
 */
public class TVSamsungProduct implements Product {

	@Override
	public String getName() {
		return "TVSamsung";
	}

	@Override
	public int getPrice() {
		return 2_500;
	}
}
