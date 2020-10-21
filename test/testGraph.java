package test;

import graph.Graph;
import graph.Vertex;

public class testGraph {
    static void testGraphReadFile() {
        Graph graph = new Graph(false);
        graph.readInputFile("test04.txt");
        // System.out.println(graph.vertexsList.size());
        // System.out.println(graph.edgeList.size());
        // System.out.println(graph.showVertex());
        // System.out.println(graph.showEdges());
        System.out.println(graph.printGraph());
        // System.out.println(graph.BFS(graph.vertexsList.getHead().getData()));
        System.out.println("BFS");
        // System.out.println(graph.BFS_ShortRoute());
        System.out.println(graph.BFS());

        System.out.println("Ruta desde el nodo 7");
        Vertex vertex = graph.findVertex("7");
        graph.getShortPath(vertex);
        System.out.println("DFS");
        // System.out.println(graph.DFS(graph.getVertexsList().getHead().getData()));
        // boolean isCyclic = graph.isCyclic();
        // System.out.println(isCyclic ? "Es ciclico" : "No es ciclico");
        boolean isConnected = graph.isConnected();
        System.out.println(isConnected ? "Es conexo" : "No es conexo");
    }

    public static void main(String[] args) {
        testGraphReadFile();
    }
}
