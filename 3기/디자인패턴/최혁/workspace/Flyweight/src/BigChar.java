import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class BigChar {
    private char charname; // 인스턴스 이름 0,1,2...
    private String fontdata; // 파일 읽은 데이터

	public BigChar(char charname) {
		this.charname = charname;
		String filePath = "big" + charname + ".txt";
		InputStream is = this.getClass().getResourceAsStream(filePath);
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));

			String line;

			StringBuffer buf = new StringBuffer();
			while ((line = reader.readLine()) != null) {
				buf.append(line);
				buf.append("\n");
			}
			reader.close();
			this.fontdata = buf.toString();
		} catch (IOException e) {
			this.fontdata = charname + "?" + filePath;
		}
	}

	public void print() {
		System.out.println(fontdata);
	}
}
