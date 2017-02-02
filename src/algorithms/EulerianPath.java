package algorithms;

import utils.GraphUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by connected on 12/2/16.
 */
public class EulerianPath {

    private static Optional<Integer> getVertexWithRemainingEdge(List<Integer> path, Integer[] vertexDegree) {
        return path
                .stream()
                .filter(i -> vertexDegree[i] > 0)
                .findFirst();
    }

    public static List<Integer> eulerianPath(Integer[][] graph) {
        List<Integer> path = new ArrayList<>();

        if (GraphUtils.allVerticesAreEven(graph)) {
            Integer[] vertexDegree = GraphUtils.calculatesTheDegreeOfEachVertex(graph);
            Integer degree = GraphUtils.calculatesTheDegree(graph);
            Integer vertex = 0;

            while (degree > 0) {
                Integer adjacent = GraphUtils.findAdjacentVertex(graph, vertex);

                path.add(vertex);

                System.out.print(vertex + " - ");

                degree -= 2;
                vertexDegree[vertex]--;
                vertexDegree[adjacent]--;
                graph[vertex][adjacent] = graph[adjacent][vertex] = null;

                System.out.println(adjacent);

                if (path.contains(adjacent) && vertexDegree[adjacent] == 0) {
                    vertex = getVertexWithRemainingEdge(path, vertexDegree).orElse(null);
                } else {
                    vertex = adjacent;
                }
            }
        }

        return path;
    }
}
