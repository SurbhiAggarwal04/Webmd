package course.dv.webmd.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import course.dv.webmd.dao.SearchQueryDAO;
import course.dv.webmd.model.ConceptMapObject;

@Service("SearchTopQuestionsKeywordsForAQueryService")
public class SearchTopQuestionsKeywordsForAQueryService {
	
	public Set<ConceptMapObject> getTopQuestionForQuery(String query, String filePath) throws Exception {
		return SearchQueryDAO.getSearchResultForAQuery(query, filePath);
	}
}
