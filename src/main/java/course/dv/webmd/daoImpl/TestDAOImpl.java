package course.dv.webmd.daoImpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import course.dv.webmd.dao.TestDAO;
import course.dv.webmd.model.User;

@Repository("testDAO")
public class TestDAOImpl implements TestDAO{
	@Autowired
    private SessionFactory sessionFactory;
	
	
	public void add(User user)
	{
		Session session = sessionFactory.getCurrentSession(); 
		session.persist(user);
		//sessionFactory.getCurrentSession().persist(user);
	}
	
	
	public void add1(User user)
	{
		System.out.println("hiiiiii user");
	}


	public void addUser(User user) {
		// TODO Auto-generated method stub
		
	}



}
