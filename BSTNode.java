package Dictionary;

public class BSTNode {
	private Definition info;
	private BSTNode left, right;
	//Constructor
	public BSTNode(Definition info) {
		this.info = info;
	}
	//Accessors
	public Definition getInfo() {
		return info;
	}
	public void setInfo(Definition info) {
		this.info = info;
	}
	public BSTNode getLeft() {
		return left;
	}
	public void setLeft(BSTNode left) {
		this.left = left;
	}
	public BSTNode getRight() {
		return right;
	}
	public void setRight(BSTNode right) {
		this.right = right;
	}
}
