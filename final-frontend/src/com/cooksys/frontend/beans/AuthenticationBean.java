package com.cooksys.frontend.beans;



import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import com.cooksys.frontend.beans.dao.UserDao;
import com.cooksys.frontend.beans.dao.impl.PasswordEncoder;
import com.cooksys.frontend.model.User;


/**
 * This authentication bean is able to keep track whether or not 
 * a user is logged in to the website.
 * @author Gary McGuire
 *
 */
@Service
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class AuthenticationBean {
    private Logger log = LoggerFactory.getLogger(AuthenticationBean.class);

    @Autowired
    private UserDao userDao;


    private User user;
    public String userName = "session cleared";

    private PasswordEncoder encoder = new PasswordEncoder();

    @PostConstruct
    public void init() {
        log.info("AuthenticationBean.init()");
        user = new User();
    }
    
    /**
	 * This method checks the username and password combination 
	 * and checks to see if it matches with the database. The
	 * password is checked to see if it matches it's encrypted version
	 * in the database. If the combination of username and password
	 * are true, then it returns true and allows the user to login.
	 * @param user
	 * @return
	 */
    public boolean login(User user) { //if I have time, pretty this method up a bit...
        log.info("boolean AuthenticationBean.login(User user)");
        if (user != null && user.getUsername() != null
                && user.getPassword() != null) {

            String name = user.getUsername().trim();
            String password = user.getPassword().trim(); // This returns the raw password
           
            log.info("username =" + user.getUsername());
            log.info("password =" + user.getPassword());

            if (!name.isEmpty() && !password.isEmpty()) {
                User dbUser = userDao.getUserByName(name);
                if (dbUser != null
                        && encoder.getPasswordEncoder().matches(password,
                                dbUser.getPassword())) {
                    log.info("login successfully");
                    
                    this.userName = dbUser.getUsername();
                    this.user = dbUser;
                    return true;
                    
                    //Crapton of logs for testing purposes. Remove later...
                } else {
                    log.info("passwords don't match");
                }
            } else {
                log.info("user or password are empty");
            }
        } else {
            log.info("user or password are null");
        }
        log.info("login unsuccessfully");
        return false;
    }
    
    /**
	 * Basic boolean which can be called upon elsewhere to see if
	 * the user is still logged in.
	 * @return
	 */
    public boolean isLoggedIn() {
        log.info("boolean AuthenticationBean.isLoggedIn()");
        if ((user != null) && (userName != "session cleared")) {
            log.info("return is logged in");
            return true;
        } else {
            log.info("return is not logged in");
            return false;
        }
    }
    
    /**
	 * Completes the logout process and invalidates the current session.
	 * @return
	 */
    public String logout() {
        log.info("String AuthenticationBean.logout()");
        user = null;
        FacesContext.getCurrentInstance().getExternalContext()
                .invalidateSession();
        log.info("logged-out");
        userName = "session cleared";
        return "logout";
    }

    public String getUsername() {
        log.info("String AuthenticationBean.getUserEmail()");
        if ((user != null) && (userName != "session cleared")) {
            log.info("return " + user.getUsername());
            return user.getUsername();
        } else {
            log.info("return null");
            return null;
        }
    }


    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    //May or may not use this method later...

//    public void setUsername(String userName) {
//        this.userName = userName;
//    }

}