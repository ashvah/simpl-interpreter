package simpl.parser.ast;

import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.StreamValue;
import simpl.interpreter.ThunkValue;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.StreamType;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Stream extends BinaryExpr {

	public Stream(Expr l, Expr r) {
		super(l, r);
	}

	public String toString() {
		return "(stream, " + l + ", " + r + ")";
	}

	@Override
	public TypeResult typecheck(TypeEnv E) throws TypeError {
		TypeResult tl = this.l.typecheck(E);
		TypeResult tr = this.r.typecheck(tl.s.compose(E));
		Substitution solution = tr.s.compose(tl.s);
		Type stl = solution.apply(tl.t);
		Type str = solution.apply(tr.t);
		Substitution s = str.unify(new StreamType(stl)).compose(solution);
		return TypeResult.of(s, new StreamType(stl));
	}

	@Override
	public Value eval(State s) throws RuntimeError {
		Value lv = this.l.eval(s);
		return new StreamValue(lv, new ThunkValue(s.E, this.r));
	}

	@Override
	public Expr Substitude(Symbol s, Expr e) {
		return new Stream(this.l.Substitude(s, e), this.r.Substitude(s, e));
	}

}
