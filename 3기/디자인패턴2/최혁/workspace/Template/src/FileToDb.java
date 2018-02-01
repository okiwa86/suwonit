
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
