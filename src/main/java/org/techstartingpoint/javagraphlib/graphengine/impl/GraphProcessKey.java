
package org.techstartingpoint.javagraphlib.graphengine.impl;



/**
 * Job Name / Activity Name pair to be used as identification in running processes
 * @author Jose Alberto Guastavino
 *
 */
public class GraphProcessKey {

	String workflowId;
	public String getActivitId() {
		return workflowId;
	}
	public void setWorkflowId(String workflowId) {
		this.workflowId = workflowId;
	}
	
	
	
	
	
	
	public GraphProcessKey( String workflowId) {
		super();
		this.workflowId = workflowId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((workflowId == null) ? 0 : workflowId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GraphProcessKey other = (GraphProcessKey) obj;
		if (workflowId == null) {
			if (other.workflowId != null)
				return false;
		} else if (!workflowId.equals(other.workflowId))
			return false;
		return true;
	}
	
	
	
}
