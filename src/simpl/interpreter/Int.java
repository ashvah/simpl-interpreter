package simpl.interpreter;

public class Int {

	private int n;

	public Int(int n) {
		this.n = n;
	}

	public int get() {
		return n;
	}

	public String toString() {
		return "Pointer@" + this.n;
	}

	public void set(int n) {
		this.n = n;
	}
}
