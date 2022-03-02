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

public class Loop extends Expr {

	public Expr e1, e2;

	public Loop(Expr e1, Expr e2) {
		this.e1 = e1;
		this.e2 = e2;
	}

	public String toString() {
		return "(while " + e1 + " do " + e2 + ")";
	}

	@Override
	public TypeResult typecheck(TypeEnv E) throws TypeError {
		TypeResult t1 = this.e1.typecheck(E);
		Type st1 = t1.s.apply(t1.t);
		Substitution solution = st1.unify(Type.BOOL).compose(t1.s);
		TypeResult t2 = this.e2.typecheck(solution.compose(E));
		solution = t2.s.compose(solution);
		Type st2 = solution.apply(t2.t);
		Substitution s = st2.unify(Type.UNIT).compose(solution);
		return TypeResult.of(s, Type.UNIT);
	}

	@Override
	public Value eval(State s) throws RuntimeError {
		while (true) {
			BoolValue cond = (BoolValue) this.e1.eval(s);
			if (cond.equals(Value.TRUE)) {
				Seq newExpr = new Seq(this.e2, this);
				Value v = newExpr.eval(s);
				if (v != Value.SIGNAL)
					// without optimization
					// create lots of stack frames here
					return newExpr.eval(s);
				else
					// exactly one stack for each level of loop
					// exit the stack after executing the sequences
					continue;
			} else
				return Value.UNIT;
		}
	}

	@Override
	public Expr Substitude(Symbol s, Expr e) {
		return new Loop(this.e1.Substitude(s, e), this.e2.Substitude(s, e));
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
