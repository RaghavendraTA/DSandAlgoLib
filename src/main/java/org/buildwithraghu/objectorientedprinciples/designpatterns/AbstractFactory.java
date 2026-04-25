package org.buildwithraghu.objectorientedprinciples.designpatterns;

interface Chair {
    void sitOn();  // Common functionality for all chairs
}

interface Table {
    void use();    // Common functionality for all tables
}

interface FurnitureFactory {
    Chair createChair();  // Method to create a Chair
    Table createTable();  // Method to create a Table
}

class ModernChair implements Chair {
    @Override
    public void sitOn() {
        System.out.println("Sitting on a modern chair!");
    }
}

class ModernTable implements Table {
    @Override
    public void use() {
        System.out.println("Using a modern table!");
    }
}

class VictorianChair implements Chair {
    @Override
    public void sitOn() {
        System.out.println("Sitting on a Victorian chair!");
    }
}

class VictorianTable implements Table {
    @Override
    public void use() {
        System.out.println("Using a victorian table!");
    }
}

class ModernFurnitureFactory implements FurnitureFactory {
    @Override
    public Chair createChair() {
        return new ModernChair(); // Create a modern chair
    }

    @Override
    public Table createTable() {
        return new ModernTable(); // Create a modern table
    }
}

class VictorianFurnitureFactory implements FurnitureFactory {
    @Override
    public Chair createChair() {
        return new VictorianChair();
    }

    @Override
    public Table createTable() {
        return new VictorianTable();
    }
}

public class AbstractFactory {
    public static void main(String[] args) {
        FurnitureFactory modernFactory = new ModernFurnitureFactory();
        Chair modernChair = modernFactory.createChair();
        Table modernTable = modernFactory.createTable();

        // Use the modern furniture
        modernChair.sitOn();
        modernTable.use();

        // Create Victorian furniture using the VictorianFurnitureFactory
        FurnitureFactory victorianFactory = new VictorianFurnitureFactory();
        Chair victorianChair = victorianFactory.createChair();
        Table victorianTable = victorianFactory.createTable();

        // Use the Victorian furniture
        victorianChair.sitOn();
        victorianTable.use();
    }
}
