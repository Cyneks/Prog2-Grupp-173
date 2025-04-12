import java.util.*;

public class Searcher implements SearchOperations {
  private TreeSet<Recording> set = new TreeSet<>();
  private HashMap<String, Set<Recording>> artistMap = new HashMap<>();
  private HashSet<String> genreSet = new HashSet<>();

  public Searcher(Collection<Recording> data) {
    Collection<Recording> recordings = data;

    set.addAll(recordings);

    //HashMap med artister som keys, set med deras låtar som value
    for (Recording album : recordings) {
      String artist = album.getArtist();
      if (!artistMap.containsKey(artist)) {
        artistMap.put(artist, new TreeSet<Recording>());
      }

      artistMap.get(artist).add(album);

      //Lägger till alla genrer i en recording till ett HashSet
      for (String genre : album.getGenre()) {
        genreSet.add(genre);
      }
    }
  }

  @Override
  public long numberOfArtists() {
    long numberOfUniqueArtists = set.stream().map((Recording recording) -> recording.getArtist()).distinct().count();
    return numberOfUniqueArtists;
  }

  @Override
  public long numberOfGenres() {
    return genreSet.size();
  }

  @Override
  public long numberOfTitles() {
    long numberOfUniqueTitles = set.stream().map((Recording recording) -> recording.getTitle()).distinct().count();
    return numberOfUniqueTitles;
  }

  @Override
  public boolean doesArtistExist(String name) {
    return artistMap.containsKey(name);
  }

  @Override
  public Collection<String> getGenres() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getGenres'");
  }

  @Override
  public Recording getRecordingByName(String title) {
    return set.stream()
    .filter(recording -> recording.getTitle()
    .equals(title)).findFirst()
    .orElse(null);
  }

  @Override
  public Collection<Recording> getRecordingsAfter(int year) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getRecordingsAfter'");
  }

  @Override
  public SortedSet<Recording> getRecordingsByArtistOrderedByYearAsc(String artist) {
    return Collections.unmodifiableSortedSet((SortedSet<Recording>) artistMap.get(artist));
  }

  @Override
  public Collection<Recording> getRecordingsByGenre(String genre) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getRecordingsByGenre'");
  }

  @Override
  public Collection<Recording> getRecordingsByGenreAndYear(String genre, int yearFrom, int yearTo) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getRecordingsByGenreAndYear'");
  }

  @Override
  public Collection<Recording> offerHasNewRecordings(Collection<Recording> offered) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'offerHasNewRecordings'");
  }
}
