package utilities;

/*
 * created by raghavendra.ta on 21-Nov-2021
 */

public abstract class TestSuite {

    private static String getMessage(Object actual, Object expected) {
        return "Condition failed -> {" + actual.toString() + "} == {" + expected.toString() + "}";
    }

    public static void assertTrue(boolean condition, String message) {

        if (!condition) {
            System.out.println("\n" + message);
            failed();
            System.exit(1);
        }
    }

    public static void assertTrue(boolean condition) {

        assertTrue(condition, "condition is false !");
    }

    public static void assertEquals(int actual, int expected) {

        assertTrue(actual == expected, getMessage(actual, expected));
    }

    public static <T extends Comparable<T>> void assertEquals(T actual, T expected) {

        assertTrue(actual.compareTo(expected) == 0, getMessage(actual, expected));
    }

    abstract public void test();

    public void main() {
        this.test();
        success();
    }

    private static void success() {
        System.out.println("\n------------------------------------------");
        System.out.println("All Tests passed\n");
    }

    private static void failed() {
        System.out.println("\n------------------------------------------");
        System.out.println("Test failed\n");
    }
}
