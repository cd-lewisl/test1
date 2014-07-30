package lewisl.test1.auth;

import com.google.api.client.auth.oauth.OAuthAuthorizeTemporaryTokenUrl;

public class TokenDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url="https://marketplace.firefox.com/oauth/register/";
		OAuthAuthorizeTemporaryTokenUrl tokenURL = new OAuthAuthorizeTemporaryTokenUrl(url);
	}

}
