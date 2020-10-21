package graph;

import data.structure.ListLinked;
import util.StatusVertex;

public class Vertex {
    private String label;
    private ListLinked<Edge> edges;
    private StatusVertex status;
    private int jumps;
    private Vertex parent;
    private double dijkstraValue;

    public Vertex(String label) {
        this.label = label;
        this.jumps = 0;
        this.parent = null;
        this.dijkstraValue = Double.MAX_VALUE;
        edges = new ListLinked<>();
        status = StatusVertex.UNVISITED;
    }

    public double getDijkstraValue() {
        return dijkstraValue;
    }

    public void setDijkstraValue(double dijkstraValue) {
        this.dijkstraValue = dijkstraValue;
    }

    public Vertex getParent() {
        return parent;
    }

    public void setParent(Vertex parent) {
        this.parent = parent;
    }

    public void setJump(int jumps) {
        this.jumps = jumps;
    }

    public int getJumps() {
        return jumps;
    }

    public void addEdge(Vertex v1, Vertex v2, double weight) {
        Edge edge = new Edge(v1, v2, weight);
        edges.add(edge);
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public StatusVertex getStatus() {
        return status;
    }

    public void setStatus(StatusVertex status) {
        this.status = status;
    }

    public ListLinked<Edge> getEdges() {
        return edges;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return "Vertex={label={" + label + "},edges={" + edges + "}}";
    }
    /*
     * @Override public String toString() { return "Vertex={label={" + label + "}";
     * }
     */

}
