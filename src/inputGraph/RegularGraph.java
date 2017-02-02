package inputGraph;

import utils.StringUtils;

import java.util.stream.Stream;

/**
 * Created by connected on 11/15/16.
 */
public class RegularGraph {

    private static Integer index;

    public static Integer[][] generateGraph(Stream<String> lines, Integer size) {
        Integer[][] graph = new Integer[size][size];
        RegularGraph.index = 0;

        lines.forEach(line -> generate(line, graph, RegularGraph.index++));

        return graph;
    }

    private static void generate(String line, Integer[][] graph, Integer index) {
        StringBuilder str = new StringBuilder(line);

        Integer numberOfAdjacencies = StringUtils.getNextInteger(str);

        while (str.length() > 0) {
            Integer value = StringUtils.getNextInteger(str);
            graph[index][value - 1] = 1;
        }
    }

}
