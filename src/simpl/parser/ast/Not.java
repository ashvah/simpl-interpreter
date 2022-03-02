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

public class Not extends UnaryExpr {

	public Not(Expr e) {
		super(e);
	}

	public String toString() {
		return "(not " + e + ")";
	}

	@Override
	public TypeResult typecheck(TypeEnv E) throws TypeError {
		TypeResult t = this.e.typecheck(E);
		Type st = t.s.apply(t.t);
		Substitution s = st.unify(Type.BOOL).compose(t.s);
		return TypeResult.of(s, Type.BOOL);
	}

	@Override
	public Value eval(State s) throws RuntimeError {
		BoolValue p = (BoolValue) this.e.eval(s);
		if (p.equals(Value.TRUE))
			return Value.FALSE;
		return Value.TRUE;
	}

	@Override
	public Expr Substitude(Symbol s, Expr e) {
		return new Not(this.e.Substitude(s, e));
	}
}
