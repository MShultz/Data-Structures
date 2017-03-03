package algo.data.structures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WeightedNode<T>{
	private T value;
	private List<Connection<T>> neighborNodes = new ArrayList<Connection<T>>();

	public WeightedNode(T value) {
		this.setValue(value);
	}

	public WeightedNode(T value, HashMap<WeightedNode<T>, Integer> currentChildren) {
		this.setValue(value);
		this.addChildren(currentChildren);
	}

	public void addChild(WeightedNode<T> currentNode, Integer weight) {
		neighborNodes.add(new Connection<T>(this, currentNode, weight));
	}

	public void addChildren(HashMap<WeightedNode<T>, Integer> currentChildren) {
		for(WeightedNode<T> node: currentChildren.keySet()){
			neighborNodes.add(new Connection<T>(this, node, currentChildren.get(node)));
		}
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public List<Connection<T>> getNeighborNodes() {
		return neighborNodes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WeightedNode other = (WeightedNode) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}


}
