package com.appsoft.springproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.appsoft.springproject.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUsernameAndPassword(String un, String psw);
	
	//@Query(value = "select * from user_tbl  where username= :un and password = :psw ", nativeQuery = true) //using SQL native query
	@Query("FROM User  Where username= :un AND password= :psw ")//using JPA Query
	User checkUser(@Param("un") String un,@Param("psw") String psw);
}
