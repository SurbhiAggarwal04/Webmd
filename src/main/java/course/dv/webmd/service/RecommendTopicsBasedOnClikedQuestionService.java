package course.dv.webmd.service;

import static course.dv.webmd.dao.AnswersDAO.getQuestionIdsBasedOnQueryingAnswerContent;
import static course.dv.webmd.dao.TopicQuestionCountDAO.getQuestionCount;
import static course.dv.webmd.dao.TopicsDAO.getTopicForAQuestion;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import course.dv.webmd.common.WebmdJavaUtils;
/**
 * This class will be used when a user clicks on a question from the list of questions
 * listed after user selects a topic from Topic bubble.
 * 
 * @author RAZOR
 *
 */
@Service("recommendTopicsBasedOnClikedQuestionService")
public class RecommendTopicsBasedOnClikedQuestionService {
	
	/**
	 * This method takes question title, removes stop words and fetches recomendedQuestions.
	 * The method used here is present in AnswerDAO.java. We use the keywords from question title
	 * and run it against answerContent present in 'answer' document of elasticsearch.
	 * 
	 * TODO - run the keywords against questionTitle to find out recommended questions and merge it
	 * with the question recommended using answerContent.
	 * 
	 * @param questionTitle
	 * @return
	 * @throws IOException
	 */
	public  Map<String, Integer> getTopicsBasedOnClickedQuestions(String questionTitle, String filepath) throws IOException {
		
		WebmdJavaUtils.populateStopWords(filepath);
		String keyWords = WebmdJavaUtils.removeStopWords(questionTitle);
		
		Set<String> recomendedQuestions = getQuestionIdsBasedOnQueryingAnswerContent(keyWords);
		Set<String> recomendedTopics = new HashSet<String>();
		for(String questionId : recomendedQuestions) {
			String recommendedTopic=getTopicForAQuestion(questionId);
			if(recommendedTopic!=null)
			recomendedTopics.add(recommendedTopic);
		}
		Map<String, Integer> recomendedTopicsQuestionCount = new HashMap<String, Integer>();
		for(String topic : recomendedTopics) {
			System.out.println(topic);
			String idName[]=topic.split("-");
			String topicId=idName[0];
			if(topicId != null && !topicId.equals("null"))
				recomendedTopicsQuestionCount.put(idName[1], Integer.parseInt(""+getQuestionCount(topicId)));
		}
		return recomendedTopicsQuestionCount;
	}
	
	/*public static void main(String[] args) throws IOException {
		getTopicsBasedOnClickedQuestions("How do I take Celebrex",);
		
	}*/
}
