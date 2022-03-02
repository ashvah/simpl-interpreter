package simpl.parser.ast;

import simpl.interpreter.RefValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.RefType;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Assign extends BinaryExpr {

	public Assign(Expr l, Expr r) {
		super(l, r);
	}

	public String toString() {
		return l + " := " + r;
	}

	@Override
	public TypeResult typecheck(TypeEnv E) throws TypeError {
		TypeResult tl = this.l.typecheck(E);
		TypeResult tr = this.r.typecheck(tl.s.compose(E));
		Substitution solution = tr.s.compose(tl.s);
		Type stl = solution.apply(tl.t);
		Type str = solution.apply(tr.t);
		Substitution s = stl.unify(new RefType(str)).compose(solution);
		return TypeResult.of(s, Type.UNIT);
	}

	@Override
	public Value eval(State s) throws RuntimeError {
		// left expression is a RefValue
		RefValue ref = (RefValue) this.l.eval(s);
		// right expression is a value
		Value v = this.r.eval(s);
		// bind v to location ref.p, the old value(if any) is replaced
		s.putValue(ref.p,v);
		return Value.UNIT;
	}

	@Override
	public Expr Substitude(Symbol s, Expr e) {
		return new Assign(this.l.Substitude(s, e), this.r.Substitude(s, e));
	}
}
