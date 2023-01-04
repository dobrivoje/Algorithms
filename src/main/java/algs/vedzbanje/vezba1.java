package algs.vedzbanje;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class vezba1 {

	public static void main(String[] args) {
		List<String> L = Arrays.asList( "", "", "", "" );
		//		List<String> L = Arrays.asList( null, null, null, null, null );
		String commaSeparated = L.stream().filter( Objects::nonNull ).filter( e -> !e.trim().isEmpty() )
								 .collect( Collectors.joining( ", " ) );
		System.err.println( "comma separated : " + commaSeparated );

		String reportSubDirectories = "";//"dir1,   dir2;   C\\:  temp";
		List<String> elements = Arrays.stream( reportSubDirectories.split( "[,;]" ) )
									  .filter( Objects::nonNull ).map( String::trim )
									  .filter( e -> !e.isEmpty() )
									  .collect( Collectors.toList() );
		String res = String.join( "*  ", elements );
		System.err.println( res );
	}
}
