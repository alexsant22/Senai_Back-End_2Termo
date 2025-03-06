class Car {
    private String model, color, engine;

    private Car(String model, String color, String engine) {
        this.model = model;
        this.color = color;
        this.engine = engine;
    }

    public String viewModel() {
        return "Carro: " + model + ", Cor: " + color + ", Motor: " + engine;
    }

    static class Builder {
        private String model, color, engine;
        // Métodos que setam os atributos
        public Builder(String model) { this.model = model; }
        public Builder setColor(String color) { this.color = color; return this; }
        public Builder setEngine(String engine) { this.engine = engine; return this; }
        public Car build() { return new Car(model, color, engine); }
    }
}

public class Main2 {
    public static void main(String[] args) {
        Car car = new Car.Builder("Tesla Model S")
            .setColor("Preto")
            .setEngine("Elétrico")
            .build();

        System.out.println(car.viewModel());
    }
}




