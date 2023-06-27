package com.stoppingcar.cloud.exception;


import com.stoppingcar.cloud.damain.enums.ResponseEnum;

/**
 * @author JerryHeng
 * @since 2023/6/26
 */
public class StopException extends RuntimeException {
	private Object object;

	private ResponseEnum responseEnum;

	public StopException(String msg) {
		super(msg);
	}

	public StopException(String msg, Object object) {
		super(msg);
		this.object = object;
	}

	public StopException(String msg, Throwable cause) {
		super(msg, cause);
	}


	public StopException(ResponseEnum responseEnum) {
		super(responseEnum.getMsg());
		this.responseEnum = responseEnum;
	}

	public StopException(ResponseEnum responseEnum, Object object) {
		super(responseEnum.getMsg());
		this.responseEnum = responseEnum;
		this.object = object;
	}


	public Object getObject() {
		return object;
	}

	public ResponseEnum getResponseEnum() {
		return responseEnum;
	}

}
