package test;

import simpl.interpreter.Interpreter;
import simpl.interpreter.State;
import simpl.parser.ast.Expr;

public class LazyEvalTest {

	private static void interpret(String filename) {
		Interpreter i = new Interpreter();
		System.out.println(filename);
		i.run(filename);
	}

	public static void main(String[] args) {
		State.enableGC(true);
		System.out.println("Call by value:");
		interpret("doc/examples/lazyeval1.spl");
		interpret("doc/examples/lazyeval2.spl");
		Expr.enableLazyEval(true);
		System.out.println("Lazy evaluation:");
		interpret("doc/examples/lazyeval1.spl");
		interpret("doc/examples/lazyeval2.spl");
		Expr.enableLazyEval(false);
		interpret("doc/examples/lazyeval3.spl");
	}

}
