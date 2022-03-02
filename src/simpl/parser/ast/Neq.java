package simpl.parser.ast;

import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;

public class Neq extends EqExpr {

	public Neq(Expr l, Expr r) {
		super(l, r);
	}

	public String toString() {
		return "(" + l + " <> " + r + ")";
	}

	@Override
	public Value eval(State s) throws RuntimeError {
		Value lv = this.l.eval(s);
		Value rv = this.r.eval(s);
		if (lv.equals(rv))
			return Value.FALSE;
		else
			return Value.TRUE;
	}

	@Override
	public Expr Substitude(Symbol s, Expr e) {
		return new Neq(this.l.Substitude(s, e), this.r.Substitude(s, e));
	}
}
