package model;

/**
 * Created by connected on 12/12/16.
 */
public class Edge {

    private final Integer line;
    private final Integer column;

    public Edge(Integer line, Integer column) {
        this.line = line;
        this.column = column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge edge = (Edge) o;

        if (line != null ? !line.equals(edge.line) : edge.line != null) return false;
        return column != null ? column.equals(edge.column) : edge.column == null;
    }

    @Override
    public int hashCode() {
        int result = line != null ? line.hashCode() : 0;
        result = 31 * result + (column != null ? column.hashCode() : 0);
        return result;
    }

    public Integer getLine() {
        return line;
    }

    public Integer getColumn() {
        return column;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "line=" + line +
                ", column=" + column +
                '}';
    }
}
