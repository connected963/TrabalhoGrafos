package View;

import algorithms.*;
import inputGraph.GraphFactory;
import utils.Console;
import utils.GraphUtils;

import javax.swing.*;
import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by connected on 11/15/16.
 */
public class View {

    private static final String MENU_MESSAGE = "Olá, digite:\n"
                    + "1 - Identificação de isomorfismo.\n"
                    + "2 - Busca em amplitude.\n"
                    + "3 - Busca em profundidade.\n"
                    + "4 - Identificação de componentes conexas.\n"
                    + "5 - Identificação de componentes biconexas.\n"
                    + "6 - Identificação de ciclo Euleriano.\n"
                    + "7 - Identificação de ciclo Hamiltoniano.\n"
                    + "8 - Identificação de arvore geradora minima.\n"
                    + "9 - Calculo de fluxo em rede.\n"
                    + "10 - Sair";

    private static final String DEFAULT_ERROR_INPUT = "Ops, algo deu errado!\nTente novamente";

    public View() {
        showMenu();
    }

    private void showMenu() {
        Integer option;

        do {
            option = Console.readInteger(View.MENU_MESSAGE, View.DEFAULT_ERROR_INPUT);
            clear();
            executeAction(option);
        } while(option != 10);
    }

    private void executeAction(Integer option) {
        switch (option) {
            case 1:
                isomorfismo();
                break;
            case 2:
                buscaEmAmplitude();
                break;
            case 3:
                buscaEmProfundidade();
                break;
            case 4:
                identificacaoComponentesConexos();
                break;
            case 5:
                identificacaoBiconexos();
                break;
            case 6:
                identificacaoCicloEuleriano();
                break;
            case 7:
                identificacaoCicloHamiltoniano();
                break;
            case 8:
                identificacaoArvoreGeradoraMinima();
                break;
            case 9:
                calculoFluxoMaximo();
                break;
            case 10:
                break;
            default:
                System.out.println("Opção não reconhecida");
        }
    }

    private Integer[][] getGraph() {
        Integer option = Console.readInteger("Olá, digite:\n"
                         + "1 - Informar via arquivo.\n"
                         + "2 - Informar manualmente.", View.DEFAULT_ERROR_INPUT);

        if (option.equals(1)) {
            return buscaGrafoEmArquivo();
        } else {
            return informarGrafoManualmente();
        }
    }

    private Integer[][] buscaGrafoEmArquivo() {
        Integer[][] graph = null;
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setDialogTitle("Selecione o arquivo contendo o grafo.");

        if (jFileChooser.showOpenDialog(null) == jFileChooser.APPROVE_OPTION) {
            try {
                graph = GraphFactory.readGraphFromFile(jFileChooser.getSelectedFile().toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            graph = buscaGrafoEmArquivo();
        }

        return graph;
    }

    private Integer[][] informarGrafoManualmente() {
        Integer[][] grafo;
        Integer size = Console.readInteger("Informe o número de arestas:\n", View.DEFAULT_ERROR_INPUT);

        grafo = new Integer[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grafo[i][j] = Console.readInteger("Informe o valor do vertice[" + i + "][" + j + "]", View.DEFAULT_ERROR_INPUT);
            }
        }

        return grafo;
    }

    private void isomorfismo() {
        Integer[][] graph1 = getGraph();
        Integer[][] graph2 = getGraph();

        Boolean isIsomorphic = Isomorphism.areIsomorphic(graph1, graph2);

        System.out.println(isIsomorphic ? "São isomorficos" : "Não são isomorficos");
    }

    private void buscaEmAmplitude() {
        Integer[][] graph = getGraph();
        Integer initialVertex = Console.readInteger("Digite o vertice inicial", View.DEFAULT_ERROR_INPUT);
        Integer[] buscaEmAmplitude = BreadthFirstSearch.breadthFirstSearch(graph, --initialVertex, null);

        for (int i = 0; i < buscaEmAmplitude.length; i++) {
            System.out.println("Vértice " + (i + 1) + ": " + buscaEmAmplitude[i]);
        }
    }

    private void buscaEmProfundidade() {
        Integer[][] graph = getGraph();
        Integer initialVertex = Console.readInteger("Digite o vertice inicial", View.DEFAULT_ERROR_INPUT);
        Integer[] buscaEmProfundidade = new Integer[graph.length];

        DepthFirstSearch.depthFirstSearch(graph, buscaEmProfundidade, --initialVertex, true);

        for (int i = 0; i < buscaEmProfundidade.length; i++) {
            System.out.println("Vértice " + (i + 1) + ": " + buscaEmProfundidade[i]);
        }
    }

    private void identificacaoComponentesConexos() {
        Integer[][] graph = getGraph();
        Integer[] alreadyVisited = new Integer[graph.length];

        Integer numberOfComponents = RelatedComponents.relatedComponents(graph, alreadyVisited);

        System.out.println("Numero de componentes conexos: " + numberOfComponents);
    }

    private void identificacaoBiconexos() {
        List<Integer[][]> components = RelatedComponents.getConexComponents(getGraph());

        IntStream.range(0, components.size()).forEach(index -> {
            List<List<Integer>> biconexComponents;
            Integer initialVertex = GraphUtils.getFirstVertexWithAdjacencies(components.get(index));

            System.out.println("Componente: " + index);

            if (initialVertex != null) {
                biconexComponents = BiconexComponents.biconexComponents(components.get(index), initialVertex);

                for (int i = 0; i < biconexComponents.size(); i++) {
                    System.out.print("Grupo: " + (i + 1) + ": [");

                    for (int j = 0; j < biconexComponents.get(i).size() - 1; j++) {
                        System.out.print(biconexComponents.get(i).get(j) + 1 + ", ");
                    }

                    System.out.println(biconexComponents.get(i).get(biconexComponents.get(i).size() - 1) + 1 + "]");
                }
            } else {
                System.out.println("Componente invalida");
            }
        });
    }

    private void identificacaoCicloEuleriano() {
        List<Integer> path = EulerianPath.eulerianPath(getGraph());

        if (path.isEmpty()) {
            System.out.println("Não possui ciclo Euleriano");
        } else {
            System.out.print("Ciclo: ");
            path.forEach(i -> System.out.println(i + 1));
        }
    }

    private void identificacaoCicloHamiltoniano() {
        Integer[][] graph = getGraph();
        Integer[] alreadyCovered = new Integer[graph.length];

        if (HamiltonianCycle.hamiltonianCycle(graph, alreadyCovered)) {
            System.out.println("Vertice - nivel de visitacao");
            IntStream.range(0, alreadyCovered.length).forEach(i -> System.out.println(i + 1 + " - " + (alreadyCovered[i] + 1)));
        } else {
            System.out.println("Não possui ciclo Hamiltoniano");
        }
    }

    private void identificacaoArvoreGeradoraMinima() {
        Integer[][] minimumSpanningTree = MinimumSpanningTree.generateMinimumSpanningTree(getGraph());

        for (int i = 0; i < minimumSpanningTree.length; i++) {
            for (int j = 0; j < minimumSpanningTree[i].length; j++) {
                System.out.print((minimumSpanningTree[i][j] == null ? "-" : minimumSpanningTree[i][j]) + "\t");
            }
            System.out.println();
        }
    }

    private void calculoFluxoMaximo() {
        Integer[][] graph = getGraph();
        Integer sourceVertex, sinkVertex, value;

        do {
            sourceVertex = Console.readInteger("Informe o vertice fonte: ", View.DEFAULT_ERROR_INPUT);
            sourceVertex--;
        } while (sourceVertex < 0 || sourceVertex >= graph.length);

        do {
            sinkVertex = Console.readInteger("Informe o vertice sumidouro: ", View.DEFAULT_ERROR_INPUT);
            sinkVertex--;
        } while (sinkVertex < 0 || sinkVertex >= graph.length);

        value = FlowNetwork.maximumFlow(graph, sourceVertex, sinkVertex);
        
        if (value != null) {
            System.out.println("Fluxo máximo: ");
            for (int i = 0; i < graph.length; i++) {
                for (int j = 0; j < graph[i].length; j++) {
                    System.out.print((graph[i][j] != null ? graph[i][j] : "-") + "\t");
                }
                System.out.println();
            }
            System.out.println("Total: " + value);
        } else {
            System.out.println("Fluxo não encontrado");
        }
    }

    private void clear() {
        try {
            Console.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
