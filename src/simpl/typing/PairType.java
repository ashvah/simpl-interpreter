package simpl.typing;

import java.util.HashSet;

public final class PairType extends Type {

	public Type t1, t2;

	public PairType(Type t1, Type t2) {
		this.t1 = t1;
		this.t2 = t2;
	}

	@Override
	public boolean isEqualityType() {
		return true;
	}

	@Override
	public Substitution unify(Type t) throws TypeError {
		// if t is a type variable
		// exactly {s=a} is the same as {a=s}
		if (t instanceof TypeVar) {
			return t.unify(this);
		}
		// { s11 * s12 = s21 * s22 } => {s11=s21, s12=s22}
		if (t instanceof PairType) {
			return ((PairType) t).t1.unify(this.t1).compose(((PairType) t).t2.unify(this.t2));
		}
		throw new TypeMismatchError();
	}

	@Override
	public Type copy() {
		return new PairType(this.t1.copy(), this.t2.copy());
	}

	@Override
	public boolean contains(TypeVar tv) {
		return this.t1.contains(tv) || this.t2.contains(tv);
	}

	@Override
	public Type replace(TypeVar a, Type t) {
		return new PairType(this.t1.replace(a, t), this.t2.replace(a, t));
	}

	public String toString() {
		return "(" + t1 + " * " + t2 + ")";
	}

	@Override
	public HashSet<TypeVar> getAllTypeVar() {
		HashSet<TypeVar> s = this.t1.getAllTypeVar();
		s.addAll(this.t2.getAllTypeVar());
		return s;
	}
}
