
package org.techstartingpoint.javagraphlib.execution;


import org.techstartingpoint.javagraphlib.graph.GraphConnection;

/**
 * Connection element in an execution environment.
 * The difference with the GraphConnection is that identifies source and target flowElements using the id assigned during execution that considerates also the subflows with the notation idFlowElement.idSubFlow
 * @author Jose Alberto Guastavino
 *
 */
public class GraphExecutionConnection extends GraphConnection {

	private String sourceExecutionId;
	private String targetExecutionId;
	

	/**
	 * Generates a new connector element pointing to the index assigned in the running environment
	 * @param graphConnection
	 */
	public GraphExecutionConnection(GraphConnection graphConnection) {
		super(graphConnection.getId(), graphConnection.getSourceElement(), graphConnection.getSourceIndex(), graphConnection.getTargetElement(), graphConnection.getTargetIndex());
		this.setSourceExecutionId(graphConnection.getSourceElement().getId().toString());
		this.setTargetExecutionId(graphConnection.getTargetElement().getId().toString());
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
		return String.format("GraphExecutionConnection [sourceExecutionId=%s, targetExecutionId=%s]\n"+super.toString(), sourceExecutionId,
				targetExecutionId);
	}
	
	
	

}
