import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.reflect.TypeToken;

public class Docker {

	public static void main(String[] args) {

		String userName = "chenhang";
		String appName = "openoffice";
		String imageName = "openoffice:4.1";
		String id = "";
		listContainers(userName);
		//startContainers(id);
		//pauseContainers(id);
		//removeContainers(id);
		//createContainers(appName, imageName, userName);

	}

	static String resourceUrl = "http://222.201.187.164:10087/containers";
//	static String cmd4Create4Eclipse = 
//			"{\"Image\":\"eclipse:juno\",\"Labels\":{\"user\":\"chenhang\"},\"HostConfig\":{\"PublishAllPorts\":true}}";
	static String cmd4CreateCont = "{\"Image\":\"";
	static String cmd4CreateCont1 = "\",\"Labels\":{\"user\":\"";
	static String cmd4CreateCont2 = "\"},\"HostConfig\":{\"PublishAllPorts\":true}}";
	
	
	public static void listContainers(String userName) {

		resourceUrl = resourceUrl
				+ "/json?all=1&filters={%22label%22:[%22user=" + userName + "%22]}";
		String result = HttpHandler.httpGet(resourceUrl);
		System.out.println(result);

		Type listType = new TypeToken<ArrayList<Container>>() {
		}.getType();
		ArrayList<Container> containerList = JsonData.jsonToObject(result,
				listType);

		// make things known
		for (int i = 0; i < containerList.size(); i++) {
			containerList.get(i).embody();
			System.out.println(containerList.get(i).toString1());
		}

		resetUrl();
	}

	public static void startContainers(String id) {

		resourceUrl = resourceUrl + "/" + id + "/start";
		HttpHandler.httpPost(resourceUrl, "start");

		resetUrl();
	}

	public static void pauseContainers(String id) {

		resourceUrl = resourceUrl + "/" + id + "/stop?t=5";
		HttpHandler.httpPost(resourceUrl, "stop");

		resetUrl();
	}
	
	public static void removeContainers(String id) {

		resourceUrl = resourceUrl + "/" + id;
		HttpHandler.httpDelete(resourceUrl);

		resetUrl();
	}
	
	public static void createContainers(String appName, String imageName, String userName) {
		
		resourceUrl = resourceUrl + "/create?name=" + appName + "-" + System.currentTimeMillis();
		String cmd4CreateContainer = cmd4CreateCont + imageName + cmd4CreateCont1 + userName + cmd4CreateCont2;
		//if "201" prompt success otherwise failure
		int responseCode = HttpHandler.post4CreateCont(resourceUrl, cmd4CreateContainer);
		
		resetUrl();
	}
	
	// if singleton
	public static void resetUrl() {
		resourceUrl = "http://222.201.187.164:10087/containers";
	}

}
