package Graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {

    // Hashmap to store username - person object pair.
    private Map<String, Person> people = new HashMap<>();

    // Hashmap to store person - connections pair.
    private Map<Person, List<Person>> connections = new HashMap<>();

    private class Person {

        private String userName;

        public Person(String userName) {
            this.userName = userName;
        }

        @Override
        public String toString() {
            return userName;
        }

    }

    public void addPerson(String userName) {
        Person person = new Person(userName);
        people.putIfAbsent(userName, person);
        connections.putIfAbsent(person, new ArrayList<>());
    }

    public void addConnection(String from, String to) {

        /*
         * Creates a connection between 2 people, just like following someone on Social
         * Media.
         * A connection can be uni-directional (A follows B) or bi-directional (Both A
         * and B follow each other).
         */

        Person fromPerson = people.get(from);

        Person toPerson = people.get(to);

        if (fromPerson == null || toPerson == null) {
            throw new IllegalArgumentException();
        }

        connections.get(fromPerson).add(toPerson);
    }

    public void displayConnections() {
        /* 
         * Displays the whole connection graph of the app.
         * Time complexity: 0(n) 
         */
        
        for (Person person : connections.keySet()) {
            if (!connections.get(person).isEmpty()) {
                System.out.println(person + " is connected to: " + connections.get(person));
            }
        }
    }

    public void displayConnectionsOf(String userName) {
        // Displays all connections of a single person
        
        Person person = people.get(userName);

        System.out.println(userName + " is connected with: " + connections.get(person));
    }

    public void removePerson(String userName) {

        /* 
         * Removes a Person from the Network. 
         * All references to the deleted person will be removed from connections of other people.
         */

        connections.remove(people.get(userName));

        for (Person person : connections.keySet()) {
            connections.get(person).remove(people.get(userName));
        }

        people.remove(userName);
    }

    public void removeConnection(String from, String to) {
        /* 
         * Removes connection from Person A to Person B. Similar to unfollow feature.
         * Person B will still have a connection to Person A
         */
        
        Person fromPerson = people.get(from);
        Person toPerson = people.get(to);

        connections.get(fromPerson).remove(toPerson);
    }

    public void findConnectionGraph(String userName) {
        /*
         * Similar to displayConnections() except it displays the whole connection graph of a single person.
         */

        Person person = people.get(userName);
        List<Person> connectedPersons = connections.get(person);

        if (!connectedPersons.isEmpty()) {
            System.out.println(userName + " is connected with: " + connectedPersons);

            for (Person p : connectedPersons) {
                findConnectionGraph(p.userName);
            }
        }
    }

    private int i = 1;

    public void findConnectionsOfLevel(String userName, int level) {

        // Tries to implement Linkedin's connection level functionality.

        Person person = people.get(userName);
        List<Person> connectedPersons = connections.get(person);

        if (i <= level) {
            System.out.println(" Level " + i + " connections: " + connectedPersons);

            for (Person p : connectedPersons) {
                i++;
                findConnectionsOfLevel(p.userName, level);
            }
        }
    }

}
