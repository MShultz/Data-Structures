package algo.data.structures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class WeightedGraph<T> {
	List<WeightedNode<T>> allNodes;
	List<Connection<T>> allConnections = new ArrayList<Connection<T>>();
	List<WeightedNode<T>> visitedNodes = new ArrayList<WeightedNode<T>>();
	List<Connection<T>> forest = new ArrayList<Connection<T>>();
	HashMap<WeightedNode<T>, Integer> smallest = new HashMap<WeightedNode<T>, Integer>();
	HashMap<WeightedNode<T>, Integer> largest = new HashMap<WeightedNode<T>, Integer>();
	WeightedNode<T> bestHub;
	int totalLength;

	public WeightedGraph(List<WeightedNode<T>> allNodes) {
		this.setAllNodes(allNodes);
		this.getAllConnections();
		this.findKruskal();
		this.setTotalLength();
		this.setSmallestDistance();
	}

	public List<WeightedNode<T>> getAllNodes() {
		return allNodes;
	}

	public void setAllNodes(List<WeightedNode<T>> allNodes) {
		this.allNodes = allNodes;
	}

	private void findKruskal() {
		int i = 0;
		forest.add(allConnections.get(0));
		visitedNodes.add(forest.get(i).getSource());
		visitedNodes.add(forest.get(i).getDestination());
		for (i = 1; i < allConnections.size(); ++i) {
			if (!visitedNodes.contains(allConnections.get(i).getDestination())) {
				forest.add(allConnections.get(i));
				visitedNodes.add(allConnections.get(i).getDestination());
			}
		}
	}

	private void getAllConnections() {
		for (WeightedNode<T> node : allNodes) {
			allConnections.addAll(node.getNeighborNodes());
		}
		Collections.sort(allConnections);
	}

	private void setTotalLength() {
		totalLength = 0;
		for (Connection<T> connection : forest) {
			totalLength += connection.getWeight();
		}
	}

	public int getTotalLength() {
		return totalLength;
	}

	public void findHub() {
		setSmallestDistance();
		setLargestDistance();
		calculateBest();
	}

	private void setSmallestDistance() {
		for (WeightedNode<T> node : allNodes) {
			int smallestDistance = Integer.MAX_VALUE;
			for (Connection<T> neighbor : node.getNeighborNodes()) {
				if (neighbor.getWeight() < smallestDistance)
					smallestDistance = neighbor.getWeight();
			}
			smallest.put(node, smallestDistance);
		}
	}

	private void setLargestDistance() {
		ArrayList<GraphNode<T>> graphNodes = populateGraph();
		for (int i = 0; i < graphNodes.size(); ++i) {
			int biggestDistance = Integer.MIN_VALUE;
			for (int j = 0; j < graphNodes.size(); ++j) {
				if (i != j) {
					Graph<T> graph = new Graph<T>(graphNodes.get(i), graphNodes.get(j));
					int currentPathLargest = getDistance(graph.getShortestDistance());
					if (currentPathLargest > biggestDistance) {
						biggestDistance = currentPathLargest;
					}

				}
			}
			largest.put(getCurrentNode(graphNodes.get(i)), biggestDistance);
		}
	}

	private void calculateBest() {
		WeightedNode<T> currentBest = null;
		double bestAverage = Integer.MAX_VALUE;
		for (WeightedNode<T> node : allNodes) {
			double avg = (((double) smallest.get(node) + largest.get(node)) / 2);
			if (avg < bestAverage) {
				bestAverage = avg;
				currentBest = node;
			}
		}
		this.setBestHub(currentBest);
	}

	private ArrayList<GraphNode<T>> populateGraph() {
		ArrayList<GraphNode<T>> allGraphNodes = new ArrayList<GraphNode<T>>();
		for (WeightedNode<T> node : allNodes) {
			allGraphNodes.add(new GraphNode<T>(node.getValue()));
		}
		setChildren(allGraphNodes);
		return allGraphNodes;

	}

	private void setChildren(ArrayList<GraphNode<T>> allGraphNodes) {
		for (WeightedNode<T> node : allNodes) {
			GraphNode<T> currentNode = getCurrentNode(allGraphNodes, node);
			for (Connection<T> neighbors : node.getNeighborNodes()) {
				currentNode.add(getCurrentNode(allGraphNodes, neighbors.getDestination()));

			}
		}
	}

	private GraphNode<T> getCurrentNode(ArrayList<GraphNode<T>> allGraphNodes, WeightedNode<T> searchingNode) {
		GraphNode<T> result = null;
		for (GraphNode<T> node : allGraphNodes) {
			if (node.getValue().equals(searchingNode.getValue()))
				result = node;
		}
		return result;
	}

	private WeightedNode<T> getCurrentNode(GraphNode<T> searchingNode) {
		WeightedNode<T> result = null;
		for (WeightedNode<T> node : allNodes) {
			if (node.getValue().equals(searchingNode.getValue()))
				result = node;
		}
		return result;
	}

	private int getDistance(SingleLinkedList<GraphNode<T>> path) {
		int distance = 0;
		int counter = 0;

		while (counter < path.count()) {
			WeightedNode<T> source = getCurrentNode(path.get(counter));
			++counter;
			if (counter < path.count()) {
				WeightedNode<T> destination = getCurrentNode(path.get(counter));
				distance += source.getWeight(destination);
			}
		}
		return distance;

	}

	public WeightedNode<T> getBestHub() {
		return bestHub;
	}

	public void setBestHub(WeightedNode<T> bestHub) {
		this.bestHub = bestHub;
	}

	public List<Connection<T>> getForest() {
		return forest;
	}

	public ArrayList<WeightedNode<T>> getAllNodesInForest() {
		ArrayList<WeightedNode<T>> visited = new ArrayList<WeightedNode<T>>();
		visited.add(forest.get(0).getSource());
		visited.add(forest.get(0).getDestination());
		for (int i = 1; i < forest.size(); ++i) {
			if (!visited.contains(forest.get(i).getSource()))
				visited.add(forest.get(i).getSource());
			if (!visited.contains(forest.get(i).getDestination()))
				visited.add(forest.get(i).getDestination());
		}
		for (WeightedNode<T> node : visited) {
			node.clearNeighbors();
		}
		return visited;
	}

	public void setConnections(List<WeightedNode<T>> currentNodes, List<Connection<T>> allConnections) {
		for (Connection<T> link : allConnections) {
			link.getSource().addChild(link.getDestination(), link.getWeight());
			link.getDestination().addChild(link.getSource(), link.getWeight());
		}
	}

}
