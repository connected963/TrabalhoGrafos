package enums;

/**
 * Created by connected on 11/15/16.
 */
public enum TypeGraphEnum {
    VALUED_GRAPH("V"), REGULAR_GRAPH("N");

    private String type;

    private TypeGraphEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static TypeGraphEnum getValue(Character character) {
        if (character.equals('N')) {
            return TypeGraphEnum.REGULAR_GRAPH;
        }
        else if (character.equals('V')) {
            return TypeGraphEnum.VALUED_GRAPH;
         } else {
            return null;
        }

    }
}
