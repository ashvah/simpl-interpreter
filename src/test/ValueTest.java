package test;

import simpl.interpreter.*;
import simpl.parser.*;
import simpl.parser.ast.*;

public class ValueTest {

	private static void CheckOutputFormat(Value ins) {
		System.out.println("     Output format@" + ins.getClass().getSimpleName() + " : " + ins);
	}

	private static void CheckEquality(Value ins1, Value ins2, boolean expectedResult) {
		boolean equal = ins1.equals(ins2);
		if (equal) {
			System.out.print("     Equality: " + ins1 + "=" + ins2);
		} else {
			System.out.print("     Equality: " + ins1 + "!=" + ins2);
		}
		if (equal == expectedResult)
			System.out.println("\t✔ ");
		else
			System.out.println("\t✘ ");
	}

	private static void BoolTest() {
		System.out.println("\n  BoolTest:");
		BoolValue tt = new BoolValue(true);
		BoolValue ff = new BoolValue(false);
		CheckOutputFormat(tt);
		CheckOutputFormat(ff);
		CheckEquality(tt, ff, false);
		CheckEquality(tt, Value.TRUE, true);
		CheckEquality(ff, Value.FALSE, true);
	}

	private static void ConsTest() {
		System.out.println("\n  ConsTest:");
		ConsValue list1 = new ConsValue(new IntValue(1), Value.NIL);
		ConsValue list2 = new ConsValue(new IntValue(2), list1);
		CheckOutputFormat(list1);
		CheckOutputFormat(list2);
		CheckEquality(list1, list2, false);
		CheckEquality(list1, list1, true);
		CheckEquality(list2, list2, true);
	}

	private static void FunTest() {
		System.out.println("\n  FunTest:");
		FunValue f = new FunValue(Env.empty, Symbol.symbol("x"), new IntegerLiteral(1));
		CheckOutputFormat(f);
	}

	private static void IntTest() {
		System.out.println("\n  IntTest:");
		IntValue x = new IntValue(1);
		IntValue y = new IntValue(0);
		IntValue z = new IntValue(1);
		CheckOutputFormat(x);
		CheckOutputFormat(y);
		CheckEquality(x, y, false);
		CheckEquality(x, z, true);
		CheckEquality(y, y, true);
	}

	private static void PairTest() {
		System.out.println("\n  PairTest:");
		PairValue p1 = new PairValue(new IntValue(1), new IntValue(1));
		PairValue p2 = new PairValue(new IntValue(2), new IntValue(2));
		PairValue p3 = new PairValue(new IntValue(1), new IntValue(1));
		CheckOutputFormat(p1);
		CheckOutputFormat(p2);
		CheckEquality(p1, p2, false);
		CheckEquality(p1, p3, true);
		CheckEquality(p2, p2, true);
	}

	private static void RefTest() {
		System.out.println("\n  RefTest:");
		RefValue p1 = new RefValue(1);
		RefValue p2 = new RefValue(0);
		CheckOutputFormat(p1);
		CheckOutputFormat(p2);
		CheckEquality(p1, p2, false);
		CheckEquality(p1, p1, true);
		CheckEquality(p2, p2, true);
	}

	private static void NilTest() {
		System.out.println("\n  NilTest:");
		CheckOutputFormat(Value.NIL);
	}

	private static void UnitTest() {
		System.out.println("\n  UnitTest:");
		CheckOutputFormat(Value.UNIT);
	}

	public static void main(String[] args) {
		System.out.print("ValueTest:");
		BoolTest();
		ConsTest();
		FunTest();
		IntTest();
		PairTest();
		RefTest();
		NilTest();
		UnitTest();
	}

}
