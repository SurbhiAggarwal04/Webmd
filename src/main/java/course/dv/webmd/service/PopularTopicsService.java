package course.dv.webmd.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletContext;

import java.util.TreeMap;

import course.dv.webmd.dao.TopicQuestionCountDAO;
import course.dv.webmd.dao.TopicsDAO;

public class PopularTopicsService {	
	static TreeMap<String,Long> topicCountMap=new TreeMap<>();
	static Map<String,Long> mostPopularMap=new LinkedHashMap<>();
	static Map<String,Long> mediocreLevelTopicMap=new LinkedHashMap<>();
	static Map<String,Long> leastPopularTopicMap=new LinkedHashMap<>();
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";
    private static final String FILE_HEADER = "id,value";


	public static void init()
	{
		Map<String,String> topicMap=TopicsDAO.getAllTopics();
		topicCountMap=TopicQuestionCountDAO.getAllTopicQuestionsCount(topicMap);

		Iterator<Entry<String,Long>> it=topicCountMap.entrySet().iterator();
		while(it.hasNext())
		{
			Entry<String, Long> topic=it.next();
			String topicName=topic.getKey();
			Long count=topic.getValue();
			if(count>=400)mostPopularMap.put(topicName, count);
			if(count<400 && count>=200)mediocreLevelTopicMap.put(topicName, count);
			else leastPopularTopicMap.put(topicName, count);
		}
		
		getCsvFromHashMap(mostPopularMap);
	}
	
	public static void getCsvFromHashMap(Map<String,Long> mostPopular){
		FileWriter writer = null;
		try{
			writer = new FileWriter("topics.csv");
			writer.append(FILE_HEADER.toString());
			writer.append(NEW_LINE_SEPARATOR);
			
			for (Entry<String, Long> entry : mostPopular.entrySet())
			{
			    writer.append(entry.getKey());
			    writer.append(COMMA_DELIMITER);
			    writer.append(String.valueOf(entry.getValue()));
			    writer.append(NEW_LINE_SEPARATOR);
			}

		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static Map<String,Long> getMostPopularTopics() {
		return mostPopularMap;
	}

	public static Map<String,Long> getMediocreTopics() {
		return mediocreLevelTopicMap;
	}

	public static Map<String,Long> getLeastPopularTopics() {
		return leastPopularTopicMap;
	}
	public static void main(String[] args) {
		ServletContext context = null;
		InputStream path = context.getResourceAsStream("/WEB-INF/pages/login.jsp");
		System.out.println(path.toString());
		//init();
		
	}
		
}
