package algo.data.structures;

import java.util.List;

public class WeightedGraph {
	List<WeightedNode<String>> allNodes;

	public WeightedGraph(List<WeightedNode<String>> allNodes) {
		this.setAllNodes(allNodes);
	}

	public List<WeightedNode<String>> getAllNodes() {
		return allNodes;
	}

	public void setAllNodes(List<WeightedNode<String>> allNodes) {
		this.allNodes = allNodes;
	}

}
