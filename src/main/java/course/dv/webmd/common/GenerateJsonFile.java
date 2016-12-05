package course.dv.webmd.common;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import course.dv.webmd.model.ConceptMapObject;
import course.dv.webmd.model.Member;

public class GenerateJsonFile {

	public static void generateJsonFile(Map<Member, Integer> map, String filePath){
		FileWriter fileWriter = null;
		File file = null;
		JSONObject jObj = new JSONObject();
		jObj.put("name", "bubble");

		JSONArray outerChildArray = new JSONArray();
		for(Entry<Member, Integer> entry : map.entrySet()){
			JSONObject outerChild = new JSONObject();

			JSONArray innerChildArray = new JSONArray();
			for(int i=0;i<3;i++){
				JSONObject innerChild = new JSONObject();
				if(i ==0){
					innerChild.put("name", entry.getKey().getMemberType());
					innerChild.put("address", "Member Type : "+entry.getKey().getMemberType());
				}

				if(i ==1){
					innerChild.put("name", entry.getKey().getMemberHelpfulVotes());
					innerChild.put("address", "Member Helpful Votes : "+entry.getKey().getMemberHelpfulVotes());
				}

				if(i ==2){
					innerChild.put("name", entry.getKey().getMemberFollowerNumber());
					innerChild.put("address", "Member Follower Number : "+entry.getKey().getMemberFollowerNumber());
				}

				innerChildArray.add(innerChild);
			}
			outerChild.put("children", innerChildArray);
			outerChild.put("name", entry.getKey().getMemberName());
			outerChildArray.add(outerChild);

		}
		jObj.put("children", outerChildArray);

		try {  
			file=new File(filePath, "members.json");  
			//file.createNewFile();  
			fileWriter = new FileWriter(file.getAbsoluteFile());  
			fileWriter.write(jObj.toJSONString());  

		} catch (IOException e) {  
			e.printStackTrace();  
		} 
		finally{
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}  

		}
	}

	public static String generateJsonForQuestions(Map<String, Integer> map) {
		JSONArray innerChildArray = new JSONArray();
		JSONObject jObj = new JSONObject();

		for(Entry<String, Integer> entry : map.entrySet()){
			JSONObject innerObject = new JSONObject();
			innerObject.put("text", entry.getKey());
			innerObject.put("count", entry.getValue());
			innerChildArray.add(innerObject);
		}
		jObj.put("items", innerChildArray);		
		return jObj.toJSONString().substring(1, jObj.toJSONString().length() - 1);
	}

	public static String generateJsonForConceptMap(Set<ConceptMapObject> set) {
		JSONArray output = new JSONArray();
		JSONObject jObj = new JSONObject();
		JSONObject innerObject = new JSONObject();
		JSONObject outerObject = new JSONObject();
		Iterator<ConceptMapObject> it = set.iterator();

		while(it.hasNext()){
			JSONArray innerChildArray = new JSONArray();
			JSONArray outerChildArray = new JSONArray();
			innerChildArray.add(it.next().getKeywords().replaceAll(" ", ","));
			outerChildArray.add(it.next().getQuestionId());
			outerChildArray.add(innerChildArray);
			outerChildArray.add(it.next().getQuestionTitle());
			output.add(outerChildArray);
		}

		System.out.println(output.toJSONString());
		return output.toJSONString();
	}
}
