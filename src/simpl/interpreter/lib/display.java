package simpl.interpreter.lib;

import simpl.interpreter.ConsValue;
import simpl.interpreter.Env;
import simpl.interpreter.FunValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.parser.ast.Expr;
import simpl.parser.ast.Name;
import simpl.parser.ast.UnaryExpr;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class display extends FunValue {

	private static Expr DISPLAY(Symbol s) {
		final class DISPLAY extends UnaryExpr {
			public DISPLAY(Expr e) {
				super(e);
			}

			public String toString() {
				return "(display " + e + ")";
			}

			@Override
			public TypeResult typecheck(TypeEnv E) throws TypeError {
				return null;
			}

			@Override
			public Value eval(State s) throws RuntimeError {
				Value v = this.e.eval(s);
				if (v instanceof ConsValue)
					System.out.print("(" + ((ConsValue) v).getValue());
				else
					System.out.print(v);
				return Value.UNIT;
			}

			@Override
			public Expr Substitude(Symbol s, Expr e) {
				return new DISPLAY(this.e.Substitude(s, e));
			}
			
		}
		return new DISPLAY(new Name(s));
	}

	public display() {
		// return a closure
		super(Env.empty, Symbol.symbol("x"), DISPLAY(Symbol.symbol("x")));
	}
}
