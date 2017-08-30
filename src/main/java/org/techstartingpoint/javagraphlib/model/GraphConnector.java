
package org.techstartingpoint.javagraphlib.model;



/**
 * 
 * Connetor between elements
 * A connection between data elements (index >=1) means the data produced in source element will be passed to target elements
 * A connection between and async output and a target element means the target element will be launched as soon as source element starts
 * Data Messages are not sent at the end of the element execution but when the element execute this.setOutput(index,message)
 * 
 * @author Jose Alberto Guastavino
 *
 */

public class GraphConnector {
	
	private Long id;
	private GraphNode sourceElement;
	private int sourceIndex;
	private GraphNode targetElement;
	private int targetIndex;
	private boolean deleted;
	private String sourceId;
	private String targetId;
	private Boolean asyncInterface;

	private GraphConnector() {}
	
	/**
	 * Constructor for not async connectors
	 * @param id
	 * @param sourceElement
	 * @param sourceIndex
	 * @param targetElement
	 * @param targetIndex
	 * 
	 * @author Jose Alberto Guastavino
	 *
	 */
	public GraphConnector(Long id, GraphNode sourceElement, int sourceIndex, GraphNode targetElement, int targetIndex) {
		this.id=id;
		this.targetIndex=targetIndex;
		this.sourceIndex=sourceIndex;
		this.deleted=false;
		this.asyncInterface=false;
		this.setSourceElement(sourceElement);
		this.setTargetElement(targetElement);
	}

	/**
	 * Constructor for asynchronic connectors
	 * @param id
	 * @param sourceElement
	 * @param targetElement
	 * 
	 * @autthor Jose Alberto Guastavino
	 */
	public GraphConnector(Long id, GraphNode sourceElement, GraphNode targetElement) {
		this.id=id;
		this.targetIndex=1;
		this.sourceIndex=-1;
		this.deleted=false;
		this.asyncInterface=true;
		this.setSourceElement(sourceElement);
		this.setTargetElement(targetElement);
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getSourceIndex() {
		return sourceIndex;
	}

	public void setSourceIndex(int sourceIndex) {
		this.sourceIndex = sourceIndex;
	}

	public int getTargetIndex() {
		return targetIndex;
	}

	public void setTargetIndex(int targetIndex) {
		this.targetIndex = targetIndex;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public GraphNode getSourceElement() {
		return sourceElement;
	}

	public void setSourceElement(GraphNode sourceElement) {
		this.sourceElement = sourceElement;
		if (sourceElement!=null) {
			this.setSourceId(sourceElement.getId());
		}
	}

	public GraphNode getTargetElement() {
		return targetElement;
	}

	public void setTargetElement(GraphNode targetElement) {
		this.targetElement = targetElement;
		if (targetElement!=null) {
			this.setTargetId(targetElement.getId());
		}
	}





	public String getSourceId() {
		return sourceId;
	}


	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}	
	
	
	@Override
	public String toString() {
		return "GraphConnector [id=" + id + ", \nsourceElement=" + sourceElement
				+ ", \nsourceIndex=" + sourceIndex + ", \ntargetElement="
				+ targetElement + ", \ntargetIndex=" + targetIndex + ", deleted="
				+ deleted + ", \nsourceId=" + sourceId + ", \ntargetId=" + targetId
				+ "]";
	}

	public Boolean getAsyncInterface() {
		return asyncInterface;
	}

	public void setAsyncInterface(Boolean async) {
		this.asyncInterface = async;
	}

	
	}
	

