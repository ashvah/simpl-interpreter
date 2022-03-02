package simpl.interpreter;

public class ConsValue extends Value {

	public final Value v1, v2;
	public final int length;

	public ConsValue(Value v1, Value v2) {
		this.v1 = v1;
		this.v2 = v2;
		if (v2 == Value.NIL)
			this.length = 1;
		else {
			assert v2 instanceof ConsValue;
			this.length = ((ConsValue) v2).length + 1;
		}

	}

	public String toString() {
		return "list@" + this.length;
	}

	public String getValue() {
		if (v2 instanceof NilValue)
			return this.v1 + ")";
		return "" + this.v1 + ", " + ((ConsValue) this.v2).getValue();
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof ConsValue) {
			return ((ConsValue) other).v1.equals(this.v1) && ((ConsValue) other).v2.equals(this.v2);
		}
		return false;
	}
}
