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

public class Record extends Expr {

	private Symbol x;
	private Expr l;
	private Expr r;

	public Record(Symbol x, Expr l, Expr r) {
		this.x = x;
		this.l = l;
		this.r = r;
	}

	public String toString() {
		return "{" + this.x + "=" + this.l + ", " + this.r + "}";
	}

	@Override
	public TypeResult typecheck(TypeEnv E) throws TypeError {
		TypeResult tl = this.l.typecheck(E);
		TypeResult tr = this.r.typecheck(tl.s.compose(E));
		Substitution solution = tr.s.compose(tl.s);
		Type stl = solution.apply(tl.t);
		Type str = solution.apply(tr.t);
		if (!(str instanceof RecordType))
			throw new TypeMismatchError();
		RecordType result = new RecordType(((RecordType) str));
		result.add(this.x, stl);
		return TypeResult.of(solution, result);
	}

	@Override
	public Value eval(State s) throws RuntimeError {
		Value lv = this.l.eval(s);
		Value rv = this.r.eval(s);
		return new RecordValue(this.x, lv, rv);
	}

	@Override
	public Expr Substitude(Symbol s, Expr e) {
		if (this.x == s)
			return this;
		return new Record(this.x, this.l.Substitude(s, e), this.r.Substitude(s, e));
	}

	@Override
	public int isTail(Symbol x) {
		int f1 = this.l.isTail(x);
		int f2 = this.r.isTail(x);
		if (f1 == 0 && f2 == 0)
			return 0;
		return 1;
	}
}
