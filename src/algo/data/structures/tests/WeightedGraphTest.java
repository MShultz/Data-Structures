package algo.data.structures.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import algo.data.structures.WeightedGraph;
import algo.data.structures.WeightedNode;

public class WeightedGraphTest {

	@Test
	public void test() {
		WeightedGraph<String> firstGraph = initializeFirstGraph();
		assertEquals(24, firstGraph.getTotalLength());
		WeightedGraph<String> secondGraph = initializeSecondGraph();
		assertEquals(23, secondGraph.getTotalLength());
	}
	
	private WeightedGraph<String> initializeFirstGraph(){
		ArrayList<WeightedNode<String>> allNodes = new ArrayList<WeightedNode<String>>();
		WeightedNode<String> a = new WeightedNode<String>("1");
		WeightedNode<String> b = new WeightedNode<String>("2");
		WeightedNode<String> c = new WeightedNode<String>("3");
		WeightedNode<String> d = new WeightedNode<String>("4");
		WeightedNode<String> e = new WeightedNode<String>("5");
		allNodes.add(a);
		allNodes.add(b);
		allNodes.add(c);
		allNodes.add(d);
		allNodes.add(e);
		
		a.addChild(d, 3);
		a.addChild(b, 3);
		a.addChild(c, 6);
		b.addChild(a, 3);
		b.addChild(c, 3);
		b.addChild(d, 6);
		c.addChild(a, 6);
		c.addChild(b, 3);
		c.addChild(d, 6);
		d.addChild(a, 3);
		d.addChild(b, 6);
		d.addChild(c, 4);
		d.addChild(e, 15);
		e.addChild(d, 15);
		
		return new WeightedGraph<String>(allNodes);
	}
	
	private WeightedGraph<String> initializeSecondGraph(){
		ArrayList<WeightedNode<String>> allNodes = new ArrayList<WeightedNode<String>>();
		WeightedNode<String> a = new WeightedNode<String>("10");
		WeightedNode<String> b = new WeightedNode<String>("11");
		WeightedNode<String> c = new WeightedNode<String>("12");
		WeightedNode<String> d = new WeightedNode<String>("99");
		WeightedNode<String> e = new WeightedNode<String>("100");
		allNodes.add(a);
		allNodes.add(b);
		allNodes.add(c);
		allNodes.add(d);
		allNodes.add(e);
		
		a.addChild(b, 2);
		a.addChild(c, 4);
		b.addChild(a, 2);
		b.addChild(c, 2);
		c.addChild(a, 4);
		c.addChild(b, 2);
		c.addChild(d, 4);
		d.addChild(c, 4);
		d.addChild(e, 15);
		e.addChild(d, 15);
		return new WeightedGraph<String>(allNodes);
		
	}

}
