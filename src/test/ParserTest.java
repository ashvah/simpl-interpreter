package test;

import java.io.FileInputStream;
import java.io.InputStream;

import simpl.parser.Parser;

public class ParserTest {

    private static void parse(String filename) {
        try (InputStream inp = new FileInputStream(filename)) {
            Parser parser = new Parser(inp);
            java_cup.runtime.Symbol parseTree = parser.parse();
            System.out.println(filename);
            System.out.println(parseTree.value);
        }
        catch (Exception e) {
            System.out.println("syntax error");
        }
    }

    public static void main(String[] argv) {
    	parse("doc/examples/plus.spl");
		parse("doc/examples/factorial.spl");
		parse("doc/examples/gcd1.spl");
		parse("doc/examples/gcd2.spl");
		parse("doc/examples/max.spl");
		parse("doc/examples/sum.spl");
		parse("doc/examples/map.spl");
		parse("doc/examples/pcf.sum.spl");
		parse("doc/examples/pcf.even.spl");
		parse("doc/examples/pcf.minus.spl");
		parse("doc/examples/pcf.factorial.spl");
		parse("doc/examples/pcf.fibonacci.spl");
		parse("doc/examples/pcf.twice.spl");
		parse("doc/examples/pcf.lists.spl");
		parse("doc/examples/true.spl");
		parse("doc/examples/polymorphism.spl");
		parse("doc/examples/letpoly.spl");
		parse("doc/examples/lazyeval4.spl");
		parse("doc/examples/mutualrecursion.spl");
		parse("doc/examples/define.spl");
		parse("doc/examples/infinitestream.spl");
    }
}
