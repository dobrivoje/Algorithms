package digital_ocean.nevezano_vezbe.patterns.ChainOfRepsponibility;

/**
 * @author д06ри, dobri7@gmail.com
 */
public class OAuthProcessor extends AuthenticationProcessor {

	public OAuthProcessor(AuthenticationProcessor nextProcessor, String token) {
		super( nextProcessor, token );
	}

	@Override
	public boolean isAuthorized(AuthenticationProvider authProvider) {
		if (authProvider instanceof OAuthTokenProvider) {
			return authProvider.isOk( token );
		} else if (nextProcessor != null) {
			return nextProcessor.isAuthorized( authProvider );
		} else {
			return false;
		}
	}
}
