package algo.data.structures.tests;

import java.util.ArrayList;

import org.junit.Test;

import algo.data.structures.Graph;
import algo.data.structures.GraphNode;

public class GraphTest {

	@Test
	public void test() {
		// GraphNode<String> A = new GraphNode<String>("A");
		// GraphNode<String> B = new GraphNode<String>("B");
		// GraphNode<String> C = new GraphNode<String>("C");
		// GraphNode<String> D = new GraphNode<String>("D");
		// GraphNode<String> E = new GraphNode<String>("E");
		// GraphNode<String> F = new GraphNode<String>("F");
		//
		// A.add(B);
		// B.add(A);
		// B.add(C);
		// B.add(F);
		// B.add(D);
		// C.add(B);
		// D.add(B);
		// D.add(F);
		// D.add(E);
		// E.add(D);
		// E.add(F);
		// F.add(B);
		// F.add(D);
		// F.add(E);
		//
		// Graph<String> graph = new Graph<String>(A, E);
		// System.out.println(graph.getShortestDistance().toString());
		//
		// GraphNode<String> A = new GraphNode<String>("A");
		// GraphNode<String> B = new GraphNode<String>("B");
		// GraphNode<String> C = new GraphNode<String>("C");
		// GraphNode<String> D = new GraphNode<String>("D");
		// A.add(C);
		// A.add(B);
		// B.add(A);
		// B.add(C);
		// B.add(D);
		// C.add(A);
		// C.add(B);
		// D.add(B);
		//
		// Graph<String> graph = new Graph<String>( A, D);
		// System.out.println(graph.getShortestDistance().toString());

		// GraphNode<String> A = new GraphNode<String>("A");
		// GraphNode<String> B = new GraphNode<String>("B");
		// GraphNode<String> C = new GraphNode<String>("C");
		// GraphNode<String> D = new GraphNode<String>("D");
		// GraphNode<String> E = new GraphNode<String>("E");
		// GraphNode<String> F = new GraphNode<String>("F");
		//
		// A.add(B);
		// A.add(C);
		// B.add(C);
		// B.add(A);
		// C.add(B);
		// C.add(A);
		// D.add(E);
		// D.add(F);
		// E.add(D);
		// E.add(F);
		// Graph<String> graph = new Graph<String>(A, F);
		// System.out.println(graph.getShortestDistance().toString());


		GraphNode<String> A = new GraphNode<String>("AX1");
		GraphNode<String> B = new GraphNode<String>("AX2");
		GraphNode<String> C = new GraphNode<String>("AX3");
		GraphNode<String> D = new GraphNode<String>("AX4");
		GraphNode<String> E = new GraphNode<String>("AX5");
		GraphNode<String> F = new GraphNode<String>("AX10");
		GraphNode<String> G = new GraphNode<String>("AX11");
		GraphNode<String> H = new GraphNode<String>("AX12");
		GraphNode<String> I = new GraphNode<String>("AX99");
		GraphNode<String> J = new GraphNode<String>("AX100");

		A.add(B);
		A.add(C);
		A.add(D);
		B.add(A);
		B.add(C);
		B.add(D);
		C.add(A);
		C.add(B);
		C.add(D);
		D.add(A);
		D.add(B);
		D.add(C);
		D.add(E);
		E.add(D);
		F.add(G);
		F.add(H);
		G.add(F);
		G.add(I);
		G.add(H);
		I.add(H);
		I.add(J);
		J.add(I);

		ArrayList<GraphNode<String>> allNodes = new ArrayList<GraphNode<String>>();
		allNodes.add(A);
		allNodes.add(B);
		allNodes.add(C);
		allNodes.add(D);
		allNodes.add(E);
		allNodes.add(F);
		allNodes.add(G);
		allNodes.add(H);
		allNodes.add(I);
		allNodes.add(J);
		
	}

	
}
