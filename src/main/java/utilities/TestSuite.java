package utilities;

public abstract class TestSuite {

    protected void assertEquals(int expected, int actual) {
        if (expected != actual) {
            throw new AssertionError("Expected: " + expected + ", but was: " + actual);
        }
    }

    public abstract void test();

    public void main() {
        test();
        System.out.println("Test passed.");
    }
}
