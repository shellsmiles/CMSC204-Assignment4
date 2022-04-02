
public class CourseDBElement implements Comparable<CourseDBElement> {

	public String courseID; 
	public String roomNo;
	public String insName;
	public int CRN;
	public int numOfCredits;
	
	public CourseDBElement(String courseID, int CRN, int numOfCredits, String roomNo, String insName) {
		this.courseID = courseID;
		this.CRN = CRN;
		this.numOfCredits = numOfCredits;
		this.roomNo = roomNo;
		this.insName = insName;
	}

	public CourseDBElement() {
		
	}

	@Override
	public int compareTo(CourseDBElement o) {
		
		if (CRN == o.CRN)
			return 0;
		else if (CRN > o.CRN)
			return 1;
		else 
			return -1;
	}

	/**
	 * Gets the CRN
	 * @return
	 */
	public int getCRN() 
	{
		return CRN;
	}

	/**
	 * Gets the course ID
	 * @return
	 */
	public String getID() {	
		return courseID;
	}

	/**
	 * Gets the course room number
	 * @return
	 */
	public String getRoomNum() {
		return roomNo;
	}
	
	/**
	 * toString
	 */
	public String toString()
	{
		return "\nCourse:"+ courseID + " CRN:"+ CRN + " Credits:" + 
				numOfCredits +" Instructor:" + insName + 
				" Room:"+roomNo;
	}


	public void setCRN(int parseInt) {
		CRN= parseInt;
		
	}
	
	
	
}
