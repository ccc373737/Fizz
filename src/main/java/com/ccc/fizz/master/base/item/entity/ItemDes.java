package com.ccc.fizz.master.base.item.entity;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class ItemDes {
	@Setter @Getter
	private Long itemId;
	
	@Setter @Getter
	private String itemDesc;
	
	@Setter @Getter
	private Date created;
	
	@Setter @Getter
	private Date updated;
}
