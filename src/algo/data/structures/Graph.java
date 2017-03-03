package algo.data.structures;


public class Graph<T> {
	private GraphNode<T> head, tail;
	private SingleLinkedList<GraphNode<T>> shortestDistance = new SingleLinkedList<GraphNode<T>>();

	public Graph(GraphNode<T> head, GraphNode<T> tail) {
		this.setHead(head);
		this.setTail(tail);
		this.findPath();
		this.createPath();
	}
	
	public Graph(){
		
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
	
	public boolean pathExists(GraphNode<T> head, GraphNode<T> tail){
		this.setHead(head);
		this.setTail(tail);
		findPath();
		createPath();
		return shortestDistance.size() > 1;
	}
	
	public boolean pathExists(){
		return shortestDistance.size() > 1;
	}

}
