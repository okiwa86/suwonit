
public class Main {

	public static void main(String[] args) {
		FileToDb oracle = new TxtToOracle("내문서");
		oracle.writeDb();
		FileToDb mysql = new TxtToMysql("기물문서폴더");
		mysql.writeDb();
	}

}
