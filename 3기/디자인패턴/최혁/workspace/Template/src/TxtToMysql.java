
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
