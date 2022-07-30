package com.mockTests.Repository;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

abstract class TestAbs {
	void print(int i) {
		System.out.println("int is:" + i);
	}

	abstract void show();
}

public class AbstractTesting implements Inter1, Inter2{


	
	@Override
	public void ko() {
		// TODO Auto-generated method stub
		
	}
	


	public static void main(String args[]) {
		System.out.println("string");
		String address = "www.facebook.com";
		String google = "www.google.com";
		
//		try {
//			InetAddress inetAddress = Inet4Address.getByName(google);
//			
//			System.out.println(inetAddress.getHostAddress());
//		} catch (UnknownHostException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		for(int i=0;i<5;i++) {
			String str = "str"+i;
			System.out.println(str.hashCode());
			AbstractTesting ad = new AbstractTesting();
			System.out.println(ad);
			ThreadTest test = new ThreadTest(str);
			test.start();
		 
		}
		
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



	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	

//	public static void main(Integer args[]) {
//		System.out.println("integer");
//	}

}
