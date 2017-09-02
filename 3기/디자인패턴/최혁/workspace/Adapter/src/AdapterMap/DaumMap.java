package AdapterMap;

public class DaumMap implements MapApi {
  String mapName = "다음맵";

  public String drawMap() {
    return mapName;
  }

  public String moveToSuwon() {
    return "수원";
  }

  public String moveToSeoul() {
    return "서울";
  }
}
