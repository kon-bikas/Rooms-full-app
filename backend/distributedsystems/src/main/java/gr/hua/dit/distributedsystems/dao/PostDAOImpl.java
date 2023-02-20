package gr.hua.dit.distributedsystems.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gr.hua.dit.distributedsystems.entities.Post;

@Repository
public class PostDAOImpl implements PostDAO {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	@Transactional
	public List<Post> findAll() {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createQuery("from Post", Post.class);
		List<Post> posts = query.getResultList();
		return posts;
	}

	@Override
	@Transactional
	public Post findById(int id) {
		return entityManager.find(Post.class, id);
	}

	@Override
	@Transactional
	public void save(Post post) {
		Post apost = entityManager.merge(post);
		
	}

	@Override
	@Transactional
	public void delete(Post post) {
		entityManager.remove(post);
		
	}

}
