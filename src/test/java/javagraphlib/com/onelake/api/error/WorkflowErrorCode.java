package javagraphlib.com.onelake.api.error;

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