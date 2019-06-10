package com.ccc.fizz.master.user.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ccc.fizz.base.common.TheResult;
import com.ccc.fizz.master.user.service.UserService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController("/user")
public class LoginController {
	@Resource
	private UserService userService;
	
	@ApiOperation(value = "验证用户", notes = "验证用户")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "cartToken", value = "缓存key", dataType = "String", paramType = "query")
	})
	@GetMapping("/verify")
	public TheResult userLogin(String username, String password, String cartToken) {
		return userService.VerifyUser(username, password, cartToken);
	}
}
