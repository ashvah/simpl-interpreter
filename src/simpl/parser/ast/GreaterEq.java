package simpl.parser.ast;

import simpl.interpreter.IntValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;

public class GreaterEq extends RelExpr {

	public GreaterEq(Expr l, Expr r) {
		super(l, r);
	}

	public String toString() {
		return "(" + l + " >= " + r + ")";
	}

	@Override
	public Value eval(State s) throws RuntimeError {
		IntValue lv = (IntValue) this.l.eval(s);
		IntValue rv = (IntValue) this.r.eval(s);
		if (lv.n >= rv.n)
			return Value.TRUE;
		else
			return Value.FALSE;
	}

	@Override
	public Expr Substitude(Symbol s, Expr e) {
		return new GreaterEq(this.l.Substitude(s, e), this.r.Substitude(s, e));
	}
}
