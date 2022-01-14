package string;

/*
 * created by raghavendra.ta on 08-Jan-2022
 */

public class StringPool {

    public static void main(String[] args) {
        String name = "Raghu";
        String secondName = "Raghu";
        String thirdName = new String("Raghu");

        System.out.println("name == secondName ? " + (name == secondName));
        System.out.println("name.equals(secondName) ? " +  name.equals(secondName));

        System.out.println("name == thirdName ? " + (name == thirdName));
        System.out.println("name.equals(thirdName) ? " +  name.equals(thirdName));

        System.out.println("name == thirdName ? is false because \"new String()\" created new object inside string pool");
    }
}
