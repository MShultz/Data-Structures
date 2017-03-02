package algo.data.structures;

import java.util.HashMap;

public class WeightedNode<T> {
	private T value;
	private HashMap<WeightedNode<T>, Integer> nodeChildren = new HashMap<WeightedNode<T>, Integer>();

	public WeightedNode(T value) {
		this.setValue(value);
	}

	public WeightedNode(T value, HashMap<WeightedNode<T>, Integer> currentChildren) {
		this.setValue(value);
		this.addChildren(currentChildren);
	}

	public void addChild(WeightedNode<T> currentNode, Integer weight) {
		nodeChildren.put(currentNode, weight);
	}

	public void addChildren(HashMap<WeightedNode<T>, Integer> currentChildren) {
		nodeChildren.putAll(currentChildren);
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

}
