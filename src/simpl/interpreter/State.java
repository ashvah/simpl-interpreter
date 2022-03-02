package simpl.interpreter;

import java.util.HashSet;

import simpl.parser.ast.Expr;

public class State {

	public Env E;
	private static boolean gc_enabled = false;
	private static boolean debug = false;
	private int from = 0;
	private static int MAX_HEAP = 100;
	public final Mem[] M = new Mem[2];
	public final Int p;

	protected State(Env E, Mem M, Int p) {
		this.E = E;
		this.M[0] = M;
		this.p = p;
		this.M[1] = new Mem();
		this.from = 0;
	}

	public static State of(Env E, Mem M, Int p) {
		return new State(E, M, p);
	}

	public static void setHeapSize(int size) {
		State.MAX_HEAP = size;
	}

	public static void enableGC(boolean flag) {
		State.gc_enabled = flag;
	}

	public static void enableDebug(boolean flag) {
		State.debug = flag;
	}

	public Mem getMem() {
		return this.M[this.from];
	}

	protected void copyCollection() {
		// collect
		HashSet<RefValue> reachable = this.E.getRefValue();
		int ad = 0;
		// copy
		for (RefValue v : reachable) {
			this.M[1 - from].put(ad, this.M[from].get(v.p));
			ad += 1;
		}
		if (State.debug) {
			System.out.println("\n[DEBUG][From GC]Before GC: " + this.p.get() + " cells in total.");
			System.out.println("[DEBUG][From GC]After GC: " + ad + " cells in total.");
		}
		// swap
		this.p.set(ad);
		this.from = 1 - this.from;
	}

	public int allocate() throws RuntimeError {
		int address = this.p.get();
		// if the heap is full, try to collect garbage
		if (address >= State.MAX_HEAP) {
			if (State.gc_enabled && !Expr.isThunk())
				this.copyCollection();
			address = this.p.get();
			if (address == State.MAX_HEAP)
				throw new RuntimeError("HeapOverflow"); // gc doesn't work, throw heap overflow error
		}
		// allocate a memory cell
		this.p.set(address + 1);
		if (State.debug)
			System.out.print("Alloc:@" + address + "; ");
		return address;
	}

	public void putValue(int address, Value v) {
		this.M[this.from].put(address, v);
	}

	public Value getValue(int address) {
		return this.M[this.from].get(address);
	}
}
