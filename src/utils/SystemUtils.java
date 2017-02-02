package utils;

import utils.enums.OperationSystem;

/**
 * Created by connected on 10/28/16.
 */
public class SystemUtils {

    public static OperationSystem getOperationSystem() {
        return SystemUtils.isLinux() ? OperationSystem.UNIX : OperationSystem.WINDOWS;
    }

    public static boolean isLinux() {
        return System.getProperty("os.name").toLowerCase().contains("linux");
    }

    public static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("windows");
    }
}
