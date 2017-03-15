package com.aelns.core.support;

/**
 * 封装统一的JSON返回结果, 包含code值, code=0为返回正常, 其他的编码为错误信息
 * @param <T>
 */
public class JsonWrapper<T> {
	
	private String code;
	
	private String message;
	
	private long version;
	
	private T data;

	public JsonWrapper(T result) {
		this.data = result;
		code = "0";
		message = "";
	}
	
	public JsonWrapper(T result, String message) {
		this.data = result;
		code = "0";
		this.message = message;
	}
	
	public JsonWrapper(T result, long version) {
		this.data = result;
		code = "0";
		message = "";
		this.version = version;
	}

	private JsonWrapper() {
		super();
	}
	
	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @return the data
	 */
	public T getData() {
		return data;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(T data) {
		this.data = data;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "JsonWrapper [code=" + code + ", message=" + message + ", data=" + data
				+ "]";
	}
}
