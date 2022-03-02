package simpl.typing;

import java.util.HashMap;
import java.util.HashSet;

import simpl.parser.Symbol;

public class RecordType extends Type {

	private HashMap<Symbol, Type> label = new HashMap<Symbol, Type>();

	public RecordType(RecordType rt) {
		this.label.putAll(rt.label);
	}

	public RecordType() {
	}

	public void add(Symbol x, Type t1) {
		this.label.put(x, t1);
	}

	@Override
	public boolean isEqualityType() {
		return false;
	}

	public Type get(Symbol x) {
		return this.label.get(x);
	}

	@Override
	public Type replace(TypeVar a, Type t) {
		RecordType t1 = this.copy();
		for (Symbol s : t1.label.keySet()) {
			Type v = t1.label.get(s);
			t1.label.put(s, v.replace(a, t));
		}
		return t1;
	}

	@Override
	public RecordType copy() {
		return new RecordType(this);
	}

	@Override
	public HashSet<TypeVar> getAllTypeVar() {
		HashSet<TypeVar> list = new HashSet<TypeVar>();
		for (Symbol s : this.label.keySet()) {
			Type t = this.label.get(s);
			list.addAll(t.getAllTypeVar());
		}
		return list;
	}

	public String toString() {
		if (this.label == null)
			return "{recordnil}";
		String result = "{";
		for (Symbol s : this.label.keySet()) {
			Type t = this.label.get(s);
			result = result + s + ":" + t + ", ";
		}
		return result + "recordnil}";
	}

	@Override
	public boolean contains(TypeVar tv) {
		for (Symbol s : this.label.keySet()) {
			Type t = this.label.get(s);
			if (t.contains(tv))
				return true;
		}
		return false;
	}

	@Override
	public Substitution unify(Type t) throws TypeError {
		if (t instanceof TypeVar) {
			return t.unify(this);
		}

		Substitution solution = Substitution.IDENTITY;
		if (t instanceof RecordType) {
			for (Symbol s : this.label.keySet()) {
				Type a = ((RecordType) t).label.get(s);
				if (a != null)
					solution = a.unify(this.label.get(s)).compose(solution);
			}
		}
		return solution;
	}
}
