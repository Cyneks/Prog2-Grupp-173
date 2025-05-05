import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class Exercise4 {
   public Graph<Node> graph = new ListGraph<>();

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
      SortedMap<Integer, SortedSet<Record>> alsoLiked = new TreeMap<>(Collections.reverseOrder());

      for (Edge<Node> itemEdge : graph.getEdgesFrom(item)){
         Node personConnectedToItem = (Person) itemEdge.getDestination();

         for (Edge<Node> personEdge : graph.getEdgesFrom(personConnectedToItem)){
            Record recordConnectedToPerson = (Record) personEdge.getDestination();

            if (!alsoLiked.containsKey(getPopularity(recordConnectedToPerson))) {
               alsoLiked.put(getPopularity(recordConnectedToPerson), new TreeSet<Record>(Comparator.comparing(Record::getName)));
            }

            alsoLiked.get(getPopularity(recordConnectedToPerson)).add(recordConnectedToPerson);
         }
      }

      return alsoLiked;
   }

   public int getPopularity(Record item) {
      if (!graph.getNodes().contains(item)) return 0;
      return graph.getEdgesFrom(item).size();
   }

   public SortedMap<Integer, Set<Record>> getTop5() {
      SortedMap<Integer, Set<Record>> allRecords = new TreeMap<>(Collections.reverseOrder());

      for (Node node : graph.getNodes()) {
         if (node instanceof Record) {
            for (Edge<Node> itemEdge : graph.getEdgesFrom(node)) {
               Node personConnectedToItem = (Person) itemEdge.getDestination();
      
               for (Edge<Node> personEdge : graph.getEdgesFrom(personConnectedToItem)) {
                  Record recordConnectedToPerson = (Record) personEdge.getDestination();
      
                  if (!allRecords.containsKey(getPopularity(recordConnectedToPerson))) {
                     allRecords.put(getPopularity(recordConnectedToPerson), new TreeSet<Record>(Comparator.comparing(Record::getName)));
                  }
      
                  allRecords.get(getPopularity(recordConnectedToPerson)).add(recordConnectedToPerson);
               }
            }
         }
      }

      int amount = 5;
      if (allRecords.keySet().size() < 5) {
         amount = allRecords.keySet().size();
      }

      SortedMap<Integer, Set<Record>> topFive = new TreeMap<>(Collections.reverseOrder());
      for (int i = 0; i < amount; i++) {
         Integer max = Collections.max(allRecords.keySet());

         topFive.put(max, allRecords.get(max));
         allRecords.remove(max);
      }

      return topFive;
   }

   public void loadRecommendationGraph(String fileName) {
      HashMap<String, Node> nodes = new HashMap<>();

      try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
         String line;
         while ((line = br.readLine()) != null) {
				String[] parts = line.split(";");

            String personName = parts[0];
            Person person = new Person(personName);
            nodes.putIfAbsent(personName, person);
            graph.add(person);

            String recordName = parts[1];
            String recordArtist = parts[2];
            Record record = new Record(recordName, recordArtist);
            nodes.putIfAbsent(parts[1], record);
            graph.add(record);

				graph.connect(nodes.get(personName), nodes.get(recordName), "Edge", 0);
			}
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}
