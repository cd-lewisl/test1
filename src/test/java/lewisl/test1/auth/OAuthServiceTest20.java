package lewisl.test1.auth;

import java.io.IOException;

import org.scribe.builder.ServiceBuilder;
import org.scribe.oauth.OAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OAuthServiceTest20 {
    private static final Logger logger = LoggerFactory.getLogger(OAuthServiceTest20.class);
    private static final String PROTECTED_ORIGIN = "https://marketplace.firefox.com";
    private static final String PROTECTED_RESOURCE_URL = PROTECTED_ORIGIN
            + "/api/v1/account/permissions/mine/";

    public static void main(String[] args) throws IOException {
        String securityKey =
                "95c606340dfca5b556b416202cda844bb88bce43ef484c572c9f95968463ab18050ebbf699d4347b9cea9737357f3169fd6137cbe7754cdd053e770b778fa0bf";
        OAuthService service =
                new ServiceBuilder().provider(FFAPI20.class)
                        .apiKey("mkt:10874589:yi_liu@tcl.com:0").apiSecret(securityKey)
                        .callback("oob").build();

        /**
         * 
         * Firefox MARKETPLACE DON'T SUPPORT AUTH2.0
         * 
         * 
         */
    }

}
