import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpHandler {

	public static String httpGet(String dockerUrlResource) {

		String result = "";
		BufferedReader in = null;
		try {
			URL realUrl = new URL(dockerUrlResource);
			System.out.println(realUrl.toString());
			HttpURLConnection connection = (HttpURLConnection) realUrl
					.openConnection();
			connection.setRequestProperty("Content-Type", "application/json");
			// connection.setRequestProperty("all", "1");

			connection.connect();
			System.out.println(connection.getResponseCode());

			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
	
	public static void httpPost(String dockerUrlResource,String action) {

		try {
			URL realUrl = new URL(dockerUrlResource);
			HttpURLConnection connection = (HttpURLConnection) realUrl
					.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			if (action.equals("start")) {
				connection.setRequestProperty("Content-Type", "application/json");
			}

			OutputStream outStream = connection.getOutputStream();
			outStream.flush();
			outStream.close();
			System.out.println(action + " => " + connection.getResponseCode());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static int httpDelete(String dockerUrlResource) {

		int responseCode = 0;
		try {
			URL realUrl = new URL(dockerUrlResource);
			HttpURLConnection connection = (HttpURLConnection) realUrl
					.openConnection();
			connection.setRequestMethod("DELETE");
			connection.setDoOutput(true);
			responseCode = connection.getResponseCode();
			System.out.println("Remove => " + responseCode);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseCode;
	}
	
	public static int post4CreateCont(String dockerUrlResource,String param) {

		int responseCode = 0;
		try {
			URL realUrl = new URL(dockerUrlResource);
			HttpURLConnection connection = (HttpURLConnection) realUrl
					.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/json");

			byte[] bypes = param.toString().getBytes();
			OutputStream outStream = connection.getOutputStream();
			outStream.write(bypes);
			outStream.flush();
			outStream.close();
			responseCode = connection.getResponseCode();
			System.out.println("run container" + " => " + responseCode);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseCode;
	}
}
