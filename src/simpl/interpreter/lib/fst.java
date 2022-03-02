package simpl.interpreter.lib;

import simpl.interpreter.Env;
import simpl.interpreter.FunValue;
import simpl.interpreter.PairValue;
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

public class fst extends FunValue {

	private static Expr FST(Symbol s) {
		final class Fst extends UnaryExpr {
			public Fst(Expr e) {
				super(e);
			}

			public String toString() {
				return "(fst " + e + ")";
			}

			@Override
			public TypeResult typecheck(TypeEnv E) throws TypeError {
				// the type of fst is given in the DefaultTypeEnv
				// we never call this function
				return null;
			}

			@Override
			public Value eval(State s) throws RuntimeError {
				// return the first value
				PairValue p = (PairValue) this.e.eval(s);
				return p.v1;
			}
			
			@Override
			public Expr Substitude(Symbol s, Expr e) {
				return new Fst(this.e.Substitude(s, e));
			}
			
		}
		return new Fst(new Name(s));
	}

	public fst() {
		// return a closure
		super(Env.empty, Symbol.symbol("x"), FST(Symbol.symbol("x")));
	}
}
