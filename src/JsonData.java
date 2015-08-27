import java.lang.reflect.Type;
import java.util.ArrayList;
import com.google.gson.Gson;

public class JsonData {

	public static ArrayList<Container> jsonToObject(String jsonData, Type listType) {
		Gson gson = new Gson();
		System.out.println(jsonData);
		ArrayList<Container> cList = gson.fromJson(jsonData, listType);
//		dci.setcList(cList);
		System.out.println(cList.size());
//		System.out.println(cList.get(0).toString());
		
		return cList; 
	}
}
