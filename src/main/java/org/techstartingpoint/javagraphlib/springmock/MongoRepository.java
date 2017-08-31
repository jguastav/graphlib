package org.techstartingpoint.javagraphlib.springmock;

import org.techstartingpoint.javagraphlib.graph.GraphNodeType;
import org.techstartingpoint.javagraphlib.execution.GraphProcessSet;

public interface MongoRepository<T, T1> {
    GraphProcessSet save(GraphProcessSet job);

    GraphNodeType save(GraphNodeType elementType);
}
