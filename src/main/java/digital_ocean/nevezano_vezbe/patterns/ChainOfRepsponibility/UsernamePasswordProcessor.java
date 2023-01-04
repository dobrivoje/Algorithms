package digital_ocean.nevezano_vezbe.patterns.ChainOfRepsponibility;

/**
 * @author д06ри, dobri7@gmail.com
 */
public class UsernamePasswordProcessor extends AuthenticationProcessor {

	public UsernamePasswordProcessor(AuthenticationProcessor nextProcessor, String token) {
		super( nextProcessor, token );
	}

	@Override
	public boolean isAuthorized(AuthenticationProvider authProvider) {
		if (authProvider instanceof UserNamePassProvider) {
			return authProvider.isOk( token );
		} else if (nextProcessor != null) {
			return nextProcessor.isAuthorized( authProvider );
		} else {
			throw new RuntimeException( "Aborting! " );
		}
	}
}
