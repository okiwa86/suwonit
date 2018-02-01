# Template 패턴

## 나만의 Template Example

FileToDb라는 abstract class에서 동작순서대로 흐르는 

writeDb()라는 메소드를 공통으로 사용하게 끔 만든다.

writeDb() 를 final 로 선언한건 수정안되게...?

그리고 abstract 메소드들은 상속받은 클래스가 구현하도록 하기 위함.

**targetFile(폴더주소)**
```
폴더를 변경해서 지정할수 있도록 하기 위함.

return: 파일을 String Array로 리턴해서 loop를 돌면서 사용한다.
```
**parsingFile(파일)** 
```
Loop를 돌면서 txt파일을 읽고 sql을 만들어낸다.

return: SQL문 String Array
```
**mergeDb(SQL)** 
```
SQL문법을 받아 DB에 INSERT/UPDATE 하기 위함.

return: void
```

**writeDb()**
```
위 메소드로 알고리즘 정의(보통은 공통메소드)
```

### Abstract class 
```java
public abstract class FileToDb {
	String folderPath;
	
	public abstract String[] targetFile(String folderPath);
	public abstract String[] parsingFile(String file);
	public abstract void mergeDb(String[] sql);
	
	public final void writeDb() {
		System.out.println(folderPath);
		String[] fileList = targetFile(folderPath); //open 할 txt파일 리스트 
		for(String file : fileList) {
			String[] sql = parsingFile(file); //file을 열어만든 sql arr
			mergeDb(sql); // sql excute
		}
		System.out.println("end of wrtieDb\n");
	}
}
```
### Concrete Class #1
오라클 DB에 넣는다

클래스 생성시 folderPath 는 부모클래스에 folderPath를 사용

```java
public class TxtToOracle extends FileToDb {
	
	TxtToOracle(String path){
		super.folderPath = path;
	}
	@Override
	public String[] targetFile(String folderPath) {
		System.out.println("내문서 파일 열기");
		String[] returnVal = {"가요톱텐11월3주.txt","인기가요11월3주.txt"};
		return returnVal;
	}

	@Override
	public String[] parsingFile(String file) {
		//read file
		String[] sqlArr = {"INSERT INTO SONG(TITLE..."  ,"INSERT...."};
		System.out.println(file + "Parsing txt -> Sql Query for Oracle");

		return sqlArr;
	}

	@Override
	public void mergeDb(String[] sqlArr) {
		for(String sql : sqlArr) {
			System.out.println("SqlSession.excute(" + sql );;
		}
	}
}
```

### Concrete Class #2
MySQL DB에 넣는다.

Concrete Class#1 과 거의 같다.
구체적인 내용만 변경해서 사용
```java

public class TxtToMysql extends FileToDb {
	TxtToMysql(String path){
		super.folderPath = path;
	}
	@Override
	public String[] targetFile(String folderPath) {
		System.out.println("기밀문서 폴더 파일 열기");
		String[] returnVal = {"청와대연봉.txt","최순실.txt"};
		return returnVal;
	}

	@Override
	public String[] parsingFile(String file) {
		//read file
		String[] sqlArr = {"INSERT INTO MONEY(TITLE..."  ,"INSERT...."};
		System.out.println(file + "Parsing txt -> Sql Query for Mysql");
		return sqlArr;
	}

	@Override
	public void mergeDb(String[] sqlArr) {
		for(String sql : sqlArr) {
			System.out.println("MYSQL SqlSession.excute(" + sql );;
		}
	}
}

```

이처럼 같은 패턴이 반복되고, 공통으로 사용되는 함수가 있을땐 사용하면 좋을 것 같다.

### Hook method
Abstract Class에 구현해 놓은 공통메소드에 수정이 되면 다른 부분도 다 수정이 된다.

좀더 유연성을 주고싶을땐, Hook Method를 이용한다.

```java
public void endHookMethod(){
    // 훅메소드에 아무것도 정의하지 않고 둔다.
    // 자식클래스가 지지고 복도록 한다.
}
public final void writeDb() {
		System.out.println(folderPath);
		String[] fileList = targetFile(folderPath); 
		
		for(String file : fileList) {
			String[] sql = parsingFile(file);
			mergeDb(sql);
		}
        // 빈 메소드를 두고 쓰고싶은 사람만 override 해서 사용하게끔.
		endHookMethod(); 
		System.out.println("end of wrtieDb\n");
}
```

abastract method가 아니기 때문에 강제성이 없어 유연성을 확보할 수 있다.