package simpl.interpreter.lib;

import simpl.interpreter.Env;
import simpl.interpreter.FunValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.StreamValue;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.parser.ast.Expr;
import simpl.parser.ast.Name;
import simpl.parser.ast.UnaryExpr;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class car extends FunValue {

	private static Expr CAR(Symbol s) {
		final class CAR extends UnaryExpr {
			public CAR(Expr e) {
				super(e);
			}

			public String toString() {
				return "(car " + e + ")";
			}

			@Override
			public TypeResult typecheck(TypeEnv E) throws TypeError {
				return null;
			}

			@Override
			public Value eval(State s) throws RuntimeError {
				StreamValue p = (StreamValue) this.e.eval(s);
				return p.v1;
			}

			@Override
			public Expr Substitude(Symbol s, Expr e) {
				return new CAR(this.e.Substitude(s, e));
			}
		}
		return new CAR(new Name(s));
	}

	public car() {
		// return a closure
		super(Env.empty, Symbol.symbol("x"), CAR(Symbol.symbol("x")));
	}
}
