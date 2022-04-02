

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This is the test file for the CourseDBManager
 * which is implemented from the CourseDBManagerInterface
 * 
 */
public class CourseDBManager_STUDENT_Test { 
	private CourseDBManagerInterface myMgr = new CourseDBManager();

	/**
	 * Create an instance of CourseDBManager
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		myMgr = new CourseDBManager();
	}

	/**
	 * Set myMgr reference to null
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		myMgr = null;
	}

	/**
	 * Test for the add method
	 */
	@Test
	public void testAddToDB() {
		try {
			myMgr.add("CMSC203",10001,4,"BB101","Prof.Bag-O-Donuts");
		}
		catch(Exception e) {
			fail("This should not have caused an Exception");
		}
	}
	
	/**
	 * Test for the showAll method
	 */
	@Test
	public void testShowAll() {
		myMgr.add("CMSC203",10001,4,"BB101","Prof.Bag-O-Donuts");
		myMgr.add("CMSC203",10002,4,"BB101","Prof.Who-Dunit");
		myMgr.add("CMSC204",10003,4,"BB102","Prof.Jones");
		ArrayList<String> list = myMgr.showAll();
		assertEquals(list.get(0),"\nCourse:CMSC204 CRN:10003 Credits:4 Instructor:Prof.Jones Room:BB102");
	 	assertEquals(list.get(1),"\nCourse:CMSC203 CRN:10002 Credits:4 Instructor:Prof.Who-Dunit Room:BB101");
		assertEquals(list.get(2),"\nCourse:CMSC203 CRN:10001 Credits:4 Instructor:Prof.Bag-O-Donuts Room:BB101");
	}
	
	/**
	 * Test for the read method
	 */
	@Test
	public void testRead() {
		try {
			File inputFile = new File("StudentTest.txt");
			PrintWriter inFile = new PrintWriter(inputFile);
			inFile.println("CMSC203 10001 4 BB101 Prof.Bag-O-Donuts");
			inFile.println("CMSC203 10002 4 BB101 Prof.Who-Duni");
			
			inFile.close();
			myMgr.readFile(inputFile);
			assertEquals("CMSC203",myMgr.get(10001).getID());
			assertEquals("CMSC203",myMgr.get(10002).getID());
		} catch (Exception e) {
			fail("Should not have thrown an exception");
		}
	}
}


