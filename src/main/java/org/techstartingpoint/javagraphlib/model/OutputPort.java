package org.techstartingpoint.javagraphlib.model;


public class OutputPort<T> extends Port {


    T value;

    String targetId;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public String getTarget() {
        return targetId;
    }

    public void setTarget(String targetId) {
        this.targetId = targetId;
    }

    public void sendMessage(Object value,int index) {
        // TODO: implement
    };
}