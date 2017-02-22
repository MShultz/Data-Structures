package algo.data.structures;
import java.util.ArrayList;

public class Graph<T> {
	private GraphNode<T> head, tail;
	private ArrayList<SingleLinkedList<T>> allPaths;

	public Graph(GraphNode<T> head, GraphNode<T> tail) {
		this.setHead(head);
		this.setTail(tail);
		this.getAllPaths(head, new SingleLinkedList<T>());
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
	
	private void getAllPaths(GraphNode<T> currentNode, SingleLinkedList<T> currentTraversal){
		ArrayList<GraphNode<T>> currentNeighbors = currentNode.getNodeNeighbors();
		for(GraphNode<T> neighborNode: currentNeighbors){
			if(neighborNode == tail){
				currentTraversal.add(neighborNode.getValue());
				allPaths.add(currentTraversal);
			}else if(!currentTraversal.contains(neighborNode)){
				currentTraversal.add(neighborNode.getValue());
				getAllPaths(neighborNode, currentTraversal);
			}
		}
	}

}
