package middleTier;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.jar.JarException;

import javax.naming.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Save {

	public String writeJSONtosave(ArrayList<User> users) throws JarException, JSONException {
        JSONObject temp = new JSONObject();
        JSONArray names = new JSONArray();
        
        for(int i = 0; i < users.size(); i++)
        {
        	String name = users.getClass().getName();
        	JSONObject person = new JSONObject();
        	JSONArray entryArray = new JSONArray();
        	
        	for(int j = 0; j < users.get(i).getEntries().size(); j++)
        	{
        		JSONObject temp2 = new JSONObject();
        		temp2.put("date", users.get(i).getEntries().get(j).getEntryDate());
        		temp2.put("entryType", users.get(i).getEntries().get(j).getCategory());
        		temp2.put("value", users.get(i).getEntries().get(j).getValue());
        		temp2.put("description", users.get(i).getEntries().get(j).getDescription());
        		
        		entryArray.put(temp2);
        	}
        	person.put("name", name);
        	person.put("entries", entryArray);
        	
        	temp.put("people", person);
        }

        return temp.toString();
    }
	
	 public Void savetostorage(String data, String filename)
	    {
	        FileOutputStream outputStream;
	        try{
	            //String filename = ;
	            String newFile = filename.replaceAll("/", "_");
	            outputStream = openFileOutput(newFile, Context.MODE_PRIVATE);
	            outputStream.write(data.getBytes());
	            outputStream.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
}
