package tcpFactory;

import org.json.JSONException;
import org.json.JSONObject;

public class FileFactory implements AbstractFileFactory {

	public JSONObject createFileNameMessage(String name){
		JSONObject json = new JSONObject();
		try {
			json.put("name", name);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
	
}
