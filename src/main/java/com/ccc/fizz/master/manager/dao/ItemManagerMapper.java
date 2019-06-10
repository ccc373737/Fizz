package com.ccc.fizz.master.manager.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ccc.fizz.base.common.TheResult;
import com.ccc.fizz.master.base.item.entity.Item;


@Mapper
public interface ItemManagerMapper {
	
	/**
	 * 根据id查询商品
	 * @param id
	 * @return
	 */
	public Item getItemById(Long id);
	
	/**
	 * 根据页数查询商品
	 * @param startNum
	 * @param page
	 * @return
	 */
	public List<Item> getItemsByPage(@Param("startNum") Integer startNum, @Param("count") Integer count);
	
	/**
	 * 添加商品
	 * @param item
	 */
	public void insertItem(Item item);
	
	/**
	 * 更新商品
	 * @param item
	 */
	public void updateItemByPrimaryKey(Item item);
	
	/**
	 * 删除商品
	 * @param id
	 */
	public void deleteItem(Long id);
}
