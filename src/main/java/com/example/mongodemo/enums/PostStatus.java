package com.example.mongodemo.enums;

public enum PostStatus {

	PENDING_APPROVAL("Pending Approval"),
	APPROVED("Approved"),
	CLOSED("Closed");

	private String value;

	PostStatus(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
