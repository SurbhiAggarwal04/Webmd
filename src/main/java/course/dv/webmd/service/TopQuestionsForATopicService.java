package course.dv.webmd.service;

import static course.dv.webmd.dao.TopicQuestionAnswerMemberDAO.*;
import static course.dv.webmd.common.SortMapByValue.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
public class TopQuestionsForATopicService {

	/**
	 * Given a topic, this method fetches all question ids from relatedTopics2
	 * (elastic search) and for each question id, it fetches answer count and 
	 * stores it in a HashMap<QuestionId, NumOfAnswersCount>.
	 * Returns the HashMap after sorting it by value of HashMap in descending order.
	 *  
	 * @param topic
	 * @return HashMap<QuestionId, NumOfAnswersCount>
	 */
	public static Map<String, Integer> getTopFifteenQuestionsForATopic(String topic) {
		Set<String> allQuestions = getAllQuestionsForTopic(topic);
		Map<String, Integer> questionCount = new HashMap<String, Integer>();
		for(String questionId : allQuestions) {
			questionCount.put(questionId, Integer.parseInt(""+getAllAnswersForAQuestion(questionId).getTotalHits()));
		}
		questionCount = sortByValue(questionCount);
		HashMap<String, Integer> top15Questions = new HashMap<String, Integer>();
		int count=0;
		for(String questionId : questionCount.keySet()) {
			if(count == 15) break;
			top15Questions.put(questionId,questionCount.get(questionId));
			count++;
		}
		return top15Questions;
	}

	public static void main(String[] args) {
		Object a = getTopFifteenQuestionsForATopic("periodquestions");
		System.out.println(a);
	}
}