package com.onelake.api.error;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ravi on 8/17/17.
 */
public class OnelakeException extends Exception {
    private final ErrorCode errorCode;
    private final Map<String, Object> errorData = new HashMap<String, Object>();

    private OnelakeException(ErrorCode errorCode, Throwable cause)
    {
        super(errorCode.getMessage(), cause);
        this.errorCode = errorCode;
    }

    private OnelakeException(ErrorCode errorCode)
    {
        this.errorCode = errorCode;
    }

    /**
     *
     * @param errorCode
     * @param cause
     * @return
     */
    public static OnelakeException build(ErrorCode errorCode, Throwable cause)
    {
        return  new OnelakeException(errorCode,cause);
    }

    /**
     *
     * @param errorCode
     * @return
     */
    public static OnelakeException build(ErrorCode errorCode)
    {
        return  new OnelakeException(errorCode);
    }

    /**
     * set method allows callers to set multiple value available during error
     * @param key
     * @param value
     * @return
     */
    public OnelakeException set(String key, Object value) {
        errorData.put(key,value);
        return this;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public Map<String, Object> getErrorData() {
        return errorData;
    }

    @Override
    public String toString()
    {
        StringWriter stringWriter = new StringWriter();
        stringWriter.write(super.getMessage());
        if (super.getCause() != null) {
            stringWriter.write(String.format("\ncaused by %s", super.getCause().toString()));
            stringWriter.write(String.format("\nError Code: %d, Error message:%s", errorCode.getCode(), errorCode.getMessage()));
        }

        for (String key : errorData.keySet()) {
            stringWriter.write(String.format("\n%s:%s", key, errorData.get(key).toString()));
        }
        return stringWriter.toString();
    }

}
