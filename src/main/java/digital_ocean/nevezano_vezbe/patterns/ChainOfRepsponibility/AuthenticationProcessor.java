package digital_ocean.nevezano_vezbe.patterns.ChainOfRepsponibility;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public abstract class AuthenticationProcessor {

	public    AuthenticationProcessor nextProcessor;
	protected String                  token;

	public abstract boolean isAuthorized(AuthenticationProvider authProvider);
}