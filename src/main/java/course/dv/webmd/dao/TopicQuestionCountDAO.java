package course.dv.webmd.dao;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;

import course.dv.webmd.common.InitiateTransportClient;

public class TopicQuestionCountDAO {
	private static final TransportClient client = InitiateTransportClient.client;

	/**
	 * Returns number of questions for given topic.
	 * @param topicId
	 * @return
	 */
	public static Long getQuestionCount(String topicId)
	{
		SearchResponse response = client.prepareSearch("webmd")
				.setTypes("questions2")
				.setSearchType(SearchType.DFS_QUERY_AND_FETCH)
				.setQuery(QueryBuilders.termQuery("questionTopicId", topicId))
				.execute()
				.actionGet();
		return response.getHits().getTotalHits();
	}
	
	/**
	 * Uses the HashMap of TopicDAO getAllTopics() and returns count of questions 
	 * for each topic. Return Map<topicId, countOfQuestions>
	 * @param topicMap
	 * @return
	 */
	public static TreeMap<String,Long> getAllTopicQuestionsCount(Map<String,String> topicMap)
	{
		Map<String,Long> topicCountMap=new HashMap<>();
        TreeMap<String,Long> sortedTopicCount=new TreeMap<>(new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				if(topicCountMap.get(o2)-topicCountMap.get(o1)>0)return 1;
				else return -1;
			}
		});
		
		Iterator<Entry<String,String>> it=topicMap.entrySet().iterator();
		while(it.hasNext())
		{
			Entry<String, String> topic=it.next();
			String topicId=topic.getKey();
			String topicName=topic.getValue();
			Long count=getQuestionCount(topicId);
			topicCountMap.put(topicId+"-"+topicName, count);
			
		}
		sortedTopicCount.putAll(topicCountMap);
		return sortedTopicCount;
	}
}
