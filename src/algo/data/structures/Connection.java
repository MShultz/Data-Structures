package algo.data.structures;

public class Connection<T> implements Comparable<Connection<T>>{
	private WeightedNode<T> source, destination;
	private int weight;
	
	public Connection(WeightedNode<T> source, WeightedNode<T> destination, int weight){
		this.setSource(source);
		this.setDestination(destination);
		this.setWeight(weight);
	}
	public WeightedNode<T> getSource() {
		return source;
	}
	public void setSource(WeightedNode<T> source) {
		this.source = source;
	}
	public WeightedNode<T> getDestination() {
		return destination;
	}
	public void setDestination(WeightedNode<T> destination) {
		this.destination = destination;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	@Override
	public int compareTo(Connection<T> o) {
		return (this.getWeight() > o.getWeight() ? 1 : this.getWeight() < o.getWeight() ? -1 : 0);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((destination == null) ? 0 : destination.hashCode());
		result = prime * result + weight;
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
		Connection other = (Connection) obj;
		if (destination == null) {
			if (other.destination != null)
				return false;
		} else if (!destination.equals(other.destination))
			return false;
		if (weight != other.weight)
			return false;
		return true;
	}

	
	
}
