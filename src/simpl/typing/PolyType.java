package simpl.typing;

import java.util.HashSet;

public class PolyType extends Type {

	HashSet<TypeVar> polyList;
	Type t;

	// given a TypeEnv E and a type t, we need to construct a universal type
	// \forall t_i, t where t_i is the type variables in t but not in E
	// first get the type variables in E as a hashset typeOfE
	// get the type variables in t as a hashset typeVarOft
	// typeVarOft - typeOfE is exactly the set of t_i mentioned above
	public PolyType(Type t, TypeEnv E) {
		HashSet<TypeVar> typeOfE = E.getAllType();
		HashSet<TypeVar> typeVarOft = t.getAllTypeVar();
		typeVarOft.removeAll(typeOfE);
		this.polyList = typeVarOft;
		this.t = t;
	}

	// if E is empty, polylist is all type variables in t
	public PolyType(Type t) {
		this.polyList = t.getAllTypeVar();
		this.t = t;
	}

	// construct a substitution mapping t_i in polyList to a new typevariable
	// apply this substitution to type scheme t, return an instance of this poly-type
	public Type instantiate() {
		Substitution s = Substitution.IDENTITY;
		for (TypeVar t : this.polyList) {
			s = Substitution.of(t, new TypeVar(t.isEqualityType())).compose(s);
		}
		return s.apply(this.t);
	}

	@Override
	public boolean isEqualityType() {
		return false;
	}

	public String toString() {
		return "" + this.t;
	}

	@Override
	public Type replace(TypeVar a, Type t) {
		return this;
	}

	@Override
	public Type copy() {
		return this.t.copy();
	}

	@Override
	public boolean contains(TypeVar tv) {
		return this.t.contains(tv) && !this.polyList.contains(tv);
	}

	@Override
	public Substitution unify(Type t) throws TypeError {
		return Substitution.IDENTITY;
	}

	@Override
	public HashSet<TypeVar> getAllTypeVar() {
		return new HashSet<TypeVar>();
	}
}
