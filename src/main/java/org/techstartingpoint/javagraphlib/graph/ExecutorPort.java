package org.techstartingpoint.javagraphlib.graph;

public class ExecutorPort<T> {
    String name;
    T value;
    boolean setted=false;

    public ExecutorPort( String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }


    public boolean isSetted() {

        return setted;
    }

    public void setSetted(boolean setted) {
        this.setted = setted;
    }

    @Override
    public String toString() {
        return "ExecutorPort{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
