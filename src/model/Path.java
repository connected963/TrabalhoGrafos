package model;

import enums.EdgeEnum;

/**
 * Created by connected on 12/10/16.
 */
public class Path extends Edge {
    private EdgeEnum type;

    public Path(Integer line, Integer column, EdgeEnum type) {
        super(line, column);
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Path path = (Path) o;

        return type == path.type;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    public EdgeEnum getType() {
        return type;
    }

    public void setType(EdgeEnum type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Path{" +
                "line=" + getLine() +
                ", column=" + getColumn() +
                ", type=" + type +
                '}';
    }
}
