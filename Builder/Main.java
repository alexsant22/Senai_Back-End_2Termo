// Classe que representa um Carro
class Car {
    private String model, color, engine; // Atributos do carro

    // Construtor privado: impede a criação direta do objeto Car
    private Car(String model, String color, String engine) {
        this.model = model;
        this.color = color;
        this.engine = engine;
    }

    // Método toString para exibir os detalhes do carro
    @Override
    public String toString() {
        return "Carro: " + model + ", Cor: " + color + ", Motor: " + engine;
    }

    // Classe Builder responsável por construir objetos Car
    static class Builder {
        private String model, color, engine; // Atributos do Builder

        // Construtor do Builder que define o modelo do carro (campo obrigatório)
        Builder(String model) { 
            this.model = model; 
        }

        // Método para definir a cor do carro (opcional)
        Builder setColor(String color) { 
            this.color = color; 
            return this; // Retorna o próprio Builder para encadeamento
        }

        // Método para definir o tipo de motor (opcional)
        Builder setEngine(String engine) { 
            this.engine = engine; 
            return this; // Retorna o próprio Builder para encadeamento
        }

        // Método que cria e retorna um objeto Car com os valores definidos
        Car build() { 
            return new Car(model, color, engine); 
        }
    }
}

// Classe principal com o método main para testar o padrão Builder
public class Main {
    public static void main(String[] args) {
        // Criando um carro usando o Builder
        Car car = new Car.Builder("Tesla Model S") // Define o modelo obrigatório
            .setColor("Preto") // Define a cor (opcional)
            .setEngine("Elétrico") // Define o motor (opcional)
            .build(); // Constrói o objeto Car

        // Exibe os detalhes do carro no console
        System.out.println(car);
    }
}
