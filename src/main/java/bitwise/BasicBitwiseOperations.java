package bitwise;

/*
 * created by raghavendra.ta on 13-Jul-2021
 */

public class BasicBitwiseOperations {

    static int checkKthBitIsSet(int n, int k) {
        return n & (1 << k - 1);
    }

    static void setKthBit(int n, int k) {
        n = n | (1 << k - 1);
    }

    static void clearKthBit(int n, int k) {
        n = n & ~(1 << k - 1);
    }

    static void togglingKthBit(int n, int k) {
        n = n ^ (1 << k - 1);
    }

    static void checkIs2Power(int n) {
        boolean check = (n & n - 1) == 0;
    }

    static void multiplyBy2PowerK(int n, int k) {
        n = n << k;
    }

    static void divideBy2PowerK(int n, int k) {
        n = n >> k;
    }

}
