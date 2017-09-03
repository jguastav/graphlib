package com.onelake.workflowexecutor.error;

import com.onelake.api.error.ErrorCode;

/**
 * Created by ravi on 8/17/17.
 */
public enum WorkflowErrorCode implements ErrorCode {

    NoSuchVertex(101,"No Such Edge found"),
    ClassNotInstantiable(102, "Class cannot be instantiated" ),
    ClassNotFound(102, "Class not found"),
    JsonParserError(103, "Json parse error"),
    InsufficientCommandOptions(104, "Failed to parse command line options"),
    FileNotFound(105, "File not found"),
    UnableToReadFile(106, "Unable to read file"),
    IOException(107,"Error accessing filesystem"),
    URISyntaxError(108,"URI Syntax Error"),
    IllegalAccess(109,"Cannot access the class"),
    ZeroNodesGraph(110,"Graph contains 0 nodes"),
    NoImplementationName(111,"Node does not have implementation name"),
    WrongIdNodeOnConnector(112,"Wrong node Id on connector"),
    GraphHasCycle(113,"Graph contains at least one cycle"),
    UnconnectedNodes(114,"There are unconnected nodes in the workflow");

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
