package lewisl.test1.auth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.io.IOUtils;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OAuthServiceTest {
	private static final Logger logger = LoggerFactory
			.getLogger(OAuthServiceTest.class);
	private static final String PROTECTED_ORIGIN = "https://marketplace.firefox.com";
	private static final String PROTECTED_RESOURCE_URL = PROTECTED_ORIGIN
			+ "/api/v1/account/settings/mine/";

	public static void main(String[] args) throws IOException {
		// Step1:Generate the request Token
		String securityKey = "95c606340dfca5b556b416202cda844bb88bce43ef484c572c9f95968463ab18050ebbf699d4347b9cea9737357f3169fd6137cbe7754cdd053e770b778fa0bf";
		OAuthService service = new ServiceBuilder().provider(FFAPI.class)
				.apiKey("mkt:10874589:yi_liu@tcl.com:0").apiSecret(securityKey)
				.callback("oob").build();

		Token reqToken = service.getRequestToken();
		logger.info("Request Token is : {}", reqToken.getToken());
		logger.info(reqToken.toString());

		// Step2: accept the grant
		String authorizationUrl = service.getAuthorizationUrl(reqToken);
		logger.info("Please pick up this url to Broswer Location bar and grant the premission:\n{}",authorizationUrl);
		System.out.print("Please input the verified Code:");
		String verifiedCode;
		if (System.console() != null) {
			verifiedCode = System.console().readLine();
		} else {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));
			verifiedCode = br.readLine();
		}

		logger.info("The Verified Code is :{}", verifiedCode);
		// Step3: gain the access TOken
		Verifier verifier = new Verifier(verifiedCode);
		Token accessToken = service.getAccessToken(reqToken, verifier);
		logger.info("Access Token is : {}", accessToken.getToken());

		// Step4: sign the request with the access token gained in last step.
		OAuthRequest request = new OAuthRequest(Verb.GET,
				PROTECTED_RESOURCE_URL);
		service.signRequest(accessToken, request);
		Response resp = request.send();

		System.out.println(IOUtils.toString(resp.getStream()));

	}

}

//2014-07-30 09:32:39,799 - lewisl.test1.auth.OAuthServiceTest -0    [main] INFO   - Request Token is : fe033d1428b795edd6b680bc935a6cdb24b4208b0826a8f29ff1e16037362f842095c6acfda81fa57a235895a95c2b4e92b558b8be9067e8ed937a205fa052df
//2014-07-30 09:32:39,800 - lewisl.test1.auth.OAuthServiceTest -1    [main] INFO   - Token[fe033d1428b795edd6b680bc935a6cdb24b4208b0826a8f29ff1e16037362f842095c6acfda81fa57a235895a95c2b4e92b558b8be9067e8ed937a205fa052df , 7eca9bae3d3390dae2a59a043b0665e33b633ca03c8f1d913126106968081a14b5870ef14767b347655a8129f2bb47ea87b1efa99c4d1b09735cf37a43517d90]
//2014-07-30 09:32:39,800 - lewisl.test1.auth.OAuthServiceTest -1    [main] INFO   - Please pick up this url to Broswer Location bar and grant the premission:
//https://marketplace.firefox.com/oauth/authorize?oauth_token=fe033d1428b795edd6b680bc935a6cdb24b4208b0826a8f29ff1e16037362f842095c6acfda81fa57a235895a95c2b4e92b558b8be9067e8ed937a205fa052df
//Please input the verified Code:ffe8962cb4cc5ce077eac7a72a1a9a0b95b40de594764007c85e52817188845502ecb2a983264eeeb6320fe5b8ec0e4666d99e73f3bd99d1fe7abb2a24c7093b
//2014-07-30 09:33:15,565 - lewisl.test1.auth.OAuthServiceTest -35766 [main] INFO   - The Verified Code is :ffe8962cb4cc5ce077eac7a72a1a9a0b95b40de594764007c85e52817188845502ecb2a983264eeeb6320fe5b8ec0e4666d99e73f3bd99d1fe7abb2a24c7093b
//2014-07-30 09:33:16,539 - lewisl.test1.auth.OAuthServiceTest -36740 [main] INFO   - Access Token is : ccb3dd229318cd198e0448a9637514469caf1fe9a5d026c649a89cee8ec3d29ca0f5b802d7909d28ceb668ea3832c9946bf304b647ee750bb5d64893f7724b31
//{"display_name":"yi_liu"}
