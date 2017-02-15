package algo.data.structures;

public class BinarySearchTree<T extends Comparable<? super T>> {
	private BinaryNode<T> root;
	private int count;

	public BinarySearchTree() {
		count = 0;
		root = null;
	}

	public boolean add(T value) {
		if (root != null) {
			BinaryNode<T> parentNode = getParent(root, value);
			BinaryNode<T> childNode = new BinaryNode<T>(parentNode, null, null, value);
			if (value.compareTo(parentNode.getValue()) >= 0)
				parentNode.setRight(childNode);
			else
				parentNode.setLeft(childNode);
		} else
			root = new BinaryNode<T>(null, null, null, value);
		return true;
	}
	
	public boolean contains(T value){
		return contains(root, value);		
	}
	private boolean contains(BinaryNode<T> currentNode, T value){
		boolean contains = currentNode.getValue().equals(value);
		if(currentNode.getLeft() != null)
			contains = contains || contains(currentNode.getLeft(), value);
		return   || contains(currentNode.getRight(), value);
	}

	private BinaryNode<T> getParent(BinaryNode<T> currentNode, T value) {
		if (value.compareTo(currentNode.getValue()) >= 0)
			return currentNode.getRight() == null ? currentNode : getParent(currentNode.getRight(), value);
		else
			return currentNode.getLeft() == null ? currentNode : getParent(currentNode.getLeft(), value);
	}
}
