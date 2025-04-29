import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.SortedMap;

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
       return null;
    }

    public int getPopularity(Record item) {
       return -1;
    }

    public SortedMap<Integer, Set<Record>> getTop5() {
       return null;
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
