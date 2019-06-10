package com.ccc.fizz.master.user.service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.ccc.fizz.base.common.TheResult;
import com.ccc.fizz.base.constant.FizzConstant;
import com.ccc.fizz.base.utils.GsonUtil;
import com.ccc.fizz.master.base.user.entity.User;
import com.ccc.fizz.master.base.user.entity.UserCart;
import com.ccc.fizz.master.user.dao.UserMapper;

@Service
@Transactional(rollbackOn = Exception.class)
public class UserService {
	@Resource
	private UserMapper userMapper;
	
	@Resource
	private UserCartService userCartService;
	
	@Autowired
	private StringRedisTemplate redisTemplate;
	
	/**
	 * 验证用户密码
	 * @param username
	 * @param password
	 * @return
	 */
	public TheResult VerifyUser(String username, String password, String cartToken) {
		User user = userMapper.selectByUsername(username);
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			return TheResult.error(400, "用户名或密码不能为空");
		}
		//判断用户名是否存在
		if (user == null) {
			return TheResult.error(400, "用户名不存在");
		}
		//判断密码是否正确
		String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
		if (! md5Password.equals(user.getPassword())) {
			return TheResult.error(400, "密码或用户名错误");
		}
		//加入缓存
		String token = UUID.randomUUID().toString();
		redisTemplate.opsForValue().set(FizzConstant.USER_INFO + ":" + token, GsonUtil.objectToJson(user), 1800, TimeUnit.SECONDS);
		
		//购物车合并
		String userId = user.getId().toString();
		tokenHandle(userId, cartToken);
		
		return TheResult.ok(token);
	}
	
	/**
	 * 缓存购物车与用户购物车合并
	 * @param token
	 */
	public void tokenHandle(String userId, String cartToken) {
		
		//如果有缓存数据
		if (cartToken != null) {
			//true表示相同，false表示不同
			boolean flag = false;
			//未登录缓存购物车
			List<UserCart> tokenCart = userCartService.getUserCarts(cartToken, false);
			//用户购物车
			List<UserCart> userCart = userCartService.getUserCarts(userId, true);
			
			for (UserCart tc : tokenCart) {
				for (UserCart uc : userCart) {
					//相同商品，数量相加
					if (tc.getId().equals(uc.getId())) {
						Integer num = tc.getNum() + uc.getNum();
						userCartService.updateUserCart(userId, tc.getId().toString(), num, true);
						flag = true;
					}
				}
				//表示一轮遍历没有相同商品，直接添加
				if (flag == false) {
					userCartService.addItemToCart(userId, tc.getId().toString(), tc.getNum(), true);
				}
				//标识重置
				flag = false;
			}
			//合并完成后删除缓存库
			redisTemplate.delete(FizzConstant.CART_KEY_TOKEN + ":" + cartToken);
		}
	}
}
