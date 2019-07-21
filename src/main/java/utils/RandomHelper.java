package utils;

public class RandomHelper {

    public static String getFourDigitCode() {
        return String.valueOf((int) (Math.random() * 8999) - 1000);
    }
}
