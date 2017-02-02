package utils.enums;

/**
 * Created by connected on 10/28/16.
 */
public enum OperationSystem {
    UNIX(1),WINDOWS(2);

    Integer id;

    OperationSystem(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
