import Graphs.Graph;

public class App {
    public static void main(String[] args) {
      Graph graph = new Graph();

        graph.addPerson("Josh");
        graph.addPerson("Alice");
        graph.addPerson("James");
        graph.addPerson("Casey");
        graph.addPerson("Mia");
        graph.addPerson("Candice");
        graph.addPerson("Jake");
        graph.addPerson("Alex");

        graph.addConnection("Josh", "Alice");
        graph.addConnection("Josh", "Jake");
        graph.addConnection("Alice", "Mia");
        graph.addConnection("Mia", "James");
        graph.addConnection("Alex", "Alice");
        graph.addConnection("Alex", "James");
        graph.addConnection("Alice", "James");

        /*
         * josh is connected to [alice, jake]
         * alice is connected to [mia, james]
         * mia isconnected to [james]
         */

        // graph.removeConnection("Josh", "Alice");

        graph.findConnectionGraph("Josh");
    }
}
