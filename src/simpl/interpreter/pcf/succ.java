package simpl.interpreter.pcf;

import simpl.interpreter.Env;
import simpl.interpreter.FunValue;
import simpl.parser.Symbol;
import simpl.parser.ast.IntegerLiteral;
import simpl.parser.ast.Name;
import simpl.parser.ast.Add;

public class succ extends FunValue {

	public succ() {
		super(Env.empty, Symbol.symbol("x"), new Add(new Name(Symbol.symbol("x")), new IntegerLiteral(1)));
	}
}
