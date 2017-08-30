
package org.techstartingpoint.javagraphlib.model;

import java.util.List;




/**
 * Data structure to persist job information
 * @author Jose Alberto Guastavino
 *
 */
public class GraphProcessSet {
	private String id;

	private String name;
	
	
	private List<GraphProcess> activities;
	
	private GraphProcessSet() {}
	
	
	public GraphProcessSet(String name) {
		this.name=name;
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


	public List<GraphProcess> getActivities() {
		return activities;
	}


	public void setActivities(List<GraphProcess> activities) {
		this.activities = activities;
	}
	
	
	
    @Override
	public String toString() {
    	/*
		return "GraphProcessSet [id=" + id + ", name=" + name + ", activities="
				+ activities + "]";
				*/
		return "GraphProcessSet [id=" + id + ", name=" + name + ", activities="
		 +activities+ "]";
	}
}
