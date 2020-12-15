import java.util.List;
import java.util.Random;
import java.util.Scanner;
import jdbc.daos.ArtistDao;
import jdbc.daos.CriticDao;
import jdbc.daos.GenreDao;
import jdbc.daos.PlaylistDao;
import jdbc.daos.ReviewDao;
import jdbc.daos.SongDao;
import jdbc.daos.UserDao;
import jdbc.models.Artist;
import jdbc.models.Critic;
import jdbc.models.Genre;
import jdbc.models.Playlist;
import jdbc.models.Review;
import jdbc.models.Song;
import jdbc.models.User;

public class MusicCritique {
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

    boolean cstate = true;
    Critic c = new Critic(123, 123, "");
    Artist a = new Artist(123, "", "", 123);

    System.out.println("This is an application to track user inputted songs and reviews, maintaining"
        + "an easily searchable database and allowing for updates and edits.");
    System.out.println("Please enter your username and password, or the new username and password "
        + "that you would like to use:");
    Scanner scanner = new Scanner(System.in);
    String username = scanner.nextLine();
    String password = scanner.nextLine();

    User u = user.findUserByName(username, password);

    if (u == null) {
      System.out.println("Those credentials are not valid, a new user will be created: ");
      System.out.println("Please enter your first name, last name, and email");
      scanner = new Scanner(System.in);
      String first = scanner.nextLine();
      String last = scanner.nextLine();
      String email = scanner.nextLine();
      int add = random.nextInt(100);
      uid += add;
      u = new User(username, password, first, last, email, uid);
      user.createUser(u);
      System.out.println("Please enter the role your account will fulfill "
          + "(1 for artist, 2 for critic): ");
      scanner = new Scanner(System.in);
      int choice = scanner.nextInt();
      if (choice == 1) {
        cstate = false;
        System.out.println("Please enter your stagename, and bio: ");
        scanner = new Scanner(System.in);
        String sn = scanner.nextLine();
        String bio = scanner.nextLine();
        aid += add;
        a = new Artist(aid, sn, bio, u.getUid());
        artist.createArtist(a);
      } else if (choice == 2) {
        cstate = true;
        System.out.println("Please enter your skills:");
        scanner = new Scanner(System.in);
        String skill = scanner.nextLine();
        cid += add;
        c = new Critic(cid, u.getUid(), skill);
        critic.createCritic(c);
      } else {
        System.out.println("Invalid choice");
      }
    }
    System.out.println(u.toString());
    if (critic.findCriticById(u.getUid()) == null) {
      cstate = false;
    }

    while (true) {
      System.out.println("Enter command: ");
      if (cstate) {
        System.out.println("1 submit a review");
      } else {
        System.out.println("1 submit a new song/playlist");
      }
      System.out.println("2 view and search songs");
      System.out.println("3 view and search critiques");
      System.out.println("4 Make an update");
      System.out.println("5 view/edit account information");

      scanner = new Scanner(System.in);
      int input = scanner.nextInt();

      if (input == 6) {
        break;
      }

      switch (input) {
        case 1:
          if (cstate) {
            System.out.println("Please enter the song, rating, and comment");
            scanner = new Scanner(System.in);
            String song = scanner.nextLine();
            int rating = scanner.nextInt();
            String comment = scanner.nextLine();
            int add = random.nextInt(100);
            int ci = c.getCid();
            int si = songdao.findSongByName(song).getSid();
            int ai = songdao.findSongByName(song).getAid();
            review.createReview(new Review(si, ci, ai, rating, comment));
            System.out.println("Critique created");
          } else {
            System.out.println("Would you like to create a 1 new song or 2 new playlist");
            scanner = new Scanner(System.in);
            int option = scanner.nextInt();
            if (option == 1) {
              System.out.println("Please enter the song name, runtime, and playlist");
              scanner = new Scanner(System.in);
              String song = scanner.nextLine();
              int runtime = scanner.nextInt();
              String playlist = scanner.nextLine();
              int add = random.nextInt(100);
              sid += add;
              int ai = a.getAid();
              int pi = pdao.findPlaylistByName(playlist).getPid();
              songdao.createSong(new Song(sid, pi, runtime, song, ai));
              System.out.println("Song created");
            } else if (option == 2){
              System.out.println("Please enter the title, genre, and description");
              scanner = new Scanner(System.in);
              String title = scanner.nextLine();
              String genre = scanner.nextLine();
              String description = scanner.nextLine();
              int add = random.nextInt(100);
              pid += add;
              int ai = a.getAid();
              int gi = gdao.findGByName(genre).getGid();
              pdao.createPlaylist(new Playlist(pid, gi, title, description, ai));
              System.out.println("Playlist created");
            }
          }
          break;
        case 2:
          System.out.println("All Artists, Playlists, Songs, and Genres: ");
          System.out.println("Artists:");
          List<Artist> artists = artist.findAllArtists();
          for (Artist i: artists) {
            System.out.println(i.toString());
          }
          System.out.println("Playlists:");
          List<Playlist> plists = pdao.findAllPlaylists();
          for (Playlist i: plists) {
            System.out.println(i.toString());
          }
          System.out.println("Songs:");
          List<Song> songs = songdao.findAllSongs();
          for (Song i: songs) {
            System.out.println(i.toString());
          }
          System.out.println("Genres:");
          List<Genre> genres = gdao.findAllGenres();
          for (Genre i: genres) {
            System.out.println(i.toString());
          }

          System.out.println("Search by 1 artist, 2 song, 3 playlist");
          scanner = new Scanner(System.in);
          int searchCriteria = scanner.nextInt();
          switch(searchCriteria) {
            case 1:
              System.out.println("Enter the artist name");
              scanner = new Scanner(System.in);
              String in = scanner.nextLine();
              int ai = artist.findArtistByName(in).getAid();
              songs = songdao.findSongs("aid", ai);
              for (Song i: songs) {
                System.out.println(i.toString());
              }
              break;
            case 2:
              System.out.println("Enter the song name");
              scanner = new Scanner(System.in);
              in = scanner.nextLine();
              songdao.findSongByName(in);
              break;
            case 3:
              System.out.println("Enter the playlist title");
              scanner = new Scanner(System.in);
              in = scanner.nextLine();
              int pi = pdao.findPlaylistByName(in).getPid();
              songs = songdao.findSongs("pid", pi);
              for (Song i: songs) {
                System.out.println(i.toString());
              }
              break;
            default:
              System.out.println("Invalid command");
              break;
          }
          break;
        case 3:
          System.out.println("All Critics and Reviews: ");
          System.out.println("Critics:");
          List<Critic> critics = critic.findAllCritics();
          for (Critic i: critics) {
            System.out.println(i.toString());
          }
          System.out.println("Reviews:");
          List<Review> reviews = review.findAllReviews();
          for (Review i: reviews) {
            System.out.println(i.toString());
          }

          System.out.println("Search by 1 artist, 2 song, 3 critic");
          scanner = new Scanner(System.in);
          int search = scanner.nextInt();
          switch(search) {
            case 1:
              System.out.println("Enter the artist name");
              scanner = new Scanner(System.in);
              String in = scanner.nextLine();
              int ai = artist.findArtistByName(in).getAid();
              reviews = review.findReviews("aid", ai);
              for (Review i : reviews) {
                System.out.println(i.toString());
              }
              break;
            case 2:
              System.out.println("Enter the song name");
              scanner = new Scanner(System.in);
              in = scanner.nextLine();
              int si = songdao.findSongByName(in).getSid();
              reviews = review.findReviews("sid", si);
              for (Review i : reviews) {
                System.out.println(i.toString());
              }
              break;
            case 3:
              System.out.println("Enter the critic id");
              scanner = new Scanner(System.in);
              int in1 = scanner.nextInt();
              reviews = review.findReviews("cid", in1);
              for (Review i : reviews) {
                System.out.println(i.toString());
              }
              break;
            default:
              System.out.println("Invalid command");
              break;
          }
          break;
        case 4:
          if (!cstate) {
            System.out.println("Enter the song that you would like to update/delete: ");
            scanner = new Scanner(System.in);
            String song = scanner.nextLine();
            System.out.println(songdao.findSongByName(song).toString());
            System.out.println("Would you like to 1 update or 2 delete: ");
            scanner = new Scanner(System.in);
            int option = scanner.nextInt();
            if (option == 1) {
              System.out.println("Enter the new runtime and title: ");
              scanner = new Scanner(System.in);
              int runtime = scanner.nextInt();
              String title = scanner.nextLine();
              songdao.updateSong(songdao.findSongByName(song).getSid(),
                  new Song(songdao.findSongByName(song).getSid(),
                      songdao.findSongByName(song).getPid(), runtime, title,
                      a.getAid()));
            }
            else {
              songdao.deleteSong(songdao.findSongByName(song).getSid());
            }
          } else {
            System.out.println("Enter the song and critic id of the critique that you would like to"
                + "update/delete: ");
            scanner = new Scanner(System.in);
            String song = scanner.nextLine();
            int ci = scanner.nextInt();
            int si = songdao.findSongByName(song).getSid();
            System.out.println(review.findReviewById(ci, si).toString());
            System.out.println("Would you like to 1 update or 2 delete: ");
            scanner = new Scanner(System.in);
            int option = scanner.nextInt();
            if (option == 1) {
              System.out.println("Enter the new rating and comment");
              int rating = scanner.nextInt();
              String comment = scanner.nextLine();
              review.updateReview(new Review(si, c.getCid(), songdao.findSongByName(song).getAid(), rating,
                  comment));
            } else {
              review.deleteReview(si, ci);
            }
          }
          break;
        case 5:
          System.out.println("User info: ");
          System.out.println(u.toString());
          System.out.println("Role info: ");
          if (cstate) {
            System.out.println(c.toString());
          } else {
            System.out.println(a.toString());
          }
          System.out.println("Would you like to 1 update information or 2 delete account");
          scanner = new Scanner(System.in);
          int  choice = scanner.nextInt();
          if (choice == 1) {
            System.out.println("Enter the new first name, last name, and email:");
            scanner = new Scanner(System.in);
            String first = scanner.nextLine();
            String last = scanner.nextLine();
            String email = scanner.nextLine();
            user.updateUser(u.getUid(), new User(u.getUsername(), u.getPassword(), first, last,
                email, u.getUid()));
          } else if(choice == 2) {
            System.out.println("Please enter 'delete' to confirm user deletion: ");
            scanner = new Scanner(System.in);
            String in = scanner.nextLine();
            if (in == "delete") {
              user.deleteRecord(u.getUid());
              System.out.println("user deleted, ending program.");
              System.exit(0);
              break;
            } else {
              System.out.println("returning to main menu...");
            }
          }
          break;
        default:
          System.out.println("Invalid command");
          break;
      }
    }
  }
}
