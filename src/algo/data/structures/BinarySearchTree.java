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
			this.setRoot(new BinaryNode<T>(null, null, null, value));
		++count;
		return true;
	}

	public boolean contains(T value) {
		return contains(root, value);
	}

	private boolean contains(BinaryNode<T> currentNode, T value) {
		if (currentNode == null)
			return false;
		int result = value.compareTo(currentNode.getValue());
		return result < 0 ? contains(currentNode.getLeft(), value)
				: result > 0 ? contains(currentNode.getRight(), value) : true;
	}

	public void clear() {
		this.setRoot(null);
		count = 0;
	}

	public int count() {
		return count;
	}

	public int height() {
		return height(root);
	}

	public int height(BinaryNode<T> currentNode) {
		if (currentNode == null)
			return -1;
		int leftHeight = height(currentNode.getLeft());
		int rightHeight = height(currentNode.getRight());

		return leftHeight > rightHeight ? leftHeight + 1 : rightHeight + 1;
	}

	private BinaryNode<T> getParent(BinaryNode<T> currentNode, T value) {
		if (value.compareTo(currentNode.getValue()) >= 0)
			return currentNode.getRight() == null ? currentNode : getParent(currentNode.getRight(), value);
		else
			return currentNode.getLeft() == null ? currentNode : getParent(currentNode.getLeft(), value);
	}

	public void setRoot(BinaryNode<T> root) {
		this.root = root;
	}

}
