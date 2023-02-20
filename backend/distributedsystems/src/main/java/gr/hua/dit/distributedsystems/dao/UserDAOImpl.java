package gr.hua.dit.distributedsystems.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gr.hua.dit.distributedsystems.entities.User;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	@Transactional
	public List<User> findAll() {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createQuery("from User", User.class);
		List<User> users = query.getResultList();
		return users;
	}

	@Override
	@Transactional
	public User findById(int id) {
		return entityManager.find(User.class, id);
	}

	@Override
	@Transactional
	public void save(User user) {
		User auser = entityManager.merge(user);
	}

	@Override
	@Transactional
	public void delete(User user) {
		entityManager.remove(user);
	}

	
	
}
