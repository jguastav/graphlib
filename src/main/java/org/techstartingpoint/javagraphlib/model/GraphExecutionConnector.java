
package org.techstartingpoint.javagraphlib.model;


/**
 * Connection element in an execution environment.
 * The difference with the GraphConnector is that identifies source and target flowElements using the id assigned during execution that considerates also the subflows with the notation idFlowElement.idSubFlow
 * @author Jose Alberto Guastavino
 *
 */
public class GraphExecutionConnector extends GraphConnector {

	private String sourceExecutionId;
	private String targetExecutionId;
	

	/**
	 * Generates a new connector element pointing to the index assigned in the running environment
	 * @param graphConnector
	 * @param prefix
	 */
	public GraphExecutionConnector(GraphConnector graphConnector, String prefix) {
		super(graphConnector.getId(), graphConnector.getSourceElement(), graphConnector.getSourceIndex(), graphConnector.getTargetElement(), graphConnector.getTargetIndex());
		this.setSourceExecutionId(prefix+ graphConnector.getSourceElement().getId().toString());
		this.setTargetExecutionId(prefix+ graphConnector.getTargetElement().getId().toString());
	};

	public String getSourceExecutionId() {
		return sourceExecutionId;
	}

	public void setSourceExecutionId(String sourceExecutionId) {
		this.sourceExecutionId = sourceExecutionId;
	}

	public String getTargetExecutionId() {
		return targetExecutionId;
	}

	public void setTargetExecutionId(String targetExecutionId) {
		this.targetExecutionId = targetExecutionId;
	}

	@Override
	public String toString() {
		return String.format("GraphExecutionConnector [sourceExecutionId=%s, targetExecutionId=%s]\n"+super.toString(), sourceExecutionId,
				targetExecutionId);
	}
	
	
	

}
