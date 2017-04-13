package Util;

public class Console {

	public void log(String s) {
		System.out.println(s);
	}

	public void err(String s) {
		System.out.println("\u001B[31m" + s + "\u001B[0m");
	}

}