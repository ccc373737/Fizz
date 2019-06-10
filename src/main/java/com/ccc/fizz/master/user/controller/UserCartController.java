package com.ccc.fizz.master.user.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ccc.fizz.base.common.TheResult;
import com.ccc.fizz.master.base.user.entity.UserCart;
import com.ccc.fizz.master.user.service.UserCartService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController("/cart")
public class UserCartController {
	@Resource
	private UserCartService userCartService;
	
	@ApiOperation(value = "购物车添加商品", notes = "添加商品")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "userId", value = "用户id", dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "itemId", value = "商品id", required = true, dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "num", value = "数量", required = true, dataType = "Integer", paramType = "query"),
		@ApiImplicitParam(name = "isLogin", value = "是否登录", required = true, dataType = "Boolean", paramType = "query")
	})
	@PostMapping("/add")
	public TheResult addItemToCart(String userId, String itemId, Integer num, Boolean isLogin) {
		return userCartService.addItemToCart(userId, itemId, num, isLogin);
	}
	
	@ApiOperation(value = "查看购物车商品", notes = "查看商品")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "userId", value = "用户id", dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "isLogin", value = "是否登录", required = true, dataType = "Boolean", paramType = "query")
	})
	@GetMapping("/get")
	public List<UserCart> getCartItems(String userId, Boolean isLogin) {
		return userCartService.getUserCarts(userId, isLogin);
	}
	
	@ApiOperation(value = "购物车更新商品", notes = "更新商品")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "userId", value = "用户id", dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "itemId", value = "商品id", required = true, dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "num", value = "数量", required = true, dataType = "Integer", paramType = "query"),
		@ApiImplicitParam(name = "isLogin", value = "是否登录", required = true, dataType = "Boolean", paramType = "query")
	})
	@PutMapping("/update")
	public TheResult updateUserCart(String userId, String itemId, Integer num, Boolean isLogin) {
		return userCartService.updateUserCart(userId, itemId, num, isLogin);
	}
	
	@ApiOperation(value = "购物车删除商品", notes = "删除商品")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "userId", value = "用户id", dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "itemId", value = "商品id", required = true, dataType = "String", paramType = "query"),
		@ApiImplicitParam(name = "isLogin", value = "是否登录", required = true, dataType = "Boolean", paramType = "query")
	})
	@DeleteMapping("/delete")
	public TheResult deleteUserCart(String userId, String itemId, Boolean isLogin) {
		return userCartService.deleteUserCart(userId, itemId, isLogin);
	}
}
