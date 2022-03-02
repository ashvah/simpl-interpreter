package simpl.parser.ast;

import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public abstract class RelExpr extends BinaryExpr {

	public RelExpr(Expr l, Expr r) {
		super(l, r);
	}

	@Override
	public TypeResult typecheck(TypeEnv E) throws TypeError {
		TypeResult tl = this.l.typecheck(E);
		TypeResult tr = this.r.typecheck(tl.s.compose(E));
		Substitution solution = tr.s.compose(tl.s);
		Type stl = solution.apply(tl.t);
		Type str = solution.apply(tr.t);
		Substitution s = str.unify(Type.INT).compose(stl.unify(Type.INT)).compose(solution);
		return TypeResult.of(s, Type.BOOL);
	}
}
