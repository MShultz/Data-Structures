package algo.data.structures;

import java.util.ArrayList;


public class Graph<T> {
	private GraphNode<T> head, tail;
	private SingleLinkedList<GraphNode<T>> shortestDistance = new SingleLinkedList<GraphNode<T>>();

	public Graph(GraphNode<T> head, GraphNode<T> tail) {
		this.setHead(head);
		this.setTail(tail);
		this.findPath();
		this.createPath();
	}

	public GraphNode<T> getHead() {
		return head;
	}

	private void setHead(GraphNode<T> head) {
		this.head = head;
	}

	public GraphNode<T> getTail() {
		return tail;
	}

	private void setTail(GraphNode<T> tail) {
		this.tail = tail;
	}

//	private void getShortestPath(GraphNode<T> currentNode){
//		Map<GraphNode<T>, GraphNode<T>> nodeTrail = new HashMap<GraphNode<T>, GraphNode<T>>();
//		GraphNode<T> source = currentNode;
//		SingleLinkedList<GraphNode<T>> nodesToCheck = new SingleLinkedList<GraphNode<T>>();
//		nodesToCheck.add(source);
//		Set<GraphNode<T>> visitedNodes = new HashSet<GraphNode<T>>();
//		visitedNodes.add(currentNode);
//		
//		while(!nodesToCheck.isEmpty()){
//			source = nodesToCheck.remove();
//	//		if(!source.equals(tail)){
//				for(GraphNode<T> neighborNode : source.getNodeNeighbors()){
//					if(!visitedNodes.contains(neighborNode)){
//						nodesToCheck.add(neighborNode);
//						visitedNodes.add(neighborNode);
//						nodeTrail.put(source, neighborNode);
//					}
//				}
//			}
//		//}
//		
//		if(source.equals(tail)){
//		for(GraphNode<T> node = currentNode; node != null; node = nodeTrail.get(node))
//			shortestDistance.add(node);
//		}
//	}
	
	private void findPath(){
		SingleLinkedList<GraphNode<T>> nodesToCheck = new SingleLinkedList<GraphNode<T>>();
		head.setDistance(0);
		nodesToCheck.add(head);
		
		while(!nodesToCheck.isEmpty()){
			GraphNode<T> current = nodesToCheck.remove();
			
			for(GraphNode<T> node: current.getNodeNeighbors()){
				if(node.getDistance() == Integer.MAX_VALUE){
					node.setDistance(current.getDistance() + 1);
					node.setPath(current);
					nodesToCheck.add(node);
				}
			}
		}
	}
	
	private void createPath(){
		shortestDistance.add(tail);
		GraphNode<T> nextNode = tail.getPath();
		while(nextNode != null){
			shortestDistance.insert(nextNode, 0);
			nextNode = nextNode.getPath();
		}
	}

	public SingleLinkedList<GraphNode<T>> getShortestDistance() {
		return shortestDistance;
	}

}
