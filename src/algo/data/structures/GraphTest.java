package algo.data.structures;

import static org.junit.Assert.*;

import org.junit.Test;

public class GraphTest {

	@Test
	public void test() {
		GraphNode<String> A = new GraphNode<String>("A");
		GraphNode<String> B = new GraphNode<String>("B");
		GraphNode<String> C = new GraphNode<String>("C");
		GraphNode<String> D = new GraphNode<String>("D");
		GraphNode<String> E = new GraphNode<String>("E");
		GraphNode<String> F = new GraphNode<String>("F");
		
		A.add(B);
		B.add(C);
		B.add(F);
		B.add(D);
		C.add(B);
		D.add(B);
		D.add(F);
		D.add(E);
		E.add(D);
		E.add(F);
		F.add(B);
		F.add(D);
		F.add(E);
	}

}
