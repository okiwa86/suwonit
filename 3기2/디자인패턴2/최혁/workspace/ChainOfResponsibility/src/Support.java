
public abstract class Support {
	private String name;
	private Support next;

	public Support(String name) {
		this.name = name;
	}
	
	// LinkedList 처럼 다음에 누가 올지를 set 해준다.
	public Support setNext(Support next) {
		this.next = next;
		return next;
	}

	public final void support(Trouble trouble) { // 트러블 해결의 수순 (Template패턴)
		if (resolve(trouble)) {
			done(trouble);
		} else if (next != null) {
			next.support(trouble);
		} else {
			fail(trouble);
		}
	}

	protected abstract boolean resolve(Trouble trouble); // 해결용 method

	protected void done(Trouble trouble) { // 해결
		System.out.println("Done! " + trouble + " resolved by " + this);
	}

	protected void fail(Trouble trouble) { // 해결 불가
		System.out.println("Fail  " + trouble);
	}

	@Override
	public String toString() {
		return "[" + name + "]";
	}
}
