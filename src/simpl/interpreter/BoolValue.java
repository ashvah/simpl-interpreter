package simpl.interpreter;

public class BoolValue extends Value {

	public final boolean b;

	public BoolValue(boolean b) {
		this.b = b;
	}

	public String toString() {
		return "" + this.b;
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof BoolValue) {
			return ((BoolValue) other).b == this.b;
		}
		return false;
	}
}
