package simpl.parser.ast;

import simpl.interpreter.RecValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.ThunkValue;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.PolyType;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Name extends Expr {

	public Symbol x;

	public Name(Symbol x) {
		this.x = x;
	}

	public String toString() {
		return "" + x;
	}

	@Override
	public TypeResult typecheck(TypeEnv E) throws TypeError {
		Type t = E.get(x);
		// if get nothing, error occur
		if (t == null)
			throw new TypeError("Can't type " + x);
		// if it's a universal polymorphism type scheme,
		// we need to instantiate it
		if (t instanceof PolyType)
			return TypeResult.of(((PolyType) t).instantiate());
		return TypeResult.of(t);
	}

	@Override
	public Value eval(State s) throws RuntimeError {
		if (this.x == Symbol.symbol("newline")) {
			System.out.println("");
			return Value.UNIT;
		}
		Value v = s.E.get(x);
		// if cannot find the value of x, raise a runtime error
		if (v == null)
			throw new RuntimeError("Variable not found");
		// if E(x)= rec E1 x1 e1
		// evaluate rec x1=>e1 in the environment E1
		if (v instanceof RecValue) {
			Rec newExpr = new Rec(((RecValue) v).x, ((RecValue) v).e);
			return newExpr.eval(State.of(((RecValue) v).E, s.getMem(), s.p));
		}
		if (v instanceof ThunkValue && Expr.isLazy()) {
			return ((ThunkValue) v).forceEval(s);
		}
		return v;
	}

	@Override
	public Expr Substitude(Symbol s, Expr e) {
		if (this.x == s)
			return e;
		return this;
	}

	@Override
	public int isTail(Symbol x) {
		if (this.x == x)
			return 2;
		return 0;
	}
}
