package algo.data.structures;

import static org.junit.Assert.*;

import org.junit.Test;

public class BinarySearchTreeTest {

	@Test
	public void addTest() {
		BinarySearchTree<Integer> bs = new BinarySearchTree<Integer>();
		assertFalse(bs.contains(12));
		assertTrue(bs.add(12));
		assertTrue(bs.contains(12));
		assertFalse(bs.contains(8));
		assertTrue(bs.add(8));
		assertTrue(bs.contains(8));
		assertFalse(bs.contains(15));
		assertTrue(bs.add(15));
		assertTrue(bs.contains(15));
		assertFalse(bs.contains(10));
		assertTrue(bs.add(10));
		assertTrue(bs.contains(10));
		assertTrue(bs.add(1000000));
	}

	@Test
	public void containsTest() {
		BinarySearchTree<Integer> bs = new BinarySearchTree<Integer>();
		assertFalse(bs.contains(1000));
		assertFalse(bs.contains(12));
		assertTrue(bs.add(12));
		assertTrue(bs.contains(12));
		assertFalse(bs.contains(8));
		assertTrue(bs.add(8));
		assertTrue(bs.contains(8));
		assertFalse(bs.contains(15));
		assertTrue(bs.add(15));
		assertTrue(bs.contains(15));
		assertFalse(bs.contains(10));
		assertTrue(bs.add(10));
		assertTrue(bs.contains(10));
		assertFalse(bs.contains(1000));
	}

	@Test
	public void countTest() {
		BinarySearchTree<Integer> bs = new BinarySearchTree<Integer>();
		assertEquals(0, bs.count());
		assertTrue(bs.add(12));
		assertEquals(1, bs.count());
		assertTrue(bs.add(8));
		assertTrue(bs.add(15));
		assertTrue(bs.add(10));
		assertEquals(4, bs.count());
	}

	@Test
	public void clearTest() {
		BinarySearchTree<Integer> bs = new BinarySearchTree<Integer>();
		assertTrue(bs.add(8));
		assertTrue(bs.add(15));
		assertTrue(bs.add(10));
		bs.clear();
		assertEquals(0, bs.count());
	}

	@Test
	public void heightTest() {
		BinarySearchTree<Integer> bs = new BinarySearchTree<Integer>();
		heightTest1(bs);
		bs.clear();
		heightTest2(bs);
		bs.clear();
		heightTest3(bs);
		bs.clear();
		heightTest4(bs);
		bs.clear();
		heightTest5(bs);
		bs.clear();
		heightTest6(bs);
		bs.clear();
	}

	private void heightTest1(BinarySearchTree<Integer> bs) {
		for (int i = 1; i <= 10; ++i) {
			bs.add(i);
		}
		assertEquals(9, bs.height());
	}

	private void heightTest2(BinarySearchTree<Integer> bs) {
		bs.add(5);
		bs.add(15);
		bs.add(2);
		bs.add(26);
		bs.add(1);
		bs.add(18);
		assertEquals(3, bs.height());
	}

	private void heightTest3(BinarySearchTree<Integer> bs) {
		for (int i = 10; i > 0; --i) {
			bs.add(i);
		}
		assertEquals(9, bs.height());
	}

	private void heightTest4(BinarySearchTree<Integer> bs) {
		bs.add(5);
		bs.add(6);
		bs.add(7);
		bs.add(1);
		bs.add(2);
		bs.add(3);
		bs.add(9);
		bs.add(8);
		bs.add(7);
		bs.add(5);
		bs.add(4);
		bs.add(3);
		assertEquals(5, bs.height());
	}

	private void heightTest5(BinarySearchTree<Integer> bs) {
		bs.add(1);
		assertEquals(0, bs.height());
	}

	private void heightTest6(BinarySearchTree<Integer> bs) {
		assertEquals(-1, bs.height());
	}
	@Test
	public void inOrderTest(){
		BinarySearchTree<Integer> bs = new BinarySearchTree<Integer>();
		bs.add(10);
		bs.add(7);
		bs.add(3);
		bs.add(8);
		bs.add(12);
		bs.add(11);
		assertEquals("3, 7, 8, 10, 11, 12", bs.inorder());
		
	}
	
	@Test 
	public void preOrderTest(){
		BinarySearchTree<Integer> bs = new BinarySearchTree<Integer>();
		bs.add(10);
		bs.add(7);
		bs.add(3);
		bs.add(8);
		bs.add(12);
		bs.add(11);
		assertEquals("10, 7, 3, 8, 12, 11", bs.preorder());
	}
	
	@Test
	public void postOrderTest(){
		BinarySearchTree<Integer> bs = new BinarySearchTree<Integer>();
		bs.add(10);
		bs.add(7);
		bs.add(3);
		bs.add(8);
		bs.add(12);
		bs.add(11);
		assertEquals("3, 8, 7, 11, 12, 10", bs.postorder());
	}
	
	@Test
	public void removeTest(){
		BinarySearchTree<Integer> bs = new BinarySearchTree<Integer>();
		bs.add(10);
		bs.add(7);
		bs.add(3);
		bs.add(8);
		bs.add(12);
		bs.add(11);
		assertTrue(bs.remove(8));
		assertEquals("3, 7, 10, 11, 12", bs.inorder());
		assertFalse(bs.remove(8));
		assertEquals("3, 7, 10, 11, 12", bs.inorder());
		assertTrue(bs.remove(12));
		assertEquals("3, 7, 10, 11", bs.inorder());
		assertTrue(bs.remove(10));
		assertEquals("3, 7, 11", bs.inorder());
		assertTrue(bs.remove(7));
		assertEquals("3, 11", bs.inorder());
		assertTrue(bs.remove(3));
		assertEquals("11", bs.inorder());
	}
	
	@Test
	public void toArrayTest(){
		BinarySearchTree<Integer> bs = new BinarySearchTree<Integer>();
		assertEquals("[]", bs.toArray().toString());
	}
}
