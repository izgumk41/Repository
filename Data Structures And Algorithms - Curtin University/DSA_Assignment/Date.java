import java.io.Serializable;

/*************************************************
 * AUTHOR: Izan Muhammad
 * PURPOSE: a date object to simulate time passing.
*************************************************/

public class Date implements Serializable
{
	//class fields:
	private int day;
	private int month;
	private int year;
	

	/**************************************************
	 *Default Constructor:
	*IMPORT: none
	*EXPORT: address of new date object
	**************************************************/
	public Date()
	{
		day = 1;
		month = 1;
		year = 2020;
	}
	
	/*************************************************
	 *Alternate Constructor: 
	*IMPORT: inDay, inMonth, inYear
	*EXPORT: address of new Date object
	**************************************************/
	public Date(int inDay, int inMonth, int inYear)
	{
		setDays(inDay);
		setMonths(inMonth);
		setYears(inYear);
	}
	
	//SETTERS
    /************************************************
 *  SUBMODULE: setDays
 *  IMPORT: inDays
 *  EXPORT:none
 ***************************************************/
	public void setDays(int inDay)
	{
		//if days is over 31 day limit.
		if(inDay > 31)
		{
			throw new IllegalArgumentException("invalid day.");
		}
		day = inDay;
	}
	
    /************************************************
 *  SUBMODULE: setMonths
 *  IMPORT: inMonth
 *  EXPORT:none
 ***************************************************/
	
	public void setMonths(int inMonth)
	{
		//if month over 12 month limit.
		if(inMonth > 12)
		{
			throw new IllegalArgumentException("invalid day.");
		}
		
		month = inMonth;
	}
	
    /************************************************
 *  SUBMODULE: setYears
 *  IMPORT: inYeas
 *  EXPORT:none
 ***************************************************/
	public void setYears(int inYear)
	{
		year = inYear;
	}
	
	//END OF SETTER
	
	//GETTERS

    /************************************************
 *  SUBMODULE: getDays
 *  IMPORT: none
 *  EXPORT: day
 ***************************************************/
	public int getDays()
	{
		return day;
	}

    /************************************************
 *  SUBMODULE: getMonths
 *  IMPORT: none
 *  EXPORT: month
 ***************************************************/
	public int getMonths()
	{
		return month;
	}

    /************************************************
 *  SUBMODULE: getYears
 *  IMPORT: none
 *  EXPORT: year
 ***************************************************/
	public int getYears()
	{
		return year;
	}
	
	//END OF GETTERS
	
    /*************************************************
 *  SUBMODULE: addDay
 *  IMPORT: none
 *  EXPORT: none
 *  PURPOSE: to increase day by 1 of date object.
 *  *************************************************/
	
	public void addDay()
	{
		//increase day by 1.
		day+= 1;
		//if day is over 31 day limit.
		if(day > 31)
		{
			//reset day.
			day = 1;
			//increase month by 1.
			month += 1;
			//if month is over 12 month limit.
			if(month > 12)
			{
				//reset month to 1.
				month = 1;
				//increase year by 1.
				year = year+1;
			}
		}
	}
	
    /*************************************************
 *  SUBMODULE: toString
 *  IMPORT: none
 *  EXPORT: String
 *  PURPOSE: to output meaningful message about object.
 *  *************************************************/
	
	public String toString()
	{	
		String actualDay;
		String actualMonth;
		//this is done to keep format of day & month to 2 digits.
		if(day < 10)
		{
			actualDay = "0"+day;
		}
		else
		{
			actualDay = ""+day;
		}
		if(month < 10)
		{
			actualMonth ="0"+month;
		}
		else
		{
			actualMonth = ""+month;
		}
		return actualDay+"/"+actualMonth+"/"+year;
	}
	
	   /*************************************************
	 *  SUBMODULE: equals
	 *  IMPORT: person
	 *  EXPORT: boolean
	 *  PURPOSE: to check if objects are equal.
	 *  *************************************************/
	public boolean equals(Object inObj)
	{
    	
        boolean same = false;
        if(inObj instanceof Date)
        {
            Date inDate = (Date)inObj;
            	
            	if (day == inDate.getDays())
            	{
            		if (month == inDate.getMonths())
            		{
            			if(year == inDate.getYears())
            			{
            				same = true;
            			}
            		}
            	}
            }
    
	    return same;	
	}
	
    /*************************************************
 *  SUBMODULE: getYearDiffernce
 *  IMPORT: none
 *  EXPORT: yearDifference
 *  PURPOSE: to use division to find year difference between to dates.
 *  *************************************************/
	public int getYearDifference(Date inDate)
	{
		//this is used to calculate age 
		int yearDifference = inDate.getYears() - year;
		return yearDifference;
	}
}
