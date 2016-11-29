package course.dv.webmd.service;

import static course.dv.webmd.dao.TopicQuestionAnswerMemberDAO.*;
import static course.dv.webmd.common.SortMapByValue.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
public class TopQuestionsForATopicService {

	/**
	 * Given a topic, this method fetches all question ids and question title 
	 * from questions2 (elasticsearch) and returns HashMap<QuestionId, QuestionTitle>.
	 * 
	 * @param topic
	 * @return HashMap<QuestionId, NumOfAnswersCount>
	 */
	public static Map<String, String> getTopFifteenQuestionsForATopic(String topic) {
		Map<String, String> allQuestionAndTitles = getAllQuestionsForTopic(topic);
		Set<String> allQuestions = allQuestionAndTitles.keySet();
		Map<String, Integer> questionCount = new HashMap<String, Integer>();
		for(String questionId : allQuestions) {
			questionCount.put(questionId, Integer.parseInt(""+getAllAnswersForAQuestion(questionId).getTotalHits()));
		}
		questionCount = sortByValue(questionCount);
		HashMap<String, String> top15Questions = new HashMap<String, String>();
		int count=0;
		for(String questionId : questionCount.keySet()) {
			if(count == 15) break;
			top15Questions.put(questionId,allQuestionAndTitles.get(questionId));
			count++;
		}
		return top15Questions;
	}

	public static void main(String[] args) {
		Object a = getTopFifteenQuestionsForATopic("periodquestions");
		System.out.println(a);
	}
}