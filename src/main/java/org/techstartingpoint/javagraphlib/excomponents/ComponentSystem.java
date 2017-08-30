package org.techstartingpoint.javagraphlib.excomponents;

import org.techstartingpoint.javagraphlib.components.core.AbstractMainExecutor;

import java.util.HashMap;
import java.util.Map;

public class ComponentSystem {

    String name;
    Map<String,AbstractMainExecutor> components;

    public AbstractMainExecutor register(String componentInstanceName,AbstractMainExecutor executor) {
        components.put(componentInstanceName,executor);
        return executor;
    }

    private ComponentSystem(String name) {
        this.name=name;
        this.components=new HashMap<String,AbstractMainExecutor>();
    }

    public static ComponentSystem create(String name) {
        ComponentSystem result=new ComponentSystem(name);
        return result;
    }

    public void stop(AbstractMainExecutor currentComponent) {
    }

    public void terminate() {
    }
}
