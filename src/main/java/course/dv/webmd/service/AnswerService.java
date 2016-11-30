package course.dv.webmd.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import course.dv.webmd.dao.AnswersDAO;
import course.dv.webmd.model.Answer;

@Service("answerService")
public class AnswerService {

	public Set<Answer> getAnswersForAQuestionId(String id)
	{
		return AnswersDAO.getAnswersForAQuestion(id);
	}
}
