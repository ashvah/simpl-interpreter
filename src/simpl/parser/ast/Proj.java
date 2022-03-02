package simpl.parser.ast;

import simpl.interpreter.RecordValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.RecordType;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeMismatchError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public class Proj extends Expr {

	private Symbol x;
	private Expr e;

	public Proj(Symbol x, Expr e) {
		this.x = x;
		this.e = e;
	}

	public String toString() {
		return "" + this.e + "." + this.x;
	}

	@Override
	public TypeResult typecheck(TypeEnv E) throws TypeError {
		TypeResult t = this.e.typecheck(E);
		Type st = t.s.apply(t.t);
		if (st instanceof RecordType) {
			Type a = ((RecordType) st).get(this.x);
			if (a == null) {
				if (!Expr.is_rec)
					throw new TypeMismatchError();
				TypeVar b = new TypeVar(true);
				((RecordType) st).add(x, b);
				return TypeResult.of(t.s, b);
			}
			return TypeResult.of(t.s, a);
		}
		if (!Expr.is_rec)
			throw new TypeMismatchError();
		TypeVar s = new TypeVar(true);
		RecordType rt = new RecordType();
		rt.add(this.x, s);
		Substitution solution = st.unify(rt).compose(t.s);
		return TypeResult.of(solution, s);
	}

	@Override
	public Value eval(State s) throws RuntimeError {
		Value lv = this.e.eval(s);
		return ((RecordValue) lv).proj(this.x);
	}

	@Override
	public Expr Substitude(Symbol s, Expr e) {
		return new Proj(this.x, this.e.Substitude(s, e));
	}

	@Override
	public int isTail(Symbol x) {
		int f = this.e.isTail(x);
		if (f == 0)
			return 0;
		return 1;
	}
}
