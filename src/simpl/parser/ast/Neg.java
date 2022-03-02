package simpl.parser.ast;

import simpl.interpreter.IntValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Neg extends UnaryExpr {

	public Neg(Expr e) {
		super(e);
	}

	public String toString() {
		return "~" + e;
	}

	@Override
	public TypeResult typecheck(TypeEnv E) throws TypeError {
		TypeResult t = this.e.typecheck(E);
		Type st = t.s.apply(t.t);
		Substitution s = st.unify(Type.INT).compose(t.s);
		return TypeResult.of(s, Type.INT);
	}

	@Override
	public Value eval(State s) throws RuntimeError {
		IntValue lv = (IntValue) this.e.eval(s);
		return new IntValue(-lv.n);
	}

	@Override
	public Expr Substitude(Symbol s, Expr e) {
		return new Neg(this.e.Substitude(s, e));
	}
}
