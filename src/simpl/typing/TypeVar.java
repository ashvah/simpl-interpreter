package simpl.typing;

import java.util.HashSet;

import simpl.parser.Symbol;

public class TypeVar extends Type {

	private static int tvcnt = 0;

	private boolean equalityType;
	private Symbol name;
	private String nameOfSymbol;

	public TypeVar(boolean equalityType) {
		this.equalityType = equalityType;
		this.nameOfSymbol = "tv" + ++tvcnt;
		this.name = Symbol.symbol(this.nameOfSymbol);
	}

	public TypeVar(TypeVar t) {
		this.equalityType = t.equalityType;
		this.nameOfSymbol = t.nameOfSymbol;
		this.name = Symbol.symbol(this.nameOfSymbol);
	}

	@Override
	public boolean isEqualityType() {
		return this.equalityType;
	}

	@Override
	public Type copy() {
		return new TypeVar(this);
	}

	@Override
	public Substitution unify(Type t) throws TypeCircularityError {
		// if t is a type variable and this = t
		// return substitution I
		if (t instanceof TypeVar) {
			if (((TypeVar) t).name == this.name)
				return Substitution.IDENTITY;
		}
		// if t is another type schem
		// check whether this is in FV(t)
		// if true throw a type error
		// if false return substitution this->t
		if (t.contains(this))
			throw new TypeCircularityError();
		this.equalityType = t.isEqualityType();
		return Substitution.of(this, t);
	}

	public String toString() {
		return "" + this.nameOfSymbol;
	}

	@Override
	public boolean contains(TypeVar tv) {
		// if this.name = tv.name
		// then it's a free occurrence of tv, return true
		// otherwise return false
		return this.name.equals(tv.name);
	}

	@Override
	public Type replace(TypeVar a, Type t) {
		// if this.name=a.name, replace it
		// otherwise do nothing
		if (this.name.equals(a.name))
			return t;
		return this;
	}

	@Override
	public HashSet<TypeVar> getAllTypeVar() {
		HashSet<TypeVar> s = new HashSet<TypeVar>();
		s.add(this);
		return s;
	}

}
