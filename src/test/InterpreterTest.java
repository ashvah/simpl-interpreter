package test;

import simpl.interpreter.Interpreter;
import simpl.interpreter.State;
import simpl.parser.ast.Expr;

public class InterpreterTest {

	private static void interpret(String filename) {
		Interpreter i = new Interpreter();
		System.out.println(filename);
		i.run(filename);
	}

	public static void main(String[] args) {
		Expr.enableLazyEval(true);
		State.enableGC(true);
		Expr.enableopt(true);
		interpret("doc/examples/plus.spl");
		interpret("doc/examples/factorial.spl");
		interpret("doc/examples/gcd1.spl");
		interpret("doc/examples/gcd2.spl");
		interpret("doc/examples/max.spl");
		interpret("doc/examples/sum.spl");
		interpret("doc/examples/map.spl");
		interpret("doc/examples/pcf.sum.spl");
		interpret("doc/examples/pcf.even.spl");
		interpret("doc/examples/pcf.minus.spl");
		interpret("doc/examples/pcf.factorial.spl");
		interpret("doc/examples/pcf.fibonacci.spl");
		interpret("doc/examples/letpoly.spl");
		interpret("doc/examples/pcf.twice.spl");
		interpret("doc/examples/pcf.lists.spl");
		interpret("doc/examples/true.spl");
		interpret("doc/examples/polymorphism.spl");
		interpret("doc/examples/letpolytest1.spl");
		interpret("doc/examples/letpolytest2.spl");
		interpret("doc/examples/letpolytest3.spl");
		interpret("doc/examples/letpolytest4.spl");
		interpret("doc/examples/letpolytest5.spl");
		interpret("doc/examples/squaresum.spl");
		interpret("doc/examples/infiniteloop.spl");
		interpret("doc/examples/hd.spl");
		interpret("doc/examples/equal.spl");
		interpret("doc/examples/nil.spl");
		interpret("doc/examples/null.spl");
		interpret("doc/examples/factorial1.spl");
		interpret("doc/examples/strangeRecursion.spl");
		interpret("doc/examples/lazyeval1.spl");
		interpret("doc/examples/lazyeval2.spl");
		interpret("doc/examples/lazyeval3.spl");
		interpret("doc/examples/infinitestream.spl");
		interpret("doc/examples/define.spl");
		interpret("doc/examples/mutualrecursion.spl");
		interpret("doc/examples/tail.spl");
	}
}
