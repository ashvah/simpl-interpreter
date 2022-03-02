package simpl.parser.ast;

import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.PolyType;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Define extends Expr {

	public Symbol x;
	public Expr e;

	public Define(Symbol x, Expr e) {
		this.x = x;
		this.e = e;
	}

	public String toString() {
		return "(define " + x + " " + e + ")";
	}

	@Override
	public TypeResult typecheck(TypeEnv E) throws TypeError {
		this.e.typecheck(E);
		return TypeResult.of(Type.UNIT);
	}

	public TypeResult typecheck_define(TypeEnv E) throws TypeError {
		TypeResult t = this.e.typecheck(E);
		PolyType pt = new PolyType(t.t, t.s.compose(E));
		return TypeResult.of(t.s, pt);
	}

	@Override
	public Value eval(State s) throws RuntimeError {
		return Value.UNIT;
	}

	public Value eval_define(State s) throws RuntimeError {
		return this.e.eval(s);
	}

	@Override
	public Expr Substitude(Symbol s, Expr e) {
		if (this.x == s)
			return this;
		return new Define(this.x, this.e.Substitude(s, e));
	}

	@Override
	public int isTail(Symbol x) {
		int f = this.e.isTail(x);
		if (f >= 1)
			return 1;
		return 0;
	}
}
