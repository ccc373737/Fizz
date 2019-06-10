package com.ccc.fizz.master.base.item.entity;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class ItemCategory {
	@Setter @Getter
	private Long id;
	
	@Setter @Getter
	private Long parentId;
	
	@Setter @Getter
	private String name;
	
	@Setter @Getter
	private Integer status;
	
	@Setter @Getter
	private Integer sortOrder;
	
	@Setter @Getter
	private Boolean isParent;
	
	@Setter @Getter
	private Date created;
	
	@Setter @Getter
	private Date updated;
}
