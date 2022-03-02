package simpl.interpreter;

import java.util.HashSet;

import simpl.parser.Symbol;

public class Env {

	private final Env E;
	private final Symbol x;
	private final Value v;

	private Env() {
		E = null;
		x = null;
		v = null;
	}

	public static Env empty = new Env() {
		public Value get(Symbol y) {
			return null;
		}

		public String toString() {
			return "(empty)";
		}

		public Env clone() {
			return this;
		}

		public HashSet<RefValue> getRefValue() {
			return new HashSet<RefValue>();
		}
	};

	public Env(Env E, Symbol x, Value v) {
		this.E = E;
		this.x = x;
		this.v = v;
	}

	public String toString() {
		return "(" + this.x + ":" + this.v + ")->" + this.E;
	}

	public Value get(Symbol y) {
		// recursively get the value bingding to y
		// if not found, return null
		if (y == this.x)
			return this.v;
		return this.E.get(y);
	}

	public Env clone() {
		return new Env(this.E.clone(), this.x, this.v);
	}

	public HashSet<RefValue> getRefValue() {
		HashSet<RefValue> set = this.E.getRefValue();
		if (v instanceof RefValue)
			set.add((RefValue) v);
		return set;
	}
}
