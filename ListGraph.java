import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public class ListGraph<T> implements Graph<T> {
  private Map<T, Set<Edge<T>>> nodes = new HashMap<T, Set<Edge<T>>>();

  public void add(T node) {
    nodes.putIfAbsent(node, new HashSet<>());
  }

  public void connect(T node1, T node2, String name, int weight) {
    if (!nodes.containsKey(node1) || !nodes.containsKey(node2)) {
      throw new NoSuchElementException();
    } else if (getEdgeBetween(node1, node2) != null) {
      throw new IllegalStateException();
    }
    
    Set<Edge<T>> fromNodes = nodes.get(node1);
    Set<Edge<T>> toNodes = nodes.get(node2);

    fromNodes.add(new Edge<T>(node2, weight, name));
    toNodes.add(new Edge<T>(node1, weight, name));
  }

  public void setConnectionWeight(T node1, T node2, int weight) {
    if (!nodes.containsKey(node1) || !nodes.containsKey(node2) || getEdgeBetween(node1, node2) == null) {
      throw new NoSuchElementException();
    }

    getEdgeBetween(node1, node2).setWeight(weight);;
    getEdgeBetween(node2, node1).setWeight(weight);;
  }

  public Set<T> getNodes() {
    return nodes.keySet();
  }

  public Collection<Edge<T>> getEdgesFrom(T node) {
    if (!nodes.containsKey(node)) {
      throw new NoSuchElementException();
    }

    return nodes.get(node);
  }

  public Edge<T> getEdgeBetween(T node1, T node2) {
    if (!nodes.containsKey(node1) || !nodes.containsKey(node2)) {
      throw new NoSuchElementException();
    }

    for (Edge<T> edge : nodes.get(node1)) {
      if (edge.getDestination().equals(node2)) {
        return edge;
      }
    }

    return null;
  }

  public void disconnect(T node1, T node2) {
    if (!nodes.containsKey(node1) || !nodes.containsKey(node2)) {
      throw new NoSuchElementException();
    } else if (getEdgeBetween(node1, node2) == null) {
      throw new IllegalStateException();
    }

    Set<Edge<T>> fromNodes = nodes.get(node1);
    Set<Edge<T>> toNodes = nodes.get(node2);

    fromNodes.remove(getEdgeBetween(node1, node2));
    toNodes.remove(getEdgeBetween(node2, node1));
  }

  public void remove(T node) {
    if (!nodes.containsKey(node)) {
      throw new NoSuchElementException();
    }

    Collection<Edge<T>> edges = getEdgesFrom(node);

    //Skapar en ny list för att unvdvika ConcurrentModificationException - Mattias
    ArrayList<Edge<T>> toRemove = new ArrayList<>();

    for (Edge<T> edge : edges) {
      toRemove.add(edge);
    }

    for (Edge<T> edge : toRemove) {
      disconnect(node, edge.getDestination());
    }

    nodes.remove(node);
  }

  public boolean pathExists(T from, T to) {
    if (!nodes.containsKey(from) || !nodes.containsKey(to)) {
      return false;
    }

    Map<T, T> connection = new HashMap<>();
    recursiveConnect(from, null, connection);

    if (connection.containsKey(from) && connection.containsKey(to)) {
      return true;
    }

    return false;
  }

  private void recursiveConnect(T to, T from, Map<T, T> connection) {
    connection.put(to, from);

    for (Edge<T> edge : nodes.get(to)) {
      if (!connection.containsKey(edge.getDestination())) {
        recursiveConnect(edge.getDestination(), to, connection);
      }
    }
  }

  //getPath med depth-first sökning, gjorde denna först ifall jag inte tänkte göra Dijkstras algoritm lösningen - Mattias
  public List<Edge<T>> getPathWithDepth(T from, T to) {
    if (!pathExists(from, to)) {
      return null;
    }

    Map<T, T> connection = new HashMap<>();
    recursiveConnect(from, null, connection);

    List<Edge<T>> path = new LinkedList<>();
    T current = to;
    while (current != null && !current.equals(from)) {
      T next = connection.get(current);
      Edge<T> edge = getEdgeBetween(next, current);
      path.addFirst(edge);

      current = next;
    }

    return path;
  }

  //getPath implementation med Dijkstras algoritm, hittar optimala vägen baserat på weight, använd denna - Mattias
  public List<Edge<T>> getPath(T from, T to) {
    if (!pathExists(from, to)) {
      return null;
    }

    Map<T, Double> dist = new HashMap<>();
    Map<T, T> prev = new HashMap<>();
    Set<T> q = new HashSet<>();

    for (T v : nodes.keySet()) {
      dist.put(v, Double.POSITIVE_INFINITY);
      prev.put(v, null);
      q.add(v);
    }
    
    dist.put(from, 0.0);

    while(!q.isEmpty()) {
      T u = null;
      double minDist = Double.POSITIVE_INFINITY;

      for (T a : q) {
        double d = dist.get(a);

        if (d < minDist) {
          u = a;
          minDist = d;
        }
      }

      if (u.equals(to)) {
        break;
      }

      q.remove(u);

      for (Edge<T> v : nodes.get(u)) {
        if (q.contains(v.getDestination())) {
          double alt = dist.get(u) + v.getWeight();

          if (alt < dist.get(v.getDestination())) {
            dist.put(v.getDestination(), alt);
            prev.put(v.getDestination(), u);
          }
        }
      }
    }

    LinkedList<Edge<T>> path = new LinkedList<>();
    T u = to;

    if (prev.get(u) != null || u.equals(to)) {
      while(u != null) {
        T next = prev.get(u);
        if (next != null) {
          Edge<T> e = getEdgeBetween(next,  u);
          path.addFirst(e);
        }

        u = next;
      }
    }

    return path;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder("Nodes");
    sb.append("\n");
    for (Map.Entry<T, Set<Edge<T>>> kv : nodes.entrySet()) {
      sb.append(kv.getKey()).append(": ").append(kv.getValue()).append("\n");
    }

    return sb.toString();
  }
}
