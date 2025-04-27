import java.util.Objects;

public class Node {
    private String name;

    public Node(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

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
