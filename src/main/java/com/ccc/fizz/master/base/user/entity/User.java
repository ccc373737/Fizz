package com.ccc.fizz.master.base.user.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class User {
	@Setter @Getter
	private Long id;
	
	@Setter @Getter
	private String username;
	
	@Setter @Getter
	private String password;
	
	@Setter @Getter
	private String phone;
	
	@Setter @Getter
	private String email;
	
	@Setter @Getter
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date created;
	
	@Setter @Getter
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updated;
}
