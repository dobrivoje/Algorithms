package algs.vedzbanje;

import java.util.Arrays;
import java.util.function.Function;

public class FInterfejsi {

	public static void main(String[] args) {
		Function<String, String>[] ff = new Function[3];
		ff[0] = s -> s.replaceAll( " ", "-" );
		ff[1] = s -> ">>" + s.toUpperCase() + "<<";
		ff[2] = s -> "***" + s.toLowerCase() + "***";

		Function<String, String> res = Arrays.stream( ff ).reduce( Function.identity(), Function::andThen );

		String apply = res.apply( "   fg  " );
		System.err.println( apply );
	}
}
