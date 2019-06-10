package com.ccc.fizz.master.user.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ccc.fizz.master.base.user.entity.User;

@Mapper
public interface UserMapper {
	
	@Select("select * from tb_user where username = #{username}")
	public User selectByUsername(@Param("username") String username);
}
