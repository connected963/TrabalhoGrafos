package algorithms;

import utils.MathUtils;

/**
 * Created by connected on 11/14/16.
 */
public class Isomorphism {

    private Isomorphism() {

    }

    public static boolean areIsomorphic(Integer[][] graph, Integer[][] graph2) {
        boolean areIsomorphic = false;
        long count = 0, factorial = MathUtils.factorial(graph.length);
        Permutation permutation = new Permutation(graph.length);

        if (graph.length == graph2.length) {

            while (count < factorial && !areIsomorphic) {
                Integer[] equivalence = permutation.generateNextPermutation();
                areIsomorphic = true;

                for (int i = 0; i < graph.length; i++) {
                    for (int j = 0; j < graph[i].length; j++) {

                        if (graph[i][j] != null && !graph[i][j].equals(graph2[equivalence[i]][equivalence[j]])) {
                            areIsomorphic = false;
                            break;
                        }
                    }
                    if (!areIsomorphic) {
                        break;
                    }
                }
                count++;
            }
        }
        return areIsomorphic;
    }
}