package jdbc.daos;

import jdbc.models.Artist;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import jdbc.models.Playlist;


public class ArtistDao {

  static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
  static final String HOST = "localhost:3306";
  static final String SCHEMA = "music-review";
  static final String CONFIG = "serverTimezone=UTC";
  static final String DB_URL
      = "jdbc:mysql://" + HOST + "/" + SCHEMA + "?" + CONFIG;

  static final String USER = "dbDesign";
  static final String PASS = "dbDesign";

  static Connection connection = null;
  static PreparedStatement statement = null;
  Integer status = -1;

  public static Connection getConnection() {
    try {
      Class.forName(JDBC_DRIVER);
      connection = DriverManager
          .getConnection(DB_URL, USER, PASS);
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
    return connection;
  }

  public static void closeConnection() {
    try {
      if (connection != null) {
        connection.close();
      }
      if (statement != null) {
        statement.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  static final String CREATE_ARTIST
      = "INSERT INTO artist VALUES (?,?,?,?)";

  public Integer createArtist(Artist artist) {
    status = -1;
    connection = getConnection();
    try {
      statement = connection
          .prepareStatement(CREATE_ARTIST);
      statement.setInt(1, artist.getAid());
      statement.setString(2, artist.getStage_name());
      statement.setString(3, artist.getBio());
      statement.setInt(4, artist.getUid());
      status = statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        connection.close();
        statement.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return status;
  }

  static final String FIND_ARTIST_BY_ID =
      "SELECT * FROM artist WHERE aid=?";

  public Artist findArtistById(Integer aid) {
    connection = getConnection();
    try {
      statement = connection.prepareStatement(FIND_ARTIST_BY_ID);
      statement.setInt(1, aid);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next()) {
        String stage_name = resultSet.getString("stage_name");
        String bio = resultSet.getString("bio");
        int uid = resultSet.getInt("uid");
        Artist artist = new Artist(aid, stage_name, bio, uid);
        return artist;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        connection.close();
        statement.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return null;
  }

  static final String FIND_ARTIST_BY_UID =
      "SELECT * FROM artist WHERE uid=?";

  public Artist findArtistByUid(Integer uid) {
    connection = getConnection();
    try {
      statement = connection.prepareStatement(FIND_ARTIST_BY_UID);
      statement.setInt(1, uid);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next()) {
        String stage_name = resultSet.getString("stage_name");
        String bio = resultSet.getString("bio");
        int aid = resultSet.getInt("aid");
        Artist artist = new Artist(aid, stage_name, bio, uid);
        return artist;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        connection.close();
        statement.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return null;
  }

  static final String UPDATE_ARTIST =
      "UPDATE artist SET stage_name=? WHERE aid=?";

  public Integer updateArtist(Integer aid, Artist artist) {
    connection = getConnection();
    try {
      statement = connection.prepareStatement(UPDATE_ARTIST);
      statement.setString(1, artist.getStage_name());
      statement.setInt(2, artist.getAid());
      status = statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        connection.close();
        statement.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return status;
  }

  static final String FIND_ALL_ARTISTS
      = "SELECT * FROM artist";

  public List<Artist> findAllArtists() {
    List<Artist> artists = new ArrayList<Artist>();
    connection = getConnection();
    try {
      statement = connection
          .prepareStatement(FIND_ALL_ARTISTS);
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        Integer aid = resultSet.getInt("aid");
        String stage_name = resultSet.getString("stage_name");
        String bio = resultSet.getString("bio");
        int uid = resultSet.getInt("uid");
        Artist artist = new Artist(aid, stage_name, bio, uid);
        artists.add(artist);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        connection.close();
        statement.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return artists;
  }

  static String DELETE = "DELETE FROM artist WHERE aid=?";
  public Integer deleteArtist(int aid) {
    connection = getConnection();
    try {
      statement = connection.prepareStatement(DELETE);
      statement.setInt(1, aid);
      status = statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        connection.close();
        statement.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return status;
  }

  static final String FIND_A_BY_NAME =
      "SELECT * FROM artist WHERE stage_name=?";
  public Artist findArtistByName(String name) {
    connection = getConnection();
    try {
      statement = connection.prepareStatement(FIND_A_BY_NAME);
      statement.setString(1, name);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next()) {
        String bio = resultSet.getString("bio");
        String sn = resultSet.getString("stage_name");
        int aid = resultSet.getInt("aid");
        int uid = resultSet.getInt("uid");
        Artist ar = new Artist(aid, sn, bio, uid);
        return ar;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        connection.close();
        statement.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return null;
  }


}
