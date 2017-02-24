package algo.data.structures;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
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
		removeAll(doubleLink);
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
		assertEquals("Dog", doubleLink.toString());
		String removedString = doubleLink.remove();
		assertEquals("Dog", removedString);
		assertTrue(doubleLink.isEmpty());
		assertEquals(0, doubleLink.count());
		assertEquals("", doubleLink.toString());
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

	public void removeAll(DoubleLinkedList<String> doubleLink) {
		addMany(doubleLink);
		assertEquals("Dog, Cat, Bird, Chimp, Hamster, Frog, Fish, Snake, Mouse, Ant", doubleLink.toString());
		doubleLink.remove();
		assertEquals("Cat, Bird, Chimp, Hamster, Frog, Fish, Snake, Mouse, Ant", doubleLink.toString());
		doubleLink.remove();
		assertEquals("Bird, Chimp, Hamster, Frog, Fish, Snake, Mouse, Ant", doubleLink.toString());
		doubleLink.remove();
		assertEquals("Chimp, Hamster, Frog, Fish, Snake, Mouse, Ant", doubleLink.toString());
		doubleLink.remove();
		assertEquals("Hamster, Frog, Fish, Snake, Mouse, Ant", doubleLink.toString());
		doubleLink.remove();
		assertEquals("Frog, Fish, Snake, Mouse, Ant", doubleLink.toString());
		doubleLink.remove();
		assertEquals("Fish, Snake, Mouse, Ant", doubleLink.toString());
		doubleLink.remove();
		assertEquals("Snake, Mouse, Ant", doubleLink.toString());
		doubleLink.remove();
		assertEquals("Mouse, Ant", doubleLink.toString());
		doubleLink.remove();
		assertEquals("Ant", doubleLink.toString());
		doubleLink.remove();
		assertEquals("", doubleLink.toString());

	}

	private void addMany(DoubleLinkedList<String> doubleLink) {
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
		doubleLink.clear();

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

		assertEquals("Frog", doubleLink.removeAt(5));
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

		assertEquals("Ant", doubleLink.removeAt(9));
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
		doubleLink.clear();
		searchForDuplicate(doubleLink);
	}

	private void searchForDuplicate(DoubleLinkedList<String> doubleLink) {
		doubleLink.add("Dog");
		doubleLink.add("Cat");
		doubleLink.add("Bird");
		doubleLink.add("Chimp");
		doubleLink.add("Hamster");
		doubleLink.add("Frog");
		doubleLink.add("Fish");
		doubleLink.add("Cat");
		doubleLink.add("Snake");
		doubleLink.add("Mouse");
		doubleLink.add("Ant");

		assertEquals(0, doubleLink.search("Dog"));
		assertEquals(1, doubleLink.search("Cat"));
		assertEquals(2, doubleLink.search("Bird"));
		assertEquals(3, doubleLink.search("Chimp"));

	}
	@Test
	public void addAllTest() {
		DoubleLinkedList<String> doubleLink = new DoubleLinkedList<String>();
		addAllToEmpty(doubleLink);
		doubleLink.clear();
		addAllToNotEmpty(doubleLink);
	}

	private void addAllToEmpty(DoubleLinkedList<String> doubleLink) {
		assertTrue(doubleLink.isEmpty());
		doubleLink.addAll(getCollection());
		assertFalse(doubleLink.isEmpty());
		assertEquals("1", doubleLink.get(0));
		assertEquals("2", doubleLink.get(1));
		assertEquals("3", doubleLink.get(2));
		assertEquals("4", doubleLink.get(3));
		assertEquals("5", doubleLink.get(4));
		assertEquals(5, doubleLink.count());

	}

	private void addAllToNotEmpty(DoubleLinkedList<String> doubleLink) {
		addThreeObjects(doubleLink);
		assertEquals("Dog", doubleLink.get(0));
		assertEquals("Cat", doubleLink.get(1));
		assertEquals("Bird", doubleLink.get(2));
		doubleLink.addAll(getCollection());
		assertEquals("Dog", doubleLink.get(0));
		assertEquals("Cat", doubleLink.get(1));
		assertEquals("Bird", doubleLink.get(2));
		assertEquals("1", doubleLink.get(3));
		assertEquals("2", doubleLink.get(4));
		assertEquals("3", doubleLink.get(5));
		assertEquals("4", doubleLink.get(6));
		assertEquals("5", doubleLink.get(7));
		assertEquals(8, doubleLink.count());
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
		DoubleLinkedList<String> doubleLink = new DoubleLinkedList<String>();
		emptyTest(doubleLink);
		doubleLink.addAll(getCollection());
		differentTypeTest(doubleLink);
		doesContainTest(doubleLink);
		doesNotContainTest(doubleLink);
	}

	private void differentTypeTest(DoubleLinkedList<String> doubleLink) {
		assertFalse(doubleLink.contains(new Integer(16)));
		assertFalse(doubleLink.contains((Boolean) false));
	}

	private void doesContainTest(DoubleLinkedList<String> doubleLink) {
		assertTrue(doubleLink.contains("1"));
		assertTrue(doubleLink.contains("2"));
	}

	private void doesNotContainTest(DoubleLinkedList<String> doubleLink) {
		assertFalse(doubleLink.contains("Dog"));
		assertFalse(doubleLink.contains("Cat"));
	}

	private void emptyTest(DoubleLinkedList<String> doubleLink) {
		assertFalse(doubleLink.contains("Dog"));
		assertFalse(doubleLink.contains("Cat"));
	}

	@Test
	public void containsAllTest() {
		DoubleLinkedList<String> doubleLink = new DoubleLinkedList<String>();
		addThreeObjects(doubleLink);
		allDifferentTypeTest(doubleLink);
		allDoesNotContainTest(doubleLink);
		doubleLink.clear();
		allEmptyTest(doubleLink);
		doubleLink.addAll(getCollection());
		containsAllTest(doubleLink);
	}

	private void allDifferentTypeTest(DoubleLinkedList<String> doubleLink) {
		ArrayList<Integer> collection = new ArrayList<Integer>();
		collection.add(1);
		collection.add(2);
		collection.add(3);
		collection.add(4);
		collection.add(5);
		assertFalse(doubleLink.containsAll(collection));
	}

	private void allDoesNotContainTest(DoubleLinkedList<String> doubleLink) {
		assertFalse(doubleLink.containsAll(getCollection()));
	}

	private void allEmptyTest(DoubleLinkedList<String> doubleLink) {
		assertFalse(doubleLink.containsAll(getCollection()));
	}

	private void containsAllTest(DoubleLinkedList<String> doubleLink) {
		assertTrue(doubleLink.containsAll(getCollection()));
	}

	@Test
	public void removeBooleanTest() {
		DoubleLinkedList<String> doubleLink = new DoubleLinkedList<String>();
		assertFalse(doubleLink.remove("Dog"));
		doubleLink.addAll(getCollection());
		assertFalse(doubleLink.remove(new Integer(1)));
		assertTrue(doubleLink.remove("3"));
		assertEquals("1, 2, 4, 5", doubleLink.toString());
	}

	@Test
	public void removeAllTest() {

	}

	@Test
	public void toIteratorTest() {
		DoubleLinkedList<String> doubleLink = new DoubleLinkedList<String>();
		Iterator<String> tester = doubleLink.iterator();
		assertFalse(tester.hasNext());
		doubleLink.addAll(getCollection());
		Integer count = 1;
		tester = doubleLink.iterator();
		while (tester.hasNext()) {
			assertEquals(count.toString(), tester.next());
			++count;
		}
	}

	@Test
	public void toArrayObjTest() {
		DoubleLinkedList<String> doubleLink = new DoubleLinkedList<String>();
		doubleLink.addAll(getCollection());
		Object[] tester = doubleLink.toArray();
		for (int i = 0; i < tester.length; ++i) {
			int equals = i + 1;
			assertEquals(equals + "", tester[i]);
		}

		doubleLink.clear();
		tester = doubleLink.toArray();
		assertEquals(0, tester.length);
	}

	@Test
	public void toArrayTypeTest() {
		DoubleLinkedList<String> doubleLink = new DoubleLinkedList<String>();
		doubleLink.addAll(getCollection());
		tooSmallArrayTest(doubleLink);
		largeEnoughArrayTest(doubleLink);
		hasItemsArrayTest(doubleLink);

	}

	private void tooSmallArrayTest(DoubleLinkedList<String> doubleLink) {
		String[] result = new String[2];
		result = doubleLink.toArray(result);
		for (int i = 0; i < result.length; ++i) {
			int equals = i + 1;
			assertEquals(equals + "", result[i]);
		}
	}

	private void largeEnoughArrayTest(DoubleLinkedList<String> doubleLink) {
		String[] result = new String[10];
		result = doubleLink.toArray(result);
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

	private void hasItemsArrayTest(DoubleLinkedList<String> doubleLink) {
		justEnough(doubleLink);
		notEnough(doubleLink);
		moreThanEnough(doubleLink);
	
	}
	
	private void justEnough(DoubleLinkedList<String> doubleLink){
		String[] result = {"A", "B", "C", "D", "E"};
		result = doubleLink.toArray(result);
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
			assertEquals(5, doubleLink.count());
		}
	}
	
	private void notEnough(DoubleLinkedList<String> doubleLink){
		String[] result = {"A", "B", "C", "D"};
		result = doubleLink.toArray(result);
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
			assertEquals(5, doubleLink.count());
		}
	}
	
	private void moreThanEnough(DoubleLinkedList<String> doubleLink){
		String[] result = {"A", "B", "C", "D", "E", "F", "G"};
		result = doubleLink.toArray(result);
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
			assertEquals(5, doubleLink.count());
		}
	}

	@Test
	public void retainAllTest() {
		DoubleLinkedList<String> doubleLink = new DoubleLinkedList<String>();
		retainOne(doubleLink);
		retainMultiple(doubleLink);
		retainNone(doubleLink);
		retainAll(doubleLink);
	}
	
	private void retainOne(DoubleLinkedList<String> doubleLink){
		doubleLink.addAll(getCollection());
		ArrayList<String> testingList = new ArrayList<String>();
		testingList.add("1");
		
		assertTrue(doubleLink.retainAll(testingList));
		assertEquals("1", doubleLink.get(0));
		try{
			doubleLink.get(1);
			fail("There should not be a 5th array index.");
		}catch (IndexOutOfBoundsException e){
			assertEquals(1, doubleLink.count());
		}
	}
	
	private void retainMultiple(DoubleLinkedList<String> doubleLink){
		doubleLink.clear();
		doubleLink.addAll(getCollection());
		ArrayList<String> testingList = new ArrayList<String>();
		testingList.add("1");
		testingList.add("5");
		assertTrue(doubleLink.retainAll(testingList));
		assertEquals("1", doubleLink.get(0));
		assertEquals("5", doubleLink.get(1));
		try{
			doubleLink.get(2);
			fail("There should not be a 2nd array index.");
		}catch (IndexOutOfBoundsException e){
			assertEquals(2, doubleLink.count());
		}
	}
	private void retainNone(DoubleLinkedList<String> doubleLink){
		doubleLink.clear();
		doubleLink.addAll(getCollection());
		ArrayList<String> testingList = new ArrayList<String>();
		testingList.add("Cat");
		testingList.add("D");
		assertTrue(doubleLink.retainAll(testingList));
		try{
			doubleLink.get(0);
			fail("There should not be a 2nd array index.");
		}catch (IndexOutOfBoundsException e){
			assertEquals(0, doubleLink.count());
		}
	}
	
	private void retainAll(DoubleLinkedList<String> doubleLink){
		doubleLink.clear();
		doubleLink.addAll(getCollection());
		ArrayList<String> testingList = new ArrayList<String>();
		testingList.add("1");
		testingList.add("2");
		testingList.add("3");
		testingList.add("4");
		testingList.add("5");
		assertFalse(doubleLink.retainAll(testingList));
		assertEquals("1", doubleLink.get(0));
		assertEquals("2", doubleLink.get(1));
		assertEquals("3", doubleLink.get(2));
		assertEquals("4", doubleLink.get(3));
		assertEquals("5", doubleLink.get(4));
		try{
			doubleLink.get(5);
			fail("There should not be a 5th array index.");
		}catch (IndexOutOfBoundsException e){
			assertEquals(5, doubleLink.count());
		}
	}
	

}