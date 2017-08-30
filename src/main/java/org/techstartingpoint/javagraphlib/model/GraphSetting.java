
package org.techstartingpoint.javagraphlib.model;



/**
 * Class to store seting data
 * @author Jose Alberto Guastavino
 *
 */

public class GraphSetting {
	private String id;
	
	private NodeElementSetting nodeElement;
	private boolean initalizeDatabase;
	
	private GraphSetting() {}
	
	public GraphSetting(NodeElementSetting nodeElement) {
		this.nodeElement=nodeElement;
		this.initalizeDatabase=false;
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public NodeElementSetting getNodeElement() {
		return nodeElement;
	}

	public void setNodeElement(NodeElementSetting nodeElement) {
		this.nodeElement = nodeElement;
	}

	public boolean isInitalizeDatabase() {
		return initalizeDatabase;
	}

	public void setInitalizeDatabase(boolean initalizeDatabase) {
		this.initalizeDatabase = initalizeDatabase;
	}
	
	
	
	
}
