package graph;

import java.io.File;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import data.structure.ListLinked;
import data.structure.Node;
import util.StatusVertex;

public class Graph {
    // Vertex<E>[] vertexs = new Vertex[5];
    private Vertex[] vertexs;
    private int size;
    private boolean directed;
    private ListLinked<Vertex> vertexsList;
    private ListLinked<Edge> edgeList;
    private boolean isCyclic;
    private boolean isConnected;
    private int connectedComponents;

    /*
     * private int numVertexs;
     * 
     * public Graph(int numVertexs, boolean directed) { this.numVertexs =
     * numVertexs; this.directed = directed; vertexsList = new ListLinked<>(); }
     */

    public Graph(boolean directed) {
        this.directed = directed;
        vertexsList = new ListLinked<>();
        edgeList = new ListLinked<>();
    }

    public ListLinked<Vertex> getVertexsList() {
        return vertexsList;
    }

    public ListLinked<Edge> getEdgesList() {
        return edgeList;
    }

    public String showVertex() {
        String output = "";
        Node<Vertex> vNode = vertexsList.getHead();
        while (vNode != null) {
            output += "<<" + vNode.getData().getLabel() + ">>\n";
            vNode = vNode.getLink();
        }
        return output;
    }

    public String showEdges() {
        String output = "";
        Node<Edge> eNode = edgeList.getHead();
        while (eNode != null) {
            output += "<<" + eNode.getData().getV1().getLabel() + ">> <<" + eNode.getData().getV2().getLabel() + ">>\n";
            eNode = eNode.getLink();
        }
        return output;
    }

    public boolean isDirected() {
        return directed;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public boolean isCyclic() {
        DFS();
        return isCyclic;
    }

    public boolean isConnected() {
        if (directed) {

        } else {
            DFS();
        }
        return isConnected;
    }

    public void addVertex(Vertex vertex) {
        vertexsList.add(vertex);
    }

    public String BFS(Vertex vertexStart) {
        ListLinked<Vertex> travelDFS = new ListLinked<>();
        Queue<Vertex> queue = new LinkedList<>();
        vertexStart.setStatus(StatusVertex.VISITED);
        queue.add(vertexStart);
        travelDFS.add(vertexStart);
        while (!queue.isEmpty()) {
            Vertex vertex = queue.poll();
            Node<Edge> node = vertex.getEdges().getHead();
            while (node != null) {
                Vertex oppositeVertex = node.getData().getV2();
                if (oppositeVertex.getStatus() == StatusVertex.UNVISITED) {
                    oppositeVertex.setStatus(StatusVertex.VISITED);
                    oppositeVertex.setJump(vertex.getJumps() + 1);
                    queue.add(oppositeVertex);
                    travelDFS.add(oppositeVertex);
                }
                node = node.getLink();
            }
            vertex.setStatus(StatusVertex.PROCESSING);
        }
        System.out.println(travelDFS.size());
        String output = "";
        Node<Vertex> temp = travelDFS.getHead();
        while (temp != null) {
            output += "<<" + temp.getData().getLabel() + "{" + temp.getData().getJumps() + "}>> ";
            temp = temp.getLink();
        }
        return output;
    }

    public void DFS_Stack(Vertex vertexStart) {
        ListLinked<Vertex> travelDFS = new ListLinked<>();
        Stack<Vertex> stack = new Stack<>();
        vertexStart.setStatus(StatusVertex.VISITED);
        stack.push(vertexStart);
        travelDFS.add(vertexStart);
        while (!stack.isEmpty()) {
            Vertex vertex = stack.pop();
            Node<Edge> node = vertex.getEdges().getHead();
            while (node != null) {
                Vertex oppositVertex = node.getData().getV2();
                if (oppositVertex.getStatus() == StatusVertex.UNVISITED) {
                    oppositVertex.setStatus(StatusVertex.VISITED);
                    stack.push(oppositVertex);
                    travelDFS.add(oppositVertex);
                }
                node = node.getLink();
            }
            vertex.setStatus(StatusVertex.PROCESSING);
        }
    }

    public void Dijkstra(Vertex vertex) {
        PriorityQueue<Vertex> queue = new PriorityQueue<>();
        queue.offer(vertex);
        vertex.setStatus(StatusVertex.VISITED);
        vertex.setParent(vertex);
        while (!queue.isEmpty()) {
            vertex = queue.poll();
            
        }
    }

    public void DFS(Vertex vertex, ListLinked<Vertex> travelDFS) {
        vertex.setStatus(StatusVertex.VISITED);
        travelDFS.add(vertex);
        Node<Edge> node = vertex.getEdges().getHead();
        while (node != null) {
            Vertex oppositVertex = node.getData().getV2();
            if (oppositVertex.getStatus().compareTo(StatusVertex.UNVISITED) == 0) {
                oppositVertex.setStatus(StatusVertex.VISITED);
                DFS(oppositVertex, travelDFS);
            } else if (oppositVertex.getStatus().compareTo(StatusVertex.PROCESSING) == 0) {
                System.out.println(oppositVertex.getLabel());
                isCyclic = true;
            }
            node = node.getLink();
        }
        vertex.setStatus(StatusVertex.PROCESSING);
    }

    public String DFS(Vertex vertex) {
        ListLinked<Vertex> travelDFS = new ListLinked<>();
        DFS(vertex, travelDFS);
        String output = "";
        Node<Vertex> temp = travelDFS.getHead();
        while (temp != null) {
            output += "<<" + temp.getData().getLabel() + "{" + temp.getData().getJumps() + "}>> ";
            temp = temp.getLink();
        }
        return output;
    }

    public String DFS() {
        ListLinked<Vertex> travelDFS = new ListLinked<>();
        Node<Vertex> node = vertexsList.getHead();
        connectedComponents = 0;
        isConnected = false;
        isCyclic = false;
        while (node != null) {
            Vertex vertex = node.getData();
            if (vertex.getStatus().compareTo(StatusVertex.UNVISITED) == 0) {
                connectedComponents++;
                isConnected = connectedComponents == 1;
                DFS(vertex, travelDFS);
            }
            node = node.getLink();
        }
        String output = "";
        Node<Vertex> temp = travelDFS.getHead();
        while (temp != null) {
            output += "<<" + temp.getData().getLabel() + "{" + temp.getData().getJumps() + "}>> ";
            temp = temp.getLink();
        }
        return output;
    }

    public void getShortPath(Vertex finish) {
        while (finish != null) {
            System.out.print(finish.getLabel() + "\t");
            finish = finish.getParent();
        }
    }

    public String BFS() {
        ListLinked<Vertex> travelBFS = new ListLinked<>();
        Queue<Vertex> queue = new LinkedList<>();
        Node<Vertex> verNode = vertexsList.getHead();
        while (verNode != null) {
            Vertex vertexStart = verNode.getData();
            if (vertexStart.getStatus() == StatusVertex.UNVISITED) {
                vertexStart.setStatus(StatusVertex.VISITED);
                queue.add(vertexStart);
                travelBFS.add(vertexStart);
                while (!queue.isEmpty()) {
                    Vertex vertex = queue.poll();
                    Node<Edge> node = vertex.getEdges().getHead();
                    while (node != null) {
                        Vertex oppositeVertex = node.getData().getV2();
                        if (oppositeVertex.getStatus() == StatusVertex.UNVISITED) {
                            oppositeVertex.setStatus(StatusVertex.VISITED);
                            oppositeVertex.setParent(vertex);
                            oppositeVertex.setJump(vertex.getJumps() + 1);
                            queue.add(oppositeVertex);
                            travelBFS.add(oppositeVertex);
                        }
                        node = node.getLink();
                    }
                    vertex.setStatus(StatusVertex.PROCESSING);
                }
            }
            verNode = verNode.getLink();
        }
        String output = "";
        Node<Vertex> temp = travelBFS.getHead();
        while (temp != null) {
            output += "<<" + temp.getData().getLabel() + "{" + temp.getData().getJumps() + "}>> ";
            temp = temp.getLink();
        }

        return output;
    }

    public Vertex findVertex(String label) {
        Vertex vertex = null;
        for (int i = 0; i < vertexs.length; i++) {
            if (vertexs[i].getLabel().compareTo(label) == 0) {
                vertex = vertexs[i];
                break;
            }
        }
        return vertex;
    }

    /**
     * Determinacion de la ruta las corta en grafos no ponderados, dirigidos o no
     * dirigidos
     * 
     * @return
     */
    public String BFS_ShortRoute() {
        ListLinked<Vertex> travelBFS = new ListLinked<>();
        ListLinked<Edge> travelBFSEdges = new ListLinked<>();
        Queue<Vertex> queue = new LinkedList<>();
        Node<Vertex> verNode = vertexsList.getHead();
        while (verNode != null) {
            Vertex vertexStart = verNode.getData();
            if (vertexStart.getStatus() == StatusVertex.UNVISITED) {
                vertexStart.setStatus(StatusVertex.VISITED);
                queue.add(vertexStart);
                travelBFS.add(vertexStart);
                while (!queue.isEmpty()) {
                    Vertex vertex = queue.poll();
                    Node<Edge> node = vertex.getEdges().getHead();
                    while (node != null) {
                        Vertex oppositeVertex = node.getData().getV2();
                        if (oppositeVertex.getStatus() == StatusVertex.UNVISITED) {
                            oppositeVertex.setStatus(StatusVertex.VISITED);
                            oppositeVertex.setJump(vertex.getJumps() + 1);
                            queue.add(oppositeVertex);
                            travelBFS.add(oppositeVertex);
                            travelBFSEdges.add(node.getData());
                        }
                        node = node.getLink();
                    }
                    vertex.setStatus(StatusVertex.PROCESSING);
                }
            }
            verNode = verNode.getLink();
        }
        String output = "";
        Node<Edge> temp = travelBFSEdges.getHead();
        while (temp != null) {
            output += "<<" + temp.getData().getV1().getLabel() + "<-" + temp.getData().getV2().getLabel() + "("
                    + temp.getData().getV2().getJumps() + ")>> ";
            temp = temp.getLink();
        }
        return output;
    }

    public String BFS_ShortRoute(Vertex vertexStart, Vertex vertexEnd) {
        ListLinked<Vertex> travelBFS = new ListLinked<>();
        ListLinked<Edge> travelBFSEdges = new ListLinked<>();
        Queue<Vertex> queue = new LinkedList<>();
        vertexStart.setStatus(StatusVertex.VISITED);
        queue.add(vertexStart);
        travelBFS.add(vertexStart);
        while (!queue.isEmpty()) {
            Vertex vertex = queue.poll();
            Node<Edge> node = vertex.getEdges().getHead();
            while (node != null) {
                Vertex oppositeVertex = node.getData().getV2();
                if (oppositeVertex.getStatus() == StatusVertex.UNVISITED) {
                    oppositeVertex.setStatus(StatusVertex.VISITED);
                    oppositeVertex.setJump(vertex.getJumps() + 1);
                    queue.add(oppositeVertex);
                    travelBFS.add(oppositeVertex);
                    travelBFSEdges.add(node.getData());
                }
                node = node.getLink();
            }
            vertex.setStatus(StatusVertex.PROCESSING);
        }

        String output = "";
        Edge edge = findVertexV2InEdge(travelBFSEdges, vertexEnd);
        Vertex temp = edge.getV1();
        while (temp != null) {

        }
        return output;
    }

    public Edge findVertexV2InEdge(ListLinked<Edge> edgesList, Vertex vertex) {
        Node<Edge> eNode = edgesList.getHead();
        while (eNode != null && eNode.getData().getV2().getLabel().compareTo(vertex.getLabel()) == 0) {
            eNode = eNode.getLink();
        }
        return eNode.getData();
    }

    public Edge findVertexV1InEdge(ListLinked<Edge> edgesList, Vertex vertex) {
        Node<Edge> eNode = edgesList.getHead();
        while (eNode != null && eNode.getData().getV1().getLabel().compareTo(vertex.getLabel()) == 0) {
            eNode = eNode.getLink();
        }
        return eNode.getData();
    }

    public void readInputFile(String filename) {
        String dir = System.getProperty("user.dir");
        String separator = System.getProperty("file.separator");
        System.out.println(dir);
        try {
            Scanner scanner = new Scanner(new File(dir + separator + "input" + separator + filename));
            String line = "";
            Pattern pattern;
            Matcher matcher;
            line = scanner.nextLine();
            pattern = Pattern.compile("size\\s*=\\s*(\\d+)");
            matcher = pattern.matcher(line);
            matcher.find();
            vertexs = new Vertex[Integer.parseInt(matcher.group(1))];
            while (!(line = scanner.nextLine()).equals(";")) {
                pattern = Pattern.compile("\\s*(\\d+)\\s*=\\s*(.*)");
                matcher = pattern.matcher(line);
                if (matcher.find()) {
                    Vertex vertex = new Vertex(matcher.group(2));
                    vertexs[Integer.parseInt(matcher.group(1))] = vertex;
                    addVertex(vertex);
                }
                // System.out.println(line);
            }
            while (!(line = scanner.nextLine()).equals(";")) {
                pattern = Pattern.compile("\\s*\\(\\s*(\\d+)\\s*,\\s*(\\d+)\\s*,\\s*(\\d+|\\d+\\.\\d+)\\s*\\)");
                matcher = pattern.matcher(line);
                if (matcher.find()) {
                    int indexV1 = Integer.parseInt(matcher.group(1));
                    int indexV2 = Integer.parseInt(matcher.group(2));
                    double weight = Double.parseDouble(matcher.group(3));
                    addEdge(vertexs[indexV1], vertexs[indexV2], weight, isDirected());
                }
            }
            scanner.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void addEdge(Vertex v1, Vertex v2, double weight, boolean directed) {
        Edge edge = new Edge(v1, v2, weight);
        edgeList.add(edge);
        v1.addEdge(edge);
        if (!directed) {
            addEdge(v2, v1, weight, true);
        }
    }

    /*
     * public String toString() { return "Graph={vertexs={" + vertexsList + "}}"; }
     */
    public String toString() {
        return "Graph={vertexs={" + vertexsList + "}}";
    }

    public String printGraph() {
        String output = "";
        Node<Vertex> vNode = vertexsList.getHead();
        while (vNode != null) {
            Vertex vertex = vNode.getData();
            output += "<<" + vertex.getLabel() + ">>\t";
            Node<Edge> eNode = vertex.getEdges().getHead();
            while (eNode != null) {
                output += "{" + eNode.getData().getV2().getLabel() + "} ";
                eNode = eNode.getLink();
            }
            output += "\n";
            vNode = vNode.getLink();
        }
        return output;
    }

    static void testGraph() {
        Graph graph = new Graph(true);
        Vertex laPaz = new Vertex("La Paz");
        Vertex cochabamba = new Vertex("Cochabamba");
        Vertex santaCruz = new Vertex("Santa Cruz");
        Vertex beni = new Vertex("Beni");

        laPaz.addEdge(laPaz, cochabamba, 5.5);
        laPaz.addEdge(laPaz, santaCruz, 75);
        laPaz.addEdge(laPaz, beni, 15);

        cochabamba.addEdge(cochabamba, santaCruz, 52.2);
        cochabamba.addEdge(cochabamba, laPaz, 52.2);
        cochabamba.addEdge(cochabamba, beni, 52.2);

        graph.addVertex(laPaz);
        graph.addVertex(cochabamba);

        // System.out.println(laPaz);
        System.out.println(graph);
    }

    static void testReadFile() {
        Graph graph = new Graph(true);
        graph.readInputFile("bolivia.txt");
        System.out.println(graph);
    }
}
