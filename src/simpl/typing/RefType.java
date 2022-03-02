package simpl.typing;

import java.util.HashSet;

public final class RefType extends Type {

	public Type t;

	public RefType(Type t) {
		this.t = t;
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
		// {s1 ref = s2 ref} => {s1=s2}
		if (t instanceof RefType) {
			return ((RefType) t).t.unify(this.t);
		}
		throw new TypeMismatchError();
	}

	@Override
	public Type copy() {
		return new RefType(this.t.copy());
	}

	@Override
	public boolean contains(TypeVar tv) {
		return this.t.contains(tv);
	}

	@Override
	public Type replace(TypeVar a, Type t) {
		return new RefType(this.t.replace(a, t));
	}

	public String toString() {
		return t + " ref";
	}

	@Override
	public HashSet<TypeVar> getAllTypeVar() {
		return this.t.getAllTypeVar();
	}
}
