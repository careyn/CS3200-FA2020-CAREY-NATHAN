import java.util.List;
import jdbc.daos.ArtistDao;
import jdbc.models.Artist;

public class main {

  public static void main(String[] args) {
    ArtistDao dao = new ArtistDao();

    Artist artist = new Artist(123, "jimbo", "just a dude", 123);
    Integer status = dao.createArtist(artist);
    System.out.println("Added Jimbo");

    artist = dao.findArtistById(123);
    System.out.println(artist);

    artist = new Artist(234, "Ted", "not just a dude", 234);
    status = dao.createArtist(artist);
    System.out.println("Added Ted");

    artist = dao.findArtistById(234);
    System.out.println(artist);

    System.out.println("print out all of the artists");
    List<Artist> as = dao.findAllArtists();
    for(Artist a:as) {
      System.out.println(a);
    }

    System.out.println("Updated Jimbo to Kyle");
    artist = new Artist(123, "kyle", "just a dude", 123);
    status = dao.updateArtist(123, artist);

    artist = dao.findArtistById(123);
    System.out.println(artist);

    status = dao.deleteArtist(123);
    System.out.println("deleted Kyle/Jimbo");

    System.out.println("print out all of the artists");
    as = dao.findAllArtists();
    for(Artist a:as) {
      System.out.println(a);
    }

    status = dao.deleteArtist(234);
    System.out.println("deleted Ted");

    System.out.println("print out all of the artists (nothing left)");
    as = dao.findAllArtists();
    for(Artist a:as) {
      System.out.println(a);
    }

  }


}
