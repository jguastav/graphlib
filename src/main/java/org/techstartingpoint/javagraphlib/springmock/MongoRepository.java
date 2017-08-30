package org.techstartingpoint.javagraphlib.springmock;

import org.techstartingpoint.javagraphlib.model.GraphNodeType;
import org.techstartingpoint.javagraphlib.model.GraphProcessSet;

public interface MongoRepository<T, T1> {
    GraphProcessSet save(GraphProcessSet job);

    GraphNodeType save(GraphNodeType elementType);
}
