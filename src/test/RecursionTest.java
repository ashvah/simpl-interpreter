package test;

import simpl.interpreter.Interpreter;
import simpl.interpreter.State;
import simpl.parser.ast.Expr;

public class RecursionTest {

	private static void interpret(String filename) {
		Interpreter i = new Interpreter();
		System.out.println(filename);
			i.run(filename);
	}

	public static void main(String[] args) {
		State.enableGC(true);
		Expr.enableLazyEval(false);
		Expr.enableopt(true);
		interpret("doc/examples/define.spl");
		interpret("doc/examples/mutualrecursion.spl");
		Expr.enableopt(true);
		interpret("doc/examples/tail.spl");
		Expr.enableopt(false);
		interpret("doc/examples/tail.spl");
	}
}
