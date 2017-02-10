package algo.data.structures;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;

public class DoubleLinkedListTest {

	@Test
	public void addTest() {
		DoubleLinkedList<String> doubleLink = new DoubleLinkedList<String>();
		addTwoObjects(doubleLink);

	}

	private void addTwoObjects(DoubleLinkedList<String> doubleLink) {
		addFirstObject(doubleLink);
		addSecondObject(doubleLink);
	}

	private void addThreeObjects(DoubleLinkedList<String> doubleLink) {
		addTwoObjects(doubleLink);
		doubleLink.add("Bird");
		assertFalse(doubleLink.isEmpty());
		assertEquals(doubleLink.get(2), "Bird");
		assertEquals(doubleLink.count(), 3);
	}

	private void addFirstObject(DoubleLinkedList<String> doubleLink) {
		assertTrue(doubleLink.isEmpty());
		doubleLink.add("Dog");
		assertFalse(doubleLink.isEmpty());
		assertEquals(doubleLink.get(0), "Dog");
		assertEquals(doubleLink.count(), 1);
	}

	private void addSecondObject(DoubleLinkedList<String> doubleLink) {
		doubleLink.add("Cat");
		assertFalse(doubleLink.isEmpty());
		assertEquals(doubleLink.get(0), "Dog");
		assertEquals(doubleLink.get(1), "Cat");
		assertEquals(doubleLink.count(), 2);
	}

	@Test
	public void insertTest() {
		DoubleLinkedList<String> doubleLink = new DoubleLinkedList<String>();
		insertIntoEmptyList(doubleLink);
		addFirstObject(doubleLink); // Adding one value to test other cases.
		insertValidValue(doubleLink);
		insertIntoUnusedIndex(doubleLink);
		insertIntoNegativeIndex(doubleLink);
		insertSecondValidValue(doubleLink);

	}

	private void insertIntoEmptyList(DoubleLinkedList<String> doubleLink) {
		assertTrue(doubleLink.isEmpty());
		try {
			doubleLink.insert("Dog", 0);
			fail("Insertion into empty list should throw an exception.");
		} catch (IndexOutOfBoundsException e) {
			assertTrue(doubleLink.isEmpty());
		}
	}

	private void insertValidValue(DoubleLinkedList<String> doubleLink) {
		doubleLink.insert("Chimp", 0);
		assertEquals(doubleLink.get(0), "Chimp");
		assertEquals(doubleLink.get(1), "Dog");
		assertTrue(doubleLink.count() == 2);
	}

	private void insertIntoUnusedIndex(DoubleLinkedList<String> doubleLink) {
		try {
			doubleLink.insert("Dog", 2);
			fail("Insertion into unused index should through exception");
		} catch (IndexOutOfBoundsException e) {
			assertEquals(doubleLink.get(0), "Chimp");
			assertEquals(doubleLink.get(1), "Dog");
			try {
				doubleLink.get(2);
				fail("There should not be anything in index 2 yet.");
			} catch (IndexOutOfBoundsException ef) {
				assertFalse(doubleLink.isEmpty());
			}
		}
	}

	private void insertIntoNegativeIndex(DoubleLinkedList<String> doubleLink) {
		try {
			doubleLink.insert("Bird", -1);
			fail("Negative index should throw error");
		} catch (IndexOutOfBoundsException e) {
			assertEquals(doubleLink.get(0), "Chimp");
			assertEquals(doubleLink.get(1), "Dog");
		}
	}

	private void insertSecondValidValue(DoubleLinkedList<String> doubleLink) {
		doubleLink.insert("Bird", 1);
		assertEquals(doubleLink.get(0), "Chimp");
		assertEquals(doubleLink.get(1), "Bird");
		assertEquals(doubleLink.get(2), "Dog");
		assertTrue(doubleLink.count() == 3);
	}

	@Test
	public void getTest() {
		DoubleLinkedList<String> doubleLink = new DoubleLinkedList<String>();
		getFromEmpty(doubleLink);
		getFromOne(doubleLink);
		getNegative(doubleLink);
		getFromNonExistent(doubleLink);
	}

	private void getFromEmpty(DoubleLinkedList<String> doubleLink) {
		try {
			doubleLink.get(0);
			fail("Cannot get from empty list.");
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, doubleLink.count());
		}
	}

	private void getFromOne(DoubleLinkedList<String> doubleLink) {
		addFirstObject(doubleLink);
		assertFalse(doubleLink.isEmpty());
		assertEquals(doubleLink.get(0), "Dog");
		try {
			doubleLink.get(1);
			fail("Cannot get from nonexistent index.");
		} catch (IndexOutOfBoundsException e) {
			assertEquals(1, doubleLink.count());
		}

	}

	private void getNegative(DoubleLinkedList<String> doubleLink) {
		try {
			doubleLink.get(-1);
			fail("Cannot get from negative index.");
		} catch (IndexOutOfBoundsException e) {
			assertTrue(doubleLink.count() == 1);
		}
	}

	private void getFromNonExistent(DoubleLinkedList<String> doubleLink) {
		try {
			doubleLink.get(1);
			fail("Cannot get from nonexistent index.");
		} catch (IndexOutOfBoundsException e) {
			assertEquals(1, doubleLink.count());
		}
	}

	@Test
	public void removeTest() {
		DoubleLinkedList<String> doubleLink = new DoubleLinkedList<String>();
		removeFromEmpty(doubleLink);
		addFirstObject(doubleLink);
		removeFromOne(doubleLink);
		addTwoObjects(doubleLink);
		removeFromMultiple(doubleLink);
	}

	private void removeFromEmpty(DoubleLinkedList<String> doubleLink) {
		try {
			doubleLink.remove();
			fail("Cannot remove from empty list.");
		} catch (NoSuchElementException e) {
			assertEquals(0, doubleLink.count());
		}
	}

	private void removeFromOne(DoubleLinkedList<String> doubleLink) {
		String removedString = doubleLink.remove();
		assertEquals("Dog", removedString);
		assertTrue(doubleLink.isEmpty());
		assertEquals(0, doubleLink.count());
	}

	private void removeFromMultiple(DoubleLinkedList<String> doubleLink) {
		String removedString = doubleLink.remove();
		assertEquals("Dog", removedString);
		assertFalse(doubleLink.isEmpty());
		assertEquals(1, doubleLink.count());
		try {
			doubleLink.get(1);
		} catch (IndexOutOfBoundsException e) {
			assertEquals(1, doubleLink.count());
		}

	}

	@Test
	public void removeAtTest() {
		DoubleLinkedList<String> doubleLink = new DoubleLinkedList<String>();
		removeAtFromEmpty(doubleLink);
		removeAtFromOne(doubleLink);
		removeAtFromMultiple(doubleLink);
		removeAtFromMultiple2(doubleLink);
		doubleLink.clear();
		removeAtFromMultiple3(doubleLink);
	}

	private void removeAtFromEmpty(DoubleLinkedList<String> doubleLink) {
		try {
			doubleLink.removeAt(0);
			fail("Cannot remove from empty list.");
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, doubleLink.count());
		}
	}

	private void removeAtFromOne(DoubleLinkedList<String> doubleLink) {
		getFromOne(doubleLink);
		assertEquals("Dog", doubleLink.remove());
		getFromEmpty(doubleLink);
	}

	private void removeAtFromMultiple(DoubleLinkedList<String> doubleLink) {
		removeAtFromMiddle(doubleLink);
		doubleLink.clear();
		removeAtFromFirst(doubleLink);
		doubleLink.clear();
		removeAtFromLast(doubleLink);
		doubleLink.clear();
	}
	private void removeAtFromMultiple2(DoubleLinkedList<String> doubleLink) {
		doubleLink.add("Dog");
		doubleLink.add("Cat");
		doubleLink.add("Bird");
		doubleLink.add("Chimp");
		doubleLink.add("Hamster");
		doubleLink.add("Frog");
		doubleLink.add("Fish");
		doubleLink.add("Snake");
		doubleLink.add("Mouse");
		doubleLink.add("Ant");
		String list = doubleLink.toString();
		
		assertEquals("Frog", doubleLink.removeAt(5));
		list = doubleLink.toString();
		assertEquals("Hamster", doubleLink.get(4));
		assertEquals("Fish", doubleLink.get(5));
		assertEquals("Dog, Cat, Bird, Chimp, Hamster, Fish, Snake, Mouse, Ant", doubleLink.toString());
	}
	private void removeAtFromMultiple3(DoubleLinkedList<String> doubleLink) {
		doubleLink.add("Dog");
		doubleLink.add("Cat");
		doubleLink.add("Bird");
		doubleLink.add("Chimp");
		doubleLink.add("Hamster");
		doubleLink.add("Frog");
		doubleLink.add("Fish");
		doubleLink.add("Snake");
		doubleLink.add("Mouse");
		doubleLink.add("Ant");
		String list = doubleLink.toString();
		
		assertEquals("Ant", doubleLink.removeAt(9));
		list = doubleLink.toString();
		assertEquals("Hamster", doubleLink.get(4));
		assertEquals("Mouse", doubleLink.get(8));
		assertEquals("Dog, Cat, Bird, Chimp, Hamster, Frog, Fish, Snake, Mouse", doubleLink.toString());
	}

	private void removeAtFromMiddle(DoubleLinkedList<String> doubleLink) {
		addThreeObjects(doubleLink);
		assertEquals("Cat", doubleLink.removeAt(1));
		assertEquals("Dog", doubleLink.get(0));
		assertEquals("Bird", doubleLink.get(1));
	}

	private void removeAtFromFirst(DoubleLinkedList<String> doubleLink) {
		addThreeObjects(doubleLink);
		assertEquals("Dog", doubleLink.removeAt(0));
		assertEquals("Cat", doubleLink.get(0));
		assertEquals("Bird", doubleLink.get(1));
	}

	private void removeAtFromLast(DoubleLinkedList<String> doubleLink) {
		addThreeObjects(doubleLink);
		assertEquals("Bird", doubleLink.removeAt(2));
		assertEquals("Dog", doubleLink.get(0));
		assertEquals("Cat", doubleLink.get(1));
		try {
			doubleLink.get(2);
			fail("Cannot get from nonexistent index.");
		} catch (IndexOutOfBoundsException e) {
			assertEquals(2, doubleLink.count());
		}
	}

	@Test
	public void removeLastTest() {
		DoubleLinkedList<String> doubleLink = new DoubleLinkedList<String>();
		removeLastFromEmpty(doubleLink);
		removeLastFromOne(doubleLink);
		removeLastFromMultiple(doubleLink);
	}

	private void removeLastFromEmpty(DoubleLinkedList<String> doubleLink) {
		try {
			doubleLink.removeLast();
			fail("There is nothing in the list.");
		} catch (NoSuchElementException e) {
			assertEquals(0, doubleLink.count());
		}
	}

	private void removeLastFromOne(DoubleLinkedList<String> doubleLink) {
		getFromOne(doubleLink);
		assertEquals("Dog", doubleLink.removeLast());
		getFromEmpty(doubleLink);
	}

	private void removeLastFromMultiple(DoubleLinkedList<String> doubleLink) {
		addThreeObjects(doubleLink);
		removeFirstLast(doubleLink);
		removeSecondLast(doubleLink);
		removeFinalLast(doubleLink);
	}

	private void removeFirstLast(DoubleLinkedList<String> doubleLink) {
		assertEquals("Bird", doubleLink.removeLast());
		try {
			doubleLink.get(2);
			fail("Cannot get from nonexistent index.");
		} catch (IndexOutOfBoundsException e) {
			assertEquals(2, doubleLink.count());
		}
	}

	private void removeSecondLast(DoubleLinkedList<String> doubleLink) {
		assertEquals("Cat", doubleLink.removeLast());
		assertFalse(doubleLink.isEmpty());
		assertEquals(doubleLink.get(0), "Dog");
		try {
			doubleLink.get(1);
			fail("Cannot get from nonexistent index.");
		} catch (IndexOutOfBoundsException e) {
			assertEquals(1, doubleLink.count());
		}
	}

	private void removeFinalLast(DoubleLinkedList<String> doubleLink) {
		assertEquals("Dog", doubleLink.removeLast());
		getFromEmpty(doubleLink);
	}

	@Test
	public void toStringTest() {
		DoubleLinkedList<String> doubleLink = new DoubleLinkedList<String>();
		assertEquals("", doubleLink.toString());
		addFirstObject(doubleLink);
		assertEquals("Dog", doubleLink.toString());
		doubleLink.clear();
		addThreeObjects(doubleLink);
		assertEquals("Dog, Cat, Bird", doubleLink.toString());
		doubleLink.removeAt(1);
		assertEquals("Dog, Bird", doubleLink.toString());
	}

	@Test
	public void clearTest() {
		DoubleLinkedList<String> doubleLink = new DoubleLinkedList<String>();
		clearEmpty(doubleLink);
		clearOne(doubleLink);
		clearMultiple(doubleLink);
	}

	private void clearEmpty(DoubleLinkedList<String> doubleLink) {
		doubleLink.clear();
		getFromEmpty(doubleLink);
	}

	private void clearOne(DoubleLinkedList<String> doubleLink) {
		getFromOne(doubleLink);
		doubleLink.clear();
		getFromEmpty(doubleLink);
	}

	private void clearMultiple(DoubleLinkedList<String> doubleLink) {
		addThreeObjects(doubleLink);
		doubleLink.clear();
		getFromEmpty(doubleLink);
	}

	@Test
	public void searchTest() {
		DoubleLinkedList<String> doubleLink = new DoubleLinkedList<String>();
		assertEquals(-1, doubleLink.search("Dog"));
		addThreeObjects(doubleLink);
		assertEquals(0, doubleLink.search("Dog"));
		assertEquals(1, doubleLink.search("Cat"));
		assertEquals(2, doubleLink.search("Bird"));
		assertEquals(-1, doubleLink.search("Chimp"));
	}

}