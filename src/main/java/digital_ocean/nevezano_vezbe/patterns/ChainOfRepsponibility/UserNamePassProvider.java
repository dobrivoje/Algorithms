package digital_ocean.nevezano_vezbe.patterns.ChainOfRepsponibility;

/**
 * @author д06ри, dobri7@gmail.com
 */
public class UserNamePassProvider implements AuthenticationProvider {

	@Override
	public boolean isOk(String token) {
		if (token == null || token.isEmpty()) {
			return false;
		}

		String[] split = token.split( ":" );
		boolean res = split[0].equals( "un" ) && split[1].equals( "pa" );
		return res;
	}
}
