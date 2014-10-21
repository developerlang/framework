package com.framework.model;

import java.io.Serializable;

public class ResultDto extends DataObject implements Serializable {
	// true操作成功，false为失败
	public boolean success;
	// 描述
	public String message;
	
}
