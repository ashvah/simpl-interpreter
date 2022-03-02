package simpl.parser.ast;

import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;

public class Eq extends EqExpr {

	public Eq(Expr l, Expr r) {
		super(l, r);
	}

	public String toString() {
		return "(" + l + " = " + r + ")";
	}

	@Override
	public Value eval(State s) throws RuntimeError {
		// TODO
		Value lv = this.l.eval(s);
		Value rv = this.r.eval(s);
		if (lv.equals(rv))
			return Value.TRUE;
		else
			return Value.FALSE;
	}
	
	@Override
	public Expr Substitude(Symbol s, Expr e) {
		return new Eq(this.l.Substitude(s, e), this.r.Substitude(s, e));
	}
}
