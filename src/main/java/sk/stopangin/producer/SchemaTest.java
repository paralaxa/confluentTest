package sk.stopangin.producer;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SchemaTest {

  @JsonProperty
  private int myField1;
  @JsonProperty
  private int myField2;
  @JsonProperty(required = true)
  private String myField3;
  @JsonProperty
  private String myField4;

  public SchemaTest() {
  }

  public SchemaTest(int myField1, int myField2, String myField3) {
    this.myField1 = myField1;
    this.myField2 = myField2;
    this.myField3 = myField3;
  }

  public String getMyField4() {
    return myField4;
  }

  public void setMyField4(String myField4) {
    this.myField4 = myField4;
  }

  public int getMyField1() {
    return myField1;
  }

  public void setMyField1(int myField1) {
    this.myField1 = myField1;
  }

  public int getMyField2() {
    return myField2;
  }

  public void setMyField2(int myField2) {
    this.myField2 = myField2;
  }

  public String getMyField3() {
    return myField3;
  }

  public void setMyField3(String myField3) {
    this.myField3 = myField3;
  }

  @Override
  public String toString() {
    return "SchemaTest{" +
        "myField1=" + myField1 +
        ", myField2=" + myField2 +
        ", myField3='" + myField3 + '\'' +
        ", myField4='" + myField4 + '\'' +
        '}';
  }
}
