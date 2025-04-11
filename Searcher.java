import java.util.Collection;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

public class Searcher implements SearchOperations {
  private TreeSet<Recording> set = new TreeSet<>();
  private Iterator<Recording> loop = set.iterator();

  public Searcher(Collection<Recording> data) {
    Collection<Recording> recordings = data;

    set.addAll(recordings);
  }

  @Override
  public long numberOfArtists() {
    long numberOfUniqueArtists = set.stream().map((Recording recording) -> recording.getArtist()).distinct().count();
    return numberOfUniqueArtists;
  }

  @Override
  public long numberOfGenres() {
    long numberOfUniqueGenres = set.stream().map((Recording recording) -> recording.getGenre()).distinct().count();
    return numberOfUniqueGenres;
  }

  @Override
  public long numberOfTitles() {
    long numberOfUniqueTitles = set.stream().map((Recording recording) -> recording.getTitle()).distinct().count();
    return numberOfUniqueTitles;
  }

  @Override
  public boolean doesArtistExist(String name) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'doesArtistExist'");
  }

  @Override
  public Collection<String> getGenres() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getGenres'");
  }

  @Override
  public Recording getRecordingByName(String title) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getRecordingByName'");
  }

  @Override
  public Collection<Recording> getRecordingsAfter(int year) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getRecordingsAfter'");
  }

  @Override
  public SortedSet<Recording> getRecordingsByArtistOrderedByYearAsc(String artist) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getRecordingsByArtistOrderedByYearAsc'");
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
