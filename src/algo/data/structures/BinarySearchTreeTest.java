package algo.data.structures;

import static org.junit.Assert.*;

import org.junit.Test;

public class BinarySearchTreeTest {

	@Test
	public void test() {
		BinarySearchTree<Integer> bs = new BinarySearchTree<Integer>();
		bs.add(12);
		bs.add(8);
		bs.add(15);
		bs.add(10);
		
		assertTrue(bs.contains(10));
		assertFalse(bs.contains(188));
	}

}
