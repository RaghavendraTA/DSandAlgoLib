package org.buildwithraghu.objectorientedprinciples.designpatterns;

interface Pizza {
    void bake();
    void box();
}

class MargheritaPizza implements Pizza {
    @Override public void bake() { }
    @Override public void box() { }
}

class PepperoniPizza implements Pizza {
    @Override public void bake() { }
    @Override public void box() { }
}

// Creator interface
interface PizzaFactory {
    Pizza createPizza();
}

class MargheritaPizzaFactory implements PizzaFactory {
    @Override
    public Pizza createPizza() {
        return new MargheritaPizza();
    }
}

class PepperoniPizzaFactory implements PizzaFactory {
    @Override
    public Pizza createPizza() {
        return new PepperoniPizza();
    }
}

public class FactoryPattern {
    public static void main(String[] args) {
        PizzaFactory pizzaFactory = new MargheritaPizzaFactory();
        Pizza pizza = pizzaFactory.createPizza();

        pizza.bake();
        pizza.box();
    }
}
