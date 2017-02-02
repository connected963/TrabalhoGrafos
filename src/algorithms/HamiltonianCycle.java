package algorithms;

/**
 * Created by connected on 12/2/16.
 */
public class HamiltonianCycle {

    private HamiltonianCycle() {

    }

    public static Boolean hamiltonianCycle(Integer[][] graph, Integer[] alreadyCovered, int level) {
        Boolean hasCycle = false;

        for (int i = 0; i < graph[level].length && !hasCycle; i++) {

            if (graph[level][i] != null) {

                if (level < graph.length - 1 && alreadyCovered[i] == null) {
                    alreadyCovered[i] = level;

                    hasCycle = hamiltonianCycle(graph, alreadyCovered, level + 1);

                    if (!hasCycle) {
                        alreadyCovered[i] = null;
                    }

                }
                else if (alreadyCovered[i] == null){
                    alreadyCovered[i] = level;
                    hasCycle = true;
                }
            }
        }

        return hasCycle;
    }

    public static Boolean hamiltonianCycle(Integer[][] graph, Integer[] alreadyCovered) {
        return hamiltonianCycle(graph, alreadyCovered, 0);
    }
}
