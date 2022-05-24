package com.example.sel.exception;


public enum SELErrorCode {

    API1("API1","Error to contact API"),
    API2("API2","Response not OK"),
    ;

    private String errorCode;
    private String description;

    SELErrorCode(String errorCode, String description) {
        this.errorCode = errorCode;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getMessage(){
        return String.format("%S : %S",errorCode,description);
    }
}
