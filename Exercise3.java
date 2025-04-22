import java.util.*;
import java.io.*;

public class Exercise3 {

	private final List<Recording> recordings = new ArrayList<>();

	public void exportRecordings(String fileName) {

	}

	public void importRecordings(String fileName) {

	}

	public Map<Integer, Double> importSales(String fileName) throws IOException {
		HashMap<Integer, Double> salesMap = new HashMap<>();
		
		var fileIn = new FileInputStream(fileName);
		var dataIn = new DataInputStream(fileIn);

		int amount = dataIn.readInt();

		for (int i = 0; i < amount; i++) {
			int first = dataIn.readInt();
			int second = dataIn.readInt();
			dataIn.readInt();
			double fourth = dataIn.readDouble();
			
			int[] integers = {first, second};

			String combined = "";

			for (int num : integers) {
            	if (num < 10) {
                	combined = combined + "0" + String.valueOf(num);
            	} else {
                	combined = combined + String.valueOf(num);
            	}
        	}

			if (!salesMap.containsKey(Integer.valueOf(combined))) {
				salesMap.put(Integer.valueOf(combined), fourth);
			} else {
				salesMap.put(Integer.valueOf(combined), salesMap.get(Integer.valueOf(combined)) + fourth);
			}
		}

		fileIn.close();

		return salesMap;
	}

	public List<Recording> getRecordings() {
		return Collections.unmodifiableList(recordings);
	}

	public void setRecordings(List<Recording> recordings) {
		this.recordings.clear();
		this.recordings.addAll(recordings);
	}
}
