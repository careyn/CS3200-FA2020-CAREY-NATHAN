package jdbc.models;

public class Critic {

  private int cid;
  private int uid;
  private String abilities;

  public Critic(int cid, int uid, String abilities) {
    this.uid = uid;
    this.cid = cid;
    this.abilities = abilities;
  }

  public String toString() {
    return cid + ", " + abilities;
  }

  public int getCid() {
    return cid;
  }

  public int getUid() {
    return uid;
  }

  public String getAbilities() {
    return abilities;
  }

  public void setCid(int cid) {
    this.cid = cid;
  }

  public void setUid(int uid) {
    this.uid = uid;
  }

  public void setAbilities(String abilities) {
    this.abilities = abilities;
  }
}
