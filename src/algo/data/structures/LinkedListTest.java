package algo.data.structures;

import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedListTest {

	@Test
	public void addTest() {
		SingleLinkedList<String> singleLink = new SingleLinkedList<String>();
		addFirstObject(singleLink);
		addSecondObject(singleLink);

	}

	private void addFirstObject(SingleLinkedList<String> singleLink) {
		assertTrue(singleLink.isEmpty());
		populateOne(singleLink);
		assertFalse(singleLink.isEmpty());
		assertEquals(singleLink.get(0), "Dog");
		assertEquals(singleLink.count(), 1);
	}

	private void addSecondObject(SingleLinkedList<String> singleLink) {
		singleLink.add("Cat");
		assertFalse(singleLink.isEmpty());
		assertEquals(singleLink.get(0), "Dog");
		assertEquals(singleLink.get(1), "Cat");
		assertEquals(singleLink.count(), 2);
	}

	@Test
	public void insertTest() {
		SingleLinkedList<String> singleLink = new SingleLinkedList<String>();
		insertIntoEmptyList(singleLink);
		populateOne(singleLink); // Adding one value to test other cases.
		insertValidValue(singleLink);
		insertIntoUnusedIndex(singleLink);
		insertIntoNegativeIndex(singleLink);
		insertSecondValidValue(singleLink);

	}

	private void insertIntoEmptyList(SingleLinkedList<String> singleLink) {
		assertTrue(singleLink.isEmpty());
		try {
			singleLink.insert("Dog", 0);
			fail("Insertion into empty list should throw an exception.");
		} catch (IndexOutOfBoundsException e) {
			assertTrue(singleLink.isEmpty());
		}
	}

	private void insertValidValue(SingleLinkedList<String> singleLink) {
		singleLink.insert("Chimp", 0);
		assertEquals(singleLink.get(0), "Chimp");
		assertEquals(singleLink.get(1), "Dog");
		assertTrue(singleLink.count() == 2);
	}

	private void insertIntoUnusedIndex(SingleLinkedList<String> singleLink) {
		try {
			singleLink.insert("Dog", 2);
			fail("Insertion into unused index should through exception");
		} catch (IndexOutOfBoundsException e) {
			assertEquals(singleLink.get(0), "Chimp");
			assertEquals(singleLink.get(1), "Dog");
			try {
				singleLink.get(2);
				fail("There should not be anything in index 2 yet.");
			} catch (IndexOutOfBoundsException ef) {
				assertFalse(singleLink.isEmpty());
			}
		}
	}

	private void insertIntoNegativeIndex(SingleLinkedList<String> singleLink) {
		try {
			singleLink.insert("Bird", -1);
			fail("Negative index should throw error");
		} catch (IndexOutOfBoundsException e) {
			assertEquals(singleLink.get(0), "Chimp");
			assertEquals(singleLink.get(1), "Dog");
		}
	}

	private void insertSecondValidValue(SingleLinkedList<String> singleLink) {
		singleLink.insert("Bird", 1);
		assertEquals(singleLink.get(0), "Chimp");
		assertEquals(singleLink.get(1), "Bird");
		assertEquals(singleLink.get(2), "Dog");
		assertTrue(singleLink.count() == 3);
	}

	@Test
	public void getTest() {
		SingleLinkedList<String> singleLink = new SingleLinkedList<String>();
		getFromEmpty(singleLink);
		getFromOne(singleLink);
		getNegative(singleLink);
		getFromNonExistent(singleLink);
	}

	private void getFromEmpty(SingleLinkedList<String> singleLink) {
		try {
			singleLink.get(0);
			fail("Cannot get from empty list.");
		} catch (IndexOutOfBoundsException e) {
			assertTrue(singleLink.count() == 0);
		}
	}

	private void getFromOne(SingleLinkedList<String> singleLink) {
		populateOne(singleLink);
		assertFalse(singleLink.isEmpty());
		assertEquals(singleLink.get(0), "Dog");
		assertEquals(singleLink.count(), 1);

	}
	

	private void populateOne(SingleLinkedList<String> singleLink) {
		singleLink.add("Dog");
	}

	private void getNegative(SingleLinkedList<String> singleLink){
		try {
			singleLink.get(-1);
			fail("Cannot get from negative index.");
		} catch (IndexOutOfBoundsException e) {
			assertTrue(singleLink.count() == 1);
		}
	}
	
	private void getFromNonExistent(SingleLinkedList<String> singleLink){
		try{
			singleLink.get(1);
			fail("Cannot get from nonexistent index.");
		} catch (IndexOutOfBoundsException e) {
			assertTrue(singleLink.count() == 1);
		}
	}

}

