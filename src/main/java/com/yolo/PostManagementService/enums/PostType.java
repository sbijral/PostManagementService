package com.yolo.PostManagementService.enums;

public enum PostType {

	SEEKER("Seeker"),
	CONTRIBUTOR("Contributor");

	private String value;

	PostType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}