package algorithms;

import utils.GraphUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by connected on 11/15/16.
 */
public class BiconexComponents {

    public static List<List<Integer>> biconexComponents(final Integer[][] graph, final Integer initialVertex) {
        Integer[][] graphReturn = graph;
        Integer[][] graphThree = new Integer[graph.length][graph.length];
        Integer[] levelVertex = new Integer[graph.length];
        List<Integer> joints;
        List<Integer> landmarks;

        depthFirstSearch(graphReturn, levelVertex, initialVertex, graphThree, 0);

        Integer[] lowPt = lowPointerVector(graphThree, graph, levelVertex);
        joints = findJoints(graphThree, lowPt);
        landmarks = findLandmarks(graphThree, lowPt);

        return generateBiconexComponents(graphThree, landmarks, joints);
    }

    private static List<List<Integer>> generateBiconexComponents(Integer[][] graphThree, List<Integer> landmarks, List<Integer> joints) {
        List<List<Integer>> biconexComponents = new ArrayList<>();

        while (!landmarks.isEmpty()) {

            for (int i = 0; i < landmarks.size(); i++) {

                if(!landmarkHasArticulation(landmarks.get(i), joints, graphThree)) {
                    Integer father = findFather(graphThree, landmarks.get(i));
                    List<Integer> components = new ArrayList<>();
                    getAndRemoveComponentFromGraph(graphThree, landmarks.get(i), father, components);
                    components.add(father);

                    landmarks.remove(i);

                    if(!hasLandmarks(graphThree, father, landmarks)) {
                        joints.remove(father);
                    }
                    biconexComponents.add(components);
                }
            }
        }

        return biconexComponents;
    }

    private static void depthFirstSearch(Integer[][] graph, Integer[] alreadyVisited, Integer vertex, Integer[][] returnGraph, Integer level) {
        Integer adjacent;
        alreadyVisited[vertex] = level;

        while ((adjacent = GraphUtils.findAdjacentVertexWithLowerValue(graph, vertex, alreadyVisited)) != null) {
            returnGraph[vertex][adjacent] = 1;
            graph[vertex][adjacent] = graph[adjacent][vertex] = null;
            depthFirstSearch(graph, alreadyVisited, adjacent, returnGraph, level + 1);
        }
    }

    private static Integer[] lowPointerVector(Integer[][] graph, Integer[][] returnGraph, Integer[] levels) {
        Integer[] lowPointer = new Integer[graph.length];

        for (int i = 0; i < lowPointer.length; i++) {
            lowPointer[i] = lowPointer(graph, returnGraph, levels, i);
        }
        return lowPointer;
    }


    private static Integer lowPointer(Integer[][] graph, Integer[][] returnGraph, Integer[] levels, Integer level) {
        Integer lowPointer = level;

        for (int i = 0; i < graph[level].length; i++) {

            if(graph[level][i] != null) {
                Integer auxLow = lowPointer(graph, returnGraph, levels, i);
                if(levels[auxLow] < levels[lowPointer]) {
                    lowPointer = auxLow;
                }
            }

            if(returnGraph[level][i] != null) {
                if (levels[i] < levels[lowPointer]) {
                    lowPointer = i;
                }
            }
        }

        return lowPointer;
    }

    private static List<Integer> findJoints(Integer[][] graph, Integer[] lowPointer) {
        List<Integer> joints = new ArrayList<>();

        if (rootIsArticulation(graph[0])) {
            joints.add(0);
        }

        for (int i = 1; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                if (graph[i][j] != null && (lowPointer[j] == i || lowPointer[j] == j)) {
                    joints.add(i);
                    break;
                }
            }
        }

        return joints;
    }

    private static Boolean rootIsArticulation(Integer[] root) {
        Integer numberOfChildren = 0;

        for (int i = 0; i < root.length; i++) {
            if (root[i] != null) {
                numberOfChildren++;
            }
        }

        return numberOfChildren > 1;
    }

    private static List<Integer> findLandmarks(Integer[][] graph, Integer[] lowPointer) {
        List<Integer> landmarks = addChildrensOfRoot(graph);

        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                if (graph[i][j] != null && (lowPointer[j] == i || lowPointer[j] == j) && !landmarks.contains(j)) {
                    landmarks.add(j);
                }
            }
        }

        return landmarks;
    }

    private static List<Integer> addChildrensOfRoot(Integer[][] graph) {
        List<Integer> landmarks = new ArrayList<>();

        for (int i = 0; i < graph[0].length; i++) {
            if(graph[0][i] != null) {
                landmarks.add(i);
            }
        }

        return landmarks;
    }


    private static Boolean landmarkHasArticulation(Integer index, List<Integer> joints, Integer[][] graph) {
        Boolean hasChildrenWithArticulation = joints.contains(index);
        int i = 0;
        while (i < graph[index].length && !hasChildrenWithArticulation) {
            if(graph[index][i] != null) {
                if(joints.contains(i)) {
                    hasChildrenWithArticulation = true;
                } else {
                    hasChildrenWithArticulation = landmarkHasArticulation(i, joints, graph);
                }
            }
            i++;
        }
        return hasChildrenWithArticulation;
    }

    private static Integer findFather(Integer[][] graph, Integer vertex) {
        for (int i = 0; i < graph.length; i++) {
           if(graph[i][vertex] != null) {
               return i;
           }
        }
        return null;
    }

    private static List<Integer> getAndRemoveComponentFromGraph(Integer[][] graph, Integer vertex, Integer father, List<Integer> components) {

        components.add(vertex);
        graph[father][vertex] = null;

        for (int i = 0; i < graph[vertex].length; i++) {
            if (graph[vertex][i] != null) {
                getAndRemoveComponentFromGraph(graph, i, vertex, components);
            }
        }
        return components;
    }

    private static Boolean hasLandmarks(Integer[][] graph, Integer articulation, List<Integer> landmarks) {
        for (int i = 0; i < graph[articulation].length; i++) {
            if (graph[articulation][i] != null && landmarks.contains(i)) {
                return true;
            }
        }
        return false;
    }
}