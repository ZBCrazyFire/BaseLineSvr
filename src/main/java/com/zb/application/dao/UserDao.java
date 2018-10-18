package com.zb.application.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zb.application.domain.PaginationDomain;
import com.zb.application.domain.UserDomain;

@Mapper
public interface UserDao {
	
	void addUser(UserDomain userDomain);
	
	List<UserDomain> queryUser(UserDomain userDomain);
	
	void updateUser (UserDomain userDomain);
}
