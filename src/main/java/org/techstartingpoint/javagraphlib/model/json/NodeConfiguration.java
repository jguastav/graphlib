package org.techstartingpoint.javagraphlib.model.json;

import java.util.List;

public class NodeConfiguration {
    String dataset;
    String dataframe;
    List<JoinTableSpecification> joinTableSepcs;

    public String getDataset() {
        return dataset;
    }

    public void setDataset(String dataset) {
        this.dataset = dataset;
    }

    public String getDataframe() {
        return dataframe;
    }

    public void setDataframe(String dataframe) {
        this.dataframe = dataframe;
    }

    public List<JoinTableSpecification> getJoinTableSepcs() {
        return joinTableSepcs;
    }

    public void setJoinTableSepcs(List<JoinTableSpecification> joinTableSepcs) {
        this.joinTableSepcs = joinTableSepcs;
    }

    @Override
    public String toString() {
        return "NodeConfiguration{" +
                "dataset='" + dataset + '\'' +
                ", dataframe='" + dataframe + '\'' +
                ", joinTableSepcs=" + joinTableSepcs +
                '}';
    }
}
