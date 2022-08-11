package com.binaryTrees;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTreesLearning {

	private static class Node {
		int data;
		Node left;
		Node right;

		Node(int data) {
			this.data = data;
			this.left = null;
			this.right = null;
		}
	}

	private static class BinaryTree {
		static int idx = -1;

		public static Node buildTree(int[] nodes) {
			idx++;

			if (nodes[idx] == -1) {
				return null;
			}
			Node newNode = new Node(nodes[idx]);
			newNode.left = buildTree(nodes);
			newNode.right = buildTree(nodes);
			return newNode;
		}
	}

	/*
	 * DFS traversal
	 */
	public static void preorder(Node root) {

		if (root == null) {
			System.out.print("-1" + " ");
			return;
		}
		System.out.print(root.data + " ");
		preorder(root.left);
		preorder(root.right);

	}

	public static void inorder(Node root) {
		if (root == null) {
			System.out.print("-1" + " ");
			return;
		}
		inorder(root.left);
		System.out.print(root.data + " ");
		inorder(root.right);

	}

	public static void postorder(Node root) {
		if (root == null) {
			System.out.print("-1" + " ");
			return;
		}
		postorder(root.left);
		postorder(root.right);
		System.out.print(root.data + " ");
	}

	/*
	 * BFS traversal
	 */
	public static void levelOrder(Node root) {
		if (root == null) {
			return;
		}

		Queue<Node> q = new LinkedList<>();
		q.add(root);
		q.add(null);

		while (!q.isEmpty()) {
			Node currNode = q.remove();
			if (currNode == null) {
				System.out.println();
				if (q.isEmpty()) {
					break;
				} else {
					q.add(null);
				}
			} else {
				System.out.print(currNode.data + " ");
				if (currNode.left != null) {
					q.add(currNode.left);
				}
				if (currNode.right != null) {
					q.add(currNode.right);
				}
			}
		}

	}

	/*
	 * problems on BST
	 */
	public static int countNodes(Node root) {
		if (root == null) {
			return 0;
		}
		return countNodes(root.left) + countNodes(root.right) + 1;
	}

	public static int sumOfNodes(Node root) {
		if (root == null) {
			return 0;
		}
		return sumOfNodes(root.left) + sumOfNodes(root.right) + root.data;
	}

	public static int heightOfTree(Node root) {
		if (root == null) {
			return 0;
		}
		return Math.max(heightOfTree(root.left), heightOfTree(root.right)) + 1;
	}

	/*
	 * quadratic time O(n^2)
	 * 
	 *
	 */
	public static int diameterOfTreeQuad(Node root) {
		if (root == null) {
			return 0;
		}
		int leftDiameter = diameterOfTreeQuad(root.left);
		int rightDiameter = diameterOfTreeQuad(root.right);
		int rootDiameter = heightOfTree(root.left) + heightOfTree(root.right) + 1;

		return Math.max(Math.max(leftDiameter, rightDiameter), rootDiameter);
	}

	public static class TreeInfo {
		int ht;
		int diam;

		public TreeInfo(int ht, int diam) {
			this.ht = ht;
			this.diam = diam;
		}
	}

	/*
	 * O(n)
	 * 
	 * here we are going depth first so, when we reach bottom we hit null node which
	 * will return 0,0 and then right of the root will also return 0,0
	 */
	public static TreeInfo diameterOfTree(Node root) {

		if (root == null) {
			return new TreeInfo(0, 0);
		}

		TreeInfo leftInfo = diameterOfTree(root.left);
		TreeInfo rightInfo = diameterOfTree(root.right);

		int leftDiam = leftInfo.diam;
		int rightDiam = rightInfo.diam;
		int rootDiam = leftInfo.ht + rightInfo.ht + 1;

		int myHeight = Math.max(leftInfo.ht, rightInfo.ht) + 1;
		int myDiam = Math.max(Math.max(leftDiam, rightDiam), rootDiam);

		return new TreeInfo(myHeight, myDiam);

	}

	public static boolean isIdentical(Node root, Node subTreeRoot) {

		if (root == null && subTreeRoot == null) {
			return true;
		}

		if (root == null || subTreeRoot == null) {
			return false;
		}

		if (root.data == subTreeRoot.data) {

			System.out.println("root data: " + root.data + " subTreeRoot data: " + subTreeRoot.data);
			return isIdentical(root.left, subTreeRoot.left) && isIdentical(root.right, subTreeRoot.right);
		}

		return false;

	}

	public static boolean isSubTree(Node root, Node subTreeRoot) {
		if (subTreeRoot == null) {
			return true;
		}

		if (root == null) {
			return false;
		}

		if (root.data == subTreeRoot.data && isIdentical(root, subTreeRoot)) {
			return true;
		}

		return isSubTree(root.left, subTreeRoot) || isSubTree(root.right, subTreeRoot);
	}

	public static boolean isPartialMatch(Node root, Node subTreeRoot) {
		if (subTreeRoot == null) {
			return true;
		}

		if (root == null) {
			return false;
		}

		if (root.data == subTreeRoot.data && root.left.data == subTreeRoot.left.data
				&& root.right.data == subTreeRoot.right.data) {
			return true;
		}

		return isPartialMatch(root.left, subTreeRoot) || isPartialMatch(root.right, subTreeRoot);
	}

	public static void main(String[] args) {
		// pre-order data
		int[] nodes = { 1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1 };
		Node root = BinaryTree.buildTree(nodes);

		BinaryTree.idx = -1;
		int[] matchNodes = { 2, 4, -1, -1, 5, -1, -1 };
		Node subTreeRoot = BinaryTree.buildTree(matchNodes);

		System.out.println("Root:" + root.data);

		System.out.println("Preorder traversal");
		preorder(root);

		System.out.println();

		System.out.println("Inorder traversal");
		inorder(root);

		System.out.println();

		System.out.println("Postorder traversal");
		postorder(root);

		System.out.println();

		System.out.println("Level order traversal");
		levelOrder(root);

		System.out.println();
		System.out.println("Total nodes: " + countNodes(root));

		System.out.println();
		System.out.println("Sum of nodes: " + sumOfNodes(root));

		System.out.println();
		System.out.println("Height of tree: " + heightOfTree(root));

		System.err.println();
		System.err.println("Diameter of tree Quad time: " + diameterOfTreeQuad(root));

		System.out.println();
		System.out.println("Diameter of Tree: " + diameterOfTree(root).diam);

		System.out.println();
		System.out.println("SubTree root:" + subTreeRoot.data);

		System.out.println("Sub tree Level order traversal");
		levelOrder(subTreeRoot);

		System.out.println();
		System.out.println("is Sub tree: " + isSubTree(root, subTreeRoot));
		
		System.out.println();
		System.out.println("is Partial Match: " + isPartialMatch(root, subTreeRoot));

	}

}
