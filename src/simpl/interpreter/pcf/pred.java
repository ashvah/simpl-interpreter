package simpl.interpreter.pcf;

import simpl.interpreter.Env;
import simpl.interpreter.FunValue;
import simpl.parser.Symbol;
import simpl.parser.ast.Sub;
import simpl.parser.ast.IntegerLiteral;
import simpl.parser.ast.Name;

public class pred extends FunValue {

	public pred() {
		super(Env.empty, Symbol.symbol("x"), new Sub(new Name(Symbol.symbol("x")), new IntegerLiteral(1)));
	}
}
