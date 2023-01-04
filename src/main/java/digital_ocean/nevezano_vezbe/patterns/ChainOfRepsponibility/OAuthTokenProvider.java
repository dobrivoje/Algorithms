package digital_ocean.nevezano_vezbe.patterns.ChainOfRepsponibility;

/**
 * @author д06ри, dobri7@gmail.com
 */
public class OAuthTokenProvider implements AuthenticationProvider {

	@Override
	public boolean isOk(String token) {
		return token!=null && token.equals( "oauth2" );
	}
}
