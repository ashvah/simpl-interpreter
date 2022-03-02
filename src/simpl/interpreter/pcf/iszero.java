package simpl.interpreter.pcf;

import simpl.interpreter.Env;
import simpl.interpreter.FunValue;
import simpl.parser.Symbol;
import simpl.parser.ast.*;

public class iszero extends FunValue {

	public iszero() {
		// {E, x, x=0}
		super(Env.empty, Symbol.symbol("x"), new Eq(new Name(Symbol.symbol("x")), new IntegerLiteral(0)));
	}
}
