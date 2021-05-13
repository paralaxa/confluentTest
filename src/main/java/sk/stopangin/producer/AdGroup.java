package sk.stopangin.producer;

public class AdGroup {

  private String id;

  private String name;

  private String start_date;

  private String stop_date;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getStart_date() {
    return start_date;
  }

  public void setStart_date(String start_date) {
    this.start_date = start_date;
  }

  public String getStop_date() {
    return stop_date;
  }

  public void setStop_date(String stop_date) {
    this.stop_date = stop_date;
  }
}
