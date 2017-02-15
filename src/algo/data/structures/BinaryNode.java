package algo.data.structures;

public class BinaryNode<T> {
	private T value;
	private BinaryNode<T> parent, left, right;

	public BinaryNode(BinaryNode<T> parent, BinaryNode<T> left, BinaryNode<T> right, T value) {
		this.setParent(parent);
		this.setLeft(left);
		this.setRight(right);
		this.setValue(value);
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public BinaryNode<T> getParent() {
		return parent;
	}

	public void setParent(BinaryNode<T> parent) {
		this.parent = parent;
	}

	public BinaryNode<T> getLeft() {
		return left;
	}

	public void setLeft(BinaryNode<T> left) {
		this.left = left;
	}

	public BinaryNode<T> getRight() {
		return right;
	}

	public void setRight(BinaryNode<T> right) {
		this.right = right;
	}

}
