package jdbc.models;

public class Playlist {

  private int pid;
  private int gid;
  private String title;
  private String description;
  private int aid;

  public Playlist(int pid, int gid, String title, String description, int aid) {
    this.pid = pid;
    this.gid = gid;
    this.title = title;
    this.description = description;
    this.aid = aid;
  }

  public void setAid(int aid) {
    this.aid = aid;
  }

  public int getAid() {
    return aid;
  }

  public String toString() {
    return title + ": " + description;
  }

  public void setPid(int pid) {
    this.pid = pid;
  }

  public void setGid(int gid) {
    this.gid = gid;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getPid() {
    return pid;
  }

  public int getGid() {
    return gid;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }
}
