package lewisl.test1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.testng.annotations.DataProvider;
//import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class FetchValidationResult {
	public static final String origin = "https://marketplace.allizom.org";

	@DataProvider(name = "offerValId")
	public Object[][] dataProvider() {
		return new Object[][] { { "f5dff16d3d244a378ede6667d21bab96" } };
	}

	// public static final String origin="https://marketplace.firefox.com";

//	 @Test(alwaysRun = true, singleThreaded = true, groups = { "ffAPI" },
//	 dataProvider = "validationIDProvider", dataProviderClass =
//	 TestSendValidationRequest.class)
	@Test(alwaysRun = true, singleThreaded = true, groups = { "ffAPI" }, dataProvider = "offerValId")
	public void testFetchResult(String id) throws ClientProtocolException,
			IOException {
		HttpClient client = new DefaultHttpClient();
		// String id = "25b6163f08d24e5395c623d619fdb42a";

		System.out.println(String.format("validation ID:%s", id));
		String uri = origin + "/api/v1/apps/validation/" + id + "/";
		HttpGet getMethod = new HttpGet(uri);

		BasicHeader contentType = new BasicHeader("content-type",
				"application/json");
		getMethod.addHeader(contentType);

		ByteArrayOutputStream os = new ByteArrayOutputStream();

		client.execute(getMethod).getEntity().writeTo(os);

		System.out.println(os.toString());

		getMethod.abort();
		client.getConnectionManager().shutdown();
	}

}
