package simpl.typing;

import java.util.HashSet;

public abstract class Type {

	public abstract boolean isEqualityType();

	public abstract Type replace(TypeVar a, Type t);

	public abstract Type copy();

	public abstract HashSet<TypeVar> getAllTypeVar();

	public abstract boolean contains(TypeVar tv);

	public abstract Substitution unify(Type t) throws TypeError;

	public static final Type INT = new IntType();
	public static final Type BOOL = new BoolType();
	public static final Type UNIT = new UnitType();
}
