import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class Exercise4 {
    private Graph<Node> graph = new ListGraph<>();

    public void loadLocationGraph(String fileName){
      HashMap<String, Node> nodes = new HashMap<>();

      try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
         String line = br.readLine();

         String[] parts = line.split(";");

         String name;
         double coordX;
         double coordY;
         for(int i = 0; i < parts.length; i += 3) {
            name = parts[i];
            coordX = Double.valueOf(parts[i + 1]);
            coordY = Double.valueOf(parts[i + 2]);
            Location place = new Location(name, coordX, coordY);

            nodes.put(parts[i], place);
            graph.add(place);
         }

         while ((line = br.readLine()) != null) {
				parts = line.split(";");

				String from = parts[0];
				String to = parts[1];
				String method = parts[2];
            int time = Integer.valueOf(parts[3]);

				graph.connect(nodes.get(from), nodes.get(to), method, time);
			}
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
    }

    public SortedMap<Integer, SortedSet<Record>> getAlsoLiked(Record item) {
      
      HashMap<Record, Integer> recordCount = new HashMap<>();

      for (Edge<Node> itemEdge : graph.getEdgesFrom(item)){

         Node personConnectedToItem = itemEdge.getDestination();

         if (personConnectedToItem instanceof Person person){

            person = (Person) personConnectedToItem;

            for (Edge<Node> personEdge : graph.getEdgesFrom(person)){

               Node personRecords = personEdge.getDestination();

               if (personRecords instanceof Record personRecord && !personRecord.equals(item)){
                  recordCount.put(personRecord, recordCount.getOrDefault(personRecord, 0) + 1);
               }

            }

         }

      }

      SortedMap<Integer, SortedSet<Record>> alsoLiked = new TreeMap<>(Collections.reverseOrder());

      for (Map.Entry<Record, Integer> entry : recordCount.entrySet()){
         
         int count = entry.getValue();
         alsoLiked.computeIfAbsent(count, k -> new TreeSet<>(Comparator.comparing(Record::getName))).add(entry.getKey());

      }

       return alsoLiked;
    }

    public int getPopularity(Record item) {
      if (!graph.getNodes().contains(item)) return 0;
      return graph.getEdgesFrom(item).size();
    }

    public SortedMap<Integer, Set<Record>> getTop5() {

      SortedMap<Integer, Set<Record>> allRecords = new TreeMap<>(Collections.reverseOrder());

      HashMap<Record, Integer> amountOfLikes = new HashMap<>();

      for (Node node : graph.getNodes()){

         if (node instanceof Person){

            for (Edge<Node> personEdge : graph.getEdgesFrom(node)){

               Node currentNode = personEdge.getDestination();

               if (currentNode instanceof Record record){
                  amountOfLikes.put(record, amountOfLikes.getOrDefault(amountOfLikes, 0) + 1);
               }

            }

         }

      }

      for (Map.Entry<Record, Integer> entry : amountOfLikes.entrySet()){
         Record record = entry.getKey();
         Integer count = entry.getValue();

         allRecords.computeIfAbsent(count, k -> new TreeSet<>(Comparator.comparing(Record::getName))).add(record);

      }

      SortedMap<Integer, Set<Record>> topFive = new TreeMap<>(Collections.reverseOrder());
      int added = 0;
      int limit = 5;

      for (Map.Entry<Integer, Set<Record>> entry : allRecords.entrySet()){

         if (added >= limit) break;
         topFive.put(entry.getKey(), entry.getValue());
         added++;

      }

       return topFive;

    }

    public void loadRecommendationGraph(String fileName) {
      HashMap<String, Node> nodes = new HashMap<>();

      try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
         String line;
         while ((line = br.readLine()) != null) {
				String[] parts = line.split(";");

            String pName = parts[0];
            Person person = new Person(pName);
            nodes.putIfAbsent(pName, person);
            graph.add(person);

            String rName = parts[1];
            String rArtist = parts[2];
            Record record = new Record(rName, rArtist);
            nodes.putIfAbsent(parts[1], record);
            graph.add(record);

				graph.connect(nodes.get(pName), nodes.get(rName), "Edge", 0);
			}
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
    }

}
