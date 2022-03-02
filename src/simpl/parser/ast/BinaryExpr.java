package simpl.parser.ast;

import simpl.parser.Symbol;

public abstract class BinaryExpr extends Expr {

	public Expr l, r;

	public BinaryExpr(Expr l, Expr r) {
		this.l = l;
		this.r = r;
	}

	public int isTail(Symbol x) {
		int f1 = this.l.isTail(x);
		int f2 = this.r.isTail(x);
		if (f1 == 0 && f2 == 0)
			return 0;
		return 1;
	}
}
