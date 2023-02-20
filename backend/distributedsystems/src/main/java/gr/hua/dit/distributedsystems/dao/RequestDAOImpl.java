package gr.hua.dit.distributedsystems.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gr.hua.dit.distributedsystems.entities.Request;

@Repository
public class RequestDAOImpl implements RequestDAO {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	@Transactional
	public List<Request> findAll() {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createQuery("from Request", Request.class);
		List<Request> requests = query.getResultList();
		return requests;
	}

	@Override
	@Transactional
	public Request findById(int id) {
		return entityManager.find(Request.class, id);
	}

	@Override
	@Transactional
	public void save(Request request) {
		Request arequest = entityManager.merge(request);
	}

	@Override
	@Transactional
	public void delete(int id) {
		Request request = entityManager.find(Request.class, id);
		entityManager.remove(request);
	}
	
}
