import java.util.Collection;
import java.util.SortedSet;

public interface SearchOperations {
    
    public long numberOfArtists();

    public long numberOfGenres();

    public long numberOfTitles();

    public boolean doesArtistExist(String name);

    public Collection<String> getGenres();

    public Recording getRecordingByName(String title);

    public Collection<Recording> getRecordingsAfter(int year);

    public SortedSet<Recording> getRecordingsByArtistOrderedByYearAsc(String artist);

    public Collection<Recording> getRecordingsByGenre(String genre);

    public Collection<Recording> getRecordingsByGenreAndYear(String genre, int yearFrom, int yearTo);

    public Collection<Recording> offerHasNewRecordings(Collection<Recording> offered);

}
