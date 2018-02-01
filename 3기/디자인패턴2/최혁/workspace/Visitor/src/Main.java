
public class Main {

	public static void main(String[] args) {
		System.out.println("Making root entries...");
		Directory rootDir = new Directory("root");
		Directory binDir = new Directory("bin");
		Directory tmpDir = new Directory("tmp");
		Directory usrDir = new Directory("usr");

		rootDir.add(binDir);
		rootDir.add(tmpDir);
		rootDir.add(usrDir);
		binDir.add(new File("vi", 10000));
		binDir.add(new File("latex", 20000));
		rootDir.accept(new ListVisitor());

		System.out.println("");
		System.out.println("Making user entires...");
		Directory Kim = new Directory("Kim");
		Directory Lee = new Directory("Lee");
		Directory Park = new Directory("Park");

		usrDir.add(Kim);
		usrDir.add(Lee);
		usrDir.add(Park);

		Kim.add(new File("diary.html", 100));
		Kim.add(new File("Composite.java", 200));

		Lee.add(new File("memo.txt", 300));

		Park.add(new File("game.doc", 400));
		Park.add(new File("junk.mail", 500));

		rootDir.accept(new ListVisitor());
	}

}
