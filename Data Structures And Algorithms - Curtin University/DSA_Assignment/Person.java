import java.io.Serializable;

/*************************************************
 * AUTHOR: Izan Muhammad
 * PURPOSE: Initialize an object which will simulate a person in a virus pandemic.
*************************************************/
public class Person implements Serializable
{
	//CLASS FEILDS:
	//name of person
	private String name;
	//age of person
	private Date dateOfBirth;
	//health status of person (susceptible,infected,recovered,dead)
	private String healthStatus;
	//LinkedList of connections with other people
	private DSALinkedList connections;
	//number of days infected
	private int daysInfected;
	//stores char value of group
	private char group1;
	private char group2;
	//if hospitalized
	private boolean inHospital;
	private DSALinkedList contacts;
	
	/*************************************************
	 *Alternate Constructor #1: 
	*IMPORT: inName, inDOB, inHealthStatus, inGroup1
	*EXPORT: address of new Person object
	**************************************************/
	public Person(String inName, String inDOB, String inHealthStatus,char inGroup1)
	{
		//call setName method
		setName(inName);
		//call setAge method
		setDOB(inDOB);
		//call setHealthStatus method
		setHealthStatus(inHealthStatus);
		//initialize LinkedList
		connections = new DSALinkedList();
		//set group1
		setGroup1(inGroup1);
		//set group2 to default 
		group2 = 0;
		daysInfected = 0;
		inHospital = false;
		contacts = new DSALinkedList();
		
	}

	/*************************************************
	 *Alternate Constructor #1: 
	*IMPORT: inName, inDOB, inHealthStatus, inGroup1, inGroup2
	*EXPORT: address of new Person object
	**************************************************/
	public Person(String inName, String inDOB, String inHealthStatus,char inGroup1, char inGroup2)
	{
		//call setnNme method
		setName(inName);
		//call setAge method
		setDOB(inDOB);
		//call setHealthStatus method
		setHealthStatus(inHealthStatus);
		//initialize LinkedList
		connections = new DSALinkedList();
		//set group1
		setGroup1(inGroup1);
		//setgroup2
		setGroup2(inGroup2);
		daysInfected = 0;
		inHospital = false;
		contacts = new DSALinkedList();
	}
	
	//GETTERS
	
    /************************************************
 *  SUBMODULE: getName
 *  IMPORT: none
 *  EXPORT: name
 ***************************************************/
	public String getName()
	{
		return name;
	}
	
    /************************************************
 *  SUBMODULE: getDOB
 *  IMPORT: none
 *  EXPORT: dateOfBirth
 ***************************************************/
	public Date getDOB()
	{
		return dateOfBirth;
	}
	
    /************************************************
 *  SUBMODULE: getHealthStatus
 *  IMPORT: none
 *  EXPORT: healthStatus
 ***************************************************/
	public String getHealthStatus()
	{
		return healthStatus;
	}

    /************************************************
 *  SUBMODULE: getConnections
 *  IMPORT: none
 *  EXPORT: connections
 ***************************************************/
	public DSALinkedList getConnections()
	{
		return connections;
	}
	
    /************************************************
 *  SUBMODULE: geGroup1
 *  IMPORT: none
 *  EXPORT: group1
 ***************************************************/
	public char getGroup1()
	{
		return group1;
	}

    /************************************************
 *  SUBMODULE: geGroup2
 *  IMPORT: none
 *  EXPORT: group2
 ***************************************************/	
	public char getGroup2()
	{
		return group2;	
	}

    /************************************************
 *  SUBMODULE: geDaysInfection
 *  IMPORT: none
 *  EXPORT: daysInfected
 ***************************************************/
	public int getDaysInfected()
	{
		return daysInfected;
	}

    /************************************************
 *  SUBMODULE: getInHospital
 *  IMPORT: none
 *  EXPORT: inHospital
 ***************************************************/
	public boolean getInHospital()
	{
		return inHospital;
	}
	
    /************************************************
 *  SUBMODULE: getContacts
 *  IMPORT: none
 *  EXPORT: inHospital
 ***************************************************/
	public DSALinkedList getContacts()
	{
		return contacts;
	}
	
    /************************************************
 *  SUBMODULE: getLocation
 *  IMPORT: none
 *  EXPORT: location
 ***************************************************/
	public char getLocation()
	{	
		//variable declarations
		char location;
		
		//initialize random probability.
		double randomProbability = Math.random();
		//if group2 is empty.
		//to add realism - a person could be in 10 different locations(shopping,restaurant,socializing,etc).
		
		if(group2 == 0)
		{
			location = group1;
		}
		else
		{		
			//50% chance of being in group1 location.
			if(0.5 < randomProbability)
			{
				location = group1;
			}
			//50% change of being in group2 location.
			else
			{
				location = group2;
			}
		}
		
		return location;
	}
	
	//END OF GETTERS
	
	//SETTERS

    /************************************************
 *  SUBMODULE: setName
 *  IMPORT: inName
 *  EXPORT: none
 ***************************************************/
	public void setName(String inName)
	{
		if(inName.equals(""))
		{
			throw new IllegalArgumentException("invalid name.");
		}

		name = inName;
	}

    /************************************************
 *  SUBMODULE: setDOB
 *  IMPORT: inDOB
 *  EXPORT: none
 ***************************************************/
	public void setDOB(String inDOB)
	{
		//variable declarations
		Date inDate = null;
		//initialize inDate
		inDate = convertStringDate(inDOB);		
		dateOfBirth = inDate;
	}

    /************************************************
 *  SUBMODULE: setHealthStatus
 *  IMPORT: inHealthStatus
 *  EXPORT: none
 ***************************************************/
	public void setHealthStatus(String inHealthStatus)
	{
		//check if health status is valid
		if(inHealthStatus.equals("susceptible"))
		{
		}
		else if(inHealthStatus.equals("infected"))
		{
		}
		else if(inHealthStatus.equals("recovered"))
		{
		}
		else if(inHealthStatus.equals("dead"))
		{
		}
		else
		{
			//if health status is not valid
			throw new IllegalArgumentException("invalid health status.");
		}
		
		healthStatus = inHealthStatus;
	}

    /************************************************
 *  SUBMODULE: setGroup1
 *  IMPORT: inGroup
 *  EXPORT: none
 ***************************************************/
	public void setGroup1(char inGroup)
	{
		//if group is valid
		if(inGroup == 'S')
		{	
		}
		else if(inGroup == 'W')
		{	
		}
		else if(inGroup == 'C')
		{	
		}
		else if(inGroup == 'H')
		{	
		}
		else
		{
			//if group is invalid
			throw new IllegalArgumentException("invalid group.");
		}
		
		group1 = inGroup;
	}

    /************************************************
 *  SUBMODULE: setGroup2
 *  IMPORT: inGroup
 *  EXPORT: none
 ***************************************************/
	public void setGroup2(char inGroup)
	{
		//if group is valid
		if(inGroup == 'S')
		{	
		}
		else if(inGroup == 'W')
		{	
		}
		else if(inGroup == 'C')
		{	
		}
		else if(inGroup == 'H')
		{	
		}
		else
		{
			//if group is invalid
			throw new IllegalArgumentException("invalid group.");
		}
		
		group2 = inGroup;
	}
	
    /************************************************
 *  SUBMODULE: setGroup2
 *  IMPORT: inGroup
 *  EXPORT: none
 ***************************************************/
	public void setDaysInfected(int inDaysInfected)
	{
		if(inDaysInfected < 0)
		{
			throw new IllegalArgumentException("");
		}
		
		daysInfected = inDaysInfected;
	}
	//END OF SETTERS
	
    /************************************************
 *  SUBMODULE: geConnectionsList
 *  IMPORT: none
 *  EXPORT: connections.iterator
 *  PURPOSE: get iterator of LinkedList connections
 ***************************************************/	
	public Iterator getConnectionList()
	{
		return connections.iterator();
	}

    /************************************************
 *  SUBMODULE: convertStringDate
 *  IMPORT: inDOB
 *  EXPORT: none
 *  PURPOSE: convert string to date object
 ***************************************************/	
	public Date convertStringDate(String inDOB)
	{
		//variable declarations
		Date newDate = null;
		int days, months, year;
		//declare char array to store string
		char[] charDOB = new char [8];
		//declare integer array to store convert char variables
		int [] intDOB = new int [8];
		//used as counter for charDOB;
		int j = 0;
		//get days
		for(int i = 0; i < 2; i++)
		{
			charDOB[j] = inDOB.charAt(i);
			j++;
		}
		//get month
		for(int i = 3; i < 5; i++)
		{
			charDOB[j] = inDOB.charAt(i);
			j++;
		}
		//get year
		for(int i = 6; i < 10; i++)
		{
			charDOB[j] = inDOB.charAt(i);
			j++;
		}
		//convert char to integers
		for(int i = 0; i < charDOB.length; i++)
		{
			intDOB[i] = Character.getNumericValue(charDOB[i]);
		}
		
		//convert first 2 index to days variable.
		days = intDOB[0] * 10 + intDOB[1];
		//convert 2,3 index to months variable.
		months = intDOB[2] * 10 + intDOB[3];
		//convert 4,5,6 and 7 index to year variable.
		year = intDOB[4] * 1000 + intDOB[5] * 100 + intDOB[6] * 10 + intDOB[7];
		//initialize newDate variable
		newDate = new Date(days,months,year);
		
		return newDate;
	}
	
    /*************************************************
 *  SUBMODULE: isInfected
 *  IMPORT: none
 *  EXPORT: check
 *  PURPOSE: to check if person is infected.
 *  *************************************************/
	
	public boolean isInfected()
	{
		boolean check = false;
		
		if(healthStatus.equals("infected"))
		{
			check = true;
		}
		
		return check;
	}
	
	   /*************************************************
	 *  SUBMODULE: isDead
	 *  IMPORT: none
	 *  EXPORT: check
	 *  PURPOSE: to check if person is dead.
	 *  *************************************************/	
	public boolean isDead()
	{
		boolean check = false;
		
		if(healthStatus.equals("dead"))
		{
			check = true;
		}
		
		return check;
	}
	
		/*************************************************
	 *  SUBMODULE: isSusceptible
	 *  IMPORT: none
	 *  EXPORT: check
	 *  PURPOSE: to check if person is susceptible.
	 *  *************************************************/
	
	public boolean isSusceptible()
	{
		boolean check = false;
		
		if(healthStatus.equals("susceptible"))
		{
			check = true;
		}
		
		return check;
	}

	   /*************************************************
	 *  SUBMODULE: isRecovered
	 *  IMPORT: none
	 *  EXPORT: check
	 *  PURPOSE: to check if person is recovered.
	 *  *************************************************/
	public boolean isRecovered()
	{
		boolean check = false;
		
		if(healthStatus.equals("recovered"))
		{
			check = true;
		}
		
		return check;
	}
	
	   /*************************************************
	 *  SUBMODULE: addPerson
	 *  IMPORT: person
	 *  EXPORT: none
	 *  PURPOSE: to add a edge between person.
	 *  *************************************************/
	public void addConnection(Person person)
	{
		if(!havePerson(person) )
		{
			//add person to LinkedList using insertLast.
			connections.insertLast(person);
		}
	}
	
    /*************************************************
 *  SUBMODULE: havePerson
 *  IMPORT: person
 *  EXPORT:check
 *  PURPOSE: to check if person is already added to LinkedList
 *  *************************************************/
	public boolean havePerson(Person person)
	{
		Iterator iterate = connections.iterator();
		Person tempPerson = null;
		boolean check = false;
		if(person.equals(this))
		{
			check = true;
		}
		else
		{
			while(iterate.hasNext())
			{
				tempPerson = (Person)iterate.next();
				if(tempPerson.equals(person))
				{
					check = true;
				}
			}
		}
		
		return check;
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
        if(inObj instanceof Person)
        {
            Person inPerson = (Person)inObj;
            	
            	if (name.equals(inPerson.getName()))
            	{
            		if (healthStatus.equals(inPerson.getHealthStatus()))
            		{
            			if(dateOfBirth.equals(inPerson.getDOB()))
            			{
            				if(group1 == inPerson.getGroup1())
            				{
            					if(group2 == inPerson.getGroup2())
            					{
            						same = true;
            					}
            				}
            			}
            		}
            	}
            }
    
	    return same;		
	}

	   /*************************************************
	 *  SUBMODULE: toString
	 *  IMPORT: none
	 *  EXPORT: string
	 *  PURPOSE: output information about object.
	 *  *************************************************/	
	public String toString()
	{
		String toString;
		if(group2 == 0)
		{
			toString = "This person is named: "+name+" and was born on "+dateOfBirth+". Currenty in "+group1+" group and according to their medical records: They are "+healthStatus+".";
		}
		else
		{
			toString = "This person is named: "+name+" and was born on "+dateOfBirth+". Currenty in "+group1+" and "+group2+" group and according to their medical records: They are "+healthStatus+".";
		}
		
		return toString;
	}

	   /*************************************************
	 *  SUBMODULE: toFileString
	 *  IMPORT: none
	 *  EXPORT: string
	 *  PURPOSE: output information about object in CSV format.
	 *  *************************************************/	
	public String toFileString()
	{
		String toFileString;
		if(group2 == 0)
		{
			toFileString = name+","+dateOfBirth.toString()+","+healthStatus+","+group1;
		}
		else
		{
			toFileString = name+","+dateOfBirth.toString()+","+healthStatus+","+group1+","+group2;
		}
		
		return toFileString;
	}

	   /*************************************************
	 *  SUBMODULE: deleteConnectionFromList
	 *  IMPORT: person
	 *  EXPORT: none
	 *  PURPOSE: deletes imported person from adjacency list.
	 *  *************************************************/
	public void deleteConnectionFromList(Person person)
	{

		Iterator iterate = connections.iterator();
		Person tempPerson = null;
		while(iterate.hasNext())
		{
			tempPerson = (Person) iterate.next();
			
			if(tempPerson.equals(person))
			{
				connections.deletePerson(person);
			}
		}
		
		if(tempPerson == null)
		{
			throw new IllegalArgumentException("could not delete person");
		}
		
	}

	   /*************************************************
	 *  SUBMODULE: infectPerson
	 *  IMPORT: bPerson
	 *  EXPORT: none
	 *  PURPOSE: to change infected person object to infected.
	 *  *************************************************/
	public void infectPerson(Person bPerson)
	{
		if(isInfected())
		{
			bPerson.setHealthStatus("infected");
		}
	}

	/*************************************************
	 *  SUBMODULE: recovered
	 *  IMPORT: none
	 *  EXPORT: none
	 *  PURPOSE: change health status to recovered and inHospital to false.
	 *  *************************************************/
	public void recovered()
	{
		healthStatus = "recovered";
		connections = new DSALinkedList();
		inHospital = false;
		
	}

	   /*************************************************
	 *  SUBMODULE: getRecoveryRate
	 *  IMPORT: recoveryRate, inDate
	 *  EXPORT: none
	 *  PURPOSE: to modify recoveryRate depending on person object.
	 *  *************************************************/
	public double getRecoveryRate(double recoveryRate, Date inDate)
	{
		//2% decrease against recovery rate for every day infected
		double healthDeterioration = 0.02 * daysInfected;
		//adjust recovery rate
		recoveryRate -= healthDeterioration;
		//if in hospital boost recovery rate
		if(inHospital && daysInfected > 5)
		{
			recoveryRate *= 1.1;
		}
		//if infected for over 14 days reduce recovery rate
		if(daysInfected > 14)
		{
			recoveryRate *= 0.75;
		}
		//return recoveryRate
		return recoveryRate;
	}

	   /*************************************************
	 *  SUBMODULE: death
	 *  IMPORT: none
	 *  EXPORT: none
	 *  PURPOSE: change health status to dead.
	 *  *************************************************/
	public void death()
	{
		healthStatus = "dead";
		//re-initialize connections
		connections = new DSALinkedList();
		
	}

	   /*************************************************
	 *  SUBMODULE: hospitalize
	 *  IMPORT: none
	 *  EXPORT: none
	 *  PURPOSE: change inHospital to true.
	 *  *************************************************/
	public void hospitalize()
	{
		inHospital = true;

	}

	   /*************************************************
	 *  SUBMODULE: getDeathRate
	 *  IMPORT: deathRate, inDate
	 *  EXPORT: none
	 *  PURPOSE: modify death rate depending on person object.
	 *  *************************************************/
	public double getDeathRate(double deathRate, Date inDate)
	{
		//increase death rate by 0.5% for every day infected
		double healthDeterioration = 0.0005 * daysInfected;
		deathRate += healthDeterioration;
		return deathRate;
	}

	   /*************************************************
	 *  SUBMODULE: addDaysInfected
	 *  IMPORT: none
	 *  EXPORT: none
	 *  PURPOSE: add 1 to daysInfected 
	 *  *************************************************/
	public void addDayInfected()
	{
		daysInfected += 1;
	}

	   /*************************************************
	 *  SUBMODULE: isHospitalizedString
	 *  IMPORT: none
	 *  EXPORT: string 
	 *  PURPOSE: returns string to be using in health simulator to display newly infected.
	 *  *************************************************/
	public String isHospitalizedString()
	{
		String string;
		if(inHospital)
		{
			string = "hospitalized.";
		}
		else
		{
			string = "not hospitalized.";
		}
		
		return string;
	}
	
    /*************************************************
 *  SUBMODULE: displayCounts
 *  IMPORT: person
 *  EXPORT:none
 *  PURPOSE: to output all contacts which have occurred
 *  with this person object.
 *  *************************************************/
	public void displayContacts()
	{
		//initialize iterator to loop to events
		Iterator iterate = contacts.iterator();
		while(iterate.hasNext())
		{
			//initialize events string
			String contact = (String) iterate.next();
			//display events/contacts
			System.out.println(contact);
		}
		
	}

    /*************************************************
 *  SUBMODULE: addMessage
 *  IMPORT: event
 *  EXPORT:check
 *  PURPOSE: to add an event to contacts linked list.
 *  *************************************************/
	public void addMessage(String event)
	{
		//add event to contacts linked list
		contacts.insertLast(event);	
	}
}
