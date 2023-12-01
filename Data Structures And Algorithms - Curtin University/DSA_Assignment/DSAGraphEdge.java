import java.io.Serializable;

public class DSAGraphEdge implements Serializable
{
	private Person from;
	private Person to;
	private char group;
	private String typeOfContact;
	private int distance;
	private double contactRate;
	
	/*************************************************
	 *Alternate Constructor: 
	*IMPORT: inFrom, inTo, inGroup
	*EXPORT: address of new DSAGraphEdge object
	**************************************************/
	public DSAGraphEdge(Person inFrom, Person inTo,char inGroup)
	{
		setFrom(inFrom);
		setTo(inTo);
		setGroup(inGroup);
		typeOfContact = "";
		setTypeOfContact(inGroup);
		distance = 0;
		setDistance(inGroup);
		contactRate = 0;
	}

	//SETTERS
    /************************************************
 *  SUBMODULE: setDistance
 *  IMPORT: inGroup
 *  EXPORT:none
 ***************************************************/
	public void setDistance(char inGroup)
	{
		//distance will be initialized depending on group.
		if(inGroup == 'W')
		{
			distance = 7;
		}
		else if(inGroup == 'S')
		{
			distance = 4;
		}
		else if(inGroup == 'H')
		{
			distance = 3;
		}
		else if(inGroup == 'C')
		{
			distance = 2;
		}
		else
		{
			//if group invalid
			throw new IllegalArgumentException("invalid group");
		}
		
	}

    /************************************************
 *  SUBMODULE: setFrom
 *  IMPORT: inFrom
 *  EXPORT:none
 ***************************************************/
	public void setFrom(Person inFrom)
	{
		from = inFrom;		
	}
    /************************************************
 *  SUBMODULE: setTo
 *  IMPORT: inTo
 *  EXPORT:none
 ***************************************************/
	public void setTo(Person inTo)
	{		
		to = inTo;	
	}
	
    /************************************************
 *  SUBMODULE: setGroup
 *  IMPORT: inGroup
 *  EXPORT:none
 ***************************************************/
	public void setGroup(char inGroup)
	{
		//if group is valid
		if(inGroup == 'W' || inGroup == 'S' || inGroup == 'H' || inGroup == 'C')
		{
			group = inGroup;
		}
		else
		{
			//if group is invalid, exception will be thrown
			throw new IllegalArgumentException("invalid group");
		}

	}
	
    /************************************************
 *  SUBMODULE: setTypeOfContact
 *  IMPORT: inGroup
 *  EXPORT:none
 ***************************************************/
	public void setTypeOfContact(char inGroup)
	{
		if(inGroup == 'W')
		{
			getWorkContact();
		}
		if(inGroup == 'S')
		{
			getSchoolContact();
		}
		if(inGroup == 'H')
		{
			getHomeContact();
		}
		if(inGroup == 'C')
		{
			getClubContact();
		}
	}
	
	//END OF SETTERS
	
	//GETTERS
    /************************************************
 *  SUBMODULE: getFrom
 *  IMPORT: none
 *  EXPORT:from
 ***************************************************/	
	public Person getFrom()
	{
		return from;
	}
    /************************************************
 *  SUBMODULE: getTo
 *  IMPORT: none
 *  EXPORT:to
 ***************************************************/	
	public Person getTo()
	{
		return to;
	}
    /************************************************
 *  SUBMODULE: getGroup
 *  IMPORT: none
 *  EXPORT: group
 ***************************************************/	
	public char getGroup()
	{
		return group;
	}
    /************************************************
 *  SUBMODULE: getTypeOfContact
 *  IMPORT: none
 *  EXPORT:String
 ***************************************************/	
	public String getTypeOfContact()
	{
		return typeOfContact;
	}

    /************************************************
 *  SUBMODULE: getDistance
 *  IMPORT: none
 *  EXPORT: distance
 ***************************************************/	
	public int getDistance()
	{
		return distance;
	}
	
    /************************************************
 *  SUBMODULE: getContactRate
 *  IMPORT: none
 *  EXPORT:contactRate
 ***************************************************/	
	public double getContactRate()
	{
		return contactRate;
	}
	
	//END OF GETTERS
	
	
    /*************************************************
 *  SUBMODULE: getWorkContact
 *  IMPORT: none
 *  EXPORT: none
 *  PURPOSE: to initialize type of contact and
 *  contact rate.
 *  *************************************************/
	public void getWorkContact()
	{
		if(0.25 > Math.random())
		{
			typeOfContact = "shakes hand";
			contactRate = 0.03;
		}
		else if(0.25 > Math.random())
		{
			typeOfContact = "converses";
			contactRate = 0.01;
		}
		else if(0.25 > Math.random())
		{
			typeOfContact = "shares work equipment";
			contactRate = 0.02;
		}
		else
		{
			typeOfContact = "has a meeting";
			contactRate = 0.01;
		}
	}
	
	   /*************************************************
	 *  SUBMODULE: getSchoolContact
	 *  IMPORT: none
	 *  EXPORT: none
	 *  PURPOSE: to initialize type of contact and
	 *  contact rate.
	 *  *************************************************/	
	public void getSchoolContact()
	{
		if(0.25 > Math.random())
		{
			typeOfContact = "is in a group project";
			contactRate = 0.03;
		}
		else if(0.25 > Math.random())
		{
			typeOfContact = "shares lunch";
			contactRate = 0.02;
		}
		else if(0.25 > Math.random())
		{
			typeOfContact = "shakes hand";
			contactRate = 0.03;
		}
		else
		{
			typeOfContact = "converses";
			contactRate = 0.01;
		}
	}
	
	   /*************************************************
	 *  SUBMODULE: getHomeContact
	 *  IMPORT: none
	 *  EXPORT: none
	 *  PURPOSE: to initialize type of contact and
	 *  contact rate.
	 *  *************************************************/	
	public void getHomeContact()
	{
		if(0.25 > Math.random())
		{
			typeOfContact = "converses";
			contactRate = 0.01;
		}
		else if(0.25 > Math.random())
		{
			typeOfContact = "practices social distancing";
			contactRate = 0.005;
		}
		else if(0.25 > Math.random())
		{
			typeOfContact = "talks about the coronavirus";
			contactRate = 0.02;
		}
		else
		{
			typeOfContact = "shakes hand";
			contactRate = 0.03;
		}
	}

	   /*************************************************
	 *  SUBMODULE: getClubContact
	 *  IMPORT: none
	 *  EXPORT: none
	 *  PURPOSE: to initialize type of contact and
	 *  contact rate.
	 *  *************************************************/
	public void getClubContact()
	{
		if(0.25 > Math.random())
		{
			typeOfContact = "converses";
			contactRate = 0.01;
		}
		else if(0.25 > Math.random())
		{
			typeOfContact = "shares p.e equipment";
			contactRate = 0.02;
		}
		else if(0.25 > Math.random())
		{
			typeOfContact = "shakes hand";
			contactRate = 0.03;
		}
		else
		{
			typeOfContact = "playes sports";
			contactRate = 0.01;
		}
	}
	
	
	   /*************************************************
	 *  SUBMODULE: equals
	 *  IMPORT: person
	 *  EXPORT: boolean
	 *  PURPOSE: to check if objects are equal.
	 *  *************************************************/
	public boolean equals(Object inObj)
	{
		//add more to equals
		boolean same = false;
		if(inObj instanceof DSAGraphEdge)
		{
			DSAGraphEdge inDSAGraphEdge = (DSAGraphEdge)inObj;
			
         	if (from == inDSAGraphEdge.getFrom())
         	{
         		if (to == inDSAGraphEdge.getTo())
         		{
         			if(group == inDSAGraphEdge.getGroup())
         			{
         				if(distance == inDSAGraphEdge.getDistance())
         				{
         					same = true;
         				}
         			}
         		}
         	}
         }
 
	    return same;	
	}
	
	public String toString()
	{
		return "this edge is between "+from.getName()+" and "+to.getName()+".";
	}
	
}

