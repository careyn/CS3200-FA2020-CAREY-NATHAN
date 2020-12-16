package jdbc.daos;

import jdbc.models.Artist;
import jdbc.models.Critic;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class CriticDao {

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

  static final String CREATE_CRITIC
      = "INSERT INTO critic VALUES (?,?,?)";
  public Integer createCritic(Critic critic) {
    status = -1;
    connection = getConnection();
    try {
      statement = connection
          .prepareStatement(CREATE_CRITIC);
      statement.setInt(1, critic.getCid());
      statement.setString(2, critic.getAbilities());
      statement.setInt(3, critic.getUid());
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

  static final String FIND_CRITIC_BY_ID =
      "SELECT * FROM critic WHERE cid=?";

  public Critic findCriticById(Integer cid) {
    connection = getConnection();
    try {
      statement = connection.prepareStatement(FIND_CRITIC_BY_ID);
      statement.setInt(1, cid);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next()) {
        String a = resultSet.getString("abilities");
        Integer u = resultSet.getInt("uid");
        Critic critic = new Critic(cid, u, a);
        return critic;
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

  static final String FIND_CRITIC_BY_UID =
      "SELECT * FROM critic WHERE uid=?";

  public Critic findCriticByUid(Integer uid) {
    connection = getConnection();
    try {
      statement = connection.prepareStatement(FIND_CRITIC_BY_UID);
      statement.setInt(1, uid);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next()) {
        String a = resultSet.getString("abilities");
        Integer c = resultSet.getInt("cid");
        Critic critic = new Critic(c, uid, a);
        return critic;
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

  static final String UPDATE_CRITIC =
      "UPDATE critic SET abilities=? WHERE cid=?";
  public Integer updateCritic(Integer cid, Critic critic) {
    connection = getConnection();
    try {
      statement = connection.prepareStatement(UPDATE_CRITIC);
      statement.setString(1, critic.getAbilities());
      statement.setInt(2, critic.getCid());
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

  static final String FIND_ALL_CRITICS
      = "SELECT * FROM critic";
  public List<Critic> findAllCritics() {
    List<Critic> critics = new ArrayList<Critic>();
    connection = getConnection();
    try {
      statement = connection
          .prepareStatement(FIND_ALL_CRITICS);
      ResultSet resultSet = statement.executeQuery();
      while(resultSet.next()) {
        Integer cid = resultSet.getInt("cid");
        String a = resultSet.getString("abilities");
        Integer u = resultSet.getInt("uid");
        Critic critic = new Critic(cid, u, a);
        critics.add(critic);
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
    return critics;
  }

  static String DELETE = "DELETE FROM critic WHERE cid=?";
  public Integer deleteCritic(int cid) {
    connection = getConnection();
    try {
      statement = connection.prepareStatement(DELETE);
      statement.setInt(1, cid);
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
