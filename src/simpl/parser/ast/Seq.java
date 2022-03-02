package simpl.parser.ast;

import simpl.interpreter.Env;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Seq extends BinaryExpr {

	public Seq(Expr l, Expr r) {
		super(l, r);
	}

	public String toString() {
		return "(" + l + " ; " + r + ")";
	}

	@Override
	public TypeResult typecheck(TypeEnv E) throws TypeError {
		TypeResult tl, tr;
		Expr e;
		if (this.l instanceof Group && ((Group) this.l).is_define()) {
			e = ((Group) this.l).get_define();
		} else
			e = this.l;
		if (e instanceof Define) {
			tl = ((Define) e).typecheck_define(E);
			tr = this.r.typecheck(TypeEnv.of(tl.s.compose(E), ((Define) e).x, tl.t));
		} else {
			tl = this.l.typecheck(E);
			tr = this.r.typecheck(tl.s.compose(E));
		}

		Substitution solution = tr.s.compose(tl.s);
		Type stl = solution.apply(tl.t);
		Type str = solution.apply(tr.t);
		if (e instanceof Define)
			return TypeResult.of(solution, str);
		Substitution s = stl.unify(Type.UNIT).compose(solution);
		return TypeResult.of(s, str);
	}

	@Override
	public Value eval(State s) throws RuntimeError {

		if (Expr.opt) {
			Expr e, l0 = this.l, r0 = this.r;
			State s0 = s;
			while (true) {
				if (l0 instanceof Group && ((Group) l0).is_define())
					e = ((Group) l0).get_define();
				else
					e = l0;

				if (e instanceof Define) {
					Value v = ((Define) e).eval_define(s0);
					s0 = State.of(new Env(s0.E, ((Define) e).x, v), s0.getMem(), s0.p);
				} else {
					l0.eval(s0);
				}
				while (r0 instanceof Group)
					r0 = ((Group) r0).e;
				if (r0 instanceof Seq) {
					l0 = ((Seq) r0).l;
					r0 = ((Seq) r0).r;
				} else
					break;
			}
			while (r0 instanceof Loop)
				return Value.SIGNAL;
			return r0.eval(s0);
		}

		Expr e;
		if (this.l instanceof Group && ((Group) this.l).is_define())
			e = ((Group) this.l).get_define();
		else
			e = this.l;

		if (e instanceof Define) {
			Value v = ((Define) e).eval_define(s);
			return this.r.eval(State.of(new Env(s.E, ((Define) e).x, v), s.getMem(), s.p));
		}
		this.l.eval(s);
		return this.r.eval(s);
	}

	@Override
	public Expr Substitude(Symbol s, Expr e) {
		return new Seq(this.l.Substitude(s, e), this.r.Substitude(s, e));
	}

	@Override
	public int isTail(Symbol x) {
		int f1 = this.l.isTail(x);
		int f2 = this.r.isTail(x);
		if (f1 == 0)
			return f2;
		return 1;
	}
}
