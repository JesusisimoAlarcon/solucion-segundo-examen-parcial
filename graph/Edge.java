package graph;

public class Edge {
    private Vertex v1, v2;
    private double weight;

    public Edge(Vertex v1, Vertex v2, double weight) {
        this.v1 = v1;
        this.v2 = v2;
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public Vertex getV1() {
        return v1;
    }

    public Vertex getV2() {
        return v2;
    }

    public String toString() {
        return "Edge={v1={" + v1.getLabel() + "},v2={" + v2.getLabel() + "},weight={" + weight + "}}";
    }
}
