package jdbc.models;

public class Genre {

  private int gid;
  private String name;
  private String description;

  public Genre(int gid, String name, String description) {
    this.name = name;
    this.gid = gid;
    this.description = description;
  }

  public String toString() {
    return name + ", " + description;
  }

  public void setGid(int gid) {
    this.gid = gid;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getGid() {
    return gid;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }
}
