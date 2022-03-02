package simpl.parser.ast;

import simpl.interpreter.RefValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.RefType;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Ref extends UnaryExpr {

	public Ref(Expr e) {
		super(e);
	}

	public String toString() {
		return "(ref " + e + ")";
	}

	@Override
	public TypeResult typecheck(TypeEnv E) throws TypeError {
		TypeResult t = this.e.typecheck(E);
		Type st = t.s.apply(t.t);
		return TypeResult.of(t.s, new RefType(st));
	}

	@Override
	public Value eval(State s) throws RuntimeError {
		Value v = this.e.eval(s);
		// allocate a new memory cell for value v
		int p = s.allocate();
		s.putValue(p, v);
		// return the address of v
		return new RefValue(p);
	}

	@Override
	public Expr Substitude(Symbol s, Expr e) {
		return new Ref(this.e.Substitude(s, e));
	}
}
