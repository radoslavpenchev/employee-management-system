package employeeManagment.additional;

import java.util.List;

public class StringListPair {
  private String string;
  private List<String> list;

  public StringListPair(String string, List<String> list) {
    this.string = string;
    this.list = list;
  }

  public String getStr() {
    return string;
  }

  public List<String> getList() {
    return list;
  }
}
