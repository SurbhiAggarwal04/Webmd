package course.dv.webmd.dao;

import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

import course.dv.webmd.common.InitiateTransportClient;

public class AnswersDAO {

	private static final TransportClient client = InitiateTransportClient.client;

	/**
	 * There are two files on server "answer" and "answers". We are using "answer"
	 * because it has more filtered data with more number of answers present.
	 * 
	 * @return
	 */
	public static Map<String, String> getAllAnswers() {

		SearchResponse response = client.prepareSearch("webmd")
				.setTypes("answer")
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(QueryBuilders.matchAllQuery())
				//.setQuery(qb)
				.addFields("topicId","topicName")
				.setSize(65000)
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
	
	
	public static void main(String[] args) {
		getAllAnswers();
	}
}
