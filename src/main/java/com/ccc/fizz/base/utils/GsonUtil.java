package com.ccc.fizz.base.utils;

import com.google.gson.Gson;

public class GsonUtil {
	
	private static final Gson MAPPER = new Gson();
	
	/**
	 * 对象转化为json
	 * @param obj
	 * @return
	 */
	public static String objectToJson(Object obj) {
		try {
			String jsonstr = MAPPER.toJson(obj);
			return jsonstr;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * json转换为对象
	 * @param jsonstr
	 * @param beanType
	 * @return
	 */
	public static <T> T jsonToOject(String jsonstr, Class<T> beanType) {
		try {
			T t = MAPPER.fromJson(jsonstr, beanType);
			return t;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
