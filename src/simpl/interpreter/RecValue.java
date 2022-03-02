package simpl.interpreter;

import simpl.parser.Symbol;
import simpl.parser.ast.Expr;

public class RecValue extends Value {

	public final Env E;
	public final Symbol x;
	public final Expr e;
	public int isTail;

	public RecValue(Env E, Symbol x, Expr e) {
		this.E = E;
		this.x = x;
		this.e = e;
		this.isTail = e.isTail(this.x);
	}

	@Override
	public boolean equals(Object other) {
		return false;
	}
}
