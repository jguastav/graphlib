package org.techstartingpoint.javagraphlib.model.json;

public class Connection {
    Port from;
    Port to;

    public Port getFrom() {
        return from;
    }

    public void setFrom(Port from) {
        this.from = from;
    }

    public Port getTo() {
        return to;
    }

    public void setTo(Port to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "\n\tConnection{" +
                "from=" + from +
                ", to=" + to +
                '}';
    }
}
