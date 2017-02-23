package algo.data.structures;

import java.util.ArrayList;

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
		B.add(A);
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
		
		Graph<String> graph = new Graph<String>(A, E);
		System.out.println(graph.getShortestDistance().toString());
//		
//		GraphNode<String> A = new GraphNode<String>("A");
//		GraphNode<String> B = new GraphNode<String>("B");
//		GraphNode<String> C = new GraphNode<String>("C");
//		GraphNode<String> D = new GraphNode<String>("D");
//		A.add(C);
//		A.add(B);
//		B.add(A);
//		B.add(C);
//		B.add(D);
//		C.add(A);
//		C.add(B);
//		D.add(B);
//		
//		Graph<String> graph = new Graph<String>( A, D);
//		System.out.println(graph.getShortestDistance().toString());
	}

}
