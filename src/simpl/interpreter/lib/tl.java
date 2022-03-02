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

public class tl extends FunValue {

	private static Expr TL(Symbol s) {
		final class Tl extends UnaryExpr {
			public Tl(Expr e) {
				super(e);
			}

			public String toString() {
				return "(tl " + e + ")";
			}

			@Override
			public TypeResult typecheck(TypeEnv E) throws TypeError {
				// TODO
				return null;
			}

			@Override
			public Value eval(State s) throws RuntimeError {
				// TODO
				Value p = this.e.eval(s);
				if (p.equals(Value.NIL))
					throw new RuntimeError("Tl nil");
				return ((ConsValue) p).v2;
			}

			@Override
			public Expr Substitude(Symbol s, Expr e) {
				return new Tl(this.e.Substitude(s, e));
			}
		}
		return new Tl(new Name(s));
	}

	public tl() {
		// TODO
		super(Env.empty, Symbol.symbol("x"), TL(Symbol.symbol("x")));
	}
}
