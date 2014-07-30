package lewisl.test1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;

public class FetchValidationResult {

	public static void main(String[] args) throws ClientProtocolException,
			IOException {
		HttpClient client = new DefaultHttpClient();
		String id = "25b6163f08d24e5395c623d619fdb42a";
		String uri = "https://marketplace.firefox.com/api/v1/apps/validation/"
				+ id + "/";
		HttpGet getMethod = new HttpGet(uri);

		BasicHeader contentType = new BasicHeader("content-type",
				"application/json");
		getMethod.addHeader(contentType);

		ByteArrayOutputStream os = new ByteArrayOutputStream();

		client.execute(getMethod).getEntity().writeTo(os);

		System.out.println(os.toString());

		client.getConnectionManager().shutdown();
	}

}
