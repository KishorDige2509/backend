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

	private static class NodeInfo {
		int leftHeight;
		int rightHeight;
		int diameterOfTree;

		public NodeInfo(int lh, int rh) {
			this.leftHeight = lh;
			this.rightHeight = rh;

		}
	}

	/*
	 * quadratic time O(n^2)
	 */
	public static int diameterOfTreeQuad(Node root) {
		if (root == null) {
			return 0;
		}
		return heightOfTree(root.left) + heightOfTree(root.right) + 1;
	}

	public static int diameterOfTree(Node root) {

		
		int diameter = 0;

		if (root == null) {
			return 0;
		}
		
		
		return Math.max(diameterOfTree(root.left), diameterOfTree(root.right)) + 1;		

	}

	public static void main(String[] args) {
		// pre-order data
		int[] nodes = { 1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1 };
		BinaryTree tree = new BinaryTree();
		Node root = tree.buildTree(nodes);

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
		System.out.println("Diameter of Tree: " + diameterOfTree(root));

	}

}
