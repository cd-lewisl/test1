package lewisl.test1;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.KeyManager;
// import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeSocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;

public class SSLHTTP {

    public static void main(String[] args) throws ClientProtocolException, IOException,
            NoSuchAlgorithmException, KeyManagementException {
        HttpClient client = new DefaultHttpClient();
        SSLContext sslcontext = SSLContext.getInstance(SSLSocketFactory.TLS);
        sslcontext.init(new KeyManager[] {}, new TrustManager[] {new X509TrustManager() {

            public void checkClientTrusted(X509Certificate[] chain, String authType)
                    throws CertificateException {

            }

            public void checkServerTrusted(X509Certificate[] chain, String authType)
                    throws CertificateException {

            }

            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        }}, null);

        SchemeSocketFactory factory = new SSLSocketFactory(sslcontext);

        // SSLSocketFactory.getSocketFactory();
        Scheme scheme = new Scheme("https", 443, factory);
        client.getConnectionManager().getSchemeRegistry().register(scheme);
        HttpPost post = new HttpPost("https://api.cumulis.net:8443/oauth2/token");
        client.execute(post);
    }
}
