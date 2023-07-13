package Dictionary;

public class BST {
	private BSTNode root;
	
	//Constructor
	public BST() {
		root=null;
	}
	//accessor that returns the root of the BST
	public BSTNode getRoot() {
		return root;
	}
	private void addHelper(Definition d, BSTNode b) {
		//Adds a node to the binary search tree
		if(b == null) {
			b= new BSTNode(d);
		}
		else if((d.getWord()).equals((root.getInfo()).getWord())) {
			if((d.getDescription()).equals((root.getInfo()).getDescription())) {
				System.out.println("Definition already exists");
			}
			else addHelper(d,b.getLeft());
		}
		else if((d.compare(b.getInfo(), 0)).equals("less")){
			addHelper(d,b.getLeft());
		}
		else {
			addHelper(d,b.getRight());
		}
	}
	public void add(Definition d) {
		addHelper(d, root);
	}
}
