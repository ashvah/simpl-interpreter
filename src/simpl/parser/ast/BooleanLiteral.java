package simpl.parser.ast;

import simpl.interpreter.BoolValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class BooleanLiteral extends Expr {

	public boolean b;

	public BooleanLiteral(boolean b) {
		this.b = b;
	}

	public String toString() {
		return "" + b;
	}

	@Override
	public TypeResult typecheck(TypeEnv E) throws TypeError {
		return TypeResult.of(Type.BOOL);
	}

	@Override
	public Value eval(State s) throws RuntimeError {
		return new BoolValue(b);
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
