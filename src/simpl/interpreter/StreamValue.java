package simpl.interpreter;

public class StreamValue extends Value {

	public Value v1;
	public ThunkValue v2;

	public StreamValue(Value v1, ThunkValue v2) {
		this.v1 = v1;
		this.v2 = v2;
	}

	@Override
	public boolean equals(Object other) {
		return false;
	}

	public String toString() {
		return "(stream, " + this.v1 + ", " + this.v2 + ")";
	}

}
