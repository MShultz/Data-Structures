package algo.data.structures;

public class PQNode implements Comparable<PQNode>{
	private int priority, value;

	public PQNode(int priority, int value) {
		this.setPriority(priority);
		this.setValue(value);
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public int compareTo(PQNode arg0) {
		if(this.priority < arg0.priority)
			return -1;
		else if(this.priority > arg0.priority)
			return 1;
		else{
			 if(this.value < arg0.value)
				return 1;
			else if(this.value > arg0.value)
				return -1;
			else
				return 0;
		}
	}

	@Override
	public String toString() {
		return priority + ":" + value;
	}
	

}
