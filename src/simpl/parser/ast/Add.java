package simpl.parser.ast;

import simpl.interpreter.IntValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;

public class Add extends ArithExpr {

	public Add(Expr l, Expr r) {
		super(l, r);
	}

	public String toString() {
		return "(" + l + " + " + r + ")";
	}

	@Override
	public Value eval(State s) throws RuntimeError {
		// evaluate left expression
		IntValue lv = (IntValue) this.l.eval(s);
		// evaluate right expression
		IntValue rv = (IntValue) this.r.eval(s);
		// return lv + rv
		return new IntValue(lv.n + rv.n);
	}
	
	@Override
	public Expr Substitude(Symbol s, Expr e) {
		return new Add(this.l.Substitude(s, e), this.r.Substitude(s, e));
	}
}
