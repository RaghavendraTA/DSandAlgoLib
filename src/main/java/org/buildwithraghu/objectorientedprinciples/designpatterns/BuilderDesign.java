package org.buildwithraghu.objectorientedprinciples.designpatterns;

class Computer {
    private String processor;
    private int memory;
    private int storage;
    private String graphicsCard;
    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public void setGraphicsCard(String graphicsCard) {
        this.graphicsCard = graphicsCard;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }
}

interface ComputerBuilder {
    ComputerBuilder buildProcessor(String processor);
    ComputerBuilder buildMemory(int memory);
    ComputerBuilder buildStorage(int storage);
    ComputerBuilder buildGraphicsCard(String graphicsCard);
    Computer build();
}

class DesktopComputerBuilder implements ComputerBuilder {
    private Computer computer;

    public DesktopComputerBuilder() {
        this.computer = new Computer();
    }

    @Override
    public ComputerBuilder buildProcessor(String processor) {
        computer.setProcessor(processor);
        return this;
    }

    @Override
    public ComputerBuilder buildMemory(int memory) {
        computer.setMemory(memory);
        return this;
    }

    @Override
    public ComputerBuilder buildStorage(int storage) {
        computer.setStorage(storage);
        return this;
    }

    @Override
    public ComputerBuilder buildGraphicsCard(String graphicsCard) {
        computer.setGraphicsCard(graphicsCard);
        return this;
    }

    @Override
    public Computer build() {
        return computer;
    }
}

class ComputerDirector {
    private ComputerBuilder computerBuilder;

    public ComputerDirector(ComputerBuilder computerBuilder) {
        this.computerBuilder = computerBuilder;
    }

    public Computer constructComputer() {
        return computerBuilder
                .buildProcessor("Intel i7")
                .buildMemory(16)
                .buildStorage(512)
                .buildGraphicsCard("NVIDIA GTX 1660")
                .build();
    }
}

public class BuilderDesign {
    public static void main(String[] args) {
        ComputerBuilder desktopBuilder = new DesktopComputerBuilder();

        new ComputerDirector(desktopBuilder).constructComputer();
    }
}
