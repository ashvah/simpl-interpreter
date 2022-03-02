package simpl.parser.ast;

import simpl.interpreter.Env;
import simpl.interpreter.RecValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public class Rec extends Expr {

	public Symbol x;
	public Expr e;

	public Rec(Symbol x, Expr e) {
		this.x = x;
		this.e = e;
	}

	public String toString() {
		return "(rec " + x + "." + e + ")";
	}

	@Override
	public TypeResult typecheck(TypeEnv E) throws TypeError {
		TypeVar a = new TypeVar(false);
		Expr.is_rec = true;
		TypeResult t = this.e.typecheck(TypeEnv.of(E, x, a));
		Expr.is_rec = false;
		Type st = t.s.apply(t.t);
		Substitution s = st.unify(t.s.apply(a)).compose(t.s);
		return TypeResult.of(s, s.apply(st));
	}

	@Override
	public Value eval(State s) throws RuntimeError {
		RecValue rec = new RecValue(s.E.clone(), this.x, this.e);
		Value v = this.e.eval(State.of(new Env(s.E, this.x, rec), s.getMem(), s.p));
		return v;
	}

	@Override
	public Expr Substitude(Symbol s, Expr e) {
		if (this.x == s)
			return this;
		return new Rec(this.x, this.e.Substitude(s, e));
	}

	@Override
	public int isTail(Symbol x) {
		int f = this.e.isTail(x);
		if (f == 0)
			return 0;
		return 1;
	}
}
