package lexico;

import java.util.ArrayList;

public final class Code {
	public static ArrayList<tokenzz> tokens = new ArrayList<tokenzz>();
	
	public static void reset() {
		tokens = new ArrayList<tokenzz>();
	}
	
	public static void push(tokenzz z) {
		tokens.add(z);
	}
	
	public static void print() {
		for (int i = 0; i < tokens.size(); i++) {
			System.out.println(tokens.get(i).caract+": "+ tokens.get(i).cadena + " "+tokens.get(i).x+" "+tokens.get(i).y);
		}
	}
}
