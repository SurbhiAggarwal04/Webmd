package course.dv.webmd.service;

import static course.dv.webmd.dao.AnswersDAO.getQuestionIdsBasedOnQueryingAnswerContent;
import static course.dv.webmd.dao.TopicsDAO.getTopicForAQuestion;
import static course.dv.webmd.dao.TopicQuestionCountDAO.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/**
 * This class will be used when a user clicks on a question from the list of questions
 * listed after user selects a topic from Topic bubble.
 * 
 * @author RAZOR
 *
 */
public class RecommendTopicsBasedOnClikedQuestionService {
	
	private static Set<String> stopWords = new HashSet<String>();
	
	private static void getStopWords(String filepath) throws IOException
	{
		File file = null;
		FileReader fr = null;
		String currentLine = "";
		file = new File(filepath, "stopwordslist.txt");
		fr = new FileReader(file.getAbsoluteFile());
		
        BufferedReader br= new BufferedReader(fr);
        while ((currentLine = br.readLine()) != null){
        	stopWords.add(currentLine);
        }
        br.close();
        fr.close();
	}
	
	private static String removeStopWords(String question)
	{	
		for(String stopWord : stopWords){
		    question = question.replaceAll(" "+ stopWord + " ", " ");
		}
		return question;
	}
	
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
	public static Map<String, Integer> getTopicsBasedOnClickedQuestions(String questionTitle, String filepath) throws IOException {
		//initial set up
		getStopWords(filepath);
		//removeStopWords
		String keyWords = removeStopWords(questionTitle);
		Set<String> recomendedQuestions = getQuestionIdsBasedOnQueryingAnswerContent(keyWords);
		Set<String> recomendedTopics = new HashSet<String>();
		for(String questionId : recomendedQuestions) {
			recomendedTopics.add(getTopicForAQuestion(questionId));
		}
		Map<String, Integer> recomendedTopicsQuestionCount = new HashMap<String, Integer>();
		for(String topicId : recomendedTopics) {
			if(topicId != null && !topicId.equals("null"))
				recomendedTopicsQuestionCount.put(topicId, Integer.parseInt(""+getQuestionCount(topicId)));
		}
		return recomendedTopicsQuestionCount;
	}
	
	/*public static void main(String[] args) throws IOException {
		getTopicsBasedOnClickedQuestions("How do I take Celebrex",);
		
	}*/
}
