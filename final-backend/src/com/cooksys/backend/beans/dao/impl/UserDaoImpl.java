package com.cooksys.backend.beans.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cooksys.backend.beans.dao.UserDao;
import com.cooksys.backend.model.User;

@Repository
@Transactional
@SuppressWarnings("unchecked")
public class UserDaoImpl implements UserDao {

	private Logger log = LoggerFactory.getLogger(UserDaoImpl.class);
	private PasswordEncoder encoder = new PasswordEncoder();

	@Autowired
	private SessionFactory factory;

	@Override
	public User getUserByName(String username) {
		return (User) factory.getCurrentSession()
				.createQuery("from User u where u.username = :username")
				.setString("username", username).uniqueResult();
	}
	
	@Override
	public User getUserById(Integer id) {
		return (User) factory.getCurrentSession()
				.createQuery("from User u where u.id = :id")
				.setInteger("id", id).uniqueResult();
	}
	
	@Override
	public List<User> getUserByUsername(String username) {
		return factory
				.getCurrentSession()
				.createQuery(
						"          select u                            "
								+ "  from User u                       "
								+ " where u.username like :name        ")
				.setString("name", "%" + username + "%").list();

	}
	
	/**
	 * This method registers the user in the database. All user
	 * information, i.e username and password, are to be set
	 * first before calling this method.
	 * @return 
	 */
	@Override
	public User registerUser(User user) {
		Session session = factory.getCurrentSession();
		User result = null;
		try {
			String encodedPassword = encoder.encryptPassword(user.getPassword());
			user.setPassword(encodedPassword);
			session.save(user);
			result = user;
			log.info("Successfully registered user.");
			
		} catch (Throwable t) {
			log.warn("Could not register user", t);
		}
		return result;
	}
	
	
	/**
	 * This method updates the user in the database. All changes
	 * made to the user are to be set first before calling this 
	 * method.
	 */
	@Override
	public User updateUser(User user) {
		Session session = factory.getCurrentSession();
		User result = null;
		try {
			String encodedPassword = encoder.encryptPassword(user.getPassword());
			user.setPassword(encodedPassword);
			session.update(user);
			result = user;
			log.info("User updated successully");
		} catch (Throwable t) {
			log.warn("Could not update user", t);
		}
		return result;
	}

	@Override
	public List<User> getUsers() {
		Session session = factory.getCurrentSession();
		return session.createQuery("from User").list();
	}
	
	@Override
	public User getUserByEmail(String email) {
		return (User) factory.getCurrentSession()
				.createQuery("from User u where u.email = :email")
				.setString("email", email).uniqueResult();
	}

	

}
