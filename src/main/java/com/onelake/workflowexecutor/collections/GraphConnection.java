
package com.onelake.workflowexecutor.collections;


import com.onelake.workflowexecutor.api.AbstractMainExecutor;

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

public class GraphConnection {
	
	private Long id;
	private AbstractMainExecutor sourceElement;
	private int sourceIndex;
	private AbstractMainExecutor targetElement;
	private int targetIndex;
	private String sourceId;
	private String targetId;


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
	public GraphConnection(Long id, AbstractMainExecutor sourceElement, int sourceIndex, AbstractMainExecutor targetElement, int targetIndex) {
		this.id=id;
		this.targetIndex=targetIndex;
		this.sourceIndex=sourceIndex;
		this.setSourceElement(sourceElement);
		this.setTargetElement(targetElement);
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

	public AbstractMainExecutor getSourceElement() {
		return sourceElement;
	}

	public void setSourceElement(AbstractMainExecutor sourceElement) {
		this.sourceElement = sourceElement;
		if (sourceElement!=null) {
			this.setSourceId(sourceElement.getNodeId());
		}
	}

	public AbstractMainExecutor getTargetElement() {
		return targetElement;
	}

	public void setTargetElement(AbstractMainExecutor targetElement) {
		this.targetElement = targetElement;
		if (targetElement!=null) {
			this.setTargetId(targetElement.getNodeId());
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
		return "GraphConnection [id=" + id + ", \nsourceElement=" + sourceElement
				+ ", \nsourceIndex=" + sourceIndex + ", \ntargetElement="
				+ targetElement + ", \ntargetIndex=" + targetIndex
				+ ", \nsourceId=" + sourceId + ", \ntargetId=" + targetId
				+ "]";
	}


	
	}
	

