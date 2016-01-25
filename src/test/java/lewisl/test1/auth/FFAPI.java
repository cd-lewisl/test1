package lewisl.test1.auth;

import org.scribe.builder.api.DefaultApi10a;
import org.scribe.model.Token;
import org.scribe.model.Verb;

public class FFAPI extends DefaultApi10a {

	@Override
	public String getRequestTokenEndpoint() {
	    System.out.println("getRequestTokenEndpoint");
		return "https://marketplace.firefox.com/oauth/token/";
	}
	
	@Override
    public String getAuthorizationUrl(Token requestToken) {
        System.out.println("getAuthorizationUrl");

        return String
                .format("https://marketplace.firefox.com/oauth/authorize?oauth_token=%s",
                        requestToken.getToken());
    }
	
	@Override
	public String getAccessTokenEndpoint() {
        System.out.println("getAccessTokenEndpoint");

		return "https://marketplace.firefox.com/oauth/register/";
	}

	

	public Verb getAccessTokenVerb() {
		return Verb.POST;
	}

}
