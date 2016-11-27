package course.dv.webmd.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	@RequestMapping(value = { "/", "/welcome**","/logout" }, method = RequestMethod.GET)
	public ModelAndView defaultPage() {
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
