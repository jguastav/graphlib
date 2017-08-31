
package org.techstartingpoint.javagraphlib.model;

import org.techstartingpoint.javagraphlib.model.json.NodeConfiguration;

/**
 * Information about main element
 * This information is intended to be persisted and used in model frontend
 * @author Jose Alberto Guastavino
 *
 */
public class GraphNode {
	private String id;
	
	/**
	 * Name in the graph. this name is not an identifier as identifiers can be present in implementation
	 * 
	 */
    private String name;
	private GraphNodeType type;
	private int inputPorts;
	private int outputPorts;
	private boolean deleted;
	String environmentKey;
	NodeConfiguration nodeConfiguration;


	private GraphNode() {}
	
	public GraphNode(String id,
					 String name,
					 GraphNodeType elementType,
					 String environmentKey,
					 NodeConfiguration nodeConfiguration) {
		this.id = id;
	    this.name = name;
		this.type = elementType;
		this.inputPorts=elementType.getInputPorts();
		this.outputPorts=elementType.getOutputPorts();
		this.deleted=false;
		this.environmentKey=environmentKey;
		this.nodeConfiguration=nodeConfiguration;
	}

	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GraphNodeType getType() {
		return type;
	}

	public void setType(GraphNodeType type) {
		this.type = type;
	}

	public int getInputPorts() {
		return inputPorts;
	}

	public void setInputPorts(int inputPorts) {
		this.inputPorts = inputPorts;
	}

	public int getOutputPorts() {
		return outputPorts;
	}

	public void setOutputPorts(int outputPorts) {
		this.outputPorts = outputPorts;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public String getEnvironmentKey() {
		return environmentKey;
	}

	public void setEnvironmentKey(String environmentKey) {
		this.environmentKey = environmentKey;
	}

	public NodeConfiguration getNodeConfiguration() {
		return nodeConfiguration;
	}

	public void setNodeConfiguration(NodeConfiguration nodeConfiguration) {
		this.nodeConfiguration = nodeConfiguration;
	}


    @Override
    public String toString() {
        return "GraphNode{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", inputPorts=" + inputPorts +
                ", outputPorts=" + outputPorts +
                ", deleted=" + deleted +
                ", environmentKey='" + environmentKey + '\'' +
                ", nodeConfiguration=" + nodeConfiguration +
                '}';
    }
}
