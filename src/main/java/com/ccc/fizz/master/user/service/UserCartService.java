package com.ccc.fizz.master.user.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.ccc.fizz.base.common.TheResult;
import com.ccc.fizz.base.constant.FizzConstant;
import com.ccc.fizz.base.utils.GsonUtil;
import com.ccc.fizz.master.base.item.entity.Item;
import com.ccc.fizz.master.base.user.entity.UserCart;
import com.ccc.fizz.master.manager.dao.ItemManagerMapper;

@Service
@Transactional(rollbackOn = Exception.class)
public class UserCartService {
	@Resource
	private ItemManagerMapper itemMapper;
	
	@Autowired
	private StringRedisTemplate redisTemplate;
	
	@Value(FizzConstant.CART_KEY_USER)
	private String userKey;
	
	@Value(FizzConstant.CART_KEY_TOKEN)
	private String tokenKey;

	/**
	 * 从缓存库中读取商品信息并转化为对象返回
	 * @param userId
	 * @param itemId
	 * @return
	 */
	private UserCart getUserCartByUserIdAndItemId(String userId, String itemId) {
		String userCartJson = (String) redisTemplate.opsForHash().get(userId, itemId);
		return GsonUtil.jsonToOject(userCartJson, UserCart.class);
	}
	
	/**
	 * 根据登录状态返回key
	 * @param userId
	 * @param isLogin
	 * @return
	 */
	private String loginStatus(String userId, Boolean isLogin) {
		String cartKey = null;
		//登录
		if (isLogin) {
			cartKey = userKey + ":" + userId;
		//未登录
		} else {
			//第一次，无token
			if (userId == null) {
				cartKey = tokenKey + ":" + UUID.randomUUID().toString();
			//已有token
			} else {
				cartKey = tokenKey + ":" + userId;
			}
		}
		return cartKey;
	}
	
	/**
	 * 添加商品
	 * @param userId
	 * @param itemId
	 * @param num
	 * @return
	 */
	public TheResult addItemToCart(String userId, String itemId, Integer num, Boolean isLogin) {
		String theKey = loginStatus(userId, isLogin);
		UserCart userCart = null;
		//数据库已有商品的情况下，数量相加
		if ((userCart = getUserCartByUserIdAndItemId(theKey, itemId)) != null) {
			userCart.setNum(userCart.getNum() + num);
		//没有商品，封装对象
		} else {
			Item item = itemMapper.getItemById(Long.parseLong(itemId));
			userCart = new UserCart();
			userCart.setId(item.getId());
			userCart.setItemTitle(item.getTitle());
			userCart.setItemImage(item.getImage());
			userCart.setPrice(item.getPrice());
			userCart.setNum(num);
		}
		String cartJson = GsonUtil.objectToJson(userCart);
		redisTemplate.opsForHash().put(theKey, itemId, cartJson);
		return TheResult.ok();
	}
	
	/**
	 * 查看商品
	 * @param userId
	 * @return
	 */
	public List<UserCart> getUserCarts(String userId, Boolean isLogin) {
		String theKey = loginStatus(userId, isLogin);
		Map<Object, Object> map = redisTemplate.opsForHash().entries(theKey);
		List<UserCart> list = new ArrayList<>();
		
		for(Object key : map.keySet()) {
			String cart = (String) map.get(key);
			UserCart u = GsonUtil.jsonToOject(cart, UserCart.class);
			list.add(u);
		}
		return list;
	}
	
	/**
	 * 更新商品
	 * @param userId
	 * @param itemId
	 * @param num
	 * @return
	 */
	public TheResult updateUserCart(String userId, String itemId, Integer num, Boolean isLogin) {
		String theKey = loginStatus(userId, isLogin);
		UserCart userCart = getUserCartByUserIdAndItemId(theKey, itemId);
		userCart.setNum(num);
		String cartJson = GsonUtil.objectToJson(userCart);
		redisTemplate.opsForHash().put(theKey, itemId, cartJson);
		return TheResult.ok();
	}
	
	/**
	 * 删除商品
	 * @param userId
	 * @param itemId
	 * @return
	 */
	public TheResult deleteUserCart(String userId, String itemId, Boolean isLogin) {
		String theKey = loginStatus(userId, isLogin);
		redisTemplate.opsForHash().delete(theKey, itemId);
		return TheResult.ok();
	}
}
