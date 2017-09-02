package org.techstartingpoint.javagraphlib.execution;

import org.techstartingpoint.javagraphlib.graph.AbstractMainExecutor;

import java.util.HashMap;
import java.util.Map;

public class ComponentSystem {

    Map<String,AbstractMainExecutor> components;

    public AbstractMainExecutor register(String componentInstanceName,AbstractMainExecutor executor) {
        components.put(componentInstanceName,executor);
        return executor;
    }

    private ComponentSystem() {
        this.components=new HashMap<String,AbstractMainExecutor>();
    }

    public static ComponentSystem create() {
        ComponentSystem result=new ComponentSystem();
        return result;
    }

    public Map<String, AbstractMainExecutor> getComponents() {
        return components;
    }

}
