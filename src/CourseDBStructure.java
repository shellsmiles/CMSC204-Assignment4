import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;





/**
 * Create the CourseDBStructure to hold elements
 * @author yana
 *
 */
public class CourseDBStructure<T> implements CourseDBStructureInterface
{
	public int numOfCourses;
	public String testPur;
	public int tableSize;
	public final double LOADFACTOR= 1.5;
	public int CRN;
	public int ip;
	LinkedList<CourseDBElement>[] hashTable;
	public Node firstNode;
	int numberOfEntries;
	
	/**
	 * Constructor that takes in an integer that is the number of courses
	 * @param NumOfCourses
	 */
	@SuppressWarnings("unchecked")
	public CourseDBStructure(int NumOfCourses)
	{
		this.numOfCourses = NumOfCourses;
		tableSize = getTableSize (numOfCourses, LOADFACTOR, NumOfCourses);
		hashTable = new LinkedList[getTableSize()];
		firstNode = null;
		numberOfEntries=0;
	}
	
	/**
	 * Constructor that is used for testing purposes
	 * @param testPur
	 * @param NumOfCourses
	 */
	@SuppressWarnings("unchecked")
	public CourseDBStructure(String testPur, int tableSize) 
	{
		this.testPur = testPur;
		this.tableSize = tableSize;
		hashTable = new LinkedList[getTableSize()];
		firstNode = null;
		numberOfEntries=0;
	}
	
	/** 
	* Adds a CourseDBElement object to the CourseDBStructure using the hashcode
	* of the CourseDatabaseElemen object's crn value.
	* If the CourseDatabaseElement already exists, exit quietly
	* @param element the CourseDBElement to be added to CourseDBStructure
	*/
	public void add( CourseDBElement element) 
	{
		String strCRN = element.getCRN()+"";
		int hashCode = strCRN.hashCode();
		int ip= hashCode % tableSize;
		ListIterator<CourseDBElement> lIter;
		Node newNode = null;
		
		if (hashTable[ip] == null)
		{
			hashTable[ip]= new LinkedList<>();
			newNode = new Node(element);
			firstNode = newNode;
			numberOfEntries ++;
		}
		else
		{
			lIter = hashTable[ip].listIterator();
			while (lIter.hasNext())
			{
				// fields are the same, then ignore adding the element
				if(lIter.next().compareTo(element)==0)
				{	// if only the CRN are the same, replace it.
					hashTable[ip].set(hashTable[ip].indexOf(lIter.previous()),element);
				}
				else 
				{  	
					newNode = new Node(element);
					newNode.next = firstNode;
					firstNode.prev = newNode;
					newNode.prev = null;
					firstNode = newNode;
					numberOfEntries++;
				}
			}
		}
	}
		
		
	public class Node<T>
	{
		Node prev;
		T value;
		Node next;
		
		Node( T element)
		{
			prev= null;
			this.value = element;
			next = null;
		}
	}
	
	
	/**
	 * Find a courseDatabaseElement based on the key (crn) of the courseDatabaseElement 
	 * If the CourseDatabaseElement is found return it 
	 * If not, throw an IOException
	 * 
	 * @param crn crn (key) whose associated courseDatabaseElement is to be returned
	 * @return a CourseDBElement whose crn is mapped to the key
	 * @throws IOException if key is not found
	 */
	public CourseDBElement get(int crn) throws IOException 
	{
		CourseDBElement result=null;
		for (int i=0; i<numberOfEntries; i++)
		{
			if (hashTable[i] != null)
			{
				ListIterator<CourseDBElement> lIter = hashTable[i].listIterator();
				while(lIter.hasNext())
				{	
					if(lIter.next().CRN == crn)
						result= lIter.previous();
				}
				throw new IOException();		
			}
		}
		return result;
	}
	
	/**
	 * @return an array list of string representation of each course in 
	 * the data structure separated by a new line. 
	 * Refer to the following example:
	 * Course:CMSC500 CRN:39999 Credits:4 Instructor:Nobody InParticular Room:SC100
	 * Course:CMSC600 CRN:4000 Credits:4 Instructor:Somebody Room:SC200
	 */
	public ArrayList<String> showAll() 
	{
		ArrayList<String> elementArray= new ArrayList<>();
		for (int i=0; i<hashTable.length; i++)
		{
			if (hashTable[i] != null)
			{
				ListIterator<CourseDBElement> lIter = hashTable[i].listIterator();
				while(lIter.hasNext())
				{  elementArray.add(lIter.next().toString());} // is iterator.next() a value? why there can't call its value
			}
		}
		
//		Object[] array = null;
//		ArrayList<String> elementArray= new ArrayList<>();
//		for (int i=0; i<hashTable.length; i++)
//		{
//			if (hashTable[i]!=null)
//			{
//				array= hashTable[i].toArray();
//				for(int j=0; j< array.length; j++)
//					elementArray.add((String) array[i]);
//			}
//		}
		return elementArray;
	}
	
	/**
	* Returns the size of the ConcordanceDataStructure (number of indexes in the array)
	*/
	public int getTableSize() 
	{
		return tableSize;
	}
	
	/**
	 * helper method for getting the table size
	 * @param numOfCourses
	 * @param LOADFACTOR
	 * @param NumOfCourses
	 * @return
	 */
	public static int getTableSize (int numOfCourses, double LOADFACTOR, int NumOfCourses)
	{
		double temp;
		int t;
		int tableSize=0;
		if  (numOfCourses% LOADFACTOR== 0)
		{
			temp= numOfCourses / LOADFACTOR;
			t = (int)temp;
		}	
		else
		{
			temp= numOfCourses / LOADFACTOR;
			t = (int)temp +1;
		}
		
		boolean tryPrime= true;
		while ( tryPrime == true)
		{
			if ( isPrime(t) )
			{
				if ( (t - 3)%4 == 0 )
				{
					tableSize = t;
					tryPrime = false;
				}
				System.out.println("3  "+ t+ " "+ tableSize);
			}
			t++;
		}	
		return tableSize;
	}
	
	/**
	 * help method for getting the table size
	 * @param n
	 * @return
	 */
	public static boolean isPrime (int n)
	{
		if (n < 2) return false;
		if (n == 2) return true;
		if (n % 2 ==0) return false;
		
		for (int i=3; i<n; i+=2)
		{
			if ( n % i ==0 )
				return false;
		}
		return true;
	}
}

	
