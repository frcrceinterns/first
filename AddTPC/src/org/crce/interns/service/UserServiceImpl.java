package org.crce.interns.service;
import java.util.ArrayList;
import java.util.List;

import org.crce.interns.beans.UserBean;
import org.crce.interns.dao.UserDao;
import org.crce.interns.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;
	
	@Override
	public void insertUser(UserBean userBean) {
		// TODO Auto-generated method stub
		User user = new User();
		BeanUtils.copyProperties(userBean, user);
		//user.setUserDob(new java.sql.Date(userBean.getUserDob().getTime()));
		userDao.insertUser(user);
	}

	@Override
	public List<UserBean> viewUsers() {
		// TODO Auto-generated method stub
		List<User> userList = userDao.viewUsers();
		return convertToBean(userList);
	}

	public List<UserBean> convertToBean(List<User> userList) {
		List<UserBean> userBeanList = new ArrayList<UserBean>();
		for (User user : userList) {
			UserBean userBean = new UserBean();
			BeanUtils.copyProperties(user, userBean);
			userBeanList.add(userBean);
		}
		return userBeanList;
	}

	@Override
	public void deleteUser(String userName) {
		// TODO Auto-generated method stub
		userDao.deleteUser(userName);
		
	}

	
}
