package simpl.typing;

import java.util.HashSet;

public class StreamType extends Type {

	public Type t;

	public StreamType(Type t1) {
		this.t = t1;
	}
	
	public String toString() {
		return t + " stream";
	}

	@Override
	public boolean isEqualityType() {
		return false;
	}

	@Override
	public Type replace(TypeVar a, Type t) {
		return new StreamType(this.t.replace(a, t));
	}

	@Override
	public Type copy() {
		return new StreamType(this.t.copy());
	}

	@Override
	public HashSet<TypeVar> getAllTypeVar() {
		HashSet<TypeVar> s = this.t.getAllTypeVar();
		return s;
	}

	@Override
	public boolean contains(TypeVar tv) {
		return this.t.contains(tv);
	}

	@Override
	public Substitution unify(Type t) throws TypeError {
		if (t instanceof TypeVar) {
			return t.unify(this);
		}
		if (t instanceof StreamType) {
			return ((StreamType) t).t.unify(this.t);
		}
		throw new TypeMismatchError();
	}

}
