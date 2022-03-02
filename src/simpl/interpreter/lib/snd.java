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

public class snd extends FunValue {

	private static Expr SND(Symbol s) {
		final class Snd extends UnaryExpr {
			public Snd(Expr e) {
				super(e);
			}

			public String toString() {
				return "(snd " + e + ")";
			}

			@Override
			public TypeResult typecheck(TypeEnv E) throws TypeError {
				return null;
			}

			@Override
			public Value eval(State s) throws RuntimeError {
				PairValue p = (PairValue) this.e.eval(s);
				return p.v2;
			}

			@Override
			public Expr Substitude(Symbol s, Expr e) {
				return new Snd(this.e.Substitude(s, e));
			}
			
		}
		return new Snd(new Name(s));
	}

	public snd() {
		super(Env.empty, Symbol.symbol("x"), SND(Symbol.symbol("x")));
	}
}
