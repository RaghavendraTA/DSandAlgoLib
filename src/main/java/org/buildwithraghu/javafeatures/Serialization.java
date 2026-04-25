package org.buildwithraghu.javafeatures;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;

record AgeMarker(String name, Integer age) {}

record Employee(String firstName, String secondName) implements Serializable {
    @Override
    public String toString() {
        return this.firstName + ", " + this.secondName;
    }
}

public class Serialization {
    public static void main(String[] args) throws Exception {
        Employee emp = new Employee("Raghavendra", "TA");
        try (ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(Path.of(".\\myname.txt")))) {
            out.writeObject(emp);
        }

        try (ObjectInputStream input = new ObjectInputStream(Files.newInputStream(Path.of(".\\myname.txt")))) {
            Employee emp2 = (Employee) input.readObject();
            System.out.println(emp2.toString());
        }
    }
}
