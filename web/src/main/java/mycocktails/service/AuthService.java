package mycocktails.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.stereotype.Service;

import mycocktails.dao.UserDao;
import mycocktails.entity.UserProfile;



import flex.messaging.FlexContext;

/**
 * Blazeds service
 * 
 * @author prabhakar.jayaraman
 *
 */

@Service("authService")
@RemotingDestination()
public class AuthService {
	
	
	@Autowired(required=true)
	private UserDao userDao;
	
	
	public UserProfile authenticate(UserProfile userProfile)
	{
		UserProfile loadedUserInfo = userDao.findUserByLoginIdPassword(userProfile.getLoginId(), userProfile.getPassword());
		FlexContext.getHttpRequest().getSession(true).setAttribute("loggedInUser", loadedUserInfo);
		return loadedUserInfo;
		
	}


	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}


	public UserDao getUserDao() {
		return userDao;
	}

}
