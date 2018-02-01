
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
