package com.uet.recipeweb.converter;

public class ObjectConverter {
	public static Long toLong(Object object) {
		return Long.parseLong(object.toString());
	}
}
