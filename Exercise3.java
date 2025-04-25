import java.io.*;
import java.util.*;

public class Main {
	private final List<Recording> recordings = new ArrayList<>();

	public void exportRecordings(String fileName) {
		//Going to use BufferedWriter and pass FileWriter into it
		//so the program doesnt need to make unnecessary amounts of trips
		//to the disc over and over again (speed loss) to write (if you only use FileWriter that is)
		//BufferedWriter buffers all strings and access disc to write in file only if i close or it reaches storage limit per buffer
		try (BufferedWriter buffer = new BufferedWriter(new FileWriter(fileName))) {

			StringBuilder fileText = new StringBuilder();

			for (Recording currentRec : recordings){

				fileText.append("<recording>\n");
				fileText.append("  <artist>").append(currentRec.getArtist()).append("</artist>\n");
				fileText.append("  <title>").append(currentRec.getTitle()).append("</title>\n");
				fileText.append("  <year>").append(currentRec.getYear()).append("</year>\n");
				fileText.append("  <genres>\n");

				for (String currentGenre : currentRec.getGenre()){

					fileText.append("    <genre>").append(currentGenre).append("</genre>\n");

				}

				fileText.append("  </genres>\n");
				fileText.append("</recording>\n");

			}

			buffer.write(fileText.toString());

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public void importRecordings(String fileName) {
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String line;
			while ((line = br.readLine() != null)) {
				if (line.contains(";")) {
					String[] parts = line.split(";");
					String artist = parts[0];
					String album = parts[1];
					int year = Integer.parse(parts[2]);

					line = br.readLine();
					int genreCount = Integer.parseInt(line);

					Set<String> genres = new LinkedHashSet<>();
					for (inr i = 0; i < genreCount; i++) {
						line.br.readLine();
						genres.add(line);
					}
					Recording rec = new Recording(album, artist, year, genres);
					recording.add(rec);
				}
			}
		} catch (FileNotFoundException) {
			System.err.println("File not found");
		} catch (IOException err) {
		}
	}


	public Map<Integer, Double> importSales(String fileName) {
		HashMap<Integer, Double> salesMap = new HashMap<>();

		try {
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
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

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
