package com.aelns.core.support;

/**
 * the base action controller
 * 
 */
public class BaseAction {
	
	/**
	 * wrapper JSON view and set data version
	 * @param object
	 * @param version
	 * @return
	 */
	public Object wraperResult(Object object, long version) {
		return new JsonWrapper<>(object, version);
	}
	
	/**
	 * wrapper JSON view and set message
	 * @param object
	 * @return
	 */
	public Object wraperResult(Object object, String message) {
		return new JsonWrapper<>(object, message);
	}
	
	/**
	 *  wrapper JSON view
	 * @param object
	 * @return
	 */
	public Object wraperResult(Object object) {
		return new JsonWrapper<>(object);
	}
}
