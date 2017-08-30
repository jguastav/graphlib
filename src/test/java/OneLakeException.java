import com.google.gson.Gson;
import com.onelake.api.error.OnelakeException;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public interface ErrorCode {
    public int getCode();
    public String getMessage();
}
    I have added code for OnelakeException and ErrorCode
public enum WorkflowErrorCode implements ErrorCode {

    NoSuchVertex(101,"No Such Vertex found"),
    ClassNotInstantiable(102, "Class cannot be instantiated" ),
    ClassNotFound(102, "Class not found"),
    JsonParserError(103, "Json parse error"),
    InsufficientCommandOptions(104, "Failed to parse command line options"),
    FileNotFound(105, "File not found"),
    UnableToReadFile(106, "Unable to read file");

    private final int code;
    private final String message;

    WorkflowErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
}