package com.mockTests.Classess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeSet;

public class Interview2 {
	public static void main(String[] args) {
//		arrange1();
//		System.out.println(reverse(1212999999));
//		System.out.println("Integer roll over:" +  Integer.MAX_VALUE+1);
//		System.out.println("Integer Max / 10 : "+ Integer.MAX_VALUE/10);
		Node node = new Node();
		node.value = 11;

		Node left = new Node();
		left.value = 6;
		Node right = new Node();
		right.value = 15;

		Node left1 = new Node();
		left1.value = 3;
		Node right1 = new Node();
		right1.value = 8;

		Node left2 = new Node();
		left2.value = 13;
		Node right2 = new Node();
		right2.value = 17;

		//      11
		//   6      15
		// 3   8  13  17
		node.left = left; // 6
		node.right = right; // 15

		// 6
		left.left = left1; // 3
		left.right = right1; // 8

		// 15
		right.left = left2; // 13
		right.right = right2; // 17

//		System.out.println(node.findNode(node, 17));
//		System.out.println("PreOrder: ");
//		node.preOrder(node);
//		System.out.println("InOrder: ");
//		node.inOrder(node);
//		System.out.println("PostOrder: ");
//		node.postOrder(node);
//		System.out.println("levelOrder: ");
//		node.levelOrder(node);
//		System.out.println("Height: " + node.height(node));
		char[] charArray = {'A', 'B', 'C', 'D', 'E'};
//		printPattern(charArray);
		
//		System.out.println(isPalindrome(121));
//		charCount("apple");
//		printInvertedPiramid(charArray);
		
//		charPatternCount("There is a goal jhol for the goal keeper!", null);
//		divideStringEqually("killkillkillkill", 3);
		
		int[] arr = { 0, 1, 1, 0, 1, 1, 0, 1, 0, 0, 0, 1 };
		divideAndConquer(arr);
		
		
	}
	
	private static void divideAndConquer(int[] arr) {
		
	}
	
	
	
	private static void divideStringEqually(String string, int count) {
		StringBuilder sb = new StringBuilder();
		int strLength = string.length();
		if(count > strLength) System.out.println("Count cannot be greater than String length");
		else {
			int printCount = 0;
			int length = string.length()/count;
			char[] charArray = string.toCharArray();
			for(int i=0; i<string.length(); i++) {
				sb.append(charArray[i]);
				if(sb.length() == length && printCount<count) {
					System.out.println(sb);
					sb = new StringBuilder();
					printCount++;
				} 
			}
		}
		
	}



	private static void charPatternCount(String string, String pattern) {
		if(string==null || pattern==null) System.out.println("Sample or pattern cannot be null");
		if(string.isEmpty() && pattern.isEmpty()) System.out.println("Sample or pattern cannot be empty!");
		if(pattern.length() > string.length()) System.out.println("Pattern cannot be larger than sample");
		
		char[] stringArray = string.toCharArray();
		StringBuilder sb = new StringBuilder();
		int count = 0;
		int loopCount = 0;
		for(int i=0; i<stringArray.length; i++) {
			sb.append(stringArray[i]);
			if(pattern.length() == sb.length()) {
				if(pattern.contentEquals(sb)) {
					count++;
				}
				i-=(loopCount-1);
				loopCount=0;
				sb = new StringBuilder();
			}
			loopCount++;
		}
		
		String timeOrTimes = count>1 ? " times." : " time.";
		System.out.println("Pattern: \"" + pattern + "\" occurred " + count + timeOrTimes + " in Sample: " + string);
				
	}



	private static void charCount(String string) {
		String[] charArray = string.split("");
		for(int i=0; i<charArray.length; i++) {
			if(charArray[i] != null) {
				String s = charArray[i];
				int count = 0;
				for(int j=0; j<charArray.length; j++) {
					if(charArray[j] != null && s.equals(charArray[j])) {
						count++;
						charArray[j] = null;
					}
				}
				String timeOrTimes = count>1 ? " times." : " time.";
				System.out.println(s + " occured: " + count + timeOrTimes);
			}
			
		}
	}

	static boolean isPalindrome(int x) {
        
		if(x==0 || (x!=0 && x%10 == 0)) return false;
		
		int rev = 0, num = x;
        while(num!=0){
            rev = rev * 10 + num%10;
            num/=10;
        }
        
        return rev == x;
    }
	
	static void printInvertedPiramid(char[] charArray) {
		for(int i=0; i < charArray.length; i++) {
			for(int j=0; j < charArray.length; j++) {
				if(j>=i) {
					System.out.print(charArray[j] + " ");
				} else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}
	
	static void printPattern(char[] charArray) {		
		for(int i = 0; i < charArray.length; i++) {
			for(int j = 0; j < charArray.length; j++) {
				System.out.print(charArray[j] + " ");
			}
			// rotate array elements
			rotateArray(charArray);
			System.out.println();
		}
	}

	private static void rotateArray(char[] charArray) {
		char temp = charArray[0];
		for(int i=0; i<charArray.length-1; i++) {
			charArray[i] = charArray[i+1];
		}
		charArray[charArray.length-1] = temp;
	}

	static void arrange() {
		int[] arr = { 0, 1, 1, 0, 1, 1, 0, 1, 0, 0, 0, 1 };
		int length = arr.length;
		for (int i = 0; i < arr.length / 2; i++) {

			if (arr[i] == 0 && arr[length - (i + 1)] == 1) {

				continue;
			} else {
				arr[i] = 0;
				arr[length - (i + 1)] = 1;
			}
		}

		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
	}

	static void arrange1() {
		int[] arr = { 0, 1, 1, 0, 1, 1, 0, 1, 0, 0, 0, 1 };
		for (int i = 0; i < arr.length - 2; i++) {
			if (arr[i] < arr[i + 1]) {
				if (arr[i + 1] < arr[i + 2]) {
					continue;
				} else {
					int temp = arr[i + 1];
					arr[i + 1] = arr[i + 2];
					arr[i + 2] = temp;
				}
			} else {
				int temp = arr[i];
				arr[i] = arr[i + 1];
				arr[i + 1] = temp;
			}
		}

		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
	}

	static int reverse(int x) {
		int rev = 0;
		while (x != 0) {
			int pop = x % 10;
			x /= 10;
			System.out.println("Pop is:" + pop);
			System.out.println("Integer max is:" + Integer.MAX_VALUE);
			System.out.println("Integer min is:" + Integer.MIN_VALUE);
			System.out.println("Integer max div by 10:" + Integer.MAX_VALUE / 10);
			System.out.println("Integer min div by 10:" + Integer.MIN_VALUE / 10);
			if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7))
				return 0;
			if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8))
				return 0;
			rev = rev * 10 + pop;
			System.out.println("rev now is:" + rev);
		}
		return rev;
	}

}

class Node {
	int value;
	Node left;
	Node right;

	public boolean findNode(Node root, int num) {
		boolean status = false;

		if (root == null) {
			return status;
		} else {
			if (root.value == num) {
				return status = true;
			} else {
				return findNode(root.left, num) || findNode(root.right, num);
			}
		}
	}

	public void preOrder(Node root) {
		if (root != null) {
			System.out.println(root.value);
			preOrder(root.left);
			preOrder(root.right);
		}

	}
	//      11
	//   6      15
	// 3   8  13  17

	public void inOrder(Node root) {
		if (root != null) {
			inOrder(root.left);
			System.out.println(root.value);
			inOrder(root.right);
		}

	}

	public void postOrder(Node root) {
		if (root != null) {
			postOrder(root.left);
			postOrder(root.right);
			System.out.println(root.value);
		}

	}

	public void levelOrder(Node root) {
		Queue<Node> que = new LinkedList<>();
		List<Integer> list = new ArrayList<>();
		if (root != null) {
			que.add(root);
			while (!que.isEmpty()) {
				Node node = que.poll();
				if (node != null && node.value > 0) {
					System.out.print(node.value + " ");
					list.add(node.value);					

					if (node.left != null) {
						que.add(node.left);
					}

					if (node.right != null) {
						que.add(node.right);
					}

				}

			}
		}
		System.out.println();
		System.out.println("BST: ");
		int max = 0, min = 0, range = 0, height = height(root)-1, tempHeight = height;
		while (tempHeight != 0) {
			System.out.print("\t");
			tempHeight--;
		}
		
		System.out.println(list.get(range));	
		
		for (int i = 1; i < list.size(); i = (i*2) + 1) {
			
			range = i;
			min = (min * 2) + 1;
			max = (max * 2) + 2;
			//height--;
			while (range >= min && range <= max) {
				int h = --height;
				while(h != 0) {
					System.out.print("\t");
					h--;
				}
				System.out.print(list.get(range) + "\t" + "\t");
				range++;
			}
			
			System.out.println();
		}
		

	}
	
	public int height(Node root) {
		if (root == null) return 0;
		return Math.max(height(root.left), height(root.right))  + 1;
	}
	



}

class Solution {
    void matchPairs(char nuts[], char bolts[], int n) {
        // code here
        Character[] elements = {'!','#','$','%','&','*','@','^','~'};
        
        Map<Character, Integer> orderMap = new HashMap<>();
        Map<Character, Integer> nutsMap = new HashMap<>();
        Map<Integer, Character> boltsMap = new HashMap<>();
        
        for(int i=0; i<elements.length; i++){
            orderMap.put(elements[i], i);
        }
        
        for(int i=0; i<nuts.length; i++){
            int pr = orderMap.get(nuts[i]);
            nutsMap.put(nuts[i], pr);
            boltsMap.put(pr, nuts[i]);
        }
        
        TreeSet<Integer> tst = new TreeSet<>();
        tst.addAll(nutsMap.values());
    
        
        for(int i=0; i<bolts.length; i++){
            Integer key = tst.pollFirst();
            bolts[i] = boltsMap.get(key);
            nuts[i] = boltsMap.get(key);
        }
        
    }
}
