package course.dv.webmd.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.lucene.search.TermQuery;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

import com.cedarsoftware.util.io.JsonWriter;

import course.dv.webmd.common.InitiateTransportClient;

public class TopicsDAO {

	private static final TransportClient client = InitiateTransportClient.client;

	public static Map<String, String> getAllTopics() {
		SearchResponse response = client.prepareSearch("webmd")
				.setTypes("topics")
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(QueryBuilders.matchAllQuery())
				.addFields("topicId","topicName")
				.setSize(1703)
				.execute()
				.actionGet();

		Map<String, String> result = new HashMap<String, String>();
		SearchHits a = response.getHits();
		for (SearchHit hit : response.getHits()) {
			String topicId = hit.field("topicId").getValue();
			String topicName = hit.field("topicName").getValue();
			result.put(topicId, topicName);
		}
		return result;	
	}
}
