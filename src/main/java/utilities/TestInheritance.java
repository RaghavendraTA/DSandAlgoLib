package utilities;

public class TestInheritance {

    public static void add(int a, int b) {
        System.out.println(a + b + "I");
    }

    public static void add(long a, long b) {
        System.out.println(a + b + "L");
    }

    public static void main(String[] args) {
        sol3 sol3 = new sol3();
        add(1, 2);
    }
}


class sol1 {

    public sol1() {
        System.out.println("sol1");
    }
}

class sol2 extends sol1 {

    public sol2() {
        System.out.println("Sol2");
    }

}

class sol3 extends sol2 {

    public sol3() {
        System.out.println("Sol3");
    }
}

