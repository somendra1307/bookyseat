package com.app.agritech.constant;

public enum UserType {

	ADMIN("admin", 1), END_USER("end_user", 2);

	UserType(String type, int code) {
		this.type = type;
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public int getCode() {
		return code;
	}

	private String type;
	private int code;

}
