package com.ccc.fizz.master.manager.service;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.ccc.fizz.base.common.TheResult;
import com.ccc.fizz.base.constant.FizzConstant;
import com.ccc.fizz.base.utils.PageUtil;
import com.ccc.fizz.master.base.item.entity.Item;
import com.ccc.fizz.master.manager.dao.ItemManagerMapper;

@Service
@Transactional(rollbackOn = Exception.class)
public class ItemManagerService {
	@Resource
	private ItemManagerMapper itemManagerMapper;

	/**
	 * 根据id查询商品
	 * 
	 * @param id
	 * @return
	 */
	public Item getItemById(Long id) {
		return itemManagerMapper.getItemById(id);
	}

	/**
	 * 根据页数查询商品
	 * 
	 * @param startNum
	 * @param page
	 * @return
	 */
	public List<Item> getItemsByPage(Integer page) {
		Integer startNum = PageUtil.getStartNum(page);
		return itemManagerMapper.getItemsByPage(startNum, FizzConstant.PAGE_SIZE);
	}

	/**
	 * 添加商品
	 * 
	 * @param item
	 */
	public TheResult insertItem(Item item) {
		itemManagerMapper.insertItem(item);
		return TheResult.ok();
	}
	
	/**
	 * 更新商品
	 * @param id
	 * @param item
	 */
	public TheResult updateItem(Item item) {
		itemManagerMapper.updateItemByPrimaryKey(item);
		return TheResult.ok();
	}
	
	/**
	 * 删除商品
	 * @param id
	 */
	public TheResult deleteItem(Long id) {
		itemManagerMapper.deleteItem(id);
		return TheResult.ok();
	}

}
