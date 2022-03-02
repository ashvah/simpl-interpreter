package simpl.parser.ast;

import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public abstract class Expr {

	static private boolean lazy_eval = false;
	static protected boolean is_thunk = false;
	static protected boolean is_rec = false;
	static protected boolean tail_opt = false;
	static protected boolean opt = false;

	public abstract TypeResult typecheck(TypeEnv E) throws TypeError;

	/**
	 * relies on side effect
	 * 
	 * @param s
	 * @return
	 * @throws RuntimeError
	 */

	static public void enableLazyEval(boolean flag) {
		Expr.lazy_eval = flag;
	}

	static public void enableopt(boolean flag) {
		Expr.opt = flag;
	}

	static public boolean isLazy() {
		return Expr.lazy_eval;
	}

	static public void setThunk(boolean flag) {
		Expr.is_thunk = flag;
	}

	static public boolean isThunk() {
		return Expr.is_thunk;
	}

	public abstract Value eval(State s) throws RuntimeError;

	public abstract Expr Substitude(Symbol s, Expr e);

	public abstract int isTail(Symbol x);
}
