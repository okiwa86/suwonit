import java.util.HashMap;

public class BigCharFactory {
	private HashMap<String, BigChar> pool = new HashMap<String, BigChar>(); // BigChar인스턴스 관리

	// 싱글톤 생성
	private static BigCharFactory singleton = new BigCharFactory();

	private BigCharFactory() {
	}

	public static BigCharFactory getInstance() {
		return singleton;
	}

	/**
	 * BigChar 인스턴스 생성 공유
	 * 
	 * @param charname
	 * @return
	 */
	public synchronized BigChar getBigChar(char charname) {
		BigChar bc = pool.get("" + charname);
		if (bc == null) {
			bc = new BigChar(charname); // null이면 새로 만들어버림 :)
			pool.put("" + charname, bc);
		}
		return bc;
	}
}
