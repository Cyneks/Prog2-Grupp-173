public class Edge<T> {
  T destination;
  int weight;
  String name;

  public Edge(T destination, int weight, String name) {
      this.destination = destination;
      this.name = name;

      if (weight < 0) {
        throw new IllegalArgumentException();
      }

      this.weight = weight;
  }

  public int getWeight() {
      return weight;
  }

  public void setWeight(int newWeight) {
      if (newWeight < 0) {
        throw new IllegalArgumentException();
      }
      
      weight = newWeight;
  }

  public T getDestination() {
      return destination;
  }

  public String getName() {
      return name;
  }

  public String toString() {
      return "till " + getDestination() + " med " + getName() + " tar " + getWeight();
  }
}
