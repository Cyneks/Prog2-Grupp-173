import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public final class Data {
  private final List<Recording> recordings = new ArrayList<>();
  private final Random random = new Random();

  public Data() {
    loadData();
  }

  private void add(Recording item) {
    recordings.add(item);
  }

  private void addBoth(String title, String artist, int year, Set<String> genre) {
    addCD(title, artist, year, genre);
    addLP(title, artist, year, genre);
  }

  private void addCD(String name, String artist, int year, Set<String> genre) {
    add(new Recording(name, artist, year, "CD", genre));
  }

  private void addLP(String name, String artist, int year, Set<String> genre) {
    add(new Recording(name, artist, year, "LP", genre));
  }

  private void addRandom(String title, String artist, int year, Set<String> genre) {
    var rnd = random.nextInt(10);
    if (rnd < 3) addCD(title, artist, year, genre);
    else if (rnd < 6) addLP(title, artist, year, genre);
    else addBoth(title, artist, year, genre);
  }

  private void addWithGenres(
      int year, String title, String artist, String mainGenre, String subGenre) {
    var genres =
        Arrays.stream(mainGenre.split(","))
            .map(String::trim)
            .collect(Collectors.toUnmodifiableSet());
    addRandom(title, artist, year, genres);
  }

  public Collection<Recording> getRecordings() {
    return Collections.unmodifiableCollection(recordings);
  }

  private void loadData() {
    addWithGenres(
        1967,
        "Sgt. Pepper's Lonely Hearts Club Band",
        "The Beatles",
        "Rock",
        "Rock & Roll, Psychedelic Rock");
    addWithGenres(1966, "Pet Sounds", "The Beach Boys", "Rock", "Pop Rock, Psychedelic Rock");
    addWithGenres(1966, "Revolver", "The Beatles", "Rock", "Psychedelic Rock, Pop Rock");
    addWithGenres(1965, "Highway 61 Revisited", "Bob Dylan", "Rock", "Folk Rock, Blues Rock");
    addWithGenres(1965, "Rubber Soul", "The Beatles", "Rock, Pop", "Pop Rock");
    addWithGenres(1971, "What's Going On", "Marvin Gaye", "Funk, Soul", "Soul");
    addWithGenres(
        1972,
        "Exile on Main St.",
        "The Rolling Stones",
        "Rock",
        "Blues Rock, Rock & Roll, Classic Rock");
    addWithGenres(1979, "London Calling", "The Clash", "Rock", "Punk, New Wave");
    addWithGenres(
        1966, "Blonde on Blonde", "Bob Dylan", "Rock, Blues", "Folk Rock, Rhythm & Blues");
    addWithGenres(
        1968,
        "The White Album",
        "The Beatles",
        "Rock",
        "Rock & Roll, Pop Rock, Psychedelic Rock, Experimental");
    addWithGenres(1976, "The Sun Sessions", "Elvis Presley", "Rock", "Rock & Roll");
    addWithGenres(1959, "Kind of Blue", "Miles Davis", "Jazz", "Modal");
    addWithGenres(
        1967,
        "The Velvet Underground & Nico",
        "The Velvet Underground",
        "Rock",
        "Garage Rock, Art Rock, Experimental");
    addWithGenres(
        1969, "Abbey Road", "The Beatles", "Rock", "Psychedelic Rock, Classic Rock, Pop Rock");
    addWithGenres(
        1967,
        "Are You Experienced",
        "The Jimi Hendrix Experience",
        "Rock, Blues",
        "Blues Rock, Psychedelic Rock");
    addWithGenres(1975, "Blood on the Tracks", "Bob Dylan", "Rock", "Folk Rock, Acoustic, Ballad");
    addWithGenres(1991, "Nevermind", "Nirvana", "Rock", "Alternative Rock, Grunge");
    addWithGenres(1975, "Born to Run", "Bruce Springsteen", "Rock", "Pop Rock");
    addWithGenres(
        1968,
        "Astral Weeks",
        "Van Morrison",
        "Jazz, Rock, Blues, Folk, World, Country",
        "Acoustic, Classic Rock, Free Improvisation");
    addWithGenres(1982, "Thriller", "Michael Jackson", "Funk, Soul, Pop", "Disco");
    addWithGenres(1982, "The Great Twenty_Eight", "Chuck Berry", "Rock", "Rock & Roll");
    addWithGenres(1990, "The Complete Recordings", "Robert Johnson", "Blues", "Delta Blues");
    addWithGenres(
        1970, "John Lennon/Plastic Ono Band", "John Lennon / Plastic Ono Band", "Rock", "Pop Rock");
    addWithGenres(1973, "Innervisions", "Stevie Wonder", "Funk, Soul", "Soul");
    addWithGenres(
        1963, "Live at the Apollo, 1962", "James Brown", "Funk, Soul", "Rhythm & Blues, Soul");
    addWithGenres(1977, "Rumours", "Fleetwood Mac", "Rock", "Pop Rock");
    addWithGenres(1987, "The Joshua Tree", "U2", "Rock", "Alternative Rock, Pop Rock");
    addWithGenres(
        1971, "Who's Next", "The Who", "Rock", "Hard Rock, Mod, Prog Rock, Psychedelic Rock");
    addWithGenres(1969, "Led Zeppelin", "Led Zeppelin", "Rock", "Blues Rock, Hard Rock");
    addWithGenres(1971, "Blue", "Joni Mitchell", "Pop", "Acoustic, Ballad, Folk");
    addWithGenres(
        1965,
        "Bringing It All Back Home",
        "Bob Dylan",
        "Rock, Folk, World, Country",
        "Folk Rock, Folk");
    addWithGenres(1969, "Let It Bleed", "The Rolling Stones", "Rock", "Blues Rock, Hard Rock");
    addWithGenres(1976, "Ramones", "Ramones", "Rock", "Rock & Roll, Punk");
    addWithGenres(
        1968, "Music From Big Pink", "The Band", "Rock", "Folk Rock, Acoustic, Blues Rock");
    addWithGenres(
        1972,
        "The Rise and Fall of Ziggy Stardust and the Spiders From Mars",
        "David Bowie",
        "Rock",
        "Classic Rock, Glam");
    addWithGenres(1971, "Tapestry", "Carole King", "Rock, Pop", "Folk Rock, Soft Rock");
    addWithGenres(1976, "Hotel California", "Eagles", "Rock", "Classic Rock");
    addWithGenres(2001, "The Anthology", "Muddy Waters", "Folk, World, Country", "Folk");
    addWithGenres(1963, "Please Please Me", "The Beatles", "Rock", "Beat, Rock & Roll");
    addWithGenres(1967, "Forever Changes", "Love", "Rock", "Folk Rock, Psychedelic Rock");
    addWithGenres(
        1977, "Never Mind the Bollocks Here's the Sex Pistols", "Sex Pistols", "Rock", "Punk");
    addWithGenres(1967, "The Doors", "The Doors", "Rock", "Psychedelic Rock");
    addWithGenres(1973, "The Dark Side of the Moon", "Pink Floyd", "Rock", "Prog Rock");
    addWithGenres(1975, "Horses", "Patti Smith", "Rock", "Art Rock");
    addWithGenres(
        1969,
        "The Brown Album",
        "The Band",
        "Classical, Stage & Screen",
        "Soundtrack, Modern Classical, Contemporary, Score");
    addWithGenres(
        1984,
        "Legend: The Best of Bob Marley and The Wailers",
        "Bob Marley & The Wailers",
        "Reggae",
        "Reggae, Roots Reggae");
    addWithGenres(1965, "A Love Supreme", "John Coltrane", "Jazz", "Free Jazz, Hard Bop, Modal");
    addWithGenres(
        1988,
        "It Takes a Nation of Millions to Hold Us Back",
        "Public Enemy",
        "Hip Hop",
        "Conscious");
    addWithGenres(
        1971, "At Fillmore East", "The Allman Brothers Band", "Rock, Blues", "Blues Rock");
    addWithGenres(
        1957,
        "Here's Little Richard",
        "Little Richard",
        "Rock, Blues",
        "Rock & Roll, Rhythm & Blues");
    addWithGenres(
        1970, "Bridge Over Troubled Water", "Simon & Garfunkel", "Rock", "Folk Rock, Classic Rock");
    addWithGenres(1975, "Greatest Hits (Al Green)", "Al Green", "Funk, Soul", "Soul");
    addWithGenres(1964, "Meet The Beatles!", "The Beatles", "Rock", "Beat, Rock & Roll");
    addWithGenres(
        1991,
        "The Birth of Soul",
        "Ray Charles",
        "Jazz, Funk, Soul",
        "Rhythm & Blues, Big Band, Soul, Soul-Jazz");
    addWithGenres(
        1968,
        "Electric Ladyland",
        "The Jimi Hendrix Experience",
        "Rock, Blues",
        "Electric Blues, Psychedelic Rock");
    addWithGenres(1956, "Elvis Presley", "Elvis Presley", "Rock", "Rock & Roll, Rockabilly");
    addWithGenres(1976, "Songs in the Key of Life", "Stevie Wonder", "Funk, Soul", "Soul, Disco");
    addWithGenres(
        1968,
        "Beggars Banquet",
        "The Rolling Stones",
        "Rock, Funk, Soul, Pop",
        "Blues Rock, Southern Rock, Classic Rock");
    addWithGenres(
        1976, "Chronicle: The 20 Greatest Hits", "Creedence Clearwater Revival", "Rock", "None");
    addWithGenres(
        1969,
        "Trout Mask Replica",
        "Captain Beefheart & His Magic Band",
        "Rock, Blues",
        "Dialogue, Field Recording, Avantgarde, Electric Blues, Psychedelic Rock, Experimental");
    addWithGenres(
        1970, "Greatest Hits", "Sly & The Family Stone", "Funk, Soul", "Rhythm & Blues, Funk");
    addWithGenres(
        1987, "Appetite for Destruction", "Guns N' Roses", "Rock", "Hard Rock, Heavy Metal");
    addWithGenres(
        1991,
        "Achtung Baby",
        "U2",
        "Electronic, Rock",
        "Pop Rock, Synth-pop, Alternative Rock, Arena Rock");
    addWithGenres(1971, "Sticky Fingers", "The Rolling Stones", "Rock", "Classic Rock");
    addWithGenres(
        1991,
        "Back to Mono (1958-1969)",
        "Phil Spector",
        "Rock, Funk, Soul, Pop",
        "Doo Wop, Pop Rock, Ballad, Rhythm & Blues");
    addWithGenres(
        1970,
        "Moondance",
        "Van Morrison",
        "Jazz, Rock, Funk, Soul, Folk, World, Country",
        "Folk Rock, Rhythm & Blues, Classic Rock, Contemporary Jazz");
    addWithGenres(
        2000, "Kid A", "Radiohead", "Electronic, Rock", "Alternative Rock, IDM, Experimental");
    addWithGenres(
        1979, "Off the Wall", "Michael Jackson", "Funk, Soul, Pop", "Disco, Soul, Ballad");
    addWithGenres(
        1971, "[Led Zeppelin IV]", "Led Zeppelin", "Rock", "Hard Rock, Classic Rock, Blues Rock");
    addWithGenres(1977, "The Stranger", "Billy Joel", "Rock", "Pop Rock");
    addWithGenres(
        1986,
        "Graceland",
        "Paul Simon",
        "Jazz, Rock, Funk, Soul, Pop, Folk, World, Country",
        "Folk Rock, Pop Rock, African, Afrobeat, Zydeco, Funk, Rhythm & Blues");
    addWithGenres(
        1972, "Superfly", "Curtis Mayfield", "Funk, Soul, Stage & Screen", "Soundtrack, Soul");
    addWithGenres(1975, "Physical Graffiti", "Led Zeppelin", "Rock", "Classic Rock");
    addWithGenres(1970, "After the Gold Rush", "Neil Young", "Rock", "Rock & Roll, Country Rock");
    addWithGenres(1991, "Star Time", "James Brown", "Funk, Soul", "Soul, Funk, Disco");
    addWithGenres(
        1984,
        "Purple Rain",
        "Prince and the Revolution",
        "Electronic, Rock, Funk, Soul, Stage & Screen",
        "Pop Rock, Funk, Soundtrack, Synth-pop");
    addWithGenres(1980, "Back in Black", "AC/DC", "Rock", "Hard Rock");
    addWithGenres(1965, "Otis Blue: Otis Redding Sings Soul", "Otis Redding", "Funk, Soul", "Soul");
    addWithGenres(
        1969, "Led Zeppelin II", "Led Zeppelin", "Rock", "Blues Rock, Classic Rock, Hard Rock");
    addWithGenres(1971, "Imagine", "John Lennon", "Rock", "Pop Rock");
    addWithGenres(1977, "The Clash", "The Clash", "Rock", "Punk");
    addWithGenres(1972, "Harvest", "Neil Young", "Rock", "Folk Rock, Country Rock, Classic Rock");
    addWithGenres(
        1967,
        "Axis: Bold as Love",
        "The Jimi Hendrix Experience",
        "Rock",
        "Psychedelic Rock, Electric Blues");
    addWithGenres(
        1967, "I Never Loved a Man the Way I Love You", "Aretha Franklin", "Funk, Soul", "Soul");
    addWithGenres(1968, "Lady Soul", "Aretha Franklin", "Funk, Soul", "Soul");
    addWithGenres(1984, "Born in the U.S.A.", "Bruce Springsteen", "Rock", "Pop Rock");
    addWithGenres(1979, "The Wall", "Pink Floyd", "Rock", "Alternative Rock, Prog Rock");
    addWithGenres(1968, "At Folsom Prison", "Johnny Cash", "Folk, World, Country", "Country");
    addWithGenres(
        1969, "Dusty in Memphis", "Dusty Springfield", "Rock, Funk, Soul", "Pop Rock, Soul");
    addWithGenres(1972, "Talking Book", "Stevie Wonder", "Funk, Soul", "Soul, Funk");
    addWithGenres(
        1973, "Goodbye Yellow Brick Road", "Elton John", "Rock", "Pop Rock, Classic Rock");
    addWithGenres(1978, "20 Golden Greats", "Buddy Holly", "Rock", "Rock & Roll");
    addWithGenres(1987, "Sign Of the Times", "Prince", "Rock, Reggae", "Ska, Reggae-Pop");
    addWithGenres(1984, "40 Greatest Hits", "Hank Williams", "Folk, World, Country", "Country");
    addWithGenres(1970, "Bitches Brew", "Miles Davis", "Jazz", "Fusion");
    addWithGenres(1969, "Tommy", "The Who", "Rock", "Psychedelic Rock");
    addWithGenres(1963, "The Freewheelin' Bob Dylan", "Bob Dylan", "Folk, World, Country", "Folk");
    addWithGenres(1978, "This Year's Model", "Elvis Costello", "Rock", "New Wave");
    addWithGenres(
        1971,
        "There's a Riot Goin' On",
        "Sly & The Family Stone",
        "Funk, Soul",
        "Rhythm & Blues, Funk");
    addWithGenres(1968, "Odessey and Oracle", "The Zombies", "Rock", "Psychedelic Rock");
    addWithGenres(1955, "In the Wee Small Hours", "Frank Sinatra", "Jazz, Pop", "Big Band, Ballad");
    addWithGenres(1966, "Fresh Cream", "Cream", "Rock, Blues", "Blues Rock, Electric Blues");
    addWithGenres(1959, "Giant Steps", "John Coltrane", "Jazz", "Hard Bop");
    addWithGenres(
        1970, "Sweet Baby James", "James Taylor", "Rock", "Folk Rock, Acoustic, Soft Rock");
    addWithGenres(
        1962,
        "Modern Sounds in Country and Western Music",
        "Ray Charles",
        "Funk, Soul, Folk, World, Country",
        "Country, Rhythm & Blues");
    addWithGenres(1977, "Rocket to Russia", "Ramones", "Rock", "Rock & Roll, Punk");
    addWithGenres(
        2003,
        "Portrait of a Legend 1951-1964",
        "Sam Cooke",
        "Latin, Funk, Soul",
        "Soul, Rhythm & Blues, Gospel, Cha-Cha");
    addWithGenres(1971, "Hunky Dory", "David Bowie", "Rock", "Classic Rock, Glam");
    addWithGenres(1966, "Aftermath", "The Rolling Stones", "Rock", "Blues Rock, Pop Rock");
    addWithGenres(1970, "Loaded", "The Velvet Underground", "Rock", "Art Rock, Classic Rock");
    addWithGenres(1995, "The Bends", "Radiohead", "Rock", "Alternative Rock");
    addWithGenres(
        1966,
        "If You Can Believe Your Eyes and Ears",
        "The Mamas and the Papas",
        "Rock",
        "Folk Rock, Pop Rock");
    addWithGenres(1974, "Court and Spark", "Joni Mitchell", "Rock", "Soft Rock, Pop Rock");
    addWithGenres(1967, "Disraeli Gears", "Cream", "Rock", "Psychedelic Rock, Blues Rock");
    addWithGenres(1967, "The Who Sell Out", "The Who", "Rock", "Psychedelic Rock, Mod");
    addWithGenres(
        1965, "Out of Our Heads", "The Rolling Stones", "Rock", "Blues Rock, Rock & Roll");
    addWithGenres(
        1970, "Layla and Other Assorted Love Songs", "Derek and the Dominos", "Rock", "Blues Rock");
    addWithGenres(2005, "Late Registration", "Kanye West", "Hip Hop", "None");
    addWithGenres(1960, "At Last!", "Etta James", "Funk, Soul, Blues", "Rhythm & Blues, Soul");
    addWithGenres(1968, "Sweetheart of the Rodeo", "The Byrds", "Rock", "Folk Rock, Country Rock");
    addWithGenres(
        1969, "Stand!", "Sly & The Family Stone", "Funk, Soul", "Funk, Psychedelic, Disco");
    addWithGenres(
        1972,
        "The Harder They Come",
        "Various Artists",
        "Reggae,Pop,Folk, World, Country,Stage & Screen",
        "Reggae,Roots Reggae,Rocksteady,Contemporary,Soundtrack");
    addWithGenres(1986, "Raising Hell", "Run D.M.C.", "Hip Hop", "None");
    addWithGenres(1967, "Moby Grape", "Moby Grape", "Rock", "Folk Rock, Psychedelic Rock");
    addWithGenres(1971, "Pearl", "Janis Joplin", "Rock, Blues", "Blues Rock");
    addWithGenres(1973, "Catch a Fire", "The Wailers", "Reggae", "Roots Reggae");
    addWithGenres(
        1967, "Younger Than Yesterday", "The Byrds", "Rock", "Folk Rock, Psychedelic Rock");
    addWithGenres(
        1973, "Raw Power", "Iggy and The Stooges", "Rock", "Garage Rock, Hard Rock, Punk");
    addWithGenres(
        1980, "Remain in Light", "Talking Heads", "Electronic, Rock", "New Wave, Art Rock, Funk");
    addWithGenres(1977, "Marquee Moon", "Television", "Rock", "New Wave, Punk");
    addWithGenres(1970, "Paranoid", "Black Sabbath", "Rock", "Hard Rock, Heavy Metal");
    addWithGenres(
        1977,
        "Saturday Night Fever: The Original Movie Sound Track",
        "Various Artists",
        "Electronic,Stage & Screen",
        "Soundtrack,Disco");
    addWithGenres(
        1973, "The Wild, the Innocent & the E Street Shuffle", "Bruce Springsteen", "Rock", "None");
    addWithGenres(1994, "Ready to Die", "The Notorious B.I.G.", "Hip Hop", "Thug Rap");
    addWithGenres(1992, "Slanted and Enchanted", "Pavement", "Rock", "Alternative Rock");
    addWithGenres(1974, "Greatest Hits (Elton John)", "Elton John", "Rock", "Classic Rock");
    addWithGenres(1985, "Tim", "The Replacements", "Rock", "Indie Rock");
    addWithGenres(1992, "The Chronic", "Dr. Dre", "Hip Hop", "Gangsta");
    addWithGenres(1974, "Rejuvenation", "The Meters", "Funk, Soul", "Bayou Funk, Soul");
    addWithGenres(
        1978, "Parallel Lines", "Blondie", "Electronic, Rock", "New Wave, Pop Rock, Punk, Disco");
    addWithGenres(1965, "Live at the Regal", "B.B. King", "Blues", "Chicago Blues");
    addWithGenres(
        1963,
        "A Christmas Gift for You From Phil Spector",
        "Phil Spector",
        "Rock, Funk, Soul, Pop",
        "Pop Rock");
    addWithGenres(
        1968,
        "Gris-Gris",
        "Dr. John, the Night Tripper",
        "Jazz, Rock, Funk, Soul, Blues",
        "Soul-Jazz, Louisiana Blues, Fusion, Bayou Funk");
    addWithGenres(1988, "Straight Outta Compton", "N.W.A", "Hip Hop", "Gangsta");
    addWithGenres(1977, "Aja", "Steely Dan", "Jazz, Rock", "Jazz-Rock, Classic Rock");
    addWithGenres(
        1967, "Surrealistic Pillow", "Jefferson Airplane", "Rock", "Folk Rock, Psychedelic Rock");
    addWithGenres(1970, "Deja vu", "Crosby, Stills, Nash & Young", "Rock", "Classic Rock");
    addWithGenres(1973, "Houses of the Holy", "Led Zeppelin", "Rock", "Classic Rock");
    addWithGenres(
        1969, "Santana", "Santana", "Rock, Latin, Funk, Soul", "Afro-Cuban, Psychedelic Rock");
    addWithGenres(1978, "Darkness on the Edge of Town", "Bruce Springsteen", "Rock", "Pop Rock");
    addWithGenres(2004, "Funeral", "Arcade Fire", "Rock", "Indie Rock");
    addWithGenres(
        1979,
        "The B 52's / Play Loud",
        "The B 52's",
        "Electronic, Rock, Pop",
        "New Wave, Punk, Mod");
    addWithGenres(1991, "The Low End Theory", "A Tribe Called Quest", "Hip Hop", "Conscious");
    addWithGenres(1958, "Moanin' in the Moonlight", "Howlin' Wolf", "Blues", "Chicago Blues");
    addWithGenres(1980, "Pretenders", "Pretenders", "Rock", "Alternative Rock, New Wave");
    addWithGenres(
        1989,
        "Paul's Boutique",
        "Beastie Boys",
        "Hip Hop, Rock, Funk, Soul",
        "Alternative Rock, Pop Rap, Psychedelic");
    addWithGenres(1980, "Closer", "Joy Division", "Rock", "Post-Punk, New Wave");
    addWithGenres(
        1975,
        "Captain Fantastic and the Brown Dirt Cowboy",
        "Elton John",
        "Rock",
        "Pop Rock, Classic Rock");
    addWithGenres(1975, "Alive!", "KISS", "Rock", "Hard Rock, Glam");
    addWithGenres(1971, "Electric Warrior", "T. Rex", "Rock", "Glam, Classic Rock");
    addWithGenres(
        1968, "The Dock of the Bay", "Otis Redding", "Funk, Soul", "Rhythm & Blues, Soul");
    addWithGenres(1997, "OK Computer", "Radiohead", "Electronic, Rock", "Alternative Rock");
    addWithGenres(1982, "1999", "Prince", "Funk, Soul, Pop", "None");
    addWithGenres(
        2002,
        "The Very Best of Linda Ronstadt",
        "Linda Ronstadt",
        "Rock, Pop",
        "Soft Rock, Pop Rock");
    addWithGenres(1973, "Let's Get It On", "Marvin Gaye", "Funk, Soul", "Soul");
    addWithGenres(
        1982,
        "Imperial Bedroom",
        "Elvis Costello & The Attractions",
        "Rock",
        "Alternative Rock, New Wave");
    addWithGenres(1986, "Master of Puppets", "Metallica", "Rock", "Thrash, Speed Metal");
    addWithGenres(1977, "My Aim Is True", "Elvis Costello", "Rock", "New Wave, Pop Rock");
    addWithGenres(1977, "Exodus", "Bob Marley & The Wailers", "Reggae", "Reggae, Roots Reggae");
    addWithGenres(1970, "Live at Leeds", "The Who", "Rock", "Classic Rock, Blues Rock, Hard Rock");
    addWithGenres(
        1968,
        "The Notorious Byrd Brothers",
        "The Byrds",
        "Rock",
        "Psychedelic Rock, Folk Rock, Country Rock, Pop Rock");
    addWithGenres(1971, "Every Picture Tells a Story", "Rod Stewart", "Rock", "Pop Rock");
    addWithGenres(
        1972, "Something/Anything?", "Todd Rundgren", "Rock", "Power Pop, Pop Rock, Prog Rock");
    addWithGenres(1976, "Desire", "Bob Dylan", "Rock", "Folk Rock");
    addWithGenres(1970, "Close to You", "Carpenters", "Rock, Pop", "Pop Rock, Vocal");
    addWithGenres(1976, "Rocks", "Aerosmith", "Rock", "Hard Rock, Classic Rock");
    addWithGenres(1978, "One Nation Under a Groove", "Funkadelic", "Funk, Soul", "P.Funk");
    addWithGenres(
        1992,
        "The Anthology: 1961-1977",
        "Curtis Mayfield and The Impressions",
        "Funk, Soul",
        "Rhythm & Blues, Soul, Funk");
    addWithGenres(
        2001, "The Definitive Collection", "ABBA", "Electronic, Pop", "Europop, Synth-pop, Disco");
    addWithGenres(
        1965,
        "The Rolling Stones, Now!",
        "The Rolling Stones",
        "Rock, Blues, Pop",
        "Pop Rock, Rhythm & Blues, Rock & Roll");
    addWithGenres(
        1974, "Natty Dread", "Bob Marley & The Wailers", "Reggae", "Reggae, Roots Reggae");
    addWithGenres(1975, "Fleetwood Mac", "Fleetwood Mac", "Rock, Pop", "Folk Rock, Pop Rock");
    addWithGenres(1975, "Red Headed Stranger", "Willie Nelson", "Folk, World, Country", "Country");
    addWithGenres(1990, "The Immaculate Collection", "Madonna", "Electronic, Pop", "Synth-pop");
    addWithGenres(1969, "The Stooges", "The Stooges", "Rock", "Garage Rock, Punk");
    addWithGenres(1973, "Fresh", "Sly & The Family Stone", "Funk, Soul", "Rhythm & Blues, Funk");
    addWithGenres(
        1986,
        "So",
        "Peter Gabriel",
        "Electronic, Rock, Funk, Soul, Pop",
        "Art Rock, Pop Rock, Synth-pop, Funk");
    addWithGenres(
        1967, "Buffalo Springfield Again", "Buffalo Springfield", "Rock, Pop", "Country Rock");
    addWithGenres(
        1969,
        "Happy Trails",
        "Quicksilver Messenger Service",
        "Rock",
        "Acid Rock, Psychedelic Rock");
    addWithGenres(
        1969,
        "From Elvis in Memphis",
        "Elvis Presley",
        "Rock, Funk, Soul, Folk, World, Country",
        "Country, Soul");
    addWithGenres(1970, "Fun House", "The Stooges", "Rock", "Garage Rock, Punk");
    addWithGenres(
        1969,
        "The Gilded Palace of Sin",
        "The Flying Burrito Brothers",
        "Rock, Folk, World, Country",
        "Country Rock");
    addWithGenres(1994, "Dookie", "Green Day", "Rock", "Alternative Rock, Pop Punk, Punk");
    addWithGenres(1972, "Transformer", "Lou Reed", "Rock", "Glam");
    addWithGenres(
        1966,
        "The Beano Album",
        "John Mayall & The Bluesbreakers",
        "Rock,Blues",
        "Blues Rock,Electric Blues,Harmonica Blues");
    addWithGenres(
        1998,
        "Nuggets: Original Artyfacts From the First Psychedelic Era, 1965-1968",
        "Various Artists",
        "Rock",
        "Garage Rock,Psychedelic Rock");
    addWithGenres(1983, "Murmur", "R.E.M.", "Rock", "Alternative Rock");
    addWithGenres(1967, "The Best of Little Walter", "Little Walter", "Blues", "Chicago Blues");
    addWithGenres(2001, "Is This It", "The Strokes", "Rock", "Indie Rock");
    addWithGenres(1979, "Highway to Hell", "AC/DC", "Rock", "Hard Rock");
    addWithGenres(1994, "The Downward Spiral", "Nine Inch Nails", "Electronic, Rock", "Industrial");
    addWithGenres(
        1966,
        "Parsley, Sage, Rosemary and Thyme",
        "Simon & Garfunkel",
        "Rock, Pop, Folk, World, Country",
        "Folk Rock, Pop Rock");
    addWithGenres(
        1987, "Bad", "Michael Jackson", "Funk, Soul, Pop", "Pop Rock, Ballad, Funk, Soul");
    addWithGenres(2006, "Modern Times", "Bob Dylan", "Rock", "Blues Rock, Folk Rock");
    addWithGenres(
        1968,
        "Wheels of Fire",
        "Cream",
        "Rock, Blues",
        "Blues Rock, Psychedelic Rock, Classic Rock");
    addWithGenres(1980, "Dirty Mind", "Prince", "Funk, Soul", "None");
    addWithGenres(1970, "Abraxas", "Santana", "Rock, Latin", "Fusion, Hard Rock, Psychedelic Rock");
    addWithGenres(
        1970,
        "Tea for the Tillerman",
        "Cat Stevens",
        "Rock, Stage & Screen",
        "Folk Rock, Pop Rock");
    addWithGenres(1991, "Ten", "Pearl Jam", "Rock", "Grunge");
    addWithGenres(
        1969,
        "Everybody Knows This Is Nowhere",
        "Neil Young & Crazy Horse",
        "Rock",
        "Folk Rock, Country Rock, Classic Rock");
    addWithGenres(1975, "Wish You Were Here", "Pink Floyd", "Rock", "Classic Rock, Prog Rock");
    addWithGenres(
        1994, "Crooked Rain Crooked Rain", "Pavement", "Rock", "Alternative Rock, Indie Rock");
    addWithGenres(1981, "Tattoo You", "The Rolling Stones", "Rock", "Classic Rock");
    addWithGenres(
        1991,
        "Proud Mary: The Best of Ike and Tina Turner",
        "Ike & Tina Turner",
        "Funk, Soul",
        "Rhythm & Blues, Bayou Funk, Soul");
    addWithGenres(1973, "New York Dolls", "New York Dolls", "Rock", "Glam");
    addWithGenres(
        1986,
        "Bo Diddley / Go Bo Diddley",
        "Bo Diddley",
        "Rock, Blues",
        "Rhythm & Blues, Rock & Roll");
    addWithGenres(
        1961,
        "Two Steps From the Blues",
        "Bobby 'Blue' Bland",
        "Funk, Soul, Blues",
        "Rhythm & Blues, Soul");
    addWithGenres(1986, "The Queen Is Dead", "The Smiths", "Rock, Pop", "Indie Rock");
    addWithGenres(1986, "Licensed to Ill", "Beastie Boys", "Hip Hop", "None");
    addWithGenres(1970, "Look-Ka Py Py", "The Meters", "Funk, Soul", "Bayou Funk, Soul, Funk");
    addWithGenres(1991, "Loveless", "My Bloody Valentine", "Rock", "Alternative Rock, Shoegaze");
    addWithGenres(
        1972, "New Orleans Piano", "Professor Longhair", "Funk, Soul, Blues", "Piano Blues");
    addWithGenres(1983, "War", "U2", "Rock", "Pop Rock");
    addWithGenres(
        1999, "The Neil Diamond Collection", "Neil Diamond", "Rock, Pop", "Soft Rock, Ballad");
    addWithGenres(2004, "American Idiot", "Green Day", "Rock", "Pop Rock, Punk");
    addWithGenres(1982, "Nebraska", "Bruce Springsteen", "Rock", "Acoustic");
    addWithGenres(1989, "Doolittle", "Pixies", "Rock", "Indie Rock");
    addWithGenres(1987, "Paid in Full", "Eric B. & Rakim", "Hip Hop", "None");
    addWithGenres(1975, "Toys in the Attic", "Aerosmith", "Rock", "Hard Rock, Blues Rock");
    addWithGenres(1989, "Nick of Time", "Bonnie Raitt", "Rock, Pop", "Blues Rock, Pop Rock");
    addWithGenres(1975, "A Night at the Opera", "Queen", "Rock", "Hard Rock, Pop Rock, Prog Rock");
    addWithGenres(1972, "The Kink Kronikles", "The Kinks", "Rock", "Rock & Roll, Pop Rock");
    addWithGenres(
        1965, "Mr. Tambourine Man", "The Byrds", "Rock", "Folk Rock, Garage Rock, Pop Rock");
    addWithGenres(1968, "Bookends", "Simon & Garfunkel", "Rock", "Folk Rock, Classic Rock");
    addWithGenres(2000, "The Ultimate Collection", "Patsy Cline", "Folk, World, Country", "None");
    addWithGenres(1992, "Mr. Excitement!", "Jackie Wilson", "Funk, Soul", "None");
    addWithGenres(1965, "My Generation", "The Who", "Rock", "Mod");
    addWithGenres(1962, "Howlin' Wolf", "Howlin' Wolf", "Blues", "None");
    addWithGenres(1989, "Like a Prayer", "Madonna", "Electronic, Pop", "Synth-pop");
    addWithGenres(1972, "Can't Buy a Thrill", "Steely Dan", "Rock", "Classic Rock");
    addWithGenres(
        1984,
        "Let It Be (The Replacements)",
        "The Replacements",
        "Rock",
        "Alternative Rock, Power Pop, Punk, Indie Rock");
    addWithGenres(1984, "Run D.M.C.", "Run D.M.C.", "Hip Hop", "None");
    addWithGenres(1970, "Black Sabbath", "Black Sabbath", "Rock", "Blues Rock, Heavy Metal");
    addWithGenres(
        2000,
        "The Marshall Mathers LP",
        "Eminem",
        "Hip Hop",
        "Pop Rap, Hardcore Hip-Hop, Horrorcore");
    addWithGenres(
        1993,
        "All Killer No Filler! The Jerry Lee Lewis Anthology",
        "Jerry Lee Lewis",
        "Rock, Blues, Folk, World, Country",
        "Country Blues, Rock & Roll, Rhythm & Blues");
    addWithGenres(
        1966,
        "Freak Out!",
        "The Mothers of Invention",
        "Electronic, Rock",
        "Musique Concr?te, Avantgarde, Symphonic Rock, Rhythm & Blues, Psychedelic Rock,"
            + " Experimental, Parody");
    addWithGenres(
        1969,
        "Live/Dead",
        "The Grateful Dead",
        "Rock",
        "Folk Rock, Country Rock, Psychedelic Rock, Experimental");
    addWithGenres(1959, "The Shape of Jazz to Come", "Ornette Coleman", "Jazz", "Free Jazz");
    addWithGenres(1992, "Automatic for the People", "R.E.M.", "Rock", "Alternative Rock");
    addWithGenres(1996, "Reasonable Doubt", "Jay Z", "Hip Hop", "None");
    addWithGenres(
        1977, "Low", "David Bowie", "Electronic, Rock", "Art Rock, Ambient, Experimental");
    addWithGenres(2001, "The Blueprint", "Jay Z", "Hip Hop", "None");
    addWithGenres(
        1980, "The River", "Bruce Springsteen", "Rock", "Folk Rock, Pop Rock, Classic Rock");
    addWithGenres(
        1966,
        "Complete & Unbelievable: The Otis Redding Dictionary of Soul",
        "Otis Redding",
        "Funk, Soul",
        "Soul");
    addWithGenres(1991, "Metallica (The Black Album)", "Metallica", "Rock", "Heavy Metal");
    addWithGenres(1977, "Trans Europa Express", "Kraftwerk", "Electronic", "Electro");
    addWithGenres(
        1985, "Whitney Houston", "Whitney Houston", "Funk, Soul, Pop", "Synth-pop, Rhythm & Blues");
    addWithGenres(
        1968,
        "The Kinks Are The Village Green Preservation Society",
        "The Kinks",
        "Rock",
        "Psychedelic Rock, Pop Rock");
    addWithGenres(
        1997, "The Velvet Rope", "Janet", "Electronic, Funk, Soul, Pop", "RnB/Swing, Downtempo");
    addWithGenres(1978, "Stardust", "Willie Nelson", "Pop, Folk, World, Country", "Vocal");
    addWithGenres(1970, "American Beauty", "Grateful Dead", "Rock", "Folk Rock");
    addWithGenres(
        1969,
        "Crosby, Stills & Nash",
        "Crosby, Stills & Nash",
        "Rock, Folk, World, Country",
        "Folk Rock, Classic Rock");
    addWithGenres(1988, "Tracy Chapman", "Tracy Chapman", "Rock", "None");
    addWithGenres(
        1970,
        "Workingman's Dead",
        "Grateful Dead",
        "Rock",
        "Folk Rock, Country Rock, Classic Rock");
    addWithGenres(
        1959, "The Genius of Ray Charles", "Ray Charles", "Jazz, Pop", "Soul-Jazz, Big Band");
    addWithGenres(
        1968,
        "Child Is Father to the Man",
        "Blood, Sweat & Tears",
        "Rock",
        "Blues Rock, Jazz-Rock, Classic Rock");
    addWithGenres(1973, "Quadrophenia", "The Who", "Rock", "Hard Rock, Classic Rock, Mod");
    addWithGenres(1972, "Paul Simon", "Paul Simon", "Rock", "Folk Rock, Pop Rock");
    addWithGenres(1985, "Psychocandy", "The Jesus and Mary Chain", "Rock", "Noise");
    addWithGenres(
        1978,
        "Some Girls",
        "The Rolling Stones",
        "Rock",
        "Blues Rock, Rock & Roll, Classic Rock, Disco");
    addWithGenres(
        1965, "The Beach Boys Today!", "The Beach Boys", "Rock", "Pop Rock, Psychedelic Rock");
    addWithGenres(1997, "Dig Me Out", "Sleater Kinney", "Rock", "Indie Rock");
    addWithGenres(1966, "Going to a Go-Go", "Smokey Robinson & The Miracles", "Funk, Soul", "Soul");
    addWithGenres(1974, "Nightbirds", "LaBelle", "Funk, Soul", "Funk, Disco");
    addWithGenres(1999, "The Slim Shady LP", "Eminem", "Hip Hop", "None");
    addWithGenres(1975, "Mothership Connection", "Parliament", "Funk, Soul", "P.Funk, Funk");
    addWithGenres(
        1989,
        "Rhythm Nation 1814",
        "Janet Jackson",
        "Electronic, Hip Hop, Pop",
        "RnB/Swing, Downtempo, Synth-pop");
    addWithGenres(
        1997,
        "Anthology of American Folk Music",
        "Various",
        "Blues, Folk, World, Country",
        "Cajun, Country, Field Recording, Gospel, Delta Blues, Folk");
    addWithGenres(1973, "Aladdin Sane", "David Bowie", "Rock", "Glam");
    addWithGenres(2000, "All That You Can't Leave Behind", "U2", "Rock", "Pop Rock");
    addWithGenres(1994, "My Life", "Mary J. Blige", "Hip Hop", "RnB/Swing");
    addWithGenres(1964, "Folk Singer", "Muddy Waters", "Blues", "Delta Blues");
    addWithGenres(1974, "Can't Get Enough", "Barry White", "Funk, Soul", "Soul, Disco");
    addWithGenres(
        1978, "The Cars", "The Cars", "Electronic, Rock", "New Wave, Pop Rock, Synth-pop");
    addWithGenres(1972, "Music of My Mind", "Stevie Wonder", "Funk, Soul", "Soul-Jazz, Soul");
    addWithGenres(
        1972, "I'm Still in Love With You", "Al Green", "Funk, Soul", "Rhythm & Blues, Soul");
    addWithGenres(1980, "Los Angeles", "X", "Rock", "Punk, Rock & Roll");
    addWithGenres(1968, "Anthem of the Sun", "Grateful Dead", "Rock", "Psychedelic Rock");
    addWithGenres(
        1967,
        "Something Else by The Kinks",
        "The Kinks",
        "Rock",
        "Pop Rock, Psychedelic Rock, Mod");
    addWithGenres(1973, "Call Me", "Al Green", "Funk, Soul", "Rhythm & Blues, Soul");
    addWithGenres(1977, "Talking Heads: 77", "Talking Heads", "Rock", "New Wave");
    addWithGenres(
        1975,
        "The Basement Tapes",
        "Bob Dylan and the Band",
        "Rock",
        "Folk Rock, Country Rock, Classic Rock");
    addWithGenres(
        1968,
        "White Light/White Heat",
        "The Velvet Underground",
        "Rock",
        "Avantgarde, Experimental");
    addWithGenres(
        1969, "Kick Out the Jams", "MC5", "Rock", "Garage Rock, Hard Rock, Punk, Blues Rock");
    addWithGenres(
        1970,
        "Songs of Love and Hate",
        "Leonard Cohen",
        "Folk, World, Country",
        "Folk Rock, Folk, Ballad");
    addWithGenres(1985, "Meat Is Murder", "The Smiths", "Rock", "Alternative Rock, Indie Rock");
    addWithGenres(
        1968,
        "We're Only in It for the Money",
        "The Mothers of Invention",
        "Electronic, Rock",
        "Modern Classical, Avantgarde, Psychedelic Rock, Experimental, Parody");
    addWithGenres(
        2003,
        "The College Dropout",
        "Kanye West",
        "Hip Hop",
        "Pop Rap, Conscious, Contemporary R&B");
    addWithGenres(1994, "Weezer (Blue Album)", "Weezer", "Rock", "Alternative Rock, Emo");
    addWithGenres(1971, "Master of Reality", "Black Sabbath", "Rock", "Hard Rock, Heavy Metal");
    addWithGenres(1971, "Coat of Many Colors", "Dolly Parton", "Folk, World, Country", "Country");
    addWithGenres(1990, "Fear of a Black Planet", "Public Enemy", "Hip Hop", "Conscious");
    addWithGenres(1967, "John Wesley Harding", "Bob Dylan", "Rock", "Folk Rock, Country Rock");
    addWithGenres(1994, "Grace", "Jeff Buckley", "Rock", "Alternative Rock, Folk Rock");
    addWithGenres(
        1998,
        "Car Wheels on a Gravel Road",
        "Lucinda Williams",
        "Folk, World, Country",
        "Country, Folk");
    addWithGenres(
        1996,
        "Odelay",
        "Beck",
        "Electronic, Hip Hop, Funk, Soul, Pop",
        "Electro, Downtempo, Hip Hop, Disco, Afrobeat, Abstract");
    addWithGenres(
        1964,
        "A Hard Day's Night",
        "The Beatles",
        "Rock, Stage & Screen",
        "Soundtrack, Beat, Pop Rock");
    addWithGenres(
        1956, "Songs for Swingin' Lovers!", "Frank Sinatra", "Jazz, Pop", "Vocal, Easy Listening");
    addWithGenres(
        1969,
        "Willy and the Poor Boys",
        "Creedence Clearwater Revival",
        "Rock",
        "Blues Rock, Rock & Roll, Classic Rock");
    addWithGenres(
        1991,
        "Blood Sugar Sex Magik",
        "Red Hot Chili Peppers",
        "Rock",
        "Alternative Rock, Funk Metal");
    addWithGenres(
        1994,
        "The Sun Records Collection",
        "Various",
        "Rock, Funk, Soul, Blues, Pop, Folk, World, Country",
        "Country Blues, Rock & Roll, Rockabilly");
    addWithGenres(1988, "Nothing's Shocking", "Jane's Addiction", "Rock", "Alternative Rock");
    addWithGenres(
        1994, "MTV Unplugged in New York", "Nirvana", "Rock", "Folk Rock, Acoustic, Grunge");
    addWithGenres(
        1998, "The Miseducation of Lauryn Hill", "Lauryn Hill", "Hip Hop", "RnB/Swing, Conscious");
    addWithGenres(
        1979,
        "Damn the Torpedoes",
        "Tom Petty and the Heartbreakers",
        "Rock",
        "Soft Rock, Hard Rock, Pop Rock");
    addWithGenres(
        1969,
        "The Velvet Underground",
        "The Velvet Underground",
        "Rock",
        "Garage Rock, Art Rock, Experimental");
    addWithGenres(1988, "Surfer Rosa", "Pixies", "Rock", "Alternative Rock");
    addWithGenres(1972, "Back Stabbers", "The O'Jays", "Funk, Soul", "Soul");
    addWithGenres(1973, "Burnin'", "The Wailers", "Reggae", "Reggae");
    addWithGenres(
        2001, "Amnesiac", "Radiohead", "Electronic, Rock", "Alternative Rock, Experimental");
    addWithGenres(
        1972, "Pink Moon", "Nick Drake", "Rock, Folk, World, Country", "Folk, Folk Rock, Acoustic");
    addWithGenres(1972, "Sail Away", "Randy Newman", "Rock, Pop", "None");
    addWithGenres(
        1981,
        "Ghost in the Machine",
        "The Police",
        "Rock, Pop",
        "Alternative Rock, New Wave, Pop Rock");
    addWithGenres(
        1976,
        "Station to Station",
        "David Bowie",
        "Rock, Funk, Soul",
        "Classic Rock, Soul, Funk, Art Rock");
    addWithGenres(1977, "Slowhand", "Eric Clapton", "Rock", "None");
    addWithGenres(
        1989, "Disintegration", "The Cure", "Electronic, Rock", "New Wave, Alternative Rock");
    addWithGenres(1993, "Exile in Guyville", "Liz Phair", "Rock", "Lo-Fi, Indie Rock");
    addWithGenres(1988, "Daydream Nation", "Sonic Youth", "Rock", "Alternative Rock, Indie Rock");
    addWithGenres(1986, "In the Jungle Groove", "James Brown", "Funk, Soul", "Soul, Funk");
    addWithGenres(
        1975, "Tonight's the Night", "Neil Young", "Rock", "Blues Rock, Folk Rock, Classic Rock");
    addWithGenres(
        1965, "Help!", "The Beatles", "Rock, Stage & Screen", "Beat, Soundtrack, Pop Rock");
    addWithGenres(
        1982,
        "Shoot Out the Lights",
        "Richard & Linda Thompson",
        "Rock, Folk, World, Country",
        "Folk Rock");
    addWithGenres(1981, "Wild Gift", "X", "Rock", "Rock & Roll, Punk");
    addWithGenres(
        1979, "Squeezing Out Sparks", "Graham Parker & The Rumour", "Rock, Blues", "None");
    addWithGenres(1994, "Superunknown", "Soundgarden", "Rock", "Alternative Rock");
    addWithGenres(
        2007, "In Rainbows", "Radiohead", "Electronic, Rock", "Alternative Rock, Art Rock");
    addWithGenres(1971, "Aqualung", "Jethro Tull", "Rock", "Classic Rock, Prog Rock");
    addWithGenres(
        1968,
        "Cheap Thrills",
        "Big Brother & the Holding Company",
        "Rock",
        "Folk Rock, Blues Rock, Psychedelic Rock");
    addWithGenres(
        1974,
        "The Heart of Saturday Night",
        "Tom Waits",
        "Jazz, Pop, Folk, World, Country",
        "Contemporary Jazz, Spoken Word");
    addWithGenres(1981, "Damaged", "Black Flag", "Rock", "Hardcore, Punk");
    addWithGenres(1999, "Play", "Moby", "Electronic", "Breakbeat, Leftfield, Downtempo");
    addWithGenres(1990, "Violator", "Depeche Mode", "Electronic", "Synth-pop");
    addWithGenres(1977, "Bat Out of Hell", "Meat Loaf", "Rock", "Pop Rock");
    addWithGenres(1973, "Berlin", "Lou Reed", "Rock", "Hard Rock");
    addWithGenres(
        1984,
        "Stop Making Sense",
        "Talking Heads",
        "Rock, Funk, Soul",
        "Funk, Indie Rock, New Wave");
    addWithGenres(1989, "3 Feet High and Rising", "De La Soul", "Hip Hop", "None");
    addWithGenres(1967, "The Piper at the Gates of Dawn", "Pink Floyd", "Rock", "Psychedelic Rock");
    addWithGenres(
        1960,
        "Muddy Waters at Newport 1960",
        "Muddy Waters",
        "Rock, Blues",
        "Blues Rock, Chicago Blues");
    addWithGenres(2003, "The Black Album", "Jay Z", "Hip Hop", "None");
    addWithGenres(
        1966, "Roger the Engineer", "The Yardbirds", "Rock, Blues", "Blues Rock, Pop Rock");
    addWithGenres(
        1979, "Rust Never Sleeps", "Neil Young & Crazy Horse", "Rock", "Hard Rock, Arena Rock");
    addWithGenres(1985, "Brothers in Arms", "Dire Straits", "Rock", "AOR, Classic Rock");
    addWithGenres(2010, "My Beautiful Dark Twisted Fantasy", "Kanye West", "Hip Hop", "None");
    addWithGenres(
        1978, "52nd Street", "Billy Joel", "Jazz, Rock, Pop", "Pop Rock, Ballad, Latin Jazz");
    addWithGenres(
        1965, "Having a Rave Up", "The Yardbirds", "Rock", "Blues Rock, Psychedelic Rock");
    addWithGenres(1970, "12 Songs", "Randy Newman", "Rock, Pop", "Pop Rock, Vocal");
    addWithGenres(
        1967,
        "Between the Buttons",
        "The Rolling Stones",
        "Rock",
        "Blues Rock, Psychedelic Rock, Pop Rock");
    addWithGenres(1960, "Sketches of Spain", "Miles Davis", "Jazz", "Modal");
    addWithGenres(1972, "Honky Chteau", "Elton John", "Rock", "Pop Rock,Classic Rock");
    addWithGenres(1979, "Singles Going Steady", "Buzzcocks", "Rock", "Punk");
    addWithGenres(
        2000, "Stankonia", "OutKast", "Hip Hop, Funk, Soul", "Gangsta, P.Funk, Crunk, Conscious");
    addWithGenres(1993, "Siamese Dream", "The Smashing Pumpkins", "Rock", "Alternative Rock");
    addWithGenres(1987, "Substance 1987", "New Order", "Electronic", "Synth-pop");
    addWithGenres(1971, "L.A. Woman", "The Doors", "Rock", "Blues Rock, Classic Rock");
    addWithGenres(
        1992,
        "Rage Against the Machine",
        "Rage Against the Machine",
        "Hip Hop, Rock",
        "Funk Metal");
    addWithGenres(
        1994,
        "American Recordings",
        "Johnny Cash",
        "Folk, World, Country",
        "Country, Gospel, Folk");
    addWithGenres(
        1998,
        "Ray of Light",
        "Madonna",
        "Electronic, Pop",
        "House, Techno, Downtempo, Vocal, Ambient");
    addWithGenres(1972, "Eagles", "Eagles", "Rock", "Country Rock, Classic Rock");
    addWithGenres(1987, "Louder Than Bombs", "The Smiths", "Rock", "Alternative Rock, Indie Rock");
    addWithGenres(1973, "Mott", "Mott the Hoople", "Rock", "Classic Rock");
    addWithGenres(
        2006,
        "Whatever People Say I Am, That's What I'm Not",
        "Arctic Monkeys",
        "Rock",
        "Indie Rock");
    addWithGenres(1979, "Reggatta de Blanc", "The Police", "Rock, Pop", "New Wave, Pop Rock");
    addWithGenres(
        1969,
        "Volunteers",
        "Jefferson Airplane",
        "Rock",
        "Psychedelic Rock, Folk Rock, Country Rock, Honky Tonk");
    addWithGenres(1975, "Siren", "Roxy Music", "Rock", "Art Rock, Pop Rock, Glam");
    addWithGenres(1974, "Late for the Sky", "Jackson Browne", "Rock", "Pop Rock");
    addWithGenres(1995, "Post", "Bjork", "Electronic", "Breakbeat, IDM, Electro");
    addWithGenres(
        1991,
        "The Ultimate Collection: 1948-1990",
        "John Lee Hooker",
        "Blues",
        "Country Blues, Electric Blues, Chicago Blues, Jump Blues");
    addWithGenres(1995, "(What's the Story) Morning Glory?", "Oasis", "Rock, Pop", "Brit Pop");
    addWithGenres(1994, "CrazySexyCool", "TLC", "Electronic, Hip Hop, Funk, Soul", "RnB/Swing");
    addWithGenres(
        1973,
        "Funky Kingston",
        "Toots & The Maytals",
        "Reggae",
        "Reggae Gospel, Reggae, Roots Reggae, Rocksteady");
    addWithGenres(
        2011, "The Smile Sessions", "The Beach Boys", "Rock", "Pop Rock, Psychedelic Rock");
    addWithGenres(1976, "The Modern Lovers", "The Modern Lovers", "Rock", "Art Rock, Indie Rock");
    addWithGenres(
        1978,
        "More Songs About Buildings and Food",
        "Talking Heads",
        "Rock",
        "New Wave, Indie Rock");
    addWithGenres(1966, "A Quick One", "The Who", "Rock", "Mod, Beat, Psychedelic Rock");
    addWithGenres(2001, "Love and Theft", "Bob Dylan", "Rock", "Folk Rock, Blues Rock");
    addWithGenres(
        1974, "Pretzel Logic", "Steely Dan", "Jazz, Rock", "Jazz-Rock, Pop Rock, Classic Rock");
    addWithGenres(1993, "Enter the Wu_Tang: 36 Chambers", "Wu Tang Clan", "Hip Hop", "Gangsta");
    addWithGenres(
        1985,
        "The Indestructible Beat of Soweto",
        "Various Artists",
        "Funk, Soul,Folk, World, Country",
        "African");
    addWithGenres(1989, "The End of the Innocence", "Don Henley", "Rock", "Pop Rock");
    addWithGenres(
        2003, "Elephant", "The White Stripes", "Rock", "Blues Rock, Garage Rock, Alternative Rock");
    addWithGenres(1976, "The Pretender", "Jackson Browne", "Rock", "Soft Rock, Pop Rock");
    addWithGenres(1970, "Let It Be", "The Beatles", "Rock", "Pop Rock");
    addWithGenres(
        2007,
        "Kala",
        "M.I.A.",
        "Electronic, Hip Hop, Reggae, Pop",
        "Grime, Bollywood, Hip Hop, Dancehall");
    addWithGenres(1974, "Good Old Boys", "Randy Newman", "Rock, Pop", "Pop Rock, Vocal");
    addWithGenres(
        2007,
        "Sound of Silver",
        "LCD Soundsystem",
        "Electronic, Rock",
        "Leftfield, Alternative Rock, Electro, Disco");
    addWithGenres(1973, "For Your Pleasure", "Roxy Music", "Rock", "Art Rock, Avantgarde, Glam");
    addWithGenres(1991, "Blue Lines", "Massive Attack", "Electronic, Reggae", "Dub, Downtempo");
    addWithGenres(1983, "Eliminator", "ZZ Top", "Rock", "Pop Rock");
    addWithGenres(1985, "Rain Dogs", "Tom Waits", "Rock, Blues", "Blues Rock");
    addWithGenres(
        1995,
        "Anthology: The Best of The Temptations",
        "The Temptations",
        "Electronic, Funk, Soul",
        "Soul, Disco");
    addWithGenres(
        1999, "Californication", "Red Hot Chili Peppers", "Rock", "Alternative Rock, Funk Metal");
    addWithGenres(1994, "Illmatic", "Nas", "Hip Hop", "None");
    addWithGenres(
        1973,
        "(pronounced 'leh-'nerd 'skin-'nerd)",
        "Lynyrd Skynyrd",
        "Rock",
        "Blues Rock, Hard Rock, Southern Rock");
    addWithGenres(
        1972, "Dr. John's Gumbo", "Dr. John", "Funk, Soul", "Bayou Funk, Funk, Rhythm & Blues");
    addWithGenres(1974, "Radio City", "Big Star", "Rock", "Power Pop");
    addWithGenres(1993, "Rid of Me", "PJ Harvey", "Rock", "Indie Rock");
    addWithGenres(1980, "Sandinista!", "The Clash", "Rock, Reggae", "Rock & Roll, Dub, Punk");
    addWithGenres(
        1989,
        "I Do Not Want What I Haven't Got",
        "Sinead O'Connor",
        "Rock, Funk, Soul, Blues",
        "Blues Rock, Rhythm & Blues, Soul");
    addWithGenres(1967, "Strange Days", "The Doors", "Rock", "Psychedelic Rock");
    addWithGenres(1997, "Time Out of Mind", "Bob Dylan", "Rock, Blues", "Blues Rock");
    addWithGenres(1974, "461 Ocean Boulevard", "Eric Clapton", "Rock", "Blues Rock");
    addWithGenres(1977, "Pink Flag", "Wire", "Rock", "Punk");
    addWithGenres(
        1984,
        "Double Nickels on the Dime",
        "Minutemen",
        "Rock",
        "Alternative Rock, Hardcore, Punk");
    addWithGenres(1981, "Beauty and the Beat", "The Go Go's", "Rock", "Pop Rock");
    addWithGenres(1978, "Van Halen", "Van Halen", "Rock", "Hard Rock");
    addWithGenres(1999, "Mule Variations", "Tom Waits", "Electronic, Rock", "Abstract, Art Rock");
    addWithGenres(1980, "Boy", "U2", "Rock", "New Wave, Pop Rock");
    addWithGenres(1973, "Band on the Run", "Paul McCartney & Wings", "Rock", "Pop Rock");
    addWithGenres(1994, "Dummy", "Portishead", "Electronic", "Trip Hop, Downtempo");
    addWithGenres(
        1957, "The 'Chirping' Crickets", "The Crickets", "Rock, Pop", "Rockabilly, Rock & Roll");
    addWithGenres(
        1990,
        "The Best of the Girl Groups, Volume 1",
        "Various Artists",
        "Rock,Pop",
        "Pop Rock, Vocal");
    addWithGenres(
        1963,
        "Presenting the Fabulous Ronettes Featuring Veronica",
        "The Ronettes",
        "Rock, Pop",
        "Rock & Roll, Vocal");
    addWithGenres(
        2001,
        "Anthology",
        "Diana Ross & The Supremes",
        "Electronic, Funk, Soul",
        "Pop Rock, Soul, Disco");
    addWithGenres(2002, "The Rising", "Bruce Springsteen", "Rock", "Folk Rock, Classic Rock");
    addWithGenres(
        1974,
        "Grievous Angel",
        "Gram Parsons",
        "Rock, Folk, World, Country",
        "Country Rock, Honky Tonk");
    addWithGenres(
        1978,
        "Cheap Trick at Budokan",
        "Cheap Trick",
        "Rock",
        "Power Pop, Pop Rock, Arena Rock, Hard Rock");
    addWithGenres(
        2002, "Sleepless", "Peter Wolf", "Rock, Blues, Pop", "Blues Rock, Pop Rock, Ballad");
    addWithGenres(
        1978, "Outlandos d'Amour", "The Police", "Rock", "Alternative Rock, New Wave, Punk");
    addWithGenres(1975, "Another Green World", "Brian Eno", "Electronic", "Experimental, Ambient");
    addWithGenres(2007, "Vampire Weekend", "Vampire Weekend", "Rock", "Indie Rock");
    addWithGenres(
        2000, "Stories From the City, Stories From the Sea", "PJ Harvey", "Rock", "Indie Rock");
    addWithGenres(1973, "Here Come the Warm Jets", "Brian Eno", "Rock", "Art Rock, Glam");
    addWithGenres(1970, "All Things Must Pass", "George Harrison", "Rock", "Pop Rock");
    addWithGenres(1972, "#1 Record", "Big Star", "Rock", "Power Pop");
    addWithGenres(1993, "In Utero", "Nirvana", "Rock", "Grunge, Alternative Rock");
    addWithGenres(2002, "Sea Change", "Beck", "Rock", "Alternative Rock, Post Rock");
    addWithGenres(
        2008,
        "Tha Carter III",
        "Lil Wayne",
        "Hip Hop, Funk, Soul",
        "RnB/Swing, Screw, Pop Rap, Thug Rap");
    addWithGenres(1979, "Boys Don't Cry", "The Cure", "Rock", "New Wave, Post-Punk");
    addWithGenres(1985, "Live at the Harlem Square Club, 1963", "Sam Cooke", "Funk, Soul", "Soul");
    addWithGenres(
        1985,
        "Rum Sodomy & the Lash",
        "The Pogues",
        "Rock, Folk, World, Country",
        "Folk Rock, Celtic, Punk");
    addWithGenres(1977, "Suicide", "Suicide", "Electronic, Rock", "New Wave, Experimental");
    addWithGenres(1978, "Q: Are We Not Men? A: We Are Devo!", "DEVO", "Rock", "New Wave");
    addWithGenres(1977, "In Color", "Cheap Trick", "Rock", "Power Pop, Pop Rock, Hard Rock");
    addWithGenres(1972, "The World Is a Ghetto", "War", "Funk, Soul", "Funk");
    addWithGenres(1976, "Fly Like an Eagle", "Steve Miller Band", "Rock", "Pop Rock");
    addWithGenres(1970, "Back in the USA", "MC5", "Rock", "Garage Rock, Rock & Roll");
    addWithGenres(
        1964,
        "Getz / Gilberto",
        "Stan Getz/Joao GilbertofeaturingAntonio Carlos Jobim",
        "Jazz",
        "Bossa Nova,Latin Jazz");
    addWithGenres(1983, "Synchronicity", "The Police", "Rock, Pop", "New Wave, Pop Rock");
    addWithGenres(1978, "Third/Sister Lovers", "Big Star", "Rock", "Lo-Fi,Indie Rock");
    addWithGenres(1973, "For Everyman", "Jackson Browne", "Rock", "Pop Rock, Classic Rock");
    addWithGenres(2006, "Back to Black", "Amy Winehouse", "Funk, Soul, Pop", "Soul");
    addWithGenres(1971, "John Prine", "John Prine", "Folk, World, Country", "Country, Folk");
    addWithGenres(1987, "Strictly Business", "EPMD", "Hip Hop", "None");
    addWithGenres(1971, "Love It to Death", "Alice Cooper", "Rock", "Classic Rock");
    addWithGenres(1984, "How Will the Wolf Survive?", "Los Lobos", "Rock, Latin", "Blues Rock");
    addWithGenres(1978, "Here, My Dear", "Marvin Gaye", "Funk, Soul", "Soul");
    addWithGenres(2005, "Z", "My Morning Jacket", "Rock", "Alternative Rock");
    addWithGenres(
        1970,
        "Tumbleweed Connection",
        "Elton John",
        "Rock, Folk, World, Country",
        "Soft Rock, Country Rock");
    addWithGenres(
        1968,
        "The Drifters' Golden Hits",
        "The Drifters",
        "Rock, Funk, Soul",
        "Rhythm & Blues, Soul");
    addWithGenres(1994, "Live Through This", "Hole", "Rock", "Grunge");
    addWithGenres(
        1979,
        "Metal Box",
        "Public Image Ltd.",
        "Electronic, Rock",
        "Post-Punk, Dub, Avantgarde, Experimental");
    addWithGenres(1987, "Document", "R.E.M.", "Rock", "None");
    addWithGenres(1981, "Heaven Up Here", "Echo and The Bunnymen", "Rock", "New Wave, Indie Rock");
    addWithGenres(1987, "Hysteria", "Def Leppard", "Rock", "Hard Rock, Arena Rock");
    addWithGenres(
        1999, "69 Love Songs", "The Magnetic Fields", "Electronic, Rock", "Synth-pop, Indie Rock");
    addWithGenres(
        2002, "A Rush of Blood to the Head", "Coldplay", "Rock", "Alternative Rock, Pop Rock");
    addWithGenres(1987, "Tunnel of Love", "Bruce Springsteen", "Rock", "None");
    addWithGenres(
        1965,
        "The Paul Butterfield Blues Band",
        "The Paul Butterfield Blues Band",
        "Rock, Blues",
        "Blues Rock, Electric Blues, Chicago Blues, Modern Electric Blues, Harmonica Blues");
    addWithGenres(1996, "The Score", "Fugees", "Hip Hop", "Pop Rap, Conscious");
    addWithGenres(1985, "Radio", "L.L. Cool J", "Hip Hop", "None");
    addWithGenres(
        1974,
        "I Want to See the Bright Lights Tonight",
        "Richard & Linda Thompson",
        "Rock",
        "Classic Rock, Folk Rock");
    addWithGenres(
        1987,
        "Faith",
        "George Michael",
        "Electronic, Rock, Funk, Soul, Blues, Pop",
        "Downtempo, Soft Rock, Pop Rock, Synth-pop");
    addWithGenres(1984, "The Smiths", "The Smiths", "Rock", "Indie Rock");
    addWithGenres(
        2001,
        "Proxima estacion: Esperanza",
        "Manu Chao",
        "Rock, Reggae, Latin",
        "Folk Rock, Reggae, Reggae-Pop");
    addWithGenres(
        1979, "Armed Forces", "Elvis Costello & The Attractions", "Rock", "New Wave, Pop Rock");
    addWithGenres(1997, "Life After Death", "The Notorious B.I.G.", "Hip Hop", "None");
    addWithGenres(1996, "Down Every Road", "Merle Haggard", "Folk, World, Country", "Country");
    addWithGenres(
        2002, "All Time Greatest Hits", "Loretta Lynn", "Folk, World, Country", "Country");
    addWithGenres(
        1971, "Maggot Brain", "Funkadelic", "Rock, Funk, Soul", "P.Funk, Psychedelic Rock");
    addWithGenres(1995, "Only Built 4 Cuban Linx", "Raekwon", "Hip Hop", "None");
    addWithGenres(2000, "Voodoo", "D'Angelo", "Hip Hop, Funk, Soul", "Soul, Funk, Neo Soul");
    addWithGenres(
        1986, "Guitar Town", "Steve Earle", "Rock, Folk, World, Country", "Country, Honky Tonk");
    addWithGenres(1979, "Entertainment!", "Gang of Four", "Rock", "Post-Punk, New Wave");
    addWithGenres(1972, "All the Young Dudes", "Mott the Hoople", "Rock", "Classic Rock, Glam");
    addWithGenres(1994, "Vitalogy", "Pearl Jam", "Rock", "Alternative Rock, Hard Rock");
    addWithGenres(
        1975,
        "That's the Way of the World",
        "Earth, Wind & Fire",
        "Funk, Soul",
        "Soul, Funk, Disco");
    addWithGenres(
        1983, "She's So Unusual", "Cyndi Lauper", "Electronic, Rock", "Pop Rock, Synth-pop");
    addWithGenres(1985, "New Day Rising", "Husker Du", "Rock", "Alternative Rock, Hardcore, Punk");
    addWithGenres(1976, "Destroyer", "KISS", "Rock", "Hard Rock");
    addWithGenres(1973, "Tres hombres", "ZZ Top", "Rock", "Blues Rock, Classic Rock");
    addWithGenres(
        1967,
        "Born Under a Bad Sign",
        "Albert King",
        "Funk, Soul, Blues",
        "Electric Blues, Rhythm & Blues, Soul");
    addWithGenres(1983, "Touch", "Eurythmics", "Electronic, Pop", "New Wave, Synth-pop");
    addWithGenres(2002, "Yankee Hotel Foxtrot", "Wilco", "Rock", "Alternative Rock");
    addWithGenres(
        2007, "Oracular Spectacular", "MGMT", "Electronic, Rock, Pop", "Synth-pop, Indie Rock");
    addWithGenres(1972, "Give It Up", "Bonnie Raitt", "Rock", "Blues Rock");
    addWithGenres(1969, "Boz Scaggs", "Boz Scaggs", "Rock", "Pop Rock");
    addWithGenres(
        2001,
        "White Blood Cells",
        "The White Stripes",
        "Rock",
        "Indie Rock, Alternative Rock, Blues Rock, Garage Rock");
    addWithGenres(1989, "The Stone Roses", "The Stone Roses", "Rock", "Indie Rock");
    addWithGenres(1971, "Live in Cook County Jail", "B.B. King", "Blues", "Electric Blues");
    addWithGenres(1998, "Aquemini", "OutKast", "Hip Hop", "Reggae, Gangsta, Soul, Conscious");
  }
}
