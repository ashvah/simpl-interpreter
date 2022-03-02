package test;

import simpl.interpreter.Interpreter;
import simpl.interpreter.State;
import simpl.parser.ast.Expr;

public class MutuallyRecursiveCombinatorTest {

	private static void interpret(String filename) {
		Interpreter i = new Interpreter();
		System.out.println(filename);
		i.run(filename);
	}

	public static void main(String[] args) {
		State.enableGC(true);
		Expr.enableLazyEval(false);
		interpret("doc/examples/define.spl");
		interpret("doc/examples/mutualrecursion.spl");
	}
}
