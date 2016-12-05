package course.dv.webmd.controller;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import course.dv.webmd.common.GenerateJsonFile;
import course.dv.webmd.model.Answer;
import course.dv.webmd.model.ConceptMapObject;
import course.dv.webmd.model.Member;
import course.dv.webmd.service.AnswerService;
import course.dv.webmd.service.RecommendTopicsBasedOnClikedQuestionService;
import course.dv.webmd.service.SearchTopQuestionsKeywordsForAQueryService;
import course.dv.webmd.service.TopQuestionsForATopicService;
import course.dv.webmd.service.TopRatedMembersService;

@Controller
public class MainController {
	@Autowired
	TopQuestionsForATopicService topQuestionsForATopicService;

	@Autowired
	AnswerService answerService;

	@Autowired
	RecommendTopicsBasedOnClikedQuestionService recommendTopicsBasedOnClikedQuestionService;

	@Autowired
	SearchTopQuestionsKeywordsForAQueryService searchTopQuestionsKeywordsForAQueryService;

	String filepath;

	@RequestMapping(value = { "/", "/welcome**", "/logout" }, method = RequestMethod.GET)
	public ModelAndView defaultPage(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		try {
			filepath = request.getSession().getServletContext().getRealPath("/resources/csv");
			System.out.println(filepath);

			/*
			 * Uncomment to generate csvs' dynamically
			 */
			/*
			 * System.out.println(filepath); PopularTopicsService.init(); Long
			 * mostPopularSize=(long)
			 * PopularTopicsService.getMostPopularTopics().size(); Long
			 * mediocreSize=(long)
			 * PopularTopicsService.getMediocreTopics().size(); Long
			 * leastPopularSize=(long)
			 * PopularTopicsService.getLeastPopularTopics().size();
			 * Map<String,Long> topicMap=new HashMap<>();
			 * topicMap.put("Most Popular Topics", mostPopularSize);
			 * topicMap.put("Mediocre Topics", mediocreSize);
			 * topicMap.put("Least Popular Topics", leastPopularSize);
			 * GenerateCSVFile.getCsvForTopicPopularityFromHashMap(topicMap,
			 * filepath,"topicPopularity.csv");
			 * GenerateCSVFile.getCsvFromHashMap(PopularTopicsService.
			 * getMediocreTopics(), filepath,"MediocrePopular.csv");
			 * GenerateCSVFile.getCsvFromHashMap(PopularTopicsService.
			 * getMostPopularTopics(), filepath,"MostPopular.csv");
			 * GenerateCSVFile.getCsvFromHashMap(PopularTopicsService.
			 * getLeastPopularTopics(), filepath,"LeastPopular.csv");
			 */

			model.addObject("csv", "topicPopularity.csv");
			model.addObject("pageTitle", "Topics by Popularity");
			model.setViewName("welcome");
		} catch (Exception e) {
			model.setViewName("error");
		}
		return model;

	}

	@RequestMapping(value = "topicsByPopularity", method = RequestMethod.GET)
	public ModelAndView topicsByPopularity() {
		ModelAndView model = new ModelAndView();
		try {
			model.addObject("csv", "topicPopularity.csv");
			model.addObject("pageTitle", "Topics by Popularity");
			model.setViewName("welcome");
		} catch (Exception e) {
			model.setViewName("error");
		}
		return model;
	}

	@RequestMapping(value = "memberTopicsByPopularity", method = RequestMethod.GET)
	public ModelAndView memberTopicsByPopularity() {
		ModelAndView model = new ModelAndView();
		try {
			model.addObject("csv", "topicPopularity.csv");
			model.addObject("pageTitle", "Topics by Popularity");
			model.setViewName("memberWelcome");
		} catch (Exception e) {
			model.setViewName("error");
		}
		return model;
	}

	@RequestMapping(value = "mostPopularTopics", method = RequestMethod.GET)
	public ModelAndView mostPopularTopics() {
		ModelAndView model = new ModelAndView();
		try {
			model.addObject("csv", "MostPopular.csv");
			model.addObject("questionMap", null);
			model.addObject("id", null);
			model.addObject("pageTitle", "Most Popular Topics");
			model.setViewName("popularTopics");
		} catch (Exception e) {
			model.setViewName("error");
		}
		return model;
	}

	@RequestMapping(value = "leastPopularTopics", method = RequestMethod.GET)
	public ModelAndView leastPopularTopics() {
		ModelAndView model = new ModelAndView();
		try {
			model.addObject("csv", "LeastPopular.csv");
			model.addObject("questionMap", null);
			model.addObject("id", null);
			model.addObject("pageTitle", "Least Popular Topics");
			model.setViewName("popularTopics");
		} catch (Exception e) {
			model.setViewName("error");
		}
		return model;
	}

	@RequestMapping(value = "mediocrePopularTopics", method = RequestMethod.GET)
	public ModelAndView mediocrePopularTopics() {
		ModelAndView model = new ModelAndView();
		try {
			model.addObject("csv", "MediocrePopular.csv");
			model.addObject("questionMap", null);
			model.addObject("id", null);
			model.addObject("pageTitle", "Mediocre Popular Topics");
			model.setViewName("popularTopics");
		} catch (Exception e) {
			model.setViewName("error");
		}
		return model;
	}

	@RequestMapping(value = "memberMostPopularTopics", method = RequestMethod.GET)
	public ModelAndView memberMostPopularTopics() {
		ModelAndView model = new ModelAndView();
		try {
			model.addObject("csv", "MostPopular.csv");
			model.addObject("url", "memberMostPopularTopics");
			model.addObject("pageTitle", "Most Popular Topics");
			model.setViewName("memberPopularTopics");
		} catch (Exception e) {
			model.setViewName("error");
		}
		return model;
	}

	@RequestMapping(value = "memberLeastPopularTopics", method = RequestMethod.GET)
	public ModelAndView memberLeastPopularTopics() {
		ModelAndView model = new ModelAndView();
		try {
			model.addObject("csv", "LeastPopular.csv");
			model.addObject("pageTitle", "Least Popular Topics");
			model.addObject("url", "memberLeastPopularTopics");
			model.setViewName("memberPopularTopics");
		} catch (Exception e) {
			model.setViewName("error");
		}
		return model;
	}

	@RequestMapping(value = "memberMediocrePopularTopics", method = RequestMethod.GET)
	public ModelAndView memberMediocrePopularTopics() {
		ModelAndView model = new ModelAndView();
		try {
			model.addObject("csv", "MediocrePopular.csv");
			model.addObject("pageTitle", "Mediocre Popular Topics");
			model.addObject("url", "memberMediocrePopularTopics");
			model.setViewName("memberPopularTopics");
		} catch (Exception e) {
			model.setViewName("error");
		}
		return model;
	}

	@RequestMapping(value = "getQuestions", method = RequestMethod.GET)
	public ModelAndView getQuestions(@RequestParam("name") String name, @RequestParam("id") String id,
			@RequestParam("csv") String csv) {
		ModelAndView model = new ModelAndView();
		try {
			Map<String, String> questionMap = topQuestionsForATopicService.getTopFifteenQuestionsForATopic(id);
			model.addObject("questionMap", questionMap);
			model.addObject("csv", csv);
			model.addObject("name", name);
			model.addObject("id", id);
			String pageTitle = "";
			if (csv.contains("Most"))
				pageTitle = "Most Popular Topics";
			if (csv.contains("Least"))
				pageTitle = "Least Popular Topics";
			if (csv.contains("Mediocre"))
				pageTitle = "Mediocre Popular Topics";
			model.addObject("pageTitle", pageTitle);
			model.setViewName("popularTopics");
		} catch (Exception e) {
			model.setViewName("error");
		}
		return model;
	}

	@RequestMapping(value = "getAnswers", method = RequestMethod.GET)
	public ModelAndView getAnswers(HttpServletRequest request, @RequestParam("name") String name,
			@RequestParam("id") String id, @RequestParam("topicId") String topicId,
			@RequestParam("topicName") String topicName, @RequestParam("csv") String csv) throws IOException {
		ModelAndView model = new ModelAndView();
		try {
			filepath = request.getSession().getServletContext().getRealPath("/resources/json");
			Map<String, Integer> map = recommendTopicsBasedOnClikedQuestionService
					.getTopicsBasedOnClickedQuestions(name, filepath);
			String json = GenerateJsonFile.generateJsonForQuestions(map);
			Set<Answer> answerSet = answerService.getAnswersForAQuestionId(id);
			model.addObject("answerSet", answerSet);
			model.addObject("topicId", topicId);
			model.addObject("topicName", topicName);
			model.addObject("name", name);
			model.addObject("csv", csv);
			model.addObject("pageTitle", name + " Answers");
			model.addObject("json", json);
			model.setViewName("topicAnswers");
		} catch (Exception e) {
			model.setViewName("error");
		}
		return model;

	}

	@RequestMapping(value = "membersByTopics", method = RequestMethod.GET)
	public ModelAndView membersByTopics(HttpServletRequest request, @RequestParam("url") String url,
			@RequestParam("id") String topic, @RequestParam("name") String topicName) {
		ModelAndView model = new ModelAndView();
		try {
			filepath = request.getSession().getServletContext().getRealPath("/resources/json");
			Map<String, Integer> p = TopRatedMembersService.createMemberDictionaryFromQuestionAnswers(topic);
			Map<Member, Integer> map = TopRatedMembersService.getTopRatedMembersData(p);
			GenerateJsonFile.generateJsonFile(map, filepath);
			model.addObject("pageTitle", "Topic: " + topicName);
			model.addObject("url", url);
			model.setViewName("membersByTopics");
		} catch (Exception e) {
			model.setViewName("error");
		}
		return model;
	}

	@RequestMapping(value = "test", method = RequestMethod.GET)
	public ModelAndView test(HttpServletRequest request) throws IOException {
		ModelAndView model = new ModelAndView();
		try {
			filepath = request.getSession().getServletContext().getRealPath("/resources/json");
			Map<String, Integer> map = recommendTopicsBasedOnClikedQuestionService
					.getTopicsBasedOnClickedQuestions("How do I take Celebrex", filepath);
			String json = GenerateJsonFile.generateJsonForQuestions(map);
			model.addObject("json", json);
			model.setViewName("topicAnswers");
		} catch (Exception e) {
			model.setViewName("error");
		}
		return model;
	}

	@RequestMapping(value = "searchKeyword", method = RequestMethod.GET)
	public ModelAndView searchQuestion(HttpServletRequest request, @RequestParam("searchKeyword") String searchKeyword)
			throws Exception {
		ModelAndView model = new ModelAndView();
		try {
			filepath = request.getSession().getServletContext().getRealPath("/resources/json");
			Set<ConceptMapObject> setOfConceptMapObjects = searchTopQuestionsKeywordsForAQueryService
					.getTopQuestionForQuery(searchKeyword, filepath);
			String jsonString = GenerateJsonFile.generateJsonForConceptMap(setOfConceptMapObjects);
			model.addObject("json", jsonString);
			model.setViewName("search");
			model.addObject("setOfConceptMapObjects", setOfConceptMapObjects);
			model.addObject("searchKeyword", searchKeyword);
			if(setOfConceptMapObjects==null || setOfConceptMapObjects.size()==0)
				model.addObject("errorMessage", "No Results found!");
		} catch (Exception e) {
			model.setViewName("error");
		}
		return model;
	}

	@RequestMapping(value = "search", method = RequestMethod.GET)
	public ModelAndView search(HttpServletRequest request, String searchKeywords) throws Exception {
		ModelAndView model = new ModelAndView();
		try {
			model.addObject("pageTitle", "Search");
			model.setViewName("search");
			model.addObject("setOfConceptMapObjects", null);
			model.addObject("errorMessage", null);
		} catch (Exception e) {
			model.setViewName("error");
		}
		return model;
	}

	@RequestMapping(value = "getQuestionAnswers", method = RequestMethod.GET)
	public ModelAndView getAnswers(HttpServletRequest request, @RequestParam("name") String name,
			@RequestParam("id") String id, @RequestParam("searchKeyword") String searchKeyword) throws IOException {
		ModelAndView model = new ModelAndView();
		try {
			filepath = request.getSession().getServletContext().getRealPath("/resources/json");
			Map<String, Integer> map = recommendTopicsBasedOnClikedQuestionService
					.getTopicsBasedOnClickedQuestions(name, filepath);
			String json = GenerateJsonFile.generateJsonForQuestions(map);
			System.out.println(json);
			Set<Answer> answerSet = answerService.getAnswersForAQuestionId(id);
			model.addObject("answerSet", answerSet);
			model.addObject("searchKeyword", searchKeyword);
			model.addObject("name", name);
			model.addObject("pageTitle", name + " Answers");
			model.addObject("json", json);
			model.setViewName("questionAnswers");
		} catch (Exception e) {
			model.setViewName("error");
		}
		return model;

	}

}