package simpl.parser.ast;

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

public class Force extends UnaryExpr {

	public Force(Expr e) {
		super(e);
	}

	public String toString() {
		return "(force " + e + ")";
	}

	@Override
	public TypeResult typecheck(TypeEnv E) throws TypeError {
		TypeResult t = this.e.typecheck(E);
		Type st = t.s.apply(t.t);
		TypeVar a = new TypeVar(true);
		Substitution solution = st.unify(new ArrowType(Type.UNIT, a)).compose(t.s);
		return TypeResult.of(solution, a);
	}

	@Override
	public Value eval(State s) throws RuntimeError {
		Value v = this.e.eval(s);
		if (v instanceof ThunkValue)
			return ((ThunkValue) v).forceEval(s);
		throw new RuntimeError("Not a thunk");
	}

	@Override
	public Expr Substitude(Symbol s, Expr e) {
		return new Force(this.e.Substitude(s, e));
	}

}
