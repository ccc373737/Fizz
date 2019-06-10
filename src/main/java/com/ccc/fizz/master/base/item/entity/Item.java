package com.ccc.fizz.master.base.item.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Item {
	@Setter @Getter
	private Long id;
	
	@Setter @Getter
	private String title;
	
	@Setter @Getter
	private String sellPoint;
	
	@Setter @Getter
	private Long price;
	
	@Setter @Getter
	private Integer num;
	
	@Setter @Getter
	private String barcode;
	
	@Setter @Getter
	private String image;
	
	@Setter @Getter
	private Long cid;
	
	@Setter @Getter
	private Boolean status;
	
	@Setter @Getter
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date created;
	
	@Setter @Getter
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updated;
	
}
