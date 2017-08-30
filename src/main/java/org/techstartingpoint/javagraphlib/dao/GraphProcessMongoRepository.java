
package org.techstartingpoint.javagraphlib.dao;

import org.techstartingpoint.javagraphlib.model.GraphProcessSet;
import org.techstartingpoint.javagraphlib.springmock.MongoRepository;
import org.techstartingpoint.javagraphlib.springmock.QueryDslPredicateExecutor;

import java.util.List;


/**
 * Job  Mongo Repository interface 
 * @author Jose Alberto Guastavino
 *
 */
public interface GraphProcessMongoRepository extends MongoRepository<GraphProcessSet, String>,QueryDslPredicateExecutor<GraphProcessSet> {
	/**
	 * Find unique job given the name
	 * @param name
	 * @return
	 */
	public GraphProcessSet findByName(String name);


	public GraphProcessSet findOne(String id);
}
