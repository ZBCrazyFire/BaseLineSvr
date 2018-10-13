package com.zb.application.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.zb.application.dao.UserDao;
import com.zb.application.domain.CommonResponse;
import com.zb.application.domain.UserDomain;
import com.zb.application.utils.Utils;

@RestController
@RequestMapping(value = "api")
public class UserManagerController {
	@Autowired
	UserDao userDao;

	@RequestMapping(value = "/query/user",method = RequestMethod.POST)
	public CommonResponse queryStudent() {
		CommonResponse commonResponse = new CommonResponse();
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {

			List<UserDomain> userList = userDao.queryUser();

			result.put("message", "query user success");
			result.put("userList", userList);
			commonResponse.setResult(result);
			commonResponse.setStatus("success");

		} catch (Exception e) {
			result.put("message", "query user failed" + e);
			commonResponse.setResult(result);
			commonResponse.setStatus("failed");
		}
		return commonResponse;
	}

	@RequestMapping(value = "/add/user",method = RequestMethod.POST)
	public CommonResponse addUser(@RequestBody(required = true) String params) {
		CommonResponse commonResponse = new CommonResponse();
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			if(StringUtils.isEmpty(params)) {
				result.put("message", "params can not be empty");
				commonResponse.setResult(result);
				commonResponse.setStatus("failed");
			}

			JSONObject paramsObject = JSONObject.parseObject(params);
			String name = paramsObject.getString("name");
			String password = paramsObject.getString("password");
			if(StringUtils.isEmpty(name) || StringUtils.isEmpty(password)) {
				result.put("message", "name or password can not be empty");
				commonResponse.setResult(result);
				commonResponse.setStatus("failed");
			}

			UserDomain userDomain = new UserDomain();
			userDomain.setId(Utils.generalId());
			userDomain.setName(name);
			userDomain.setPassword(password);
			userDomain.setCreateTime(Utils.nowTime());
			userDomain.setUpdateTime(Utils.nowTime());
			userDomain.setCharacter("member");

			userDao.addUser(userDomain);

			result.put("message", "ass user success");
			commonResponse.setResult(result);
			commonResponse.setStatus("success");
		} catch (Exception e) {
			result.put("message", "add user failed" + e);
			commonResponse.setResult(result);
			commonResponse.setStatus("failed");
		}
		return commonResponse;

	}
}
