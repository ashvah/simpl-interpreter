package simpl.parser.ast;

import simpl.interpreter.IntValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;

public class Mod extends ArithExpr {

	public Mod(Expr l, Expr r) {
		super(l, r);
	}

	public String toString() {
		return "(" + l + " % " + r + ")";
	}

	@Override
	public Value eval(State s) throws RuntimeError {
		IntValue lv = (IntValue) this.l.eval(s);
		IntValue rv = (IntValue) this.r.eval(s);
		if (rv.n == 0)
			throw new RuntimeError("Mod by zero");
		return new IntValue(lv.n % rv.n);
	}

	@Override
	public Expr Substitude(Symbol s, Expr e) {
		return new Mod(this.l.Substitude(s, e), this.r.Substitude(s, e));
	}
}
