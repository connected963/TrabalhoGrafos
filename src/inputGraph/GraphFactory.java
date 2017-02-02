package inputGraph;

import enums.TypeGraphEnum;
import exceptions.GraphInvalidFormatException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * Created by connected on 11/15/16.
 */
public class GraphFactory {

    private GraphFactory() {

    }

    public static Integer[][] readGraphFromFile(Path path) throws IOException {
        Stream<String> lines = Files.lines(path);
        String line = lines.findFirst().orElseThrow(() -> new GraphInvalidFormatException("Empty file"));
        TypeGraphEnum typeGraphEnum = getTypeGraph(line);

        line = removeTypeGraphOfLine(line, typeGraphEnum);
        Integer size = getNumberOfVertex(line);

        lines = Files.lines(path).skip(1);

        if(typeGraphEnum.equals(TypeGraphEnum.REGULAR_GRAPH)) {
            return RegularGraph.generateGraph(lines, size);
        } else {
            return ValuedGraph.generateGraph(lines, size);
        }

    }

    private static TypeGraphEnum getTypeGraph(String line) {
        return TypeGraphEnum.getValue(line.trim().charAt(0));
    }

    private static String removeTypeGraphOfLine(String line, TypeGraphEnum typeGraphEnum) {
        return line.replace(typeGraphEnum.getType(), "");
    }

    private static Integer getNumberOfVertex(String line) {
        return Integer.valueOf(line.trim());
    }

}
