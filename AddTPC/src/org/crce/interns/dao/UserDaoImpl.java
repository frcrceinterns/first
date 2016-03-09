package org.crce.interns.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.crce.interns.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserDaoImpl implements UserDao{

	@PersistenceContext
	private EntityManager entityManager;
	@Override
	public void insertUser(User user) {
		// TODO Auto-generated method stub
		//entityManager.persist(user);
	//	List l=(List) entityManager.createQuery("");
		entityManager.merge(user);
	}
	@Override
	public List<User> viewUsers() {
		// TODO Auto-generated method stub
		return entityManager.createQuery("select u from User u", User.class).getResultList();
	}
}
