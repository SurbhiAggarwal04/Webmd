package course.dv.webmd.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import course.dv.webmd.common.GenerateCSVFile;
import course.dv.webmd.service.PopularTopicsService;

@Controller
public class MainController {
	String filepath;

	@RequestMapping(value = { "/", "/welcome**","/logout" }, method = RequestMethod.GET)
	public ModelAndView defaultPage(HttpServletRequest request) {
//		filepath=request.getSession().getServletContext().getRealPath("/WEB-INF/csv");
//		System.out.println(filepath);
//		PopularTopicsService.init();
//		Long mostPopularSize=(long) PopularTopicsService.getMostPopularTopics().size();
//		Long mediocreSize=(long) PopularTopicsService.getMediocreTopics().size();
//		Long leastPopularSize=(long) PopularTopicsService.getLeastPopularTopics().size();
//		Map<String,Long> topicMap=new HashMap<>();
//		topicMap.put("Most Popular Topics", mostPopularSize);
//		topicMap.put("Mediocre Topics", mediocreSize);
//		topicMap.put("Least Popular Topics", leastPopularSize);
//		GenerateCSVFile.getCsvFromHashMap(topicMap, filepath,"topicPopularity.csv");
//		GenerateCSVFile.getCsvFromHashMap(PopularTopicsService.getMediocreTopics(), filepath,"MediocrePopular.csv");
//		GenerateCSVFile.getCsvFromHashMap(PopularTopicsService.getMostPopularTopics(), filepath,"MostPopular.csv");
//		GenerateCSVFile.getCsvFromHashMap(PopularTopicsService.getLeastPopularTopics(), filepath,"LeastPopular.csv");

		ModelAndView model = new ModelAndView();
		
		model.setViewName("welcome");
		return model;

	}
	@RequestMapping(value = "topicsByPopularity", method = RequestMethod.GET)
	public ModelAndView topicsByPopularity() {
		ModelAndView model = new ModelAndView();
		model.setViewName("topicsByPopularity");
		return model;

	}
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ModelAndView defaultPageAfterLogin(HttpServletRequest request,@RequestParam("username") String username,@RequestParam("password") String password) {
		request.getSession().setAttribute("user", username);
		ModelAndView model = new ModelAndView();
		model.setViewName("afterLogin");
		return model;

	}
	@RequestMapping(value = "register", method = RequestMethod.GET)
	public ModelAndView register() {
		ModelAndView model = new ModelAndView();
		model.setViewName("register");
		return model;

	}

}
