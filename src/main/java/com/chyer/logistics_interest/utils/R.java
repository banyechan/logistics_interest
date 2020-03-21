package com.chyer.logistics_interest.utils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 响应信息主体
 *
 * @param <T>
 * @author mimi
 */
@ToString
public class R<T> implements Serializable {
	private static final int SUCCESS = 200;
	private static final int FAIL = 1;
	private static final long serialVersionUID = 1L;
	@Getter
	@Setter
	private String msg = "success";

	@Getter
	@Setter
	private int code = SUCCESS;

	@Getter
	@Setter
	private T data;

	public R() {
		super();
	}

	public R(T data) {
		super();
		this.data = data;
	}

	public R(T data, String msg) {
		super();
		this.data = data;
		this.msg = msg;
	}

	public R(Throwable e) {
		super();
		this.msg = e.getMessage();
		this.code = FAIL;
	}

	public R(Integer code, Throwable e) {
		super();
		this.msg = e.getMessage();
		this.code = code;
	}
}
