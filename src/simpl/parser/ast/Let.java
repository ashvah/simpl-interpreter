package simpl.parser.ast;

import simpl.interpreter.Env;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.ThunkValue;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.PolyType;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Let extends Expr {

	public Symbol x;
	public Expr e1, e2;

	public Let(Symbol x, Expr e1, Expr e2) {
		this.x = x;
		this.e1 = e1;
		this.e2 = e2;
	}

	public String toString() {
		return "(let " + x + " = " + e1 + " in " + e2 + ")";
	}

//	@Override
//	public TypeResult typecheck(TypeEnv E) throws TypeError {
//		// check e2[e1/x]
//		TypeResult t2 = this.e2.Substitude(this.x, e1).typecheck(E);
//		// check e1
//		this.e1.typecheck(E);
//		return t2;
//	}

	@Override
	public TypeResult typecheck(TypeEnv E) throws TypeError {
		// check e1 and construct a polymorphsm type pt
		TypeResult t1 = this.e1.typecheck(E);
		PolyType pt = new PolyType(t1.t, t1.s.compose(E));

		// bind x->py and check e2
		TypeResult t2 = this.e2.typecheck(TypeEnv.of(t1.s.compose(E), this.x, pt));
		return TypeResult.of(t2.s.compose(t1.s), t2.t);
	}

	@Override
	public Value eval(State s) throws RuntimeError {
		Value v1 = null;
		// if lazy_eval is enabled, create a thunk instead of evaluating it
		if (Expr.isLazy())
			v1 = new ThunkValue(s.E.clone(), this.e1);
		else
			v1 = this.e1.eval(s);
		return this.e2.eval(State.of(new Env(s.E, x, v1), s.getMem(), s.p));
	}

	@Override
	public Expr Substitude(Symbol s, Expr e) {
		// we want to replace s->e, for every free occurrence of s
		// if x=s, all occurrences of x in e1 are free but those in e2 are bound
		// so replace x->e in e1 but do nothing in e2
		if (this.x == s)
			return new Let(this.x, this.e1.Substitude(s, e), this.e2);
		// if x!=s, all occurrences of x in e1 and e2 are free so replace them
		return new Let(this.x, this.e1.Substitude(s, e), this.e2.Substitude(s, e));
	}

	@Override
	public int isTail(Symbol x) {
		int f1 = this.e1.isTail(x);
		int f2 = this.e2.isTail(x);
		if (f1 == 0)
			return f2;
		return 1;
	}
}
