package com.aelns.core.exception;

/**
 *
 * 自定义的异常编码
 *
 * @author aelns
 */
public enum ErrorCode {
	ARGUMENTS_INCORRECT("10001", "参数不正确"),
	TIMEOUT_CALL("10002", "API调用超时"),
	INVALI_ACCESS_TOKEN("10010", "请重新登录"),
	INVALID_OPRATION("10013", "无效的操作"),
	INTERNAL_SERVER_ERROR("10014", "服务器内部错误"),
	PAGE_NOT_FOUND("10015", "请求的地址不存在"),
	USER_NAME_NOT_EXIST("10018", "用户名不存在"),
	INVALID_PASSWORD("10019", "密码错误"),

	MESSAGE_QUENE_SEND_ERROR("20001","消息队列发送失败"),
	REDIS_ERROR("20002","Redis异常"),
	;
	
	private String code;
	private String message;
	
	private ErrorCode(String code, String reasoning) {
		this.code = code;
		this.message = reasoning;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
