package com.tumoji.tumoji.utils;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by souler on 16-12-16.
 */
public class ErrorType implements Serializable {
    @SerializedName("error")
    private ErrorBean error;

    public ErrorBean getError() {
        return error;
    }

    public void setError(ErrorBean error) {
        this.error = error;
    }

    public static class ErrorBean {
        @SerializedName("name")
        private String name;
        @SerializedName("status")
        private int status;
        @SerializedName("message")
        private String message;
        @SerializedName("statusCode")
        private int statusCode;
        @SerializedName("code")
        private String code;
        @SerializedName("stack")
        private String stack;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(int statusCode) {
            this.statusCode = statusCode;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getStack() {
            return stack;
        }

        public void setStack(String stack) {
            this.stack = stack;
        }
    }
}
