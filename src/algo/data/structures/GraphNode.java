package algo.data.structures;

import java.util.ArrayList;
import java.util.Collection;

public class GraphNode<T> {
	private ArrayList<GraphNode<T>> nodeNeighbors = new ArrayList<GraphNode<T>>();
	private T value;
	private int distance;
	private GraphNode<T> path;

	public GraphNode(T value) {
		this.setValue(value);
		this.setDistance(Integer.MAX_VALUE);
	}

	public ArrayList<GraphNode<T>> getNodeNeighbors() {
		return nodeNeighbors;
	}

	public boolean add(GraphNode<T> node) {
		nodeNeighbors.add(node);
		return true;
	}

	public boolean addAll(Collection<GraphNode<T>> collection) {
		nodeNeighbors.addAll(collection);
		return true;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value.toString();
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public GraphNode<T> getPath() {
		return path;
	}

	public void setPath(GraphNode<T> path) {
		this.path = path;
	}
	

}
