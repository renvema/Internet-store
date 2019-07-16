package utils;

public class IdGenerator {

    private static Long userID = 0L;
    private static Long productID = 0L;

    public static Long generateIdUser() {
        userID++;
        return userID;
    }

    public static Long generateIdProdut() {
        productID++;
        return productID;
    }
}
