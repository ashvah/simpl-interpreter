package simpl.interpreter;

public abstract class Value {

	public static final Value NIL = new NilValue();
	public static final Value UNIT = new UnitValue();
	public static final BoolValue FALSE = new BoolValue(false);
	public static final BoolValue TRUE = new BoolValue(true);
	public static final Value RECORDNIL = new RecordNilValue();
	public static final Value SIGNAL = new SignalValue();

	public abstract boolean equals(Object other);
}
