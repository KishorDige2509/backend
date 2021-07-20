package com.mockTests.Classess;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

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

		// 11
		// 6 15
		// 3 8 13 17
		node.left = left; // 6
		node.right = right; // 15

		// 6
		left.left = left1; // 3
		left.right = right1; // 8

		// 15
		right.left = left2; // 13
		right.right = right2; // 17

		// System.out.println(node.findNode(node, 78));
//		System.out.println("PreOrder: ");
//		node.preOrder(node);
//		System.out.println("InOrder: ");
//		node.inOrder(node);
//		System.out.println("PostOrder: ");
//		node.postOrder(node);
//		System.out.println("levelOrder: ");
		node.levelOrder(node);

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
	// 11
	// 6 15
	// 3 8 13 17

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
		if (root != null) {
			que.add(root);
			while (!que.isEmpty()) {
				Node node = que.poll();
				if (node != null && node.value > 0) {
					System.out.print(node.value + " ");

					if (node.left != null) {
						que.add(node.left);
					}

					if (node.right != null) {
						que.add(node.right);
					}

				}

			}
		}

	}

}
