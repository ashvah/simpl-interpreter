package simpl.parser.ast;

import simpl.interpreter.IntValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;

public class Div extends ArithExpr {

	public Div(Expr l, Expr r) {
		super(l, r);
	}

	public String toString() {
		return "(" + l + " / " + r + ")";
	}

	@Override
	public Value eval(State s) throws RuntimeError {
		// evaluate left expression
		IntValue lv = (IntValue) this.l.eval(s);
		// evaluate right expression
		IntValue rv = (IntValue) this.r.eval(s);
		// cannot divide by 0
		if (rv.n == 0)
			throw new RuntimeError("Div by zero");
		// return lv/rv
		return new IntValue(lv.n / rv.n);
	}

	@Override
	public Expr Substitude(Symbol s, Expr e) {
		return new Div(this.l.Substitude(s, e), this.r.Substitude(s, e));
	}
}
