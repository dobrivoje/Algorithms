package algs.examples.shell.execute;

// if we execute a Java program meant for the Linux/Unix
// operating system on a Windows operating system

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class ShellCommandRunner4 {

	public static void main(String[] args) {
		try {
			System.out.println( System.getProperty( "os.name" ) );
			System.out.println();

			// ProcessBuilder pb = new ProcessBuilder( "sh", "-c", "ls" );
			ProcessBuilder pb = new ProcessBuilder( "cmd", "/c", "dir", "C:\\Users\\dobrivoje.prtenjak\\Music",
													"cmd", "/c", "dir", "C:\\Users\\dobrivoje.prtenjak\\Music");
			// Exception thrown here because folder
			// structure of Windows and Linux are different.
			// pb.directory( new File( System.getProperty( "user.home" ) ) );
			pb.directory( new File( "C:\\Users\\dobrivoje.prtenjak" ) );
			Process process = pb.start();

			StringBuilder output = new StringBuilder();
			InputStreamReader inputStreamReader = new InputStreamReader( process.getInputStream() );
			BufferedReader reader = new BufferedReader( inputStreamReader );

			String line;
			while ((line = reader.readLine()) != null) {
				output.append( line ).append( "\n" );
			}

			int exitVal = process.waitFor();
			if (exitVal == 0) {
				System.out.println( "**************************** The Output is ******************************" );
				System.out.println( output );
				System.exit( 0 );
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
