package AdapterMap;

public class GoogleMap implements MapApi{
  String ver = "GoogleMap 1.0";
  String location = "CA";
  public String version() {
    return ver;
  }
  public String thisLocation() {
    return location;
  }
  public void moveTo(double lat, double lng) {
    //이동했다.
  }
  public String darkTheme() {
    return "DarkTheme-Apply";
  }
  @Override
  public String drawMap() {
    return this.version();
  }
  @Override
  public String moveToSuwon() {
    moveTo( 37.263363,127.028625 );
    return "수원 (37.263363,127.028625)";
  }
  @Override
  public String moveToSeoul() {
    moveTo( 37.564374,126.975586 );
    return "서울 ( 37.564374,126.975586 )";
  }
}
