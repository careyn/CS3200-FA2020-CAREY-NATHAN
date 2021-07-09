package jdbc.daos;

import jdbc.models.Critic;
import jdbc.models.Genre;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import jdbc.models.Playlist;

public class GenreDao {

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

  static final String CREATE_GENRE
      = "INSERT INTO genre VALUES (?,?,?)";
  public Integer createGenre(Genre genre) {
    status = -1;
    connection = getConnection();
    try {
      statement = connection
          .prepareStatement(CREATE_GENRE);
      statement.setInt(1, genre.getGid());
      statement.setString(2, genre.getName());
      statement.setString(3, genre.getDescription());
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

  static final String FIND_GENRE_BY_ID =
      "SELECT * FROM genre WHERE gid=?";

  public Genre findGenreById(Integer gid) {
    connection = getConnection();
    try {
      statement = connection.prepareStatement(FIND_GENRE_BY_ID);
      statement.setInt(1, gid);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next()) {
        String n = resultSet.getString("name");
        String d = resultSet.getString("description");
        Genre genre = new Genre(gid, n, d);
        return genre;
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

  static final String UPDATE_GENRE =
      "UPDATE genre SET name=? WHERE gid=?";
  public Integer updateGenre(Integer gid, Genre genre) {
    connection = getConnection();
    try {
      statement = connection.prepareStatement(UPDATE_GENRE);
      statement.setString(1, genre.getName());
      statement.setInt(2, genre.getGid());
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

  static final String FIND_ALL_GENRES
      = "SELECT * FROM genre";
  public List<Genre> findAllGenres() {
    List<Genre> genres = new ArrayList<Genre>();
    connection = getConnection();
    try {
      statement = connection
          .prepareStatement(FIND_ALL_GENRES);
      ResultSet resultSet = statement.executeQuery();
      while(resultSet.next()) {
        Integer id = resultSet.getInt("gid");
        String name = resultSet.getString("name");
        String description = resultSet.getString("description");
        Genre genre = new Genre(id, name, description);
        genres.add(genre);
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
    return genres;
  }

  static String DELETE = "DELETE FROM genre WHERE gid=?";
  public Integer deleteGenre(int gid) {
    connection = getConnection();
    try {
      statement = connection.prepareStatement(DELETE);
      statement.setInt(1, gid);
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

  static final String FIND_G_BY_NAME =
      "SELECT * FROM genre WHERE name=?";
  public Genre findGByName(String name) {
    connection = getConnection();
    try {
      statement = connection.prepareStatement(FIND_G_BY_NAME);
      statement.setString(1, name);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next()) {
        String desc = resultSet.getString("description");
        int g = resultSet.getInt("gid");
        Genre genre = new Genre(g, name, desc);
        return genre;
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

