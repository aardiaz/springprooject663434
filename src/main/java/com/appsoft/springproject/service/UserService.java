package com.appsoft.springproject.service;

import com.appsoft.springproject.model.User;

public interface UserService {
	
	void userSignup(User u);
	
	User userLogin(String un, String psw);
}
