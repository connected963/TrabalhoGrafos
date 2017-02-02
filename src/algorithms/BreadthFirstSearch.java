package algorithms;

import utils.GraphUtils;

/**
 * Created by connected on 11/14/16.
 */
public class BreadthFirstSearch {

    private BreadthFirstSearch() {

    }

    public static Integer[] breadthFirstSearch(Integer[][] graph, Integer initialVertex, Integer finalVertex) {
        Integer level = 0;
        Integer vertexLevel[] = new Integer[graph.length];

        vertexLevel[initialVertex] = 0;

        while ((finalVertex != null && vertexLevel[finalVertex] == null || finalVertex == null) && level < graph.length) {

            for (int i = 0; i < graph.length; i++) {
                if (vertexLevel[i] != null && vertexLevel[i].equals(level)) {

                    for (int j = 0; j < graph.length; j++) {
                        Integer adjacent = GraphUtils.findAdjacentVertexWithLowerValue(graph, i, vertexLevel);
                        if (adjacent != null) {
                            vertexLevel[adjacent] = level + 1;
                        }
                    }
                }

            }

            level++;
        }

        return vertexLevel;
    }
}
