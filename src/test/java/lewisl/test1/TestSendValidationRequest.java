package lewisl.test1;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import lewisl.test1.ffvo.SendTaskResponse;
import lewisl.test1.ffvo.Upload;
import lewisl.test1.ffvo.ValRequest;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import sun.misc.BASE64Encoder;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Convert the zip file to base64 encoding string.
 * 
 * @author yliu
 * @date 14/07/28
 */
public class TestSendValidationRequest {

	@DataProvider(name = "validationIDProvider", parallel = false)
	@Test()
	public static Object[][] sendValidateionReq() throws IOException {
		File file = new File("test.zip");
		InputStream is = new FileInputStream(file);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		IOUtils.copy(is, os);
		is.close();
		String result = new BASE64Encoder().encode(os.toByteArray());
		// System.out.println(result);
		String validationId = sendTask(result);
		return new Object[][] { { validationId } };
	}

	/**
	 * Return the validation Id;
	 * 
	 * @param base64Str
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	private static String sendTask(String base64Str)
			throws ClientProtocolException, IOException {
		HttpClient client = new DefaultHttpClient();

		String uri = FetchValidationResult.origin + "/api/v1/apps/validation/";
		HttpPost postMethod = new HttpPost(uri);

		BasicHeader contentType = new BasicHeader("content-type",
				"application/json");
		postMethod.addHeader(contentType);

		Upload upload = new Upload();
		upload.setData(base64Str);
		upload.setType("application/zip");
		upload.setName("test.zip");

		ValRequest valReq = new ValRequest();
		valReq.setUpload(upload);

		String str = ((JSONObject) JSON.toJSON(valReq)).toJSONString();

		postMethod
				.setEntity(new StringEntity(str, ContentType.APPLICATION_JSON));
		HttpResponse response = client.execute(postMethod);
		System.out.println(response.getEntity());

		ByteArrayOutputStream respOs = new ByteArrayOutputStream();
		response.getEntity().writeTo(respOs);
		System.out.println(respOs.toString());

		// JSONSerializerMap map = new JSONSerializerMap();
		// map.put(SendTaskResponse.class, new JavaBeanDeserializer(
		// SendTaskResponse.class, Collections.singletonMap("", "")));

		SendTaskResponse resp = (SendTaskResponse) JSON.parseObject(
				respOs.toString(), SendTaskResponse.class);
		/**
		 * You can't get the validated result immediately as for non-hosted app,
		 * The remote server side just enqueued this task, If this task be
		 * processed, you will get the result with this api
		 * /api/v1/apps/validation/b77d8d1f90ad4e0a8d263f9075633179/
		 */
		// {"id":"b77d8d1f90ad4e0a8d263f9075633179","processed":false,"valid":false,"validation":null}

		client.getConnectionManager().shutdown();

		return resp.getId();
	}
}
