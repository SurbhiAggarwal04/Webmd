package course.dv.webmd.service;

import static course.dv.webmd.dao.TopicQuestionAnswerMemberDAO.*;
import static course.dv.webmd.dao.MembersDAO.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.search.SearchHit;

import course.dv.webmd.model.Member;

public class TopRatedMembersService {

	/**
	 * Based on given topic, this method fetches all questions for the topic.
	 * For each of these questions, this method finds all the answers and members
	 * and saves it in a hash map. For repeating members, it adds the numVoteCount
	 * The hash map is sorted by values in descending order and then returned.
	 * 
	 * @param topic
	 * @return Map<String, Integer>
	 */
	public static Map<String, Integer> createMemberDictionaryFromQuestionAnswers(String topic) {
		Map<String, Integer> memberHelpfulVoteCount = new HashMap<String, Integer>();
		for(String questionId : getAllQuestionsForTopic(topic)) {
			for(SearchHit answer : getAllAnswersForAQuestions(questionId)) {
				String memberId = answer.field("answerMemberId").getValue();
				int ansVoteNum = 0;
				try { 
					ansVoteNum = Integer.parseInt(answer.field("answerVoteNum").getValue()); 
				} catch (Exception e) 
				{ 
					System.out.println("exception"); 
				}
				if(memberHelpfulVoteCount.get(memberId) == null) {
					memberHelpfulVoteCount.put(memberId, ansVoteNum);
				} else {
					int count = memberHelpfulVoteCount.get(memberId);
					memberHelpfulVoteCount.put(memberId, count+ansVoteNum);
				}
			}
		}
		return sortByValue(memberHelpfulVoteCount);
	}

	/**
	 * This method sorts Map<String, Integer> by value in descending order.
	 * @param unsortMap
	 * @return Map<String, Integer>
	 */
	private static Map<String, Integer> sortByValue(Map<String, Integer> unsortMap) {
		List<Map.Entry<String, Integer>> list =
				new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1,
					Map.Entry<String, Integer> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});
		Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
		for (Map.Entry<String, Integer> entry : list) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}

	public static Map<Member, Integer> getTopRatedMembersData(Map<String, Integer> memberIdAndCount) {
		Map<Member, Integer> topRatedMemberData = new HashMap<Member, Integer>();
		int count = 0;
		for(String memberId : memberIdAndCount.keySet()) {
			if(count == 10) break;
			topRatedMemberData.put(getMemberForMemberId(memberId) , memberIdAndCount.get(memberId));
			count ++;
		}
		return topRatedMemberData;
	}
	/*public static void main(String[] args) {
		Map<String, Integer> p = createMemberDictionaryFromQuestionAnswers("periodquestions");
		Object a = getTopRatedMembersData(p);
		System.out.println(a);
	}*/
}
