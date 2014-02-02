
public class TreeDiameter {

	private static class Node {
	    Node left;
	    Node right;

	    public Node() {
	        left = null;
	        right = null;
	    }
	}
	
	private static int getHeight(Node root) {
		if (root == null) {
			return 0;
		} else {
			return 1 + Math.max(getHeight(root.left), getHeight(root.right));
		}
	}
	
	private static int getDiameter(Node root) {
		if (root == null) {
			return 0;
		}
		int leftDiameter = getDiameter(root.left);
		int rightDiameter = getDiameter(root.right);
		return Math.max(Math.max(leftDiameter, rightDiameter), 1 + getHeight(root.left) + getHeight(root.right));
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Node a = new Node();
		Node b = new Node();
		Node c = new Node();
		Node d = new Node();
		Node e = new Node();
		Node f = new Node();
		a.left = b;
		a.right = c;
		b.left = d;
		b.right = e;
		c.left = f;
		System.out.println(getDiameter(a));
	}

}
