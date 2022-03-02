package simpl.parser.ast;

import simpl.interpreter.Env;
import simpl.interpreter.FunValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.ThunkValue;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.ArrowType;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public class App extends BinaryExpr {

	public App(Expr l, Expr r) {
		super(l, r);
	}

	public String toString() {
		return "(" + l + " " + r + ")";
	}

	@Override
	public TypeResult typecheck(TypeEnv E) throws TypeError {
		TypeVar a = new TypeVar(true);
		TypeResult tl = this.l.typecheck(E);
		TypeResult tr = this.r.typecheck(tl.s.compose(E));
		Substitution solution = tr.s.compose(tl.s);
		Type stl = solution.apply(tl.t);
		Type str = solution.apply(tr.t);
		Substitution s = stl.unify(new ArrowType(str, a)).compose(solution);
		return TypeResult.of(s, a);
	}

	@Override
	public Value eval(State s) throws RuntimeError {
		FunValue fun = (FunValue) this.l.eval(s);
		Value argu = null;
		// if lazy_eval is enabled, create a thunk instead of evaluating it
		if (Expr.isLazy())
			argu = new ThunkValue(s.E.clone(), this.r);
		else
			argu = this.r.eval(s);
		
		return fun.e.eval(State.of(new Env(fun.E, fun.x, argu), s.getMem(), s.p));
	}

	@Override
	public Expr Substitude(Symbol s, Expr e) {
		return new App(this.l.Substitude(s, e), this.r.Substitude(s, e));
	}

	@Override
	public int isTail(Symbol x) {
		int f1 = this.l.isTail(x);
		int f2 = this.r.isTail(x);
		if (f2 == 0)
			return f1;
		return 1;
	}
}
