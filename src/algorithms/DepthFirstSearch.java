package algorithms;

import enums.EdgeEnum;
import model.Edge;
import utils.GraphUtils;

import java.util.List;

/**
 * Created by connected on 11/14/16.
 */
public class DepthFirstSearch {

    private DepthFirstSearch() {

    }

    public static void depthFirstSearch(Integer[][] graph, Integer[] alreadyVisited, Integer vertex, Boolean increment) {
        depthFirstSearch(graph, alreadyVisited, vertex, 0, increment);
    }

    public static void depthFirstSearch(Integer[][] graph, Integer[] alreadyVisited, Integer vertex, Integer index, Boolean increment) {
        Integer adjacent;
        alreadyVisited[vertex] = increment ? index++ : index;

        while ((adjacent = GraphUtils.findAdjacentVertexWithLowerValue(graph, vertex, alreadyVisited)) != null) {
            depthFirstSearch(graph, alreadyVisited, adjacent, index, increment);
        }
    }

    public static void depthFirstSearch(Integer[][] graph, Integer[] alreadyVisited, Integer vertex, Integer index, List<Edge> edges) {
        Integer adjacent;
        alreadyVisited[vertex] = 0;

        while ((adjacent = GraphUtils.findAdjacentVertexWithLowerValue(graph, vertex, alreadyVisited)) != null) {
            edges.add(new Edge(vertex, adjacent));
            edges.add(new Edge(adjacent, vertex));
            depthFirstSearch(graph, alreadyVisited, adjacent, index, edges);
        }
    }
}