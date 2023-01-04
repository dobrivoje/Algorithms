package digital_ocean.nevezano_vezbe.patterns.Composite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author д06ри, dobri7@gmail.com
 */
public class GHFS {

	public static void main(String[] args) {
		List<Box> kutijaSaIPhonovima = Arrays.asList(
				new Box( Arrays.asList( new iPhone7Product(), new iPhone11Product() ), null )
		);

		List<Product> povrce = Arrays.asList( new KrompirProduct() );
		Package aPackage = new Package( kutijaSaIPhonovima, povrce );

		System.err.println(aPackage.getPackageTotalPrice());
	}
}
