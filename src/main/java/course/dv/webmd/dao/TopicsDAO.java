package course.dv.webmd.dao;

import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

import course.dv.webmd.common.InitiateTransportClient;

/**
 * topics, topcis2, topicRelated
 * @author RAZOR
 *
 */
public class TopicsDAO {

	private static final TransportClient client = InitiateTransportClient.client;

	/**
	 * Fetches all topics from elasticsearch server
	 * and returns Map of topicId,topicName.
	 * @return
	 */
	public static Map<String, String> getAllTopics() {		
		/*QueryBuilder qb = QueryBuilders.termQuery(
			    "topicId",    
			    "drugquestions"   
			);*/
		SearchResponse response = client.prepareSearch("webmd")
				.setTypes("topics2")
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(QueryBuilders.matchAllQuery())
				.addFields("topicId","topicName")
				.setSize(1703)
				.execute()
				.actionGet();
		Map<String, String> result = new HashMap<String, String>();
		for (SearchHit hit : response.getHits()) {
			String topicId = hit.field("topicId").getValue();
			String topicName = hit.field("topicName").getValue();
			result.put(topicId, topicName);
		}
		return result;	
	}

	/**
	 * Finds a topic for a question.
	 * @param questionId
	 * @return
	 */
	public static String getTopicForAQuestion(String questionId) {
		SearchResponse response = client.prepareSearch("webmd")
				.setTypes("relatedTopics2")
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(QueryBuilders.termQuery("questionId", questionId))
				.addFields("topicId")
				.execute()
				.actionGet();
		for (SearchHit hit : response.getHits()) {
			String topicId = hit.field("topicId").getValue();
			String topicName = getTopicNameFromId(topicId);
			return topicId+"-"+topicName;
		}
		return null;
	}
	
	/**
	 * Finds a topic name for a topic id.
	 * @param topicId
	 * @return
	 */
	public static String getTopicNameFromId(String topicId) {
		SearchResponse response = client.prepareSearch("webmd")
				.setTypes("topics2")
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(QueryBuilders.termQuery("topicId", topicId))
				.addFields("topicId","topicName")
				.execute()
				.actionGet();
		for (SearchHit hit : response.getHits()) {
			return hit.field("topicName").getValue();
		}
		return null;
	}

	/*public static void main(String[] args) {
		//getAllTopics();
		//System.out.println(getTopicForAQuestion("11667530"));
	}*/
}
