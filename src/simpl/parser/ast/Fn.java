package simpl.parser.ast;

import simpl.interpreter.FunValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.ArrowType;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public class Fn extends Expr {

	public Symbol x;
	public Expr e;

	public Fn(Symbol x, Expr e) {
		this.x = x;
		this.e = e;
	}

	public String toString() {
		return "(fn " + x + "." + e + ")";
	}

	@Override
	public TypeResult typecheck(TypeEnv E) throws TypeError {
		// \x:a.e:b : a->b
		TypeVar a = new TypeVar(true);
		TypeVar b = new TypeVar(true);
		// Add x->a to E
		TypeResult t = this.e.typecheck(TypeEnv.of(E, this.x, a));
		Type st = t.s.apply(t.t);
		Substitution s = st.unify(b).compose(t.s);
		return TypeResult.of(s, new ArrowType(s.apply(a), s.apply(b)));
	}

	@Override
	public Value eval(State s) throws RuntimeError {
		return new FunValue(s.E.clone(), this.x, this.e);
	}

	@Override
	public Expr Substitude(Symbol s, Expr e) {
		if (this.x == s)
			return this;
		return new Fn(this.x, this.e.Substitude(s, e));
	}

	@Override
	public int isTail(Symbol x) {
		return this.e.isTail(x);
	}
}
