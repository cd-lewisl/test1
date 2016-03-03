package lewisl.test1.auth;

import org.scribe.builder.api.DefaultApi20;
import org.scribe.model.OAuthConfig;
import org.scribe.model.Verb;

public class FFAPI20 extends DefaultApi20 {

    @Override
    public Verb getAccessTokenVerb() {
        return Verb.POST;
    }

    @Override
    public String getAccessTokenEndpoint() {

        // POST /token HTTP/1.1
        // Host: server.example.com
        // Authorization: Basic czZCaGRSa3F0MzpnWDFmQmF0M2JW
        // Content-Type: application/x-www-form-urlencoded
        //
        // grant_type=authorization_code&code=SplxlOBeZQQYbYS6WxSbIA
        // &redirect_uri=https%3A%2F%2Fclient%2Eexample%2Ecom%2Fcb

        
        // An example successful response:
        //
        // HTTP/1.1 200 OK
        // Content-Type: application/json;charset=UTF-8
        // Cache-Control: no-store
        // Pragma: no-cache
        //
        // {
        // "access_token":"2YotnFZFEjr1zCsicMWpAA",
        // "token_type":"example",
        // "expires_in":3600,
        // "refresh_token":"tGzv3JOkF0XG5Qx2TlKWIA",
        // "example_parameter":"example_value"
        // }
        return "https://marketplace.firefox.com/oauth/register/";
    }

    @Override
    public String getAuthorizationUrl(OAuthConfig config) {
        // GET /authorize?response_type=code&client_id=s6BhdRkqt3&state=xyz
        // &redirect_uri=https%3A%2F%2Fclient%2Eexample%2Ecom%2Fcb HTTP/1.1
        // Host: server.example.com
        return "https://marketplace.firefox.com/oauth/authorize";
    }
}
