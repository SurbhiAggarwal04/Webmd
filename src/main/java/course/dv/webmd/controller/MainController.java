package course.dv.webmd.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import course.dv.webmd.service.TopQuestionsForATopicService;

@Controller
public class MainController {
	@Autowired
	TopQuestionsForATopicService topQuestionsForATopicService;
	
	String filepath;

	@RequestMapping(value = { "/", "/welcome**","/logout" }, method = RequestMethod.GET)
	public ModelAndView defaultPage(HttpServletRequest request) {
		filepath=request.getSession().getServletContext().getRealPath("/resources/csv");
		System.out.println(filepath);
		
		/*
		 * Uncomment to generate csvs' dynamically 
		 */
//		PopularTopicsService.init();
//		Long mostPopularSize=(long) PopularTopicsService.getMostPopularTopics().size();
//		Long mediocreSize=(long) PopularTopicsService.getMediocreTopics().size();
//		Long leastPopularSize=(long) PopularTopicsService.getLeastPopularTopics().size();
//		Map<String,Long> topicMap=new HashMap<>();
//		topicMap.put("Most Popular Topics", mostPopularSize);
//		topicMap.put("Mediocre Topics", mediocreSize);
//		topicMap.put("Least Popular Topics", leastPopularSize);
//		GenerateCSVFile.getCsvForTopicPopularityFromHashMap(topicMap, filepath,"topicPopularity.csv");
//		GenerateCSVFile.getCsvFromHashMap(PopularTopicsService.getMediocreTopics(), filepath,"MediocrePopular.csv");
//		GenerateCSVFile.getCsvFromHashMap(PopularTopicsService.getMostPopularTopics(), filepath,"MostPopular.csv");
//		GenerateCSVFile.getCsvFromHashMap(PopularTopicsService.getLeastPopularTopics(), filepath,"LeastPopular.csv");

		ModelAndView model = new ModelAndView();
		model.addObject("csv", "topicPopularity.csv");
		model.addObject("pageTitle", "Welcome");		
		model.setViewName("welcome");
		return model;

	}
	@RequestMapping(value = "topicsByPopularity", method = RequestMethod.GET)
	public ModelAndView topicsByPopularity() {
		ModelAndView model = new ModelAndView();
		model.addObject("csv", "topicPopularity.csv");
		model.addObject("pageTitle", "Topics by Popularity");		
		model.setViewName("welcome");
		return model;

	}
	
	@RequestMapping(value = "mostPopularTopics", method = RequestMethod.GET)
	public ModelAndView mostPopularTopics() {
		ModelAndView model = new ModelAndView();
		model.addObject("csv", "MostPopular.csv");
		model.addObject("pageTitle", "Most Popular Topics");		
		model.setViewName("popularTopics");
		return model;

	}
	
	@RequestMapping(value = "leastPopularTopics", method = RequestMethod.GET)
	public ModelAndView leastPopularTopics() {
		ModelAndView model = new ModelAndView();
		model.addObject("csv", "LeastPopular.csv");
		model.addObject("pageTitle", "Least Popular Topics");		
		model.setViewName("popularTopics");
		return model;

	}

	
	@RequestMapping(value = "mediocrePopularTopics", method = RequestMethod.GET)
	public ModelAndView mediocrePopularTopics() {
		ModelAndView model = new ModelAndView();
		model.addObject("csv", "MediocrePopular.csv");
		model.addObject("pageTitle", "Mediocre Popular Topics");		
		model.setViewName("popularTopics");
		return model;

	}
	
	@RequestMapping(value = "getQuestions", method = RequestMethod.GET)
	public ModelAndView getQuestions(@RequestParam("id") String id) {
		ModelAndView model = new ModelAndView();
		Map<String, String> questionMap=topQuestionsForATopicService.getTopFifteenQuestionsForATopic(id);
		model.addObject("questionMap", questionMap);
		model.addObject("pageTitle", "Questions");		
		model.setViewName("topicQuestions");
		return model;

	}


}
