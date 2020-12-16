import java.util.List;
import java.util.Random;
import jdbc.daos.ArtistDao;
import jdbc.daos.CriticDao;
import jdbc.daos.GenreDao;
import jdbc.daos.PlaylistDao;
import jdbc.daos.ReviewDao;
import jdbc.daos.SongDao;
import jdbc.daos.UserDao;
import jdbc.models.Artist;
import jdbc.models.Song;

public class main {

  public static void main(String[] args) {

    UserDao user = new UserDao();
    CriticDao critic = new CriticDao();
    ArtistDao artist = new ArtistDao();
    SongDao songdao = new SongDao();
    PlaylistDao pdao = new PlaylistDao();
    GenreDao gdao = new GenreDao();

    Random random = new Random();
    ReviewDao review = new ReviewDao();

    int uid = 567;
    int aid = 567;
    int cid = 567;
    int pid = 567;
    int sid = 567;

    List<Song> songs = songdao.findSongs("aid", 345);
    for (Song i: songs) {
      System.out.println(i.toString());
    }

  }


}
