package simpl.parser.ast;

import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public abstract class EqExpr extends BinaryExpr {

	public EqExpr(Expr l, Expr r) {
		super(l, r);
	}

	@Override
	public TypeResult typecheck(TypeEnv E) throws TypeError {
		// TODO
		TypeResult tl = this.l.typecheck(E);
		TypeResult tr = this.r.typecheck(tl.s.compose(E));
		Substitution solution = tr.s.compose(tl.s);
		Type stl = solution.apply(tl.t);
		Type str = solution.apply(tr.t);
		if (!stl.isEqualityType() || !str.isEqualityType())
			throw new TypeError("No a equality type.");
		Substitution s = stl.unify(str).compose(solution);
		return TypeResult.of(s, Type.BOOL);
	}
}
