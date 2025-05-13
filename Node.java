//package se.su.inlupp;

import java.util.Objects;

public class Node {
    private String name;
    private double coordX;
    private double coordY;

    public Node(String name, double coordX, double coordY) {
        this.name = name;
        this.coordX = coordX;
        this.coordY = coordY;
    }

    public String getName() {
        return name;
    }

    public double getCoordX() { return coordX; }

    public double getCoordY() { return coordY; }

    public boolean equals(Object obj) {
        if (obj instanceof Node node) {
            return name.equals(node.name);
        }

        return false;
    }

    public int hashCode() {
        return Objects.hashCode(name);
    }

    public String toString() {
        return getName();
    }
}
