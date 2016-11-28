package course.dv.webmd.dao;

import java.util.HashSet;
import java.util.Set;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

import course.dv.webmd.common.InitiateTransportClient;

public class TopicQuestionAnswerMemberDAO {

	private static final TransportClient client = InitiateTransportClient.client;
	
	/**
	 * This method gives question ids for each topic from
	 * relatedTopics2 document from elasticsearch.
	 * 
	 * @param topic
	 * @return Set<String>
	 */
	public static Set<String> getAllQuestionsForTopic(String topic) {
		SearchResponse response = client.prepareSearch("webmd")
				.setTypes("relatedTopics2")
				.setSearchType(SearchType.DFS_QUERY_AND_FETCH)
				.setQuery(QueryBuilders.termQuery("topicId", topic))
				.addField("questionId")
				.execute()
				.actionGet();
		Set<String> result = new HashSet<String>();
		for (SearchHit hit : response.getHits()) {
			String questionId = hit.field("questionId").getValue();
			result.add(questionId);
		}
		return result;
	}
	
	/**
	 * This method fetches all answers for given question id.
	 * @param questionId
	 * @return
	 */
	public static SearchHits getAllAnswersForAQuestion(String questionId) {
		SearchResponse response = client.prepareSearch("webmd")
				.setTypes("answer")
				.setSearchType(SearchType.DFS_QUERY_AND_FETCH)
				.setQuery(QueryBuilders.termQuery("questionId", questionId))
				.addField("answerMemberId")
				.addField("answerVoteNum")
				.execute()
				.actionGet();
		return response.getHits();
	}
	
	/*public static void main(String[] args) {
		getAllQuestionsForTopic("drugquestions");
	}*/
}
