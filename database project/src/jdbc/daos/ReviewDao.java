package jdbc.daos;

import jdbc.models.Artist;
import jdbc.models.Review;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import jdbc.models.Song;

public class ReviewDao {

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

  static final String CREATE_REVIEW
      = "INSERT INTO review VALUES (?,?,?,?,?)";
  public Integer createReview(Review review) {
    status = -1;
    connection = getConnection();
    try {
      statement = connection
          .prepareStatement(CREATE_REVIEW);
      statement.setInt(1, review.getSid());
      statement.setInt(2, review.getCid());
      statement.setInt(3, review.getAid());
      statement.setInt(4, review.getRating());
      statement.setString(5, review.getComment());
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

  static final String UPDATE_REVIEW =
      "UPDATE review SET rating=? WHERE sid=? AND cid=?";
  public Integer updateReview(Review review) {
    connection = getConnection();
    try {
      statement = connection.prepareStatement(UPDATE_REVIEW);
      statement.setInt(1, review.getRating());
      statement.setInt(2, review.getSid());
      statement.setInt(3, review.getCid());

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


  public List<Review> findReviews(String s, int value) {
    List<Review> reviews = new ArrayList<Review>();
    connection = getConnection();
    try {
      statement = connection
          .prepareStatement(s);
      statement.setInt(1, value);
      ResultSet resultSet = statement.executeQuery();
      while(resultSet.next()) {
        Integer sid = resultSet.getInt("sid");
        Integer cid = resultSet.getInt("cid");
        Integer aid = resultSet.getInt("aid");
        Integer rating = resultSet.getInt("rating");
        String comment = resultSet.getString("comment");

        Review review = new Review(sid, cid, aid, rating, comment);
        reviews.add(review);
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
    return reviews;
  }

  static final String FIND_ALL_REVIEWS
      = "SELECT * FROM review";
  public List<Review> findAllReviews() {
    List<Review> reviews = new ArrayList<Review>();
    connection = getConnection();
    try {
      statement = connection
          .prepareStatement(FIND_ALL_REVIEWS);
      ResultSet resultSet = statement.executeQuery();
      while(resultSet.next()) {
        Integer sid = resultSet.getInt("sid");
        Integer cid = resultSet.getInt("cid");
        Integer aid = resultSet.getInt("aid");
        Integer rating = resultSet.getInt("rating");
        String comment = resultSet.getString("comment");

        Review review = new Review(sid, cid, aid, rating, comment);
        reviews.add(review);
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
    return reviews;
  }

  static final String FIND_REVIEW_BY_ID =
      "SELECT * FROM review WHERE sid=? AND cid=?";

  public Review findReviewById(Integer sid, Integer cid) {
    connection = getConnection();
    try {
      statement = connection.prepareStatement(FIND_REVIEW_BY_ID);
      statement.setInt(1, sid);
      statement.setInt(2, cid);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next()) {
        Integer aid = resultSet.getInt("aid");
        Integer rating = resultSet.getInt("comment");
        String comment = resultSet.getString("rating");
        Review review = new Review(sid, cid, aid, rating, comment);
        return review;
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

  static String DELETE = "DELETE FROM review WHERE sid=? AND cid=?";
  public Integer deleteReview(int sid, int cid) {
    connection = getConnection();
    try {
      statement = connection.prepareStatement(DELETE);
      statement.setInt(1, sid);
      statement.setInt(2, cid);
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
