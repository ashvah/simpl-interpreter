package simpl.typing;

import java.util.HashSet;

final class BoolType extends Type {

	protected BoolType() {
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
		// {bool = bool} => {}
		if (t instanceof BoolType) {
			return Substitution.IDENTITY;
		}
		throw new TypeMismatchError();
	}

	@Override
	public Type copy() {
		return Type.BOOL;
	}

	@Override
	public boolean contains(TypeVar tv) {
		return false;
	}

	@Override
	public Type replace(TypeVar a, Type t) {
		return Type.BOOL;
	}

	public String toString() {
		return "bool";
	}

	@Override
	public HashSet<TypeVar> getAllTypeVar() {
		return new HashSet<TypeVar>();
	}
}
