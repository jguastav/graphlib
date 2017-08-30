package org.techstartingpoint.javagraphlib.model;

public class Port {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class getExpectedClass() {
        return expectedClass;
    }

    public void setExpectedClass(Class expectedClass) {
        this.expectedClass = expectedClass;
    }

    String name;
    Node parent;
    Class expectedClass;





    public Class getClazz() {
        return expectedClass;
    }

    public void setClazz(Class clazz) {
        this.expectedClass = clazz;
    }


    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }



}
