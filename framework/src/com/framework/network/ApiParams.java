package com.framework.network;

import java.util.HashMap;

/**
 * 
 * @author heiyl
 *
 */
public class ApiParams extends HashMap<String, String> {
	private static final long serialVersionUID = 8112047472727256876L;

	public ApiParams with(String key, String value) {
		put(key, value);
		return this;
	}
}
