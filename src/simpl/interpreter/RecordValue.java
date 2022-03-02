package simpl.interpreter;

import simpl.parser.Symbol;

public class RecordValue extends Value {

	public final Value v1, v2;
	public final Symbol x;

	public RecordValue(Symbol x, Value v1, Value v2) {
		this.v1 = v1;
		this.v2 = v2;
		this.x = x;
	}

	public String toString() {
		assert this.v2 instanceof RecordValue;
		return "{" + this.x + "=" + this.v1 + ", " + ((RecordValue) this.v2).getValue() + "}";
	}

	public String getValue() {
		if (v2 instanceof RecordNilValue)
			return "" + this.x + "=" + this.v1;
		return "" + this.x + "=" + this.v1 + ", " + ((RecordValue) this.v2).getValue();
	}

	public Value proj(Symbol x) throws RuntimeError {
		if (this.x == x)
			return this.v1;
		if (this.v2 == Value.RECORDNIL)
			throw new RuntimeError("Cannot project");
		return ((RecordValue) this.v2).proj(x);
	}

	@Override
	public boolean equals(Object other) {
		return false;
	}
}
