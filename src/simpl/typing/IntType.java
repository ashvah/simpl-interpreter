package simpl.typing;

import java.util.HashSet;

final class IntType extends Type {

	protected IntType() {
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
		// {int=int} => {}
		if (t instanceof IntType) {
			return Substitution.IDENTITY;
		}
		throw new TypeMismatchError();
	}

	@Override
	public Type copy() {
		return Type.INT;
	}

	@Override
	public boolean contains(TypeVar tv) {
		return false;
	}

	@Override
	public Type replace(TypeVar a, Type t) {
		return Type.INT;
	}

	public String toString() {
		return "int";
	}
	
	@Override
	public HashSet<TypeVar> getAllTypeVar() {
		return new HashSet<TypeVar>();
	}
}
