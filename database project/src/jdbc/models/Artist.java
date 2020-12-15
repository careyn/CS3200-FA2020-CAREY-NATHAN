package jdbc.models;

public class Artist {

  private int aid;
  private String stage_name;
  private String bio;
  private int uid;

  public Artist(int aid, String stage_name, String bio, int uid) {
    this.aid = aid;
    this.bio = bio;
    this.uid = uid;
    this.stage_name = stage_name;
  }

  public String toString() {
    return stage_name + ", " + bio;
  }

  public String getBio() {
    return bio;
  }

  public int getAid() {
    return aid;
  }

  public String getStage_name() {
    return stage_name;
  }

  public int getUid() {
    return uid;
  }

  public void setAid(int aid) {
    this.aid = aid;
  }

  public void setStage_name(String stage_name) {
    this.stage_name = stage_name;
  }

  public void setBio(String bio) {
    this.bio = bio;
  }

  public void setUid(int uid) {
    this.uid = uid;
  }
}
