package com.framework.model;

import com.framework.util.MStrOperate;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public abstract class DataObject {

	public static <T extends DataObject> T get(Class<T> clazz, String json) {
		Gson gson = new Gson();
		return gson.fromJson(json, clazz);
	}

	public static <T extends DataObject> List<T> getList(Type type, String json) {
		if (!MStrOperate.hasValue(json))
			return new ArrayList<T>();
		Gson gson = new Gson();
		return gson.fromJson(json, type);
	}
}
