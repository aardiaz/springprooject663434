package com.appsoft.springproject.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appsoft.springproject.model.User;
import com.appsoft.springproject.repository.UserRepository;
import com.appsoft.springproject.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public void userSignup(User u) {
	 
		userRepo.save(u);
	}

	@Override
	public User userLogin(String un, String psw) {
		 
		return userRepo.checkUser(un, psw);
	}

}
