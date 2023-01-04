package digital_ocean.nevezano_vezbe.patterns.ChainOfRepsponibility;

public class ChainOfResponsibilityDemo {

	public static void main(String[] args) {

		AuthenticationProcessor oauth2 = new OAuthProcessor( null, "oauth2" );
		AuthenticationProcessor chainProcessor = new UsernamePasswordProcessor( oauth2, "un:pa" );

		boolean isAuthorizedUNP = chainProcessor.isAuthorized( new UserNamePassProvider() );
		System.err.println( "isAuthorizedUNP ? " + isAuthorizedUNP );

		boolean isAuthorizedOauth2 = chainProcessor.isAuthorized( new OAuthTokenProvider() );
		System.err.println( "isAuthorizedOauth2 ? " + isAuthorizedOauth2 );

		/*String input = "   aaa   ";
		List<Function<String, String>> adapters = new LinkedList<>();
		adapters.addAll( Arrays.asList( s->s.replaceAll( "\\s", "_" ), s -> ">" + s + "<", String::toUpperCase ) );

		String result = adapters.stream().reduce( Function.identity(), Function::andThen )
								.apply( input );
		System.err.println( "result = " + result );*/
	}
}
