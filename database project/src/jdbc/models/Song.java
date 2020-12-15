package jdbc.models;

public class Song {

  private int sid;
  private int pid;
  private String title;
  private int runtime;
  private int aid;

  public Song(int sid, int pid, int runtime, String title, int aid) {
    this.sid = sid;
    this.pid = pid;
    this.title = title;
    this.runtime = runtime;
  }

  public void setAid(int aid) {
    this.aid = aid;
  }

  public int getAid() {
    return aid;
  }

  public String toString() {
    return title + ": " + Integer.toString(runtime);
  }

  public void setSid(int sid) {
    this.sid = sid;
  }

  public void setPid(int pid) {
    this.pid = pid;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setRuntime(int runtime) {
    this.runtime = runtime;
  }

  public int getSid() {
    return sid;
  }

  public int getPid() {
    return pid;
  }

  public String getTitle() {
    return title;
  }

  public int getRuntime() {
    return runtime;
  }
}
