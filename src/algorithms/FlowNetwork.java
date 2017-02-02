package algorithms;

import enums.EdgeEnum;
import model.Path;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by connected on 12/10/16.
 */
public class FlowNetwork {

    public static Integer maximumFlow(Integer[][] graph, Integer sourceVertex, Integer sinkVertex) {
        Integer[][] contrary = generateContrary(graph);
        Integer[][] direct = generateDirect(graph);
        List<Path> path = new ArrayList<>();
        Integer minimum;

        while ((minimum = augmentingPath(direct, contrary, path, sourceVertex, sinkVertex)) != null) {
            for(Path p : path) {
                if (p.getType() == EdgeEnum.DIRECT) {
                    contrary[p.getLine()][p.getColumn()] += minimum;
                    direct[p.getLine()][p.getColumn()] -= minimum;
                } else {
                    contrary[p.getLine()][p.getColumn()] -= minimum;
                    direct[p.getLine()][p.getColumn()] += minimum;
                }
                graph[p.getLine()][p.getColumn()] = contrary[p.getLine()][p.getColumn()];
            }
        }
        return sumFlow(graph, sinkVertex);
    }

    private static Integer sumFlow(Integer[][] graph, Integer sinkVertex) {
        Integer sum = 0;
        for (int i = 0; i < graph[sinkVertex].length; i++) {
            if (graph[i][sinkVertex] != null) {
                sum += graph[i][sinkVertex];
            }
        }
        return sum;
    }

    private static Integer[][] generateContrary(Integer[][] graph) {
        Integer[][] contrary = new Integer[graph.length][graph.length];

        for (int i = 0; i < graph.length; i++) {
            Arrays.fill(contrary[i], 0);
        }

        return contrary;
    }

    private static Integer[][] generateDirect(Integer[][] graph) {
        Integer[][] direct = new Integer[graph.length][graph.length];

        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                direct[i][j] = graph[i][j];
            }
        }

        return direct;
    }

    private static Integer augmentingPath(Integer[][] direct, Integer[][] contrary, List<Path> path, Integer sourceVertex, Integer sinkVertex) {
        Integer minimum = null, level = 0, vertex = 0;
        Integer alreadyVisitedDirect[] = new Integer[direct.length];
        Integer alreadyVisitedContrary[] = new Integer[contrary.length];

        alreadyVisitedDirect[sourceVertex] = 0;

        path.clear();

        while ((alreadyVisitedDirect[sinkVertex] == null) && level < direct.length) {
            boolean evaluated = false;

            for (int adjacent = 0; adjacent < direct.length; adjacent++) {

                if (direct[vertex][adjacent] != null && direct[vertex][adjacent] > 0) {
                    alreadyVisitedDirect[adjacent] = level + 1;
                    path.add(new Path(vertex, adjacent, EdgeEnum.DIRECT));
                    if (minimum == null || minimum > direct[vertex][adjacent]) {
                        minimum = direct[vertex][adjacent];
                    }
                    vertex = adjacent;
                    evaluated = true;
                }
            }

            if(!evaluated){
                for (int adjacent = 0; adjacent < direct.length; adjacent++) {
                    if (contrary[vertex][adjacent] != null && contrary[vertex][adjacent] > 0) {
                        alreadyVisitedContrary[adjacent] = level + 1;
                        path.add(new Path(vertex, adjacent, EdgeEnum.CONTRARY));

                        if (minimum == null || minimum > contrary[vertex][adjacent]) {
                            minimum = direct[vertex][adjacent];
                        }
                        vertex = adjacent;
                    }
                }
            }
            level++;
        }

        return alreadyVisitedDirect[sinkVertex] == null ? null : minimum;
    }

    public static void main(String[] args) {
        Integer[][] a = {{null, 3, 5, null, null, null},
                        {null, null, null, 2, 2, null},
                        {null, null, null, null, 7, null},
                        {null, null, null, null, null, 3},
                        {null, null, null, null, null, 5},
                        {null, null, null, null, null, null}};

        System.out.println(FlowNetwork.maximumFlow(a, 0, 5));
    }
}
