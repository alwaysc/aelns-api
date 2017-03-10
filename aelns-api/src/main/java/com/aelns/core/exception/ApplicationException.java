package com.aelns.core.exception;

/**
 * 应用程序的异常, 自定义异常的可以继承这个类别, spring会自动拦截异常信息进行展示
 */
public class ApplicationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6451736551491944656L;

	/**
	 * exception code
	 */
	private String code;

	/**
	 * exception reasoning
	 */
	private String reasoning;
	
	public ApplicationException() {
		super();
	}
	
	public ApplicationException(String code, String reasoning) {
		super();
		this.code = code;
		this.reasoning = reasoning;
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
	/**
	 * @return the reasoning
	 */
	public String getReasoning() {
		return reasoning;
	}
	/**
	 * @param reasoning the reasoning to set
	 */
	public void setReasoning(String reasoning) {
		this.reasoning = reasoning;
	}
}
