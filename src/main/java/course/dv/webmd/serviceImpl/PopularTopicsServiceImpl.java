package course.dv.webmd.serviceImpl;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import course.dv.webmd.daoImpl.TopicQuestionCountImpl;
import course.dv.webmd.daoImpl.TopicsDAOImpl;

public class PopularTopicsServiceImpl {	
	static TreeMap<String,Long> topicCountMap=new TreeMap<>();
	static Map<String,Long> mostPopularMap=new LinkedHashMap<>();
	static Map<String,Long> mediocreLevelTopicMap=new LinkedHashMap<>();
	static Map<String,Long> leastPopularTopicMap=new LinkedHashMap<>();
	

	public static void init()
	{
		Map<String,String> topicMap=TopicsDAOImpl.getAllTopics();
		topicCountMap=TopicQuestionCountImpl.getAllTopicQuestionsCount(topicMap);
		
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
//		init();
//	}
//	
//	
}
