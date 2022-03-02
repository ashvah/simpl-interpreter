package simpl.parser.ast;

import simpl.parser.Symbol;

public abstract class UnaryExpr extends Expr {

	public Expr e;

	public UnaryExpr(Expr e) {
		this.e = e;
	}

	public int isTail(Symbol x) {
		int f = this.e.isTail(x);
		if (f == 0)
			return 0;
		return 1;
	}
}
