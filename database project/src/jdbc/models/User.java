package jdbc.models;

public class User {

  private String username;
  private String password;
  private String first;
  private String last;
  private String email;
  private int uid;

  public User(String username, String password, String first, String last, String email, int uid) {
    this.username = username;
    this.password = password;
    this.first = first;
    this.last = last;
    this.email = email;
    this.uid = uid;
  }

  @Override
  public String toString() {
    return "username='" + username + '\'' +
        ", first='" + first + '\'' +
        ", last='" + last + '\'' +
        ", email='" + email + '\'';
  }
  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setFirst(String first) {
    this.first = first;
  }

  public void setLast(String last) {
    this.last = last;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setUid(int uid) {
    this.uid = uid;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String getFirst() {
    return first;
  }

  public String getLast() {
    return last;
  }

  public String getEmail() {
    return email;
  }

  public int getUid() {
    return uid;
  }
}
