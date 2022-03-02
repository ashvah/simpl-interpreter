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

public class AndAlso extends BinaryExpr {

	public AndAlso(Expr l, Expr r) {
		super(l, r);
	}

	public String toString() {
		return "(" + l + " andalso " + r + ")";
	}

	@Override
	public TypeResult typecheck(TypeEnv E) throws TypeError {
		TypeResult tl = this.l.typecheck(E);
		TypeResult tr = this.r.typecheck(tl.s.compose(E));
		Substitution solution = tr.s.compose(tl.s);
		Type stl = solution.apply(tl.t);
		Type str = solution.apply(tr.t);
		Substitution s = str.unify(Type.BOOL).compose(stl.unify(Type.BOOL)).compose(solution);
		return TypeResult.of(s, Type.BOOL);
	}

	@Override
	public Value eval(State s) throws RuntimeError {
		BoolValue lv = (BoolValue) this.l.eval(s);
		if (lv.equals(Value.FALSE))
			return Value.FALSE;
		BoolValue rv = (BoolValue) this.r.eval(s);
		return rv;
	}
	
	@Override
	public Expr Substitude(Symbol s, Expr e) {
		return new AndAlso(this.l.Substitude(s, e), this.r.Substitude(s, e));
	}
}
