package algo.data.structures;

import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedListTest {

	@Test
	public void addTest() {
		SingleLinkedList<String> singleLink = new SingleLinkedList<String>();
		assertTrue(singleLink.isEmpty());	
		singleLink.add("Dog");
		assertFalse(singleLink.isEmpty());
		assertEquals(singleLink.get(0), "Dog");
		assertEquals(singleLink.count(), 1);
		singleLink.add("Cat");
		assertFalse(singleLink.isEmpty());
		assertEquals(singleLink.get(0), "Dog");
		assertEquals(singleLink.get(1), "Cat");
		assertEquals(singleLink.count(), 2);

		
	}
	@Test
	public void insertTest(){
		SingleLinkedList<String> singleLink = new SingleLinkedList<String>();
		assertTrue(singleLink.isEmpty());
		try{
			singleLink.insert("Dog", 0);
			fail("Insertion into empty list should throw an exception.");
		}catch (IndexOutOfBoundsException e){
			assertTrue(singleLink.isEmpty());
		}
	//Adding one value so that other cases may be tested.
		singleLink.add("Dog");
		singleLink.insert("Chimp", 0);
		assertEquals(singleLink.get(0), "Chimp");
		assertEquals(singleLink.get(1), "Dog");
		try{
			singleLink.insert("Dog", 2);
			fail("Insertion into unused index should through exception");
		}catch (IndexOutOfBoundsException e){
			assertEquals(singleLink.get(0), "Chimp");
			assertEquals(singleLink.get(1), "Dog");
			try{
				singleLink.get(2);
				fail("There should not be something in index 2 yet.");
			}catch (IndexOutOfBoundsException ef){
				assertFalse(singleLink.isEmpty());
			}
		}
		
	}

}
