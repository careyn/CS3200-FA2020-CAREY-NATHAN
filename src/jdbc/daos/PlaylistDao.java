package jdbc.daos;

import jdbc.models.Critic;
import jdbc.models.Playlist;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import jdbc.models.User;


public class PlaylistDao {

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

  static final String CREATE_PLAYLIST
      = "INSERT INTO playlist VALUES (?,?,?,?,?)";
  public Integer createPlaylist(Playlist playlist) {
    status = -1;
    connection = getConnection();
    try {
      statement = connection
          .prepareStatement(CREATE_PLAYLIST);
      statement.setInt(1, playlist.getPid());
      statement.setString(2, playlist.getTitle());
      statement.setString(3, playlist.getDescription());
      statement.setInt(4, playlist.getGid());
      statement.setInt(5, playlist.getAid());
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

  static final String UPDATE_PLAYLIST =
      "UPDATE playlist SET title=? WHERE pid=?";
  public Integer updateCourse(Integer pid, Playlist playlist) {
    connection = getConnection();
    try {
      statement = connection.prepareStatement(UPDATE_PLAYLIST);
      statement.setString(1, playlist.getTitle());
      statement.setInt(2, playlist.getPid());
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

  static final String FIND_PLAYLIST_BY_ID =
      "SELECT * FROM playlist WHERE pid=?";

  public Playlist findPlaylistById(Integer pid) {
    connection = getConnection();
    try {
      statement = connection.prepareStatement(FIND_PLAYLIST_BY_ID);
      statement.setInt(1, pid);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next()) {
        String description = resultSet.getString("description");
        String title = resultSet.getString("title");
        Integer gid = resultSet.getInt("gid");
        Integer aid = resultSet.getInt("aid");
        Playlist playlist = new Playlist(pid, gid, title, description, aid);
        return playlist;
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

  static final String FIND_ALL_PLAYLISTS
      = "SELECT * FROM playlist";
  public List<Playlist> findAllPlaylists() {
    List<Playlist> playlists = new ArrayList<Playlist>();
    connection = getConnection();
    try {
      statement = connection
          .prepareStatement(FIND_ALL_PLAYLISTS);
      ResultSet resultSet = statement.executeQuery();
      while(resultSet.next()) {
        Integer id = resultSet.getInt("pid");
        String title = resultSet.getString("title");
        String description = resultSet.getString("description");
        int gid = resultSet.getInt("gid");
        int aid = resultSet.getInt("aid");
        Playlist p = new Playlist(id, gid, title, description, aid);
        playlists.add(p);
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
    return playlists;
  }

  static String DELETE = "DELETE FROM playlist WHERE pid=?";
  public Integer deletePlaylist(int pid) {
    connection = getConnection();
    try {
      statement = connection.prepareStatement(DELETE);
      statement.setInt(1, pid);
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

  static final String FIND_P_BY_NAME =
      "SELECT * FROM playlist WHERE title=?";
  public Playlist findPlaylistByName(String name) {
    connection = getConnection();
    try {
      statement = connection.prepareStatement(FIND_P_BY_NAME);
      statement.setString(1, name);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next()) {
        String desc = resultSet.getString("description");
        int p = resultSet.getInt("pid");
        int g = resultSet.getInt("gid");
        int aid = resultSet.getInt("aid");
        Playlist plist = new Playlist(p, g, name, desc, aid);
        return plist;
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
