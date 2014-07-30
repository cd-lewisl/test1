package lewisl.test1.auth;

import org.scribe.builder.api.DefaultApi10a;
import org.scribe.model.Token;
import org.scribe.model.Verb;

public class FFAPI extends DefaultApi10a {

	@Override
	public String getRequestTokenEndpoint() {
		return "https://marketplace.firefox.com/oauth/token/";
	}

	@Override
	public String getAccessTokenEndpoint() {

		return "https://marketplace.firefox.com/oauth/register/";
	}

	@Override
	public String getAuthorizationUrl(Token requestToken) {
		return String
				.format("https://marketplace.firefox.com/oauth/authorize?oauth_token=%s",
						requestToken.getToken());
	}

	public Verb getAccessTokenVerb() {
		return Verb.POST;
	}

}
