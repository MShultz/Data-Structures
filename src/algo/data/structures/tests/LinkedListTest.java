package algo.data.structures.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Test;

import algo.data.structures.SingleLinkedList;

public class LinkedListTest {

	@Test
	public void addTest() {
		SingleLinkedList<String> singleLink = new SingleLinkedList<String>();
		addTwoObjects(singleLink);

	}

	private void addTwoObjects(SingleLinkedList<String> singleLink) {
		addFirstObject(singleLink);
		addSecondObject(singleLink);
	}

	private void addThreeObjects(SingleLinkedList<String> singleLink) {
		addTwoObjects(singleLink);
		singleLink.add("Bird");
		assertFalse(singleLink.isEmpty());
		assertEquals(singleLink.get(2), "Bird");
		assertEquals(singleLink.count(), 3);
	}

	private void addFirstObject(SingleLinkedList<String> singleLink) {
		assertTrue(singleLink.isEmpty());
		singleLink.add("Dog");
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
		addFirstObject(singleLink); // Adding one value to test other cases.
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
			assertEquals(0, singleLink.count());
		}
	}

	private void getFromOne(SingleLinkedList<String> singleLink) {
		addFirstObject(singleLink);
		assertFalse(singleLink.isEmpty());
		assertEquals(singleLink.get(0), "Dog");
		try {
			singleLink.get(1);
			fail("Cannot get from nonexistent index.");
		} catch (IndexOutOfBoundsException e) {
			assertEquals(1, singleLink.count());
		}

	}

	private void getNegative(SingleLinkedList<String> singleLink) {
		try {
			singleLink.get(-1);
			fail("Cannot get from negative index.");
		} catch (IndexOutOfBoundsException e) {
			assertTrue(singleLink.count() == 1);
		}
	}

	private void getFromNonExistent(SingleLinkedList<String> singleLink) {
		try {
			singleLink.get(1);
			fail("Cannot get from nonexistent index.");
		} catch (IndexOutOfBoundsException e) {
			assertEquals(1, singleLink.count());
		}
	}

	@Test
	public void removeTest() {
		SingleLinkedList<String> singleLink = new SingleLinkedList<String>();
		removeFromEmpty(singleLink);
		addFirstObject(singleLink);
		removeFromOne(singleLink);
		addTwoObjects(singleLink);
		removeFromMultiple(singleLink);
	}

	private void removeFromEmpty(SingleLinkedList<String> singleLink) {
		try {
			singleLink.remove();
			fail("Cannot remove from empty list.");
		} catch (NoSuchElementException e) {
			assertEquals(0, singleLink.count());
		}
	}

	private void removeFromOne(SingleLinkedList<String> singleLink) {
		assertEquals("Dog", singleLink.toString());
		String removedString = singleLink.remove();
		assertEquals("Dog", removedString);
		assertTrue(singleLink.isEmpty());
		assertEquals(0, singleLink.count());
		assertEquals("", singleLink.toString());
	}

	private void removeFromMultiple(SingleLinkedList<String> singleLink) {
		String removedString = singleLink.remove();
		assertEquals("Dog", removedString);
		assertFalse(singleLink.isEmpty());
		assertEquals(1, singleLink.count());
		try {
			singleLink.get(1);
		} catch (IndexOutOfBoundsException e) {
			assertEquals(1, singleLink.count());
		}

	}

	@Test
	public void removeAtTest() {
		SingleLinkedList<String> singleLink = new SingleLinkedList<String>();
		removeAtFromEmpty(singleLink);
		removeAtFromOne(singleLink);
		removeAtFromMultiple(singleLink);
		removeAtFromMultiple2(singleLink);
		singleLink.clear();
		removeAtFromMultiple3(singleLink);

	}

	private void removeAtFromEmpty(SingleLinkedList<String> singleLink) {
		try {
			singleLink.removeAt(0);
			fail("Cannot remove from empty list.");
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, singleLink.count());
		}
	}

	private void removeAtFromOne(SingleLinkedList<String> singleLink) {
		getFromOne(singleLink);
		assertEquals("Dog", singleLink.remove());
		getFromEmpty(singleLink);
	}

	private void removeAtFromMultiple(SingleLinkedList<String> singleLink) {
		removeAtFromMiddle(singleLink);
		singleLink.clear();
		removeAtFromFirst(singleLink);
		singleLink.clear();
		removeAtFromLast(singleLink);
		singleLink.clear();
	}

	private void removeAtFromMultiple2(SingleLinkedList<String> singleLink) {
		singleLink.add("Dog");
		singleLink.add("Cat");
		singleLink.add("Bird");
		singleLink.add("Chimp");
		singleLink.add("Hamster");
		singleLink.add("Frog");
		singleLink.add("Fish");
		singleLink.add("Snake");
		singleLink.add("Mouse");
		singleLink.add("Ant");

		assertEquals("Frog", singleLink.removeAt(5));
		assertEquals("Hamster", singleLink.get(4));
		assertEquals("Fish", singleLink.get(5));
		assertEquals("Dog, Cat, Bird, Chimp, Hamster, Fish, Snake, Mouse, Ant", singleLink.toString());

	}

	private void removeAtFromMultiple3(SingleLinkedList<String> doubleLink) {
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

		assertEquals("Ant", doubleLink.removeAt(9));
		assertEquals("Hamster", doubleLink.get(4));
		assertEquals("Mouse", doubleLink.get(8));
		assertEquals("Dog, Cat, Bird, Chimp, Hamster, Frog, Fish, Snake, Mouse", doubleLink.toString());
	}

	private void removeAtFromMiddle(SingleLinkedList<String> singleLink) {
		addThreeObjects(singleLink);
		assertEquals("Cat", singleLink.removeAt(1));
		assertEquals("Dog", singleLink.get(0));
		assertEquals("Bird", singleLink.get(1));
	}

	private void removeAtFromFirst(SingleLinkedList<String> singleLink) {
		addThreeObjects(singleLink);
		assertEquals("Dog", singleLink.removeAt(0));
		assertEquals("Cat", singleLink.get(0));
		assertEquals("Bird", singleLink.get(1));
	}

	private void removeAtFromLast(SingleLinkedList<String> singleLink) {
		addThreeObjects(singleLink);
		assertEquals("Bird", singleLink.removeAt(2));
		assertEquals("Dog", singleLink.get(0));
		assertEquals("Cat", singleLink.get(1));
		try {
			singleLink.get(2);
			fail("Cannot get from nonexistent index.");
		} catch (IndexOutOfBoundsException e) {
			assertEquals(2, singleLink.count());
		}
	}

	@Test
	public void removeLastTest() {
		SingleLinkedList<String> singleLink = new SingleLinkedList<String>();
		removeLastFromEmpty(singleLink);
		removeLastFromOne(singleLink);
		removeLastFromMultiple(singleLink);
	}

	private void removeLastFromEmpty(SingleLinkedList<String> singleLink) {
		try {
			singleLink.removeLast();
			fail("There is nothing in the list.");
		} catch (NoSuchElementException e) {
			assertEquals(0, singleLink.count());
		}
	}

	private void removeLastFromOne(SingleLinkedList<String> singleLink) {
		getFromOne(singleLink);
		assertEquals("Dog", singleLink.removeLast());
		getFromEmpty(singleLink);
	}

	private void removeLastFromMultiple(SingleLinkedList<String> singleLink) {
		addThreeObjects(singleLink);
		removeFirstLast(singleLink);
		removeSecondLast(singleLink);
		removeFinalLast(singleLink);
	}

	private void removeFirstLast(SingleLinkedList<String> singleLink) {
		assertEquals("Bird", singleLink.removeLast());
		try {
			singleLink.get(2);
			fail("Cannot get from nonexistent index.");
		} catch (IndexOutOfBoundsException e) {
			assertEquals(2, singleLink.count());
		}
	}

	private void removeSecondLast(SingleLinkedList<String> singleLink) {
		assertEquals("Cat", singleLink.removeLast());
		assertFalse(singleLink.isEmpty());
		assertEquals(singleLink.get(0), "Dog");
		try {
			singleLink.get(1);
			fail("Cannot get from nonexistent index.");
		} catch (IndexOutOfBoundsException e) {
			assertEquals(1, singleLink.count());
		}
	}

	private void removeFinalLast(SingleLinkedList<String> singleLink) {
		assertEquals("Dog", singleLink.removeLast());
		getFromEmpty(singleLink);
	}

	@Test
	public void toStringTest() {
		SingleLinkedList<String> singleLink = new SingleLinkedList<String>();
		assertEquals("", singleLink.toString());
		addFirstObject(singleLink);
		assertEquals("Dog", singleLink.toString());
		singleLink.clear();
		addThreeObjects(singleLink);
		assertEquals("Dog, Cat, Bird", singleLink.toString());
		singleLink.removeAt(1);
		assertEquals("Dog, Bird", singleLink.toString());
	}

	@Test
	public void clearTest() {
		SingleLinkedList<String> singleLink = new SingleLinkedList<String>();
		clearEmpty(singleLink);
		clearOne(singleLink);
		clearMultiple(singleLink);
	}

	private void clearEmpty(SingleLinkedList<String> singleLink) {
		singleLink.clear();
		getFromEmpty(singleLink);
	}

	private void clearOne(SingleLinkedList<String> singleLink) {
		getFromOne(singleLink);
		singleLink.clear();
		getFromEmpty(singleLink);
	}

	private void clearMultiple(SingleLinkedList<String> singleLink) {
		addThreeObjects(singleLink);
		singleLink.clear();
		getFromEmpty(singleLink);
	}

	@Test
	public void searchTest() {
		SingleLinkedList<String> singleLink = new SingleLinkedList<String>();
		assertEquals(-1, singleLink.search("Dog"));
		addThreeObjects(singleLink);
		assertEquals(0, singleLink.search("Dog"));
		assertEquals(1, singleLink.search("Cat"));
		assertEquals(2, singleLink.search("Bird"));
		assertEquals(-1, singleLink.search("Chimp"));
		singleLink.clear();
		searchForDuplicate(singleLink);
	}

	private void searchForDuplicate(SingleLinkedList<String> singleLink) {
		singleLink.add("Dog");
		singleLink.add("Cat");
		singleLink.add("Bird");
		singleLink.add("Chimp");
		singleLink.add("Hamster");
		singleLink.add("Frog");
		singleLink.add("Fish");
		singleLink.add("Cat");
		singleLink.add("Snake");
		singleLink.add("Mouse");
		singleLink.add("Ant");

		assertEquals(0, singleLink.search("Dog"));
		assertEquals(1, singleLink.search("Cat"));
		assertEquals(2, singleLink.search("Bird"));
		assertEquals(3, singleLink.search("Chimp"));

	}

	@Test
	public void addAllTest() {
		SingleLinkedList<String> singleLink = new SingleLinkedList<String>();
		addAllToEmpty(singleLink);
		singleLink.clear();
		addAllToNotEmpty(singleLink);
	}

	private void addAllToEmpty(SingleLinkedList<String> singleLink) {
		assertTrue(singleLink.isEmpty());
		singleLink.addAll(getCollection());
		assertFalse(singleLink.isEmpty());
		assertEquals("1", singleLink.get(0));
		assertEquals("2", singleLink.get(1));
		assertEquals("3", singleLink.get(2));
		assertEquals("4", singleLink.get(3));
		assertEquals("5", singleLink.get(4));
		assertEquals(5, singleLink.count());

	}

	private void addAllToNotEmpty(SingleLinkedList<String> singleLink) {
		addThreeObjects(singleLink);
		assertEquals("Dog", singleLink.get(0));
		assertEquals("Cat", singleLink.get(1));
		assertEquals("Bird", singleLink.get(2));
		singleLink.addAll(getCollection());
		assertEquals("Dog", singleLink.get(0));
		assertEquals("Cat", singleLink.get(1));
		assertEquals("Bird", singleLink.get(2));
		assertEquals("1", singleLink.get(3));
		assertEquals("2", singleLink.get(4));
		assertEquals("3", singleLink.get(5));
		assertEquals("4", singleLink.get(6));
		assertEquals("5", singleLink.get(7));
		assertEquals(8, singleLink.count());
	}

	private ArrayList<String> getCollection() {
		ArrayList<String> collection = new ArrayList<String>();
		collection.add("1");
		collection.add("2");
		collection.add("3");
		collection.add("4");
		collection.add("5");
		return collection;
	}

	@Test
	public void containsTest() {
		SingleLinkedList<String> singleLink = new SingleLinkedList<String>();
		emptyTest(singleLink);
		singleLink.addAll(getCollection());
		differentTypeTest(singleLink);
		doesContainTest(singleLink);
		doesNotContainTest(singleLink);
	}

	private void differentTypeTest(SingleLinkedList<String> singleLink) {
		assertFalse(singleLink.contains(new Integer(16)));
		assertFalse(singleLink.contains((Boolean) false));
	}

	private void doesContainTest(SingleLinkedList<String> singleLink) {
		assertTrue(singleLink.contains("1"));
		assertTrue(singleLink.contains("2"));
	}

	private void doesNotContainTest(SingleLinkedList<String> singleLink) {
		assertFalse(singleLink.contains("Dog"));
		assertFalse(singleLink.contains("Cat"));
	}

	private void emptyTest(SingleLinkedList<String> singleLink) {
		assertFalse(singleLink.contains("Dog"));
		assertFalse(singleLink.contains("Cat"));
	}

	@Test
	public void containsAllTest() {
		SingleLinkedList<String> singleLink = new SingleLinkedList<String>();
		addThreeObjects(singleLink);
		allDifferentTypeTest(singleLink);
		allDoesNotContainTest(singleLink);
		singleLink.clear();
		allEmptyTest(singleLink);
		singleLink.addAll(getCollection());
		containsAllTest(singleLink);
	}

	private void allDifferentTypeTest(SingleLinkedList<String> singleLink) {
		ArrayList<Integer> collection = new ArrayList<Integer>();
		collection.add(1);
		collection.add(2);
		collection.add(3);
		collection.add(4);
		collection.add(5);
		assertFalse(singleLink.containsAll(collection));
	}

	private void allDoesNotContainTest(SingleLinkedList<String> singleLink) {
		assertFalse(singleLink.containsAll(getCollection()));
	}

	private void allEmptyTest(SingleLinkedList<String> singleLink) {
		assertFalse(singleLink.containsAll(getCollection()));
	}

	private void containsAllTest(SingleLinkedList<String> singleLink) {
		assertTrue(singleLink.containsAll(getCollection()));
	}

	@Test
	public void removeBooleanTest() {
		SingleLinkedList<String> singleLink = new SingleLinkedList<String>();
		assertFalse(singleLink.remove("Dog"));
		singleLink.addAll(getCollection());
		assertFalse(singleLink.remove(new Integer(1)));
		assertTrue(singleLink.remove("3"));
		assertEquals("1, 2, 4, 5", singleLink.toString());
	}

	@Test
	public void removeAllTest() {

	}

	@Test
	public void toIteratorTest() {
		SingleLinkedList<String> singleLink = new SingleLinkedList<String>();
		Iterator<String> tester = singleLink.iterator();
		assertFalse(tester.hasNext());
		singleLink.addAll(getCollection());
		Integer count = 1;
		tester = singleLink.iterator();
		while (tester.hasNext()) {
			assertEquals(count.toString(), tester.next());
			++count;
		}
	}

	@Test
	public void toArrayObjTest() {
		SingleLinkedList<String> singleLink = new SingleLinkedList<String>();
		singleLink.addAll(getCollection());
		Object[] tester = singleLink.toArray();
		for (int i = 0; i < tester.length; ++i) {
			int equals = i + 1;
			assertEquals(equals + "", tester[i]);
		}

		singleLink.clear();
		tester = singleLink.toArray();
		assertEquals(0, tester.length);
	}

	@Test
	public void toArrayTypeTest() {
		SingleLinkedList<String> singleLink = new SingleLinkedList<String>();
		singleLink.addAll(getCollection());
		tooSmallArrayTest(singleLink);
		largeEnoughArrayTest(singleLink);
		hasItemsArrayTest(singleLink);

	}

	private void tooSmallArrayTest(SingleLinkedList<String> singleLink) {
		String[] result = new String[2];
		result = singleLink.toArray(result);
		for (int i = 0; i < result.length; ++i) {
			int equals = i + 1;
			assertEquals(equals + "", result[i]);
		}
	}

	private void largeEnoughArrayTest(SingleLinkedList<String> singleLink) {
		String[] result = new String[10];
		result = singleLink.toArray(result);
		assertEquals("1", result[0]);
		assertEquals("2", result[1]);
		assertEquals("3", result[2]);
		assertEquals("4", result[3]);
		assertEquals("5", result[4]);
		assertEquals(null, result[5]);
		assertEquals(null, result[6]);
		assertEquals(null, result[7]);
		assertEquals(null, result[8]);
		assertEquals(null, result[9]);
	}

	private void hasItemsArrayTest(SingleLinkedList<String> singleLink) {
		justEnough(singleLink);
		notEnough(singleLink);
		moreThanEnough(singleLink);
	
	}
	
	private void justEnough(SingleLinkedList<String> singleLink){
		String[] result = {"A", "B", "C", "D", "E"};
		result = singleLink.toArray(result);
		assertEquals("1", result[0]);
		assertEquals("2", result[1]);
		assertEquals("3", result[2]);
		assertEquals("4", result[3]);
		assertEquals("5", result[4]);
	
		try{
			String shouldNotExist = result[5];
			System.out.println(shouldNotExist);
			fail("There should not be a 5th array index.");
		}catch (ArrayIndexOutOfBoundsException e){
			assertEquals(5, singleLink.count());
		}
	}
	
	private void notEnough(SingleLinkedList<String> singleLink){
		String[] result = {"A", "B", "C", "D"};
		result = singleLink.toArray(result);
		assertEquals("1", result[0]);
		assertEquals("2", result[1]);
		assertEquals("3", result[2]);
		assertEquals("4", result[3]);
		assertEquals("5", result[4]);
	
		try{
			String shouldNotExist = result[5];
			System.out.println(shouldNotExist);
			fail("There should not be a 5th array index.");
		}catch (ArrayIndexOutOfBoundsException e){
			assertEquals(5, singleLink.count());
		}
	}
	
	private void moreThanEnough(SingleLinkedList<String> singleLink){
		String[] result = {"A", "B", "C", "D", "E", "F", "G"};
		result = singleLink.toArray(result);
		assertEquals("1", result[0]);
		assertEquals("2", result[1]);
		assertEquals("3", result[2]);
		assertEquals("4", result[3]);
		assertEquals("5", result[4]);
		assertEquals("F", result[5]);
		assertEquals("G", result[6]);
		try{
			String shouldNotExist = result[7];
			System.out.println(shouldNotExist);
			fail("There should not be a 5th array index.");
		}catch (ArrayIndexOutOfBoundsException e){
			assertEquals(5, singleLink.count());
		}
	}

	@Test
	public void retainAllTest() {
		SingleLinkedList<String> singleLink = new SingleLinkedList<String>();
		retainOne(singleLink);
		retainMultiple(singleLink);
		retainNone(singleLink);
		retainAll(singleLink);
	}
	
	private void retainOne(SingleLinkedList<String> singleLink){
		singleLink.addAll(getCollection());
		ArrayList<String> testingList = new ArrayList<String>();
		testingList.add("1");
		
		assertTrue(singleLink.retainAll(testingList));
		assertEquals("1", singleLink.get(0));
		try{
			singleLink.get(1);
			fail("There should not be a 5th array index.");
		}catch (IndexOutOfBoundsException e){
			assertEquals(1, singleLink.count());
		}
	}
	
	private void retainMultiple(SingleLinkedList<String> singleLink){
		singleLink.clear();
		singleLink.addAll(getCollection());
		ArrayList<String> testingList = new ArrayList<String>();
		testingList.add("1");
		testingList.add("5");
		assertTrue(singleLink.retainAll(testingList));
		assertEquals("1", singleLink.get(0));
		assertEquals("5", singleLink.get(1));
		try{
			singleLink.get(2);
			fail("There should not be a 2nd array index.");
		}catch (IndexOutOfBoundsException e){
			assertEquals(2, singleLink.count());
		}
	}
	private void retainNone(SingleLinkedList<String> singleLink){
		singleLink.clear();
		singleLink.addAll(getCollection());
		ArrayList<String> testingList = new ArrayList<String>();
		testingList.add("Cat");
		testingList.add("D");
		assertTrue(singleLink.retainAll(testingList));
		try{
			singleLink.get(0);
			fail("There should not be a 2nd array index.");
		}catch (IndexOutOfBoundsException e){
			assertEquals(0, singleLink.count());
		}
	}
	
	private void retainAll(SingleLinkedList<String> singleLink){
		singleLink.clear();
		singleLink.addAll(getCollection());
		ArrayList<String> testingList = new ArrayList<String>();
		testingList.add("1");
		testingList.add("2");
		testingList.add("3");
		testingList.add("4");
		testingList.add("5");
		assertFalse(singleLink.retainAll(testingList));
		assertEquals("1", singleLink.get(0));
		assertEquals("2", singleLink.get(1));
		assertEquals("3", singleLink.get(2));
		assertEquals("4", singleLink.get(3));
		assertEquals("5", singleLink.get(4));
		try{
			singleLink.get(5);
			fail("There should not be a 5th array index.");
		}catch (IndexOutOfBoundsException e){
			assertEquals(5, singleLink.count());
		}
	}
	
}
