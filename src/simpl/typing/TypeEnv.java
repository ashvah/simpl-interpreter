package simpl.typing;

import java.util.HashSet;

import simpl.parser.Symbol;

public abstract class TypeEnv {

	public abstract Type get(Symbol x);

	public abstract HashSet<TypeVar> getAllType();

	public static TypeEnv of(final TypeEnv E, final Symbol x, final Type t) {
		return new TypeEnv() {
			public Type get(Symbol x1) {
				if (x == x1)
					return t;
				return E.get(x1);
			}

			public String toString() {
				return x + ":" + t + ";" + E;
			}

			@Override
			public HashSet<TypeVar> getAllType() {
				HashSet<TypeVar> s = E.getAllType();
				s.addAll(t.getAllTypeVar());
				return s;
			}
		};
	}

	public static final TypeEnv empty = new TypeEnv() {
		@Override
		public Type get(Symbol x) {
			return null;
		}

		@Override
		public HashSet<TypeVar> getAllType() {
			return new HashSet<TypeVar>();
		}
	};

}
