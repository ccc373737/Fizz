package com.ccc.fizz.master.manager.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ccc.fizz.base.common.TheResult;
import com.ccc.fizz.master.base.item.entity.Item;
import com.ccc.fizz.master.manager.service.ItemManagerService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/item")
public class ItemManagerController {
	@Resource
	private ItemManagerService itemManagerService;
	
	@ApiOperation(value = "获取单个商品", notes = "根据id获取商品详细信息")
	@ApiImplicitParam(name = "id", value = "商品id", required = true, dataType = "Long", paramType = "path")
	@GetMapping("/select/{id}")
	public Item getItemById(@PathVariable Long id) {
		return itemManagerService.getItemById(id);
	}
	
	@ApiOperation(value = "获取商品信息", notes = "根据页数获取商品信息")
	@ApiImplicitParam(name = "page", value = "页数", required = true, dataType = "Integer", paramType = "path")
	@GetMapping("/page/{page}")
	public List<Item> getItemsByPage(@PathVariable Integer page) {
		return itemManagerService.getItemsByPage(page);
	}
	
	@ApiOperation(value = "新增商品", notes = "新增商品")
	@ApiImplicitParam(name = "item", value = "参数：id, title, sell_point, price, num, barcode, image, cid, status, created, updated",
	required = true, dataType = "Item")
	@PostMapping("/insert")
	public TheResult insertItem(@RequestBody Item item) {
		return itemManagerService.insertItem(item);
	}
	
	@ApiOperation(value = "更新商品", notes = "更新商品")
	@ApiImplicitParam(name = "item", value = "参数：id, title, sell_point, price, num, barcode, image, cid, status, created, updated",
	required = true, dataType = "Item")
	@PutMapping("/update")
	public TheResult updateItem(@RequestBody Item item) {
		return itemManagerService.updateItem(item);
	}
	
	@ApiOperation(value = "删除商品", notes = "删除商品")
	@ApiImplicitParam(name = "id", value = "商品id", required = true, dataType = "Long", paramType = "path  ")
	@DeleteMapping("/delete/{id}")
	public TheResult deleteItem(@PathVariable Long id) {
		return itemManagerService.deleteItem(id);
	}
}
