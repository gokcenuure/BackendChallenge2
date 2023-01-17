package com.enoca.backendChallenge2.results;

public class ErrorResult extends Result{

    private String errorCode;
    private String errorMessage;

    public ErrorResult(String errorCode, String errorMessage) {
        super();
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public ErrorResult(String s) {
        super(false);
    }

    public ErrorResult(boolean success, String message) {
        super(success, message);
    }

    public ErrorResult(boolean success) {
        super(success);
    }
    public String getErrorCode() {
        return errorCode;
    }
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
    public String getErrorMessage() {
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
