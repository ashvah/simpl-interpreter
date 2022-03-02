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

public class hd extends FunValue {

	private static Expr HD(Symbol s) {
		final class Hd extends UnaryExpr {
			public Hd(Expr e) {
				super(e);
			}

			public String toString() {
				return "(hd " + e + ")";
			}

			@Override
			public TypeResult typecheck(TypeEnv E) throws TypeError {
				// the type of hd is given in the DefaultTypeEnv
				// we never call this function
				return null;
			}

			@Override
			public Value eval(State s) throws RuntimeError {
				// if e is a nil raise a runtime error
				// else return the head of list
				Value p = this.e.eval(s);
				if (p.equals(Value.NIL))
					throw new RuntimeError("Hd nil");
				return ((ConsValue) p).v1;
			}

			@Override
			public Expr Substitude(Symbol s, Expr e) {
				// this is for let-polymorphism
				// but it's out of time now
				// I use another algorithm to implement let-polymorphism
				// this function will never be called
				return new Hd(this.e.Substitude(s, e));
			}
			
		}
		return new Hd(new Name(s));
	}

	public hd() {
		// return a closure
		super(Env.empty, Symbol.symbol("x"), HD(Symbol.symbol("x")));
	}
}
