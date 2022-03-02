package simpl.parser.ast;

import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public abstract class ArithExpr extends BinaryExpr {

	public ArithExpr(Expr l, Expr r) {
		super(l, r);
	}

	@Override
	public TypeResult typecheck(TypeEnv E) throws TypeError {
		// check the left expression
		TypeResult tl = this.l.typecheck(E);
		// check the right expression
		TypeResult tr = this.r.typecheck(tl.s.compose(E));
		// compose two substitutions
		Substitution solution = tr.s.compose(tl.s);
		// substitute two type schemes of the sub-expressions
		Type stl = solution.apply(tl.t);
		Type str = solution.apply(tr.t);
		// compose the unification result with the solution before
		Substitution s = str.unify(Type.INT).compose(stl.unify(Type.INT)).compose(solution);
		return TypeResult.of(s, Type.INT);
	}
}
