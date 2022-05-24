package com.example.sel.exception;


public class SELException  extends Exception{

    SELErrorCode errorCode;

    public SELException( SELErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public SELException(SELErrorCode errorCode, Throwable cause) {
        super(errorCode.getMessage(), cause);
        this.errorCode = errorCode;
    }
}
