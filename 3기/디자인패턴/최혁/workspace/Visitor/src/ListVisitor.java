import java.util.Iterator;

public class ListVisitor extends Visitor {
	private String currentDir = "";

	@Override
	public void visit(File file) {
		System.out.println(currentDir + "/" + file); // file == file.toString();
	}

	@Override
	public void visit(Directory directory) {
		System.out.println(currentDir + "/" + directory); // directory == directory.toString();
		String saveDir = currentDir;
		currentDir = currentDir + "/" + directory.getName();
		Iterator<Entry> it = directory.iterator();
		while (it.hasNext()) {
			Entry entry = it.next();
			entry.accept(this);
		}
		currentDir = saveDir;
	}

}
