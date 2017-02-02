package algorithms;

import model.Edge;
import utils.GraphUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by connected on 11/14/16.
 */
public class RelatedComponents {

    private RelatedComponents() {
    }

    public static Integer relatedComponents(Integer[][] graph, Integer[] alreadyVisited) {
        Integer numberOfComponents = 0;

        for (int i = 0; i < graph.length; i++) {
            if (alreadyVisited[i] == null) {
                DepthFirstSearch.depthFirstSearch(graph, alreadyVisited, i, numberOfComponents, false);
                numberOfComponents++;
            }
        }
        return numberOfComponents;
    }

    public static List<Integer[][]> getConexComponents(Integer[][] graph) {
        List<List<Edge>> components = new ArrayList<>();
        Integer[] alreadyVisited = new Integer[graph.length];

        Integer numberOfComponents = 0;

        for (int i = 0; i < graph.length; i++) {
            if (alreadyVisited[i] == null) {
                components.add(new ArrayList<>());
                int indexOfComponent = components.size() - 1;
                DepthFirstSearch.depthFirstSearch(graph, alreadyVisited, i, numberOfComponents, components.get(indexOfComponent));
                numberOfComponents++;

                if (components.get(indexOfComponent).size() > 1) {
                    int firstVertex = components.get(indexOfComponent).get(0).getLine();
                    int lastVertex = components.get(indexOfComponent).get(components.get(indexOfComponent).size() - 1).getLine();

                    if (graph[firstVertex][lastVertex] != null) {
                        components.get(indexOfComponent).add(new Edge(firstVertex, lastVertex));
                        components.get(indexOfComponent).add(new Edge(lastVertex, firstVertex));
                    }
                }

            }
        }
        return getGraphs(components);
    }

    private static List<Integer[][]> getGraphs(List<List<Edge>> components) {
        List<Integer[][]> graphs = new ArrayList<>();

        components.forEach(edges -> graphs.add(GraphUtils.getGraphFromEdges(edges)));

        return graphs;
    }
}
