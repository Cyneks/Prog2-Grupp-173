import java.util.Collections;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;
import java.util.SortedSet;
import java.util.HashMap;
import java.util.HashSet;

public class Searcher implements SearchOperations {
  private HashMap<String, Recording> titleMap = new HashMap<>();
  private HashMap<String, Set<Recording>> artistMap = new HashMap<>();
  private HashMap<Integer, Set<Recording>> yearMap = new HashMap<>();
  private HashMap<String, Set<Recording>> genreMap = new HashMap<>();
  private HashSet<String> genreSet = new HashSet<>();

  public Searcher(Collection<Recording> data) {
    Collection<Recording> recordings = data;

    for (Recording album : recordings) {
      titleMap.put(album.getTitle(), album);

      String artist = album.getArtist();
      if (!artistMap.containsKey(artist)) {
        artistMap.put(artist, new TreeSet<Recording>());
      }

      //HashMap med artister som keys, set med deras album som value
      artistMap.get(artist).add(album);


      int year = album.getYear();
      if (!yearMap.containsKey(year)) {
        yearMap.put(year, new TreeSet<Recording>());
      }

      //HashMap med år som keys, set med album från det året som value
      yearMap.get(year).add(album);


      for (String genre : album.getGenre()) {
        //Lägger till alla genrer i en recording till ett HashSet
        genreSet.add(genre);

        if (!genreMap.containsKey(genre)) {
          genreMap.put(genre, new TreeSet<Recording>());
        }

        //HashMap med genrer som keys, set med album med samma genre som value
        genreMap.get(genre).add(album);
      }
    }
  }

  @Override
  public long numberOfArtists() {
    return artistMap.keySet().size();
  }

  @Override
  public long numberOfGenres() {
    return genreSet.size();
  }

  @Override
  public long numberOfTitles() {
    return titleMap.size();
  }

  @Override
  public boolean doesArtistExist(String name) {
    return artistMap.containsKey(name);
  }

  @Override
  public Collection<String> getGenres() {
    return Collections.unmodifiableCollection(genreSet);
  }

  @Override
  public Recording getRecordingByName(String title) {
    return titleMap.get(title);
  }

  @Override
  public Collection<Recording> getRecordingsAfter(int year) {
    TreeSet<Recording> yearSet = new TreeSet<>();
    for (int recYear : yearMap.keySet()) {
      if (recYear >= year) {
        for (Recording album : yearMap.get(recYear)) {
          yearSet.add(album);
        }
      }
    }
    
    return Collections.unmodifiableCollection(yearSet);
  }

  @Override
  public SortedSet<Recording> getRecordingsByArtistOrderedByYearAsc(String artist) {
    if (artistMap.keySet().contains(artist)) {
      return Collections.unmodifiableSortedSet((SortedSet<Recording>) artistMap.get(artist));
    }

    return Collections.emptySortedSet();
  }

  @Override
  public Collection<Recording> getRecordingsByGenre(String genre) {
    if (genreSet.contains(genre)) {
      return Collections.unmodifiableCollection(genreMap.get(genre));
    }
    
    return Collections.emptySortedSet();
  }

  @Override
  public Collection<Recording> getRecordingsByGenreAndYear(String genre, int yearFrom, int yearTo) {
    TreeSet<Recording> genreYearSet = new TreeSet<>();
    for (int recYear : yearMap.keySet()) {
      if (recYear >= yearFrom && recYear <= yearTo) {
        for (Recording album : yearMap.get(recYear)) {
          for (String recGenre : album.getGenre()) {
            if (recGenre.equals(genre)) {
              genreYearSet.add(album);
            }
          }
        }
      }
    }
    
    return Collections.unmodifiableCollection(genreYearSet);
  }

  @Override
  public Collection<Recording> offerHasNewRecordings(Collection<Recording> offered) {
    TreeSet<Recording> newRecordings = new TreeSet<>();
    for (Recording recording : offered) {
      if (!titleMap.keySet().contains(recording.getTitle())) {
        newRecordings.add(recording);
      }
    }

    return Collections.unmodifiableCollection(newRecordings);
  }
}
