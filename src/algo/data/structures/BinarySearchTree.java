package algo.data.structures;

import java.util.ArrayList;

public class BinarySearchTree<T extends Comparable<? super T>> {
	private BinaryNode<T> root;
	private String listType;
	private int count;

	public BinarySearchTree() {
		count = 0;
		root = null;
	}

	// change to check the type!
	public boolean add(T value) {
		if (root != null) {
			BinaryNode<T> parentNode = getParent(root, value);
			BinaryNode<T> childNode = new BinaryNode<T>(parentNode, null, null, value);
			if (value.compareTo(parentNode.getValue()) >= 0)
				parentNode.setRight(childNode);
			else
				parentNode.setLeft(childNode);
		} else {
			this.setRoot(new BinaryNode<T>(null, null, null, value));
			listType = root.getValue().getClass().getName();
		}
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

	public boolean remove(T value) {
		BinaryNode<T> temporaryNode = root;
		BinaryNode<T> nodeRemoved = remove(root, value);
		return temporaryNode.equals(nodeRemoved) ? false : true;
	}

	private BinaryNode<T> remove(BinaryNode<T> currentNode, T value) {
		if (currentNode != null || value.getClass().getName().equals(listType)) {
			if (currentNode.getValue().compareTo(value) > 0) {
				currentNode.setLeft(remove(currentNode.getLeft(), value));
			} else if (root.getValue().compareTo(value) < 0) {
				currentNode.setRight(remove(currentNode.getRight(), value));
			} else {
				if (currentNode.getRight() != null && currentNode.getLeft() != null) {
					BinaryNode<T> minimumNode = getSmallestNode(currentNode.getRight());
					currentNode.setValue(minimumNode.getValue());
					remove(currentNode.getRight(), minimumNode.getValue());
				} else if (currentNode.getRight() != null) {
					currentNode = currentNode.getRight();
				} else if (currentNode.getLeft() != null) {
					currentNode = currentNode.getLeft();
				} else {
					currentNode = null;
				}
			}
		}
		return currentNode;
	}

	private BinaryNode<T> getSmallestNode(BinaryNode<T> currentNode) {
		return currentNode.getLeft() == null ? currentNode : getSmallestNode(currentNode.getLeft());
	}

	public String inorder() {
		return inorder(root).toString().replace("[", "").replace("]", "");
	}

	private ArrayList<T> inorder(BinaryNode<T> currentNode) {
		ArrayList<T> inOrderTree = new ArrayList<T>();
		if (currentNode != null) {
			inOrderTree.addAll(inorder(currentNode.getLeft()));
			inOrderTree.add(currentNode.getValue());
			inOrderTree.addAll(inorder(currentNode.getRight()));
		}
		return inOrderTree;
	}
}
