package course.dv.webmd.dao;

import java.util.HashSet;
import java.util.Set;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

import course.dv.webmd.common.InitiateTransportClient;
import course.dv.webmd.common.WebmdJavaUtils;
import course.dv.webmd.model.ConceptMapObject;

public class SearchQueryDAO {

	private static TransportClient client = InitiateTransportClient.client;
	
	/**
	 * This method takes searchQuery that user enter and fetches questions based on elastic
	 * server scoring. All the questions are stored in form of ConceptMapObject which has
	 * questionId, questionTitle and keyWords.
	 * 
	 * For keywords, I use util methods present in WebmdJavaUtils in common package.
	 * @param searchQuery
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public static Set<ConceptMapObject> getSearchResultForAQuery(String searchQuery, String filePath) throws Exception {
		QueryBuilder qb = QueryBuilders.commonTermsQuery("questionTitle", searchQuery);
		
		SearchResponse response = client.prepareSearch("webmd")
				.setTypes("questions2")
				.setSearchType(SearchType.DFS_QUERY_AND_FETCH)
				.setQuery(qb)
				.addField("questionId")
				.addField("questionTitle")
				.execute()
				.actionGet();

		Set<ConceptMapObject> result = new HashSet<ConceptMapObject>();
		for (SearchHit hit : response.getHits()) {
			ConceptMapObject obj = new ConceptMapObject();
			obj.setQuestionId(hit.field("questionId").getValue());
			obj.setQuestionTitle(hit.field("questionTitle").getValue());

			WebmdJavaUtils.populateStopWords(filePath);
			String rawKeywords = WebmdJavaUtils.removeStopWords(obj.getQuestionTitle());

			rawKeywords = rawKeywords.replace("!", "").replace("\"", "").replace("\'", ""); //add more special symbols if necessary

			String[] rawKeywordsArray = rawKeywords.split(" ");
			StringBuilder keywords = new StringBuilder();

			int count = 1;
			for(String each : rawKeywordsArray) {
				if(count == rawKeywordsArray.length) {
					keywords.append(each);
				} else {
					keywords.append(each + ",");
				}
				count++;
			}
			obj.setKeywords(keywords.toString());
			
			result.add(obj);
		}
		return result;
	}
}