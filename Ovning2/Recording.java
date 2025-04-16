import java.util.Collection;
import java.util.Objects;
import java.util.Set;

public class Recording implements Comparable<Recording> {
  private final int year;
  private final String artist;
  private final String title;
  private final String type;
  private final Set<String> genre;

  public Recording(String title, String artist, int year, String type, Set<String> genre) {
    this.title = title;
    this.year = year;
    this.artist = artist;
    this.type = type;
    this.genre = genre;
  }

  public boolean equals(Object obj) {
    if (obj instanceof Recording rec) {
      return this.title.equals(rec.title) && this.getYear() == rec.getYear() && this.artist.equals(rec.artist);
    }

    return false;
	}

  public int hashCode() {
    return Objects.hash(this.getYear(), this.getArtist(), this.getTitle());
  }
  
  public int compareTo(Recording rc) {
    if (this.getYear() < rc.getYear()) {
      return -1;
    } else if (this.getYear() > rc.getYear()) {
      return 1;
    } else if (this.getArtist().compareTo(rc.getArtist()) < 0) {
      return -1;
    } else if (this.getArtist().compareTo(rc.getArtist()) > 0) {
      return 1;
    } else if (this.getTitle().compareTo(rc.getTitle()) < 0) {
      return -1;
    } else if (this.getTitle().compareTo(rc.getTitle()) > 0) {
      return 1;
    }
    
    return 0;
  }

  public String getArtist() {
    return artist;
  }

  public Collection<String> getGenre() {
    return genre;
  }

  public String getTitle() {
    return title;
  }

  public String getType() {
    return type;
  }

  public int getYear() {
    return year;
  }

  @Override
  public String toString() {
    return String.format("{ %s | %s | %s | %d | %s }", artist, title, genre, year, type);
  }
}
