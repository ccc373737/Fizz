package com.ccc.fizz.master.base.user.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class UserCart {
	@Setter @Getter
	private Long id;
	
	@Setter @Getter
	private String itemTitle;
	
	@Setter @Getter
	private String itemImage;
	
	@Setter @Getter
	private Long price;
	
	@Setter @Getter
	private Integer num;
}
