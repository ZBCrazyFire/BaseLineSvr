package com.zb.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zb.application.dao.UserDao;
import com.zb.application.domain.UserDomain;
import com.zb.application.utils.Utils;

@RestController
@RequestMapping(value = "api")
public class CheckHealthy {
	
	@RequestMapping(value = "/check/healthy",method = RequestMethod.GET)
	public String checkHealthy() {
		return "true";
	}
}
