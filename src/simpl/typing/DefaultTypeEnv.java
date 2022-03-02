package simpl.typing;

import java.util.HashSet;

import simpl.parser.Symbol;

public class DefaultTypeEnv extends TypeEnv {

	private TypeEnv E;

	public DefaultTypeEnv() {
		// Add the corresponding polymorphism type scheme of predefined function values
		TypeVar v1 = new TypeVar(true);
		TypeVar v2 = new TypeVar(true);
		this.E = TypeEnv.of(TypeEnv.empty, Symbol.symbol("fst"), new PolyType(new ArrowType(new PairType(v1, v2), v1)));

		TypeVar v3 = new TypeVar(true);
		TypeVar v4 = new TypeVar(true);
		this.E = TypeEnv.of(this.E, Symbol.symbol("snd"), new PolyType(new ArrowType(new PairType(v3, v4), v4)));

		TypeVar v5 = new TypeVar(true);
		this.E = TypeEnv.of(this.E, Symbol.symbol("hd"), new PolyType(new ArrowType(new ListType(v5), v5)));

		TypeVar v6 = new TypeVar(true);
		this.E = TypeEnv.of(this.E, Symbol.symbol("tl"),
				new PolyType(new ArrowType(new ListType(v6), new ListType(v6))));

		this.E = TypeEnv.of(this.E, Symbol.symbol("iszero"), new ArrowType(Type.INT, Type.BOOL));
		this.E = TypeEnv.of(this.E, Symbol.symbol("pred"), new ArrowType(Type.INT, Type.INT));
		this.E = TypeEnv.of(this.E, Symbol.symbol("succ"), new ArrowType(Type.INT, Type.INT));

		TypeVar v7 = new TypeVar(true);
		this.E = TypeEnv.of(this.E, Symbol.symbol("car"), new PolyType(new ArrowType(new StreamType(v7), v7)));

		TypeVar v8 = new TypeVar(true);
		this.E = TypeEnv.of(this.E, Symbol.symbol("cdr"),
				new PolyType(new ArrowType(new StreamType(v8), new StreamType(v8))));

		this.E = TypeEnv.of(this.E, Symbol.symbol("recordnil"), new RecordType());

		TypeVar v9 = new TypeVar(true);
		this.E = TypeEnv.of(this.E, Symbol.symbol("display"), new PolyType(new ArrowType(v9, Type.UNIT)));
		this.E = TypeEnv.of(this.E, Symbol.symbol("newline"), Type.UNIT);
	}

	@Override
	public Type get(Symbol x) {
		return this.E.get(x);
	}

	@Override
	public HashSet<TypeVar> getAllType() {
		return this.E.getAllType();
	}

}
