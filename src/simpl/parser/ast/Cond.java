package simpl.parser.ast;

import simpl.interpreter.BoolValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Cond extends Expr {

	public Expr e1, e2, e3;

	public Cond(Expr e1, Expr e2, Expr e3) {
		this.e1 = e1;
		this.e2 = e2;
		this.e3 = e3;
	}

	public String toString() {
		return "(if " + e1 + " then " + e2 + " else " + e3 + ")";
	}

	@Override
	public TypeResult typecheck(TypeEnv E) throws TypeError {
		TypeResult t1 = this.e1.typecheck(E);
		Type st1 = t1.s.apply(t1.t);
		Substitution solution = st1.unify(Type.BOOL).compose(t1.s);

		TypeEnv E1 = solution.compose(E);
		TypeResult t2 = this.e2.typecheck(E1);
		TypeResult t3 = this.e3.typecheck(t2.s.compose(E1));
		solution = t3.s.compose(t2.s.compose(solution));
		Type st2 = solution.apply(t2.t);
		Type st3 = solution.apply(t3.t);
		Substitution s = st2.unify(st3).compose(solution);
		return TypeResult.of(s, st2);
	}

	@Override
	public Value eval(State s) throws RuntimeError {
		BoolValue pred = (BoolValue) this.e1.eval(s);
		if (pred.equals(Value.TRUE))
			return this.e2.eval(s);
		else
			return this.e3.eval(s);
	}

	@Override
	public Expr Substitude(Symbol s, Expr e) {
		return new Cond(this.e1.Substitude(s, e), this.e2.Substitude(s, e), this.e3.Substitude(s, e));
	}

	@Override
	public int isTail(Symbol x) {
		int f1 = this.e1.isTail(x);
		int f2 = this.e2.isTail(x);
		int f3 = this.e3.isTail(x);
		if (f1 == 0 && f2 != 1 && f3 != 1)
			return f2;
		return 1;
	}
}
