package algo.data.structures;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class BinarySearchTree<T extends Comparable<? super T>> {
	private BinaryNode<T> root;
	private int count;
	private boolean removeHasChanged;

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
		this.setRemoveHasChanged(false);
		root = remove(root, value);
		return this.removeHasChanged;
	}

	private BinaryNode<T> remove(BinaryNode<T> currentNode, T value) {
		if (this.contains(value) && currentNode != null) {
			this.setRemoveHasChanged(true);
			if (value.compareTo(currentNode.getValue()) < 0) 
				currentNode.setLeft(remove(currentNode.getLeft(), value));
			 else if (value.compareTo(currentNode.getValue()) > 0) 
				currentNode.setRight(remove(currentNode.getRight(), value));
			 else if (currentNode.getRight() != null && currentNode.getLeft() != null) {
				currentNode.setValue(getSmallestNode(currentNode.getRight()).getValue());
				currentNode.setRight(remove(currentNode.getRight(), currentNode.getValue()));
			} else {
				currentNode = (currentNode.getRight() != null) ? currentNode.getRight() : currentNode.getLeft();
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

	public String preorder() {
		return preorder(root).toString().replace("[", "").replace("]", "");
	}

	private ArrayList<T> preorder(BinaryNode<T> currentNode) {
		ArrayList<T> preOrderTree = new ArrayList<T>();
		if (currentNode != null) {
			preOrderTree.add(currentNode.getValue());
			preOrderTree.addAll(preorder(currentNode.getLeft()));
			preOrderTree.addAll(preorder(currentNode.getRight()));
		}
		return preOrderTree;
	}

	public String postorder() {
		return postorder(root).toString().replace("[", "").replace("]", "");
	}

	private ArrayList<T> postorder(BinaryNode<T> currentNode) {
		ArrayList<T> postOrderTree = new ArrayList<T>();
		if (currentNode != null) {
			postOrderTree.addAll(postorder(currentNode.getLeft()));
			postOrderTree.addAll(postorder(currentNode.getRight()));
			postOrderTree.add(currentNode.getValue());
		}
		return postOrderTree;
	}

	public boolean removeHasChanged() {
		return removeHasChanged;
	}

	public void setRemoveHasChanged(boolean removeHasChanged) {
		this.removeHasChanged = removeHasChanged;
	}
	
	public T[] toArray(){
			return (T[]) inorder(root).toArray();
		
	}
}
