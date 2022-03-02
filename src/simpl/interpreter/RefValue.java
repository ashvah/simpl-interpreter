package simpl.interpreter;

public class RefValue extends Value {

	public final int p;

	public RefValue(int p) {
		this.p = p;
	}

	public String toString() {
		return "ref@" + this.p;
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof RefValue) {
			return ((RefValue) other).p == this.p;
		}
		return false;
	}
}
