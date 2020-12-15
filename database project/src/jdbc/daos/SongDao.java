package jdbc.daos;

import jdbc.models.Critic;
import jdbc.models.Song;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import jdbc.models.User;


public class SongDao {

  static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
  static final String HOST = "localhost:3306";
  static final String SCHEMA = "music-review";
  static final String CONFIG = "serverTimezone=UTC";
  static final String DB_URL
      = "jdbc:mysql://"+HOST+"/"+SCHEMA+"?"+CONFIG;

  static final String USER = "dbDesign";
  static final String PASS = "dbDesign";

  static Connection connection = null;
  static PreparedStatement statement = null;
  Integer status = -1;

  public static Connection getConnection() {
    try {
      Class.forName(JDBC_DRIVER);
      connection = DriverManager
          .getConnection(DB_URL,USER,PASS);
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
    return connection;
  }

  public static void closeConnection() {
    try {
      if(connection != null) {
        connection.close();
      }
      if(statement != null) {
        statement.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  static final String CREATE_SONG
      = "INSERT INTO song VALUES (?,?,?,?,?)";
  public Integer createSong(Song song) {
    status = -1;
    connection = getConnection();
    try {
      statement = connection
          .prepareStatement(CREATE_SONG);
      statement.setInt(1, song.getSid());
      statement.setInt(2, song.getPid());
      statement.setString(3, song.getTitle());
      statement.setInt(4, song.getRuntime());
      statement.setInt(5, song.getAid());

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

  static final String UPDATE_SONG =
      "UPDATE song SET title=? WHERE sid=?";
  public Integer updateSong(Integer sid, Song song) {
    connection = getConnection();
    try {
      statement = connection.prepareStatement(UPDATE_SONG);
      statement.setString(1, song.getTitle());
      statement.setInt(2, song.getSid());
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

  static final String FIND_ALL_SONGS
      = "SELECT * FROM song";
  public List<Song> findAllSongs() {
    List<Song> songs = new ArrayList<Song>();
    connection = getConnection();
    try {
      statement = connection
          .prepareStatement(FIND_ALL_SONGS);
      ResultSet resultSet = statement.executeQuery();
      while(resultSet.next()) {
        Integer sid = resultSet.getInt("sid");
        Integer pid = resultSet.getInt("pid");
        String title = resultSet.getString("title");
        Integer runtime = resultSet.getInt("runtime");
        Integer aid = resultSet.getInt("aid");
        Song song = new Song(sid, pid, runtime, title, aid);
        songs.add(song);
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
    return songs;
  }

  static final String FIND_SONG_BY_ID =
      "SELECT * FROM song WHERE sid=?";

  public Song findSongById(Integer sid) {
    connection = getConnection();
    try {
      statement = connection.prepareStatement(FIND_SONG_BY_ID);
      statement.setInt(1, sid);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next()) {
        Integer pid = resultSet.getInt("pid");
        String title = resultSet.getString("title");
        Integer runtime = resultSet.getInt("runtime");
        Integer aid = resultSet.getInt("aid");
        Song song = new Song(sid, pid, runtime, title, aid);
        return song;
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

  static String DELETE = "DELETE FROM song WHERE sid=?";
  public Integer deleteSong(int sid) {
    connection = getConnection();
    try {
      statement = connection.prepareStatement(DELETE);
      statement.setInt(1, sid);
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

  static final String FIND_SONG_BY_NAME =
      "SELECT * FROM song WHERE title=?";
  public Song findSongByName(String name) {
    connection = getConnection();
    try {
      statement = connection.prepareStatement(FIND_SONG_BY_NAME);
      statement.setString(1, name);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next()) {
        int s = resultSet.getInt("sid");
        int p = resultSet.getInt("pid");
        int r = resultSet.getInt("runtime");
        int a = resultSet.getInt("aid");
        Song song = new Song(s, p, r, name, a);
        return song;
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

  static final String FIND_SONGS
      = "SELECT * FROM song WHERE ?=?";
  public List<Song> findSongs(String field, int value) {
    List<Song> songs = new ArrayList<Song>();
    connection = getConnection();
    try {
      statement = connection
          .prepareStatement(FIND_SONGS);
      statement.setString(1, field);
      statement.setInt(1, value);
      ResultSet resultSet = statement.executeQuery();
      while(resultSet.next()) {
        Integer sid = resultSet.getInt("sid");
        Integer pid = resultSet.getInt("pid");
        String title = resultSet.getString("title");
        Integer runtime = resultSet.getInt("runtime");
        Integer aid = resultSet.getInt("aid");
        Song song = new Song(sid, pid, runtime, title, aid);
        songs.add(song);
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
    return songs;
  }




}
