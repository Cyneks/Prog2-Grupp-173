import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Exercise3 {

	private final List<Recording> recordings = new ArrayList<>();

	public void exportRecordings(String fileName) throws IOException{
		//Going to use BufferedWriter and pass FileWriter into it
		//so the program doesnt need to make unnecessary amounts of trips
		//to the disc over and over again (speed loss) to write (if you only use FileWriter that is)
		//BufferedWriter buffers all strings and access disc to write in file only if i close or it reaches storage limit per buffer
		BufferedWriter buffer = new BufferedWriter(new FileWriter(fileName));

		StringBuilder fileText = new StringBuilder();

		

		for (Recording currentRec : recordings){

			fileText.append("<recording> \n  ");
			fileText.append("<artist>" + currentRec.getArtist() + "</artist>\n  ");
			fileText.append("<title>" + currentRec.getTitle() + "</title>\n  ");
			fileText.append("<year>" + currentRec.getYear() + "</year>\n  ");
			fileText.append("<genres>\n    ");

			int idx = 0;
			int idxOfLastGenre = currentRec.getGenre().size() - 1;
			for (String currentGenre : currentRec.getGenre()){

				if (idx == idxOfLastGenre) fileText.append("<genre>" + currentGenre + "</genre>\n  ");
				else fileText.append("<genre>" + currentGenre + "</genre>\n    ");
				idx++;

			}

			fileText.append("</genres>\n");
			fileText.append("</recording>\n");

		}

		buffer.write(fileText.toString());
		buffer.close();

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
