package simpl.parser.ast;

import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Unit extends Expr {

	public String toString() {
		return "()";
	}

	@Override
	public TypeResult typecheck(TypeEnv E) throws TypeError {
		return TypeResult.of(Type.UNIT);
	}

	@Override
	public Value eval(State s) throws RuntimeError {
		return Value.UNIT;
	}

	@Override
	public Expr Substitude(Symbol s, Expr e) {
		return this;
	}

	@Override
	public int isTail(Symbol x) {
		return 0;
	}
}
