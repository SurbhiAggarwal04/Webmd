package course.dv.webmd.service;

import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.servlet.ServletContext;

import course.dv.webmd.dao.TopicQuestionCountDAO;
import course.dv.webmd.dao.TopicsDAO;

public class PopularTopicsService {	
	
	static TreeMap<String,Long> topicCountMap=new TreeMap<>();
	static Map<String,Long> mostPopularMap=new LinkedHashMap<>();
	static Map<String,Long> mediocreLevelTopicMap=new LinkedHashMap<>();
	static Map<String,Long> leastPopularTopicMap=new LinkedHashMap<>();

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
			else if(count<400 && count>=200)mediocreLevelTopicMap.put(topicName, count);
			else leastPopularTopicMap.put(topicName, count);
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
//	public static void main(String[] args) {
//		ServletContext context = null;
//		InputStream path = context.getResourceAsStream("/WEB-INF/pages/login.jsp");
//		System.out.println(path.toString());
//		//init();
//		
//	}
//		
}
