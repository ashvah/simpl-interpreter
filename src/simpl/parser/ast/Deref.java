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
import simpl.typing.TypeVar;

public class Deref extends UnaryExpr {

	public Deref(Expr e) {
		super(e);
	}

	public String toString() {
		return "!" + e;
	}

	@Override
	public TypeResult typecheck(TypeEnv E) throws TypeError {
		TypeResult t = this.e.typecheck(E);
		Type st = t.s.apply(t.t);
		TypeVar a = new TypeVar(true);
		Substitution s = st.unify(new RefType(a)).compose(t.s);
		return TypeResult.of(s, a);
	}

	@Override
	public Value eval(State s) throws RuntimeError {
		// get the contents of pointer p
		RefValue ref = (RefValue) this.e.eval(s);
		Value v = s.getValue(ref.p);
		if (v == null)
			throw new RuntimeError("out of pointer");
		return v;
	}

	@Override
	public Expr Substitude(Symbol s, Expr e) {
		return new Deref(this.e.Substitude(s, e));
	}
}
