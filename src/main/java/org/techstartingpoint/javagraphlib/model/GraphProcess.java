
package org.techstartingpoint.javagraphlib.model;


import java.util.ArrayList;
import java.util.List;


/**
 * Activity :
 * Each activity has a set of nodeList (excomponents) and connectorList between them that can be executed and passed data
 *  
 * @author Jose Alberto Guastavino
 *
 */
public class GraphProcess {

	
	public static String NAME_PROPERTY="name";

	private String id;
	private String name;
	
	private List<GraphNode> nodeList;

	
	private List<GraphConnection> connectorList;


	private GraphProcess() {}
	
	public GraphProcess(String id,  String name, List<GraphNode> nodeList, List<GraphConnection> connectorList) {
		this.id=id;
		this.name=name;
		this.nodeList = nodeList;
		this.connectorList = connectorList;
	}

	
	public GraphProcess(String id, String name) {
		this(id,name,new ArrayList<GraphNode>(),new ArrayList<GraphConnection>());
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

	public List<GraphNode> getNodeList() {
		return nodeList;
	}

	public void setNodeList(List<GraphNode> nodeList) {
		this.nodeList = nodeList;
	}
	public List<GraphConnection> getConnectorList() {
		return connectorList;
	}

	public void setConnectorList(List<GraphConnection> connectorList) {
		this.connectorList = connectorList;
	}

	@Override
	public String toString() {
		return "\n\nGraphProcess [id=" + id + ", name=" + name
				+ ", \n nodeList=" + nodeList + ", \n connectorList=" + connectorList + "]";
	}
	
	
	
}
