
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Manage all CourseDB elements into CourseDBStructure
 * @author yana
 *
 */

public class CourseDBManager implements CourseDBManagerInterface 
{
	CourseDBStructure cs= new CourseDBStructure(20);
	
	/**
	 * Add a CourseDBElement to CourseStructure
	 */
	@Override
	public void add(String id, int crn, int credits, String roomNum, String instructor) {
		CourseDBElement ce= new CourseDBElement(id, crn, credits,roomNum, instructor);
		cs.add(ce);
	}

	/**
	 * Get the CourseDBElement based on the crn
	 */
	@Override
	public CourseDBElement get(int crn)
	{
		CourseDBElement result= null;
		try {
			result = cs.get(crn);
		} catch (IOException e) 
		{
			System.out.print(e);
		}
		return result;
	}

	/**
	 * Read the file
	 */
	@Override
	public void readFile(File input) throws FileNotFoundException 
	{
		ArrayList<String> inputArray = new ArrayList<>();
		String in;
		int CRN, courseCredit;
		String[] seperate = null;
		try
		{
			Scanner s= new Scanner(input);
			while (s.hasNext())
			{
				in= s.nextLine();
				inputArray.add(in);
			}
			for ( int i=0; i<inputArray.size(); i++)
			{
				seperate = inputArray.get(i).split("");
				CRN = Integer.parseInt(seperate[1]);
				courseCredit= Integer.parseInt(seperate[2]);
				CourseDBElement tem= new CourseDBElement(seperate[0], CRN, courseCredit, seperate[3],seperate[4]);
			}
			s.close();
		}
		catch(FileNotFoundException a)
		{
			System.out.println(a);
		}
	}

	/**
	 * to show all the elements that added in the hashtable
	 */
	@Override
	public ArrayList<String> showAll() 
	{
		ArrayList<String> elementArray = new ArrayList<>();
		
		for( int i=0; i< cs.hashTable.length; i++)
		{
			for(Object item : cs.hashTable[i].toArray())
				elementArray.add(item.toString());
		}
		return elementArray;
	}

}
