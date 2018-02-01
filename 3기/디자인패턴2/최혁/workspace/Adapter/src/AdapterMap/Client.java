package AdapterMap;

public class Client {

  public static void main(String[] args) {
    MapApi map = new DaumMap();
    mapInfo(map);
    MapApi googleMap = new GoogleMap();
    mapInfo(googleMap);
    
    //map.darkTheme(); // error:The method darkTheme() is undefined for the type MapApi
    ((GoogleMap) googleMap).darkTheme(); // OK! MapApi에 없는 기능을 쓰려면 다운캐스팅
  }

  public static void mapInfo(MapApi map) {
    System.out.println("\n");
    System.out.println("맵 종류: " + map.drawMap());
    System.out.println("수원으로 >> " + map.moveToSuwon());
    System.out.println("서울로 >> " + map.moveToSeoul());
  }

}
