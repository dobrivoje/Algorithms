package Java67.primer2_dobar;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public interface IndexSupplier {

	Index get();

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	class Index {

		int startIndex;
		int endIndex;

		public static Index Of(int s, int e) {
			return new Index( s, e );
		}
	}

	static void main(String[] args) {
		IndexSupplier is = () -> Index.Of( 0, 0 );
		Index index = is.get();

		System.err.println( index );

		index.setStartIndex( 1 );
		index.setEndIndex( 5 );

		System.err.println( index );
	}

}
