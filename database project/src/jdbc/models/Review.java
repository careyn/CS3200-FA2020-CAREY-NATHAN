package jdbc.models;

public class Review {

  private int sid;
  private int cid;
  private int aid;
  private int rating;
  private String comment;

  public Review(int sid, int cid, int aid, int rating, String comment) {
    this.sid = sid;
    this.cid = cid;
    this.aid = aid;
    this.rating = rating;
    this.comment = comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public String getComment() {
    return comment;
  }

  public String toString() {
    return aid + ": " + Integer.toString(rating);
  }

  public void setSid(int sid) {
    this.sid = sid;
  }

  public void setCid(int cid) {
    this.cid = cid;
  }

  public void setAid(int aid) {
    this.aid = aid;
  }

  public void setRating(int rating) {
    this.rating = rating;
  }

  public int getSid() {
    return sid;
  }

  public int getCid() {
    return cid;
  }

  public int getAid() {
    return aid;
  }

  public int getRating() {
    return rating;
  }
}
