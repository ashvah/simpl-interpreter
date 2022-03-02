package simpl.interpreter.lib;

import simpl.interpreter.Env;
import simpl.interpreter.FunValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.StreamValue;
import simpl.interpreter.ThunkValue;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.parser.ast.Expr;
import simpl.parser.ast.Name;
import simpl.parser.ast.UnaryExpr;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class cdr extends FunValue {

	private static Expr CDR(Symbol s) {
		final class CDR extends UnaryExpr {
			public CDR(Expr e) {
				super(e);
			}

			public String toString() {
				return "(cdr " + e + ")";
			}

			@Override
			public TypeResult typecheck(TypeEnv E) throws TypeError {
				return null;
			}

			@Override
			public Value eval(State s) throws RuntimeError {
				StreamValue p = (StreamValue) this.e.eval(s);
				Value v = p.v2;
				if (v instanceof ThunkValue)
					return ((ThunkValue) v).forceEval(s);
				throw new RuntimeError("Not a thunk");
			}

			@Override
			public Expr Substitude(Symbol s, Expr e) {
				return new CDR(this.e.Substitude(s, e));
			}
			
		}
		return new CDR(new Name(s));
	}

	public cdr() {
		// return a closure
		super(Env.empty, Symbol.symbol("x"), CDR(Symbol.symbol("x")));
	}
}
