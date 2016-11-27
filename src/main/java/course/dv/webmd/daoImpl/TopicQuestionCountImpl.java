package course.dv.webmd.daoImpl;

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

public class TopicQuestionCountImpl {
	private static final TransportClient client = InitiateTransportClient.client;

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
	
	public static TreeMap<String,Long> getAllTopicQuestionsCount(Map<String,String> topicMap)
	{
		Map<String,Long> topicCountMap=new HashMap<>();
        TreeMap<String,Long> sortedTopicCount=new TreeMap<>(new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				return topicCountMap.get(o1).compareTo(topicCountMap.get(o2));
			}
		});
		
		Iterator<Entry<String,String>> it=topicMap.entrySet().iterator();
		while(it.hasNext())
		{
			Entry<String, String> topic=it.next();
			String topicId=topic.getKey();
			String topicName=topic.getValue();
			Long count=getQuestionCount(topicId);
			topicCountMap.put(topicName, count);
			
		}
		sortedTopicCount.putAll(topicCountMap);
		return sortedTopicCount;
	}
}
