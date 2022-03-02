package test;

import simpl.interpreter.Interpreter;
import simpl.interpreter.State;

public class GCTest {

	private static void interpret(String filename) {
		Interpreter i = new Interpreter();
		System.out.println(filename);
		i.run(filename);
	}

	public static void main(String[] args) {
		State.enableDebug(true);
		System.out.println("GC disabled");
		interpret("doc/examples/gctest.spl");
		State.enableGC(true);
		System.out.println("\nGC enabled heap size 10");
		interpret("doc/examples/gctest.spl");
		State.setHeapSize(5);
		System.out.println("\nGC enabled heap size 5");
		interpret("doc/examples/gctest.spl");
		State.setHeapSize(3);
		System.out.println("\nGC enabled heap size 3");
		interpret("doc/examples/gctest.spl");
		State.enableDebug(false);
		State.enableGC(false);
	}
}
