package digital_ocean.nevezano_vezbe.patterns.Composite;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author д06ри, dobri7@gmail.com
 */
public class Package {

	List<Box>     boxes;
	List<Product> products;

	public Package(List<Box> boxes, List<Product> products) {
		this.boxes = boxes;
		this.products = products;
	}

	public int getPackageTotalPrice() {
		int prodPrices = Optional.ofNullable( products ).orElseGet( ArrayList::new )
								 .stream().map( Product::getPrice ).reduce( 0, Integer::sum );

		int boxTotalPrice = boxes.stream().map( Box::getBoxTotalPrice ).reduce( 0, Integer::sum );
		return prodPrices + boxTotalPrice;
	}

}
