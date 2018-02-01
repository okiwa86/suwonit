
public class Printer implements Printable {
	private String name;

	public Printer() {
		heavyJob("Printer 인스턴스를 생성 중");
	}

	public Printer(String name) {
		this.name = name;
		heavyJob("Printer 인스턴스(" + name + ")를 생성 중");
	}
	
	private void heavyJob(String msg) {
		System.out.println(msg);
		// 1초마다 . 하나 씩 찍기...
		for (int i = 0; i < 5; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(".");
		}
		System.out.println("완료.");
	}


	@Override
	public void setPrinterName(String name) {
		this.name = name;
	}

	@Override
	public String getPrinterName() {
		return name;
	}

	@Override
	public void print(String string) {
		System.out.println("=== " + name + " ===");
		System.out.println(string);

	}

}
