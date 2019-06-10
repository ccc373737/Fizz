package com.ccc.fizz.base.common;

import lombok.Getter;
import lombok.Setter;

public class TheResult {
	@Setter @Getter
	private Integer status;
	@Setter @Getter
	private String msg;
	@Setter @Getter
	private Object data;
	
	public TheResult() {}

	public TheResult(Integer status, String msg, Object data) {
		super();
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	public TheResult(Object data) {
		this.status = 200;
		this.msg = "ok";
		this.data = data;
	};
	
	//无数据成功返回
	public static TheResult ok() {
		return new TheResult(null);
	}
	
	//有数据成功返回
	public static TheResult ok(Object data) {
		return new TheResult(data);
	}
	
	//失败返回
	public static TheResult error(Integer status, String msg) {
		return new TheResult(status, msg, null);
	}
	
}
