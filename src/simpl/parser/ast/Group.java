package simpl.parser.ast;

import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Group extends UnaryExpr {

	public Group(Expr e) {
		super(e);
	}

	public String toString() {
		return "" + e;
	}

	@Override
	public TypeResult typecheck(TypeEnv E) throws TypeError {
		return e.typecheck(E);
	}

	@Override
	public Value eval(State s) throws RuntimeError {
		return e.eval(s);
	}

	public boolean is_define() {
		if (this.e instanceof Define)
			return true;
		if (this.e instanceof Group)
			return ((Group) this.e).is_define();
		return false;
	}

	public Expr get_define() {
		if (this.e instanceof Define)
			return this.e;
		if (this.e instanceof Group)
			return ((Group) this.e).get_define();
		return this;
	}

	@Override
	public Expr Substitude(Symbol s, Expr e) {
		return new Group(this.e.Substitude(s, e));
	}
	
	@Override
	public int isTail(Symbol x) {
		return this.e.isTail(x);
	}
}
