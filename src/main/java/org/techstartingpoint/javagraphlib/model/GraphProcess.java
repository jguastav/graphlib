
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

	
	
	private Long id;
	private String name;
	
	/**
	 * This index can be changed. Name inside a job should be unique
	 */
	private Integer index;
	

	private List<GraphNode> nodeList;

	
	private List<GraphConnector> connectorList;


	private GraphProcess() {}
	
	public GraphProcess(Long id, Integer index, String name, List<GraphNode> nodeList, List<GraphConnector> connectorList) {
		this.id=id;
		this.index=index;
		this.name=name;
		this.nodeList = nodeList;

		this.connectorList = connectorList;

	}

	
	public GraphProcess(Long id, Integer index, String name) {
		this(id,index,name,new ArrayList<GraphNode>(),new ArrayList<GraphConnector>());
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
	public List<GraphConnector> getConnectorList() {
		return connectorList;
	}

	public void setConnectorList(List<GraphConnector> connectorList) {
		this.connectorList = connectorList;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	@Override
	public String toString() {
		return "\n\nGraphProcess [id=" + id + ", name=" + name + ", index=" + index
				+ ", \n nodeList=" + nodeList + ", \n connectorList=" + connectorList + "]";
	}
	
	
	
}
