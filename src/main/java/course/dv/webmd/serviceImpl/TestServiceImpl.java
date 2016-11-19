package course.dv.webmd.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import course.dv.webmd.dao.TestDAO;
import course.dv.webmd.model.User;
import course.dv.webmd.service.TestService;

@Service("testService")
@Transactional
public class TestServiceImpl implements TestService{

	@Autowired
	TestDAO testDAO;

	public void addUser(User user) {
		// TODO Auto-generated method stub
		
	}


}
