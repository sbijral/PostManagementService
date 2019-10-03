package com.yolo.PostManagementService.utility;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ApiResponseStatus {
    SUCCESS(1000, "Success");

    private final int code;
    private final String message;
    ApiResponseStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @JsonValue
    public ApiResponseStatusToJson toJson() {
        ApiResponseStatusToJson apiResponseStatusToJson = new ApiResponseStatusToJson();
        apiResponseStatusToJson.setCode(this.code);
        apiResponseStatusToJson.setMessage(this.message);
        return apiResponseStatusToJson;
    }

}