package algs.TIJ4th.ForkJoinTasks.danas19.geeksforgeeks.demo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class Fajlovi {

	@Data
	@EqualsAndHashCode(of = { "ime"/* , "lista" */ })
	static class Osoba {

		String        ime;
		String        prezime;
		List<Integer> lista = new ArrayList<>();
	}

	public static void main(String[] args) throws IOException {
		String description = String.format( "Site id: %d which is at 90%%, cannot move to RFS queue because "
													+ "BNG is missing for UPRN : %s", 112279,
											"Large number of UPRNs, result is attached to the ticket" );

		String winFolder = "C:\\Users\\dobrivoje.prtenjak\\Downloads\\Photos(1)\\";
		String fileName = "UPRNs Report For Missing BNGs";

		String formattedMissingUPRNForBNG =
				"This is to let you know that site 286 Stretford Road, M15 5TQ -MMR , site id: 3278007527 which is at 90% cannot move to RFS queue as some of the information is missing for the UPRNs in the site:\n"
						+ "\n"
						+ "UPRN: 10012206380 - missing info: [missing1Splitter]\n"
						+ "UPRN: 10012206373 - missing info: [missing1Splitter]\n"
						+ "UPRN: 10012206375 - missing info: [missing1Splitter]\n"
						+ "UPRN: 10012206374 - missing info: [missing1Splitter]\n"
						+ "UPRN: 10012206377 - missing info: [missing1Splitter]\n"
						+ "UPRN: 10012206376 - missing info: [missing1Splitter]\n"
						+ "UPRN: 10012206379 - missing info: [missing1Splitter]\n"
						+ "UPRN: 10012206378 - missing info: [missing1Splitter]";

		File tmp = File.createTempFile( fileName, ".txt", new File( winFolder ) );
		Path path = Paths.get( tmp.getPath() );
		Files.copy( new ByteArrayInputStream( formattedMissingUPRNForBNG.getBytes( StandardCharsets.UTF_8 ) ),
					path, StandardCopyOption.REPLACE_EXISTING );

		Files.delete( path );
	}

}
