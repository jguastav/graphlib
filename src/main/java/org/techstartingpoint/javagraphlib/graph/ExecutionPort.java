package org.techstartingpoint.javagraphlib.graph;

public class ExecutionPort {
    int index;
    String name;
    Object value;
    Class allowedClass;
    boolean setted;

    public ExecutionPort(int index,String name, Object value, Class allowedClass,boolean setted) {
        this.index = index;
        this.name = name;
        this.value = value;
        this.allowedClass = allowedClass;
        this.setted=setted;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Class getAllowedClass() {
        return allowedClass;
    }

    public void setAllowedClass(Class allowedClass) {
        this.allowedClass = allowedClass;
    }

    public boolean isSetted() {
        return setted;
    }

    public void setSetted(boolean setted) {
        this.setted = setted;
    }

    @Override
    public String toString() {
        return "ExecutionPort{" +
                "name='" + name + '\'' +
                ", value=" + value +
                ", allowedClass=" + allowedClass +
                '}';
    }
}
