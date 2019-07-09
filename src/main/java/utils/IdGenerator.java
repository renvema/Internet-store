package utils;

public class IdGenerator {

    private static Long id = 1L;

    public static Long generate() {
        id++;
        return id;
    }
}
