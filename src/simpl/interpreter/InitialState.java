package simpl.interpreter;

import simpl.interpreter.lib.hd;
import simpl.interpreter.lib.tl;
import simpl.interpreter.lib.car;
import simpl.interpreter.lib.cdr;
import simpl.interpreter.lib.display;
import simpl.interpreter.lib.fst;
import simpl.interpreter.lib.snd;
import simpl.interpreter.pcf.iszero;
import simpl.interpreter.pcf.pred;
import simpl.interpreter.pcf.succ;
import simpl.parser.Symbol;

public class InitialState extends State {

	public InitialState() {
		super(initialEnv(Env.empty), new Mem(), new Int(0));
	}

	private static Env append(Env E, String s, Value v) {
		return new Env(E, Symbol.symbol(s), v);
	}

	private static Env initialEnv(Env E) {
		// add predefined functions
		E = append(E, "hd", new hd());
		E = append(E, "tl", new tl());
		E = append(E, "fst", new fst());
		E = append(E, "snd", new snd());
		E = append(E, "iszero", new iszero());
		E = append(E, "pred", new pred());
		E = append(E, "succ", new succ());
		E = append(E, "car", new car());
		E = append(E, "cdr", new cdr());
		E = append(E, "recordnil", Value.RECORDNIL);
		E = append(E, "display", new display());
		return E;
	}
}
