package utils;

import model.Edge;

import java.util.List;

/**
 * Created by connected on 11/14/16.
 */
public class GraphUtils {

    private GraphUtils() {

    }

    public static Integer findAdjacentVertexWithLowerValue(Integer[][] graph, Integer vertex, Integer[] alreadyTraveled) {
        Integer smaller = null;

        for (int i = 0; i < graph[vertex].length; i++) {

            if (graph[vertex][i] != null  && alreadyTraveled[i] == null && (smaller == null || graph[vertex][i] < graph[vertex][smaller])) {
                smaller = i;
            }

        }

        return smaller;
    }

    public static Integer findLowerValueNotTraveled(Integer[] lambdaVector, Boolean[] alreadyTraveled) {
        Integer smaller = null;

        for (int i = 0; i < lambdaVector.length; i++) {

            if (!alreadyTraveled[i] && (smaller == null || lambdaVector[i] != null && lambdaVector[smaller] > lambdaVector[i])) {
                smaller = i;
            }
        }
        return smaller;
    }

    public static Integer findAdjacentVertex(Integer[][] graph, Integer vertex) {
        Integer adjacent = null;

        for (int i = 0; i < graph[vertex].length; i++) {
            if (graph[vertex][i] != null) {
                adjacent = i;
                break;
            }
        }
        return adjacent;
    }

    public static Boolean allVerticesAreEven(Integer[][] graph) {
        Boolean areEven = true;

        for (int i = 0; i < graph.length; i++) {
            int numberOfEdges = 0;

            for (int j = 0; j < graph[i].length; j++) {
                if(graph[i][j] != null) {
                    numberOfEdges++;
                }
            }
            if (numberOfEdges % 2 != 0) {
                areEven = false;
                break;
            }
        }
        return areEven;
    }

    public static Integer[] calculatesTheDegreeOfEachVertex(Integer[][] graph) {
        Integer[] vertexDegree = new Integer[graph.length];
        for (int i = 0; i < graph.length; i++) {
            vertexDegree[i] = 0;

            for (int j = 0; j < graph[i].length; j++) {
                if(graph[i][j] != null) {
                    vertexDegree[i]++;
                }
            }
        }
        return vertexDegree;
    }


    public static Integer calculatesTheDegree(Integer[][] graph) {
        Integer degree = 0;
        for (int i = 0; i < graph.length; i++) {

            for (int j = 0; j < graph[i].length; j++) {
                if(graph[i][j] != null) {
                    degree++;
                }
            }
        }
        return degree;
    }

    public static Integer[][] getGraphFromEdges(List<Edge> edges) {
        Integer size = edges
                    .stream()
                    .map(edge -> Math.max(edge.getLine(), edge.getColumn()))
                    .max(Integer::compareTo)
                    .orElse(0);
        size++;
        Integer[][] graph = new Integer[size][size];

        edges.forEach(edge -> graph[edge.getLine()][edge.getColumn()] = 0);

        return graph;
    }

    public static Integer getFirstVertexWithAdjacencies(Integer[][] graph) {
        Integer vertex = null;
        for (int i = 0; i < graph.length && vertex == null; i++) {
            for (int j = 0; j < graph[i].length && vertex == null; j++) {
                if (graph[i][j] != null) {
                    vertex = i;
                }
            }
        }
        return vertex;
    }

}
