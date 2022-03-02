package simpl.parser.ast;

import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.ThunkValue;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.ArrowType;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Delay extends UnaryExpr {

	public Delay(Expr e) {
		super(e);
	}

	public String toString() {
		return "(delay " + e + ")";
	}

	@Override
	public TypeResult typecheck(TypeEnv E) throws TypeError {
		TypeResult t = this.e.typecheck(E);
		Type st = t.s.apply(t.t);
		Type result = new ArrowType(Type.UNIT, st);
		return TypeResult.of(t.s, result);
	}

	@Override
	public Value eval(State s) throws RuntimeError {
		return new ThunkValue(s.E, this.e);
	}

	@Override
	public Expr Substitude(Symbol s, Expr e) {
		return new Delay(this.e.Substitude(s, e));
	}

}
