package test;

import simpl.interpreter.*;
import simpl.parser.Symbol;
import simpl.typing.ArrowType;
import simpl.typing.DefaultTypeEnv;
import simpl.typing.PolyType;
import simpl.typing.Type;
import simpl.typing.*;

public class EnvTest {

	private final static InitialState s = new InitialState();
	private final static DefaultTypeEnv t = new DefaultTypeEnv();

	private static void PrintState(State s) {
		System.out.print("     Environment: ");
		System.out.println(s.E);
		System.out.print("     Memory: ");
		System.out.println(s.M);
		System.out.print("     Pointer: ");
		System.out.println(s.p);
	}

	private static void CheckInitialize() {
		System.out.println("\n  CheckInitialState:");
		PrintState(s);
	}

	private static void Checkfun(String name) {
		Value v = s.E.get(Symbol.symbol(name));
		System.out.print("       " + name);
		if (v == null)
			System.out.println("\t✘");
		else
			System.out.println("\t✔");
	}

	private static void CheckFun() {
		System.out.println("\n  CheckFun:");
		Checkfun("hd");
		Checkfun("tl");
		Checkfun("fst");
		Checkfun("snd");
		Checkfun("iszero");
		Checkfun("pred");
		Checkfun("succ");
	}

	private static void CheckEnv() {
		System.out.println("\n  CheckEnv:");
		Env E1 = s.E.clone();
		Env E2 = s.E;
		System.out.print("     Clone E1!=E");
		if (E1 == s.E)
			System.out.println("\t✘");
		else
			System.out.println("\t✔");
		System.out.print("     WithoutClone E2==E");
		if (E2 == s.E)
			System.out.println("\t✔");
		else
			System.out.println("\t✘");
	}

	private static void ChangeState(State s) {
		s.putValue(1, Value.NIL);
		s.p.set(s.p.get() + 1);
	}

	private static void CheckStateChange() {
		System.out.println("\n  CheckState:");
		System.out.println("   Before changes:");
		PrintState(s);
		ChangeState(s);
		System.out.println("   After change1:");
		PrintState(s);
		State news = State.of(Env.empty, s.getMem(), s.p);
		ChangeState(news);
		System.out.println("   After change2:");
		PrintState(s);
	}

	private static void CheckTypeVar() {
		System.out.println("\n  CheckType:");
		for (Type t : t.getAllType())
			System.out.println("    " + t);
	}

	private static void CheckPolyType() {
		System.out.println("\n  CheckPolyType:");
		PolyType pt = new PolyType(new ArrowType(new PairType(Type.INT, new TypeVar(true)), new TypeVar(true)));
		System.out.println("    " + pt);
		System.out.println("    " + pt.instantiate());
		System.out.println("    " + pt.instantiate());
		System.out.println("    " + ((PolyType) t.get(Symbol.symbol("hd"))).instantiate());
		System.out.println("    " + ((PolyType) t.get(Symbol.symbol("hd"))).instantiate());
		System.out.println("    " + ((PolyType) t.get(Symbol.symbol("fst"))).instantiate());
		System.out.println("    " + ((PolyType) t.get(Symbol.symbol("fst"))).instantiate());
	}

	public static void main(String[] args) {
		System.out.print("StateTest:");
		CheckInitialize();
		CheckFun();
		CheckEnv();
		CheckStateChange();
		CheckTypeVar();
		CheckPolyType();
	}

}
