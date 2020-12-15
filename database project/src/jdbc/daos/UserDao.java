package jdbc.daos;

import jdbc.models.Critic;
import jdbc.models.User;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class UserDao {

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

  static final String CREATE_USER
      = "INSERT INTO user VALUES (?,?,?,?,?,?)";
  public Integer createUser(User user) {
    status = -1;
    connection = getConnection();
    try {
      statement = connection
          .prepareStatement(CREATE_USER);
      statement.setInt(1, user.getUid());
      statement.setString(2, user.getFirst());
      statement.setString(3, user.getLast());
      statement.setString(4, user.getUsername());
      statement.setString(5, user.getPassword());
      statement.setString(6, user.getEmail());
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

  static final String UPDATE_USER =
      "UPDATE user SET first=? WHERE uid=?";
  public Integer updateUser(Integer uid, User user) {
    connection = getConnection();
    try {
      statement = connection.prepareStatement(UPDATE_USER);
      statement.setString(1, user.getFirst());
      statement.setInt(2, user.getUid());
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

  static final String FIND_ALL_USERS
      = "SELECT * FROM user";
  public List<User> findAllUsers() {
    List<User> users = new ArrayList<User>();
    connection = getConnection();
    try {
      statement = connection
          .prepareStatement(FIND_ALL_USERS);
      ResultSet resultSet = statement.executeQuery();
      while(resultSet.next()) {
        Integer uid = resultSet.getInt("uid");
        String first = resultSet.getString("first");
        String last = resultSet.getString("last");
        String username = resultSet.getString("username");
        String password = resultSet.getString("password");
        String email = resultSet.getString("email");
        User user = new User(username, password, first, last, email, uid);
        users.add(user);
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
    return users;
  }

  static final String FIND_USER_BY_ID =
      "SELECT * FROM user WHERE uid=?";

  public User findUserById(Integer uid) {
    connection = getConnection();
    try {
      statement = connection.prepareStatement(FIND_USER_BY_ID);
      statement.setInt(1, uid);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next()) {
        String u = resultSet.getString("username");
        String p = resultSet.getString("password");
        String f = resultSet.getString("first");
        String l = resultSet.getString("last");
        String e = resultSet.getString("email");
        User user = new User(u, p, f, l, e, uid);
        return user;
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

  static final String FIND_USER_BY_NAME =
      "SELECT * FROM user WHERE username=? AND password=?";
  public User findUserByName(String name, String pass) {
    connection = getConnection();
    try {
      statement = connection.prepareStatement(FIND_USER_BY_NAME);
      statement.setString(1, name);
      statement.setString(2, pass);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next()) {
        String f = resultSet.getString("first");
        String l = resultSet.getString("last");
        String e = resultSet.getString("email");
        int uid = resultSet.getInt("uid");
        User user = new User(name, pass, f, l, e, uid);
        return user;
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

  static String DELETE = "DELETE FROM user WHERE uid=?";
  public Integer deleteRecord(int uid) {
    connection = getConnection();
    try {
      statement = connection.prepareStatement(DELETE);
      statement.setInt(1, uid);
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




}
