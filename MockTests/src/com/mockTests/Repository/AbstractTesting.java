package com.mockTests.Repository;

abstract class TestAbs {
	void print(int i) {
		System.out.println("int is:" + i);
	}

	abstract void show();
}

public class AbstractTesting extends TestAbs {

	@Override
	void show() {
		// need to provide definition of abstract method here

	}

	public static void main(String args[]) {
		System.out.println("string");

		String str = "aabbaadacsdfg";
		String seq = "z";
		System.out.println(rearrange(str, seq));

	}

	private static String rearrange(String str, String seq) {
		StringBuffer newStr = new StringBuffer(); // stringbuffer is thread safe that means it is synchronized and
													// cannot be accessed by two threads at same time

		String[] seqArray = seq.split("");
		String lStr = str;

		try {
			for (int i = 0; i < seqArray.length; i++) {
				while (lStr.contains(seqArray[i])) {
					String s = lStr.replaceFirst(seqArray[i], "");
					newStr.append(seqArray[i]);
					lStr = s;
				}
			}
			return newStr.toString().concat(lStr);
		} finally {
			System.out.println("in finally block");
		}

	}

//	public static void main(Integer args[]) {
//		System.out.println("integer");
//	}

}
