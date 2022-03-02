package simpl.typing;

import java.util.HashSet;

public final class ListType extends Type {

	public Type t;

	public ListType(Type t) {
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
		// {s1 list=s2 list} => {s1=s2}
		if (t instanceof ListType) {
			return ((ListType) t).t.unify(this.t);
		}
		throw new TypeMismatchError();
	}

	@Override
	public Type copy() {
		return new ListType(this.t.copy());
	}

	@Override
	public boolean contains(TypeVar tv) {
		return this.t.contains(tv);
	}

	@Override
	public Type replace(TypeVar a, Type t) {
		return new ListType(this.t.replace(a, t));
	}

	public String toString() {
		return t + " list";
	}

	@Override
	public HashSet<TypeVar> getAllTypeVar() {
		return this.t.getAllTypeVar();
	}
}
