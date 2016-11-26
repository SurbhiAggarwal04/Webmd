package course.dv.webmd.daoImpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import course.dv.webmd.dao.TestDAO;
import course.dv.webmd.model.User;

@Repository("testDAO")
public class TestDAOImpl implements TestDAO{

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		
	}

}
