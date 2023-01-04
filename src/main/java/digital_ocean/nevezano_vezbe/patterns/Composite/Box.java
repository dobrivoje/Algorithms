package digital_ocean.nevezano_vezbe.patterns.Composite;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author д06ри, dobri7@gmail.com
 */
@Getter
@Setter
public class Box {

	private final List<Product> products;
	private final List<Box>     boxes;

	public Box(List<Product> products, List<Box> boxes) {
		this.products = products;
		this.boxes = boxes;
	}

	public int getBoxTotalPrice() {
		return getBoxTotalPrice( boxes );
	}

	private int getBoxTotalPrice(List<Box> boxes) {
		if (boxes == null || boxes.isEmpty()) {
			return 0;
		}

		int sum = 0;
		for (Box box : boxes) {
			if (box.getBoxes() != null && !box.getBoxes().isEmpty()) {
				sum += getBoxTotalPrice( box.getBoxes() );
			}
			if (box.getProducts() != null && !box.getProducts().isEmpty()) {
				sum += box.getProducts().stream().map( Product::getPrice ).reduce( 0, Integer::sum );
			}
		}

		return sum;
	}

	public static void main(String[] args) {
		Box kutijaTV = new Box( Arrays.asList( new TVSamsungProduct(), new TVTeslaProduct() ), null );
		Box kutijaSaTehnikom = new Box( Arrays.asList( new iPhone11Product(), new iPhone7Product() ),
										Collections.singletonList( kutijaTV ) );

		List<Box> kutijice = Arrays.asList( new Box( Arrays.asList( new LukProduct(), new ParadajzProduct() ),
													 Collections.singletonList( kutijaSaTehnikom ) ) );

		List<Box> kutijaSalata = Collections.singletonList( new Box( null, kutijice ) );

		Box box2 = new Box( Collections.singletonList( new KrompirProduct() ), kutijaSalata );
		System.err.println( box2.getBoxTotalPrice() );

		// provera cene :
		List<Product> productList = Arrays.asList( new iPhone11Product(), new iPhone7Product(),
												   new ParadajzProduct(), new LukProduct(), new KrompirProduct(),
												   new TVTeslaProduct(), new TVSamsungProduct()
		);
		Integer ukCena = productList.stream().map( Product::getPrice ).reduce( 0, Integer::sum );
		System.err.println( "ukCena = " + ukCena );
	}
}
