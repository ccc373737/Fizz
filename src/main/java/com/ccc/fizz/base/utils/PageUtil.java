package com.ccc.fizz.base.utils;

import com.ccc.fizz.base.constant.FizzConstant;

public class PageUtil {
	
	/**
	 * 计算起始页数
	 * @param page
	 * @return
	 */
	public static Integer getStartNum(Integer page) {
		Integer startNum = (page - 1) * FizzConstant.PAGE_SIZE;
		return startNum;
	}
}
