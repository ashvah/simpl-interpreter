package simpl.parser.ast;

import simpl.interpreter.ConsValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.ListType;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Cons extends BinaryExpr {

	public Cons(Expr l, Expr r) {
		super(l, r);
	}

	public String toString() {
		return "(" + l + " :: " + r + ")";
	}

	@Override
	public TypeResult typecheck(TypeEnv E) throws TypeError {
		TypeResult tl = this.l.typecheck(E);
		TypeResult tr = this.r.typecheck(tl.s.compose(E));
		Substitution solution = tr.s.compose(tl.s);
		Type stl = solution.apply(tl.t);
		Type str = solution.apply(tr.t);
		Substitution s = str.unify(new ListType(stl)).compose(solution);
		return TypeResult.of(s, str);
	}

	@Override
	public Value eval(State s) throws RuntimeError {
		Value lv = this.l.eval(s);
		Value rv = this.r.eval(s);
		return new ConsValue(lv, rv);
	}
	
	@Override
	public Expr Substitude(Symbol s, Expr e) {
		return new Cons(this.l.Substitude(s, e), this.r.Substitude(s, e));
	}
}
