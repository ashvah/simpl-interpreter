package simpl.interpreter;

import simpl.parser.ast.Expr;

public class ThunkValue extends Value {

	Expr exp;
	Env e;
	boolean eval;
	Value v;

	public ThunkValue(Env e, Expr exp) {
		this.exp = exp;
		this.e = e;
		this.eval = false;
		this.v = null;
	}
	
	public String toString() {
		return "#thunk";
	}

	public Value forceEval(State S) throws RuntimeError {
		// we never compute the same thunk twice
		if (this.eval == true)
		return this.v;
		// if not evaluated before, evaluate it and memorize the value
		// send message to GC, don't apply copy collection when evaluating exp
		Expr.setThunk(true);
		this.v = this.exp.eval(State.of(this.e, S.getMem(), S.p));
		Expr.setThunk(false);
		this.eval = true;
		return this.v;
	}

	public boolean equals(Object other) {
		// never compare two thunk values
		return false;
	}
}
