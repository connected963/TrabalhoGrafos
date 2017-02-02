package algorithms;

import utils.GraphUtils;

import java.util.Arrays;

/**
 * Created by connected on 12/1/16.
 */
public class MinimumSpanningTree {

    private MinimumSpanningTree() {

    }

    public static Integer[][] generateMinimumSpanningTree(Integer[][] graph) {
        Integer[] lambda = new Integer[graph.length];
        Integer[] adjacentVertex = new Integer[graph.length];
        Boolean[] alreadyEvaluated = new Boolean[graph.length];

        Arrays.fill(alreadyEvaluated, false);

        Integer currentVertex = 0;

        lambda[currentVertex] = 0;
        adjacentVertex[currentVertex] = 0;
        alreadyEvaluated[currentVertex] = true;

        for (int i = 0; i < graph.length && currentVertex != null; i++) {

            for (int j = 0; j < graph[currentVertex].length; j++) {
                if(!alreadyEvaluated[j] && graph[currentVertex][j] != null && (lambda[j] == null || lambda[j] > graph[currentVertex][j])) {
                    lambda[j] = graph[currentVertex][j];
                    adjacentVertex[j] = currentVertex;
                }
            }

            alreadyEvaluated[currentVertex] = true;

            currentVertex = GraphUtils.findLowerValueNotTraveled(lambda, alreadyEvaluated);
        }

        return generateGraphFromLambda(lambda, adjacentVertex);
    }

    private static Integer[][] generateGraphFromLambda(Integer[] lambda, Integer[] adjacent) {
        Integer[][] newGraph = new Integer[lambda.length][lambda.length];

        for (int i = 0; i < lambda.length; i++) {
            newGraph[i][adjacent[i]] = newGraph[adjacent[i]][i] = lambda[i];
        }

        return newGraph;
    }
}
