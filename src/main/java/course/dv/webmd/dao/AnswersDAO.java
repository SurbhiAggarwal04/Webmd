package course.dv.webmd.dao;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

import course.dv.webmd.common.InitiateTransportClient;
import course.dv.webmd.model.Answer;



public class AnswersDAO {

	private static final TransportClient client = InitiateTransportClient.client;

	/**
	 * There are two files on server "answer" and "answers". We are using "answer"
	 * because it has more filtered data with more number of answers present.
	 * 
	 * TODO - METHOD NOT WORKING
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
	
	
//Reference: http://stackoverflow.com/questions/15114481/return-all-records-in-one-query-in-elastic-search
	//For fetching document larger than 10000
/*	public List<Map<String, Object>> getAllDocs(){
		int scrollSize = 1000;
		List<Map<String,Object>> esData = new ArrayList<Map<String,Object>>();
		SearchResponse response = null;
		int i = 0;
		while( response == null || response.getHits().hits().length != 0){
			response = client.prepareSearch(indexName)
					.setTypes(typeName)
					.setQuery(QueryBuilders.matchAllQuery())
					.setSize(scrollSize)
					.setFrom(i * scrollSize)
					.execute()
					.actionGet();
			for(SearchHit hit : response.getHits()){
				esData.add(hit.getSource());
			}
			i++;
		}
		return esData;
	}*/
	
	/**
	 * This method takes a question Id and fetches all answers.
	 * 
	 * @param questionId
	 * @return
	 */
	
	public static Set<Answer> getAnswersForAQuestion(String questionId) {
		SearchResponse response = client.prepareSearch("webmd")
				.setTypes("answer")
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(QueryBuilders.termQuery("questionId", questionId))
				.addFields("answerId",
						   "questionId",
						   "answerQuestionURL",
						   "answerMemberId",
						   "answerContent",
						   "answerPostDate",
						   "answerHelpfulNum",
						   "answerVoteNum")
				.execute()
				.actionGet();
		Set<Answer> result = new TreeSet<Answer>();
		for (SearchHit hit : response.getHits()) {
			Answer a = new Answer();
			a.setAnswerId(hit.field("answerId").getValue());
			a.setQuestionId(hit.field("questionId").getValue());
			a.setAnswerQuestionURL(hit.field("answerQuestionURL").getValue());
			a.setAnswerMemberId(hit.field("answerMemberId").getValue());
			a.setAnswerContent(hit.field("answerContent").getValue());
			a.setAnswerPostDate(hit.field("answerPostDate").getValue());
			a.setAnswerHelpfulNum(hit.field("answerHelpfulNum").getValue());
			a.setAnswerVoteNum(hit.field("answerVoteNum").getValue());
			result.add(a);
		}		
		return result;
	}
	
	/**
	 * Based on the query submitted, this method queries answer content
	 * and fetches top 50 question ids. (Based on elasticsearch scoring)
	 * 
	 * @param query
	 * @return
	 */
	public static Set<String> getQuestionIdsBasedOnQueryingAnswerContent(String query) {
		QueryBuilder qbForAnswers = QueryBuilders.commonTermsQuery("answerContent", query);
		SearchResponse response = client.prepareSearch("webmd")
				.setTypes("answer")
				.setSearchType(SearchType.DFS_QUERY_AND_FETCH)
				.setQuery(qbForAnswers)
				.addFields("questionId")
				.execute()
				.actionGet();
		
		/*QueryBuilder qbForQuestions = QueryBuilders.commonTermsQuery("questionTitle", query);
		SearchResponse questionTitleResponse = client.prepareSearch("webmd")
				.setTypes("questions2")
				.setSearchType(SearchType.DFS_QUERY_AND_FETCH)
				.setQuery(qbForQuestions)
				.addFields("questionId")
				.execute()
				.actionGet();*/
		int count = 0;

		if(response.getHits().getTotalHits() < 15) count = (int) response.getHits().getTotalHits();
		else count = 15;
		
		Set<String> relevantQuestions = new HashSet<String>();
		int tempC = 0;
		for(SearchHit hit : response.getHits()) {
			if(tempC == count) break;
			relevantQuestions.add(hit.field("questionId").getValue().toString());
			tempC++;
		}
		return relevantQuestions;
	}
	
	/*public static void main(String[] args) {
		//getAllAnswers();
		Set<String> a = getQuestionIdsBasedOnQueryingAnswerContent("warnings drug Desoxyn");
		for(String hit : a)
			System.out.println(hit);
		//5049966
		//System.out.println(getAnswersForAQuestion("5049966"));
	}*/
}
