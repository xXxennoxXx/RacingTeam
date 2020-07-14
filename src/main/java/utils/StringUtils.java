package utils;

public class StringUtils {
    public static void show(Object... obs) {
        for (Object o : obs)
            System.out.println(o.toString());
    }
}
