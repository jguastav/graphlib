
package org.techstartingpoint.javagraphlib.dao;
import org.techstartingpoint.javagraphlib.model.GraphNodeType;
import org.techstartingpoint.javagraphlib.springmock.MongoRepository;

import java.util.List;

/**
 * Tool Mongo Repository interface 
 * @author Jose Alberto Guastavino
 */


public interface GraphElementTypeRepository extends MongoRepository<GraphNodeType, String> {
	public GraphNodeType findByName(String name);

	public GraphNodeType findByClassName(String className);
	
	public List<GraphNodeType> findByJobName(String jobName);
}
