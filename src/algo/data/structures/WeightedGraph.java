package algo.data.structures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WeightedGraph<T> {
	List<WeightedNode<T>> allNodes;
	List<Connection<T>> allConnections = new ArrayList<Connection<T>>();
	List<WeightedNode<T>> visitedNodes = new ArrayList<WeightedNode<T>>();
	List<Connection<T>> forest = new ArrayList<Connection<T>>();
	int totalLength;

	public WeightedGraph(List<WeightedNode<T>> allNodes) {
		this.setAllNodes(allNodes);
		this.getAllConnections();
		this.findKruskal();
		this.setTotalLength();
	}

	public List<WeightedNode<T>> getAllNodes() {
		return allNodes;
	}

	public void setAllNodes(List<WeightedNode<T>> allNodes) {
		this.allNodes = allNodes;
	}

	
	private void findKruskal(){
		int i = 0;
		forest.add(allConnections.get(0));
		visitedNodes.add(forest.get(i).getSource());
		visitedNodes.add(forest.get(i).getDestination());
		for(i = 1; i < allConnections.size(); ++i){
			if(!visitedNodes.contains(allConnections.get(i).getDestination())){
				forest.add(allConnections.get(i));
				visitedNodes.add(allConnections.get(i).getDestination());
			}
		}
	}
	
	private void getAllConnections(){
		for(WeightedNode<T> node: allNodes){
			allConnections.addAll(node.getNeighborNodes());
		}
		Collections.sort(allConnections);
	}
	private void setTotalLength(){
		totalLength = 0;
		for(Connection<T> connection: forest){
			totalLength += connection.getWeight();
		}
	}
	
	public int getTotalLength(){
		return totalLength;
	}
}
