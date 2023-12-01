import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Random;

/*************************************************
 * AUTHOR: Izan Muhammad
 * PURPOSE: object will simulate a virus spread in a population.
*************************************************/

public class HealthSimulator implements Serializable
{
	//CLASS CONSTANTS
	//the distance in which the infection will spread.
	private static final double INFECTION_DISTANCE = 1.5;
	
	//CLASS FEILDS
	private DSAGraph society;
	private double transferRate;
	private double recoveryRate;
	private double deathRate;
	private int intervention;
	private Date date;
	private int populationCount;
	private int hospitalLimit;
	private int hospitalCount;
	private int infectedCount;
	private int susceptibleCount;
	private int recoveredCount;
	private int deadCount;
	
	/**************************************************
	 *Default Constructor:
	*IMPORT: none
	*EXPORT: address of new HealthSimulator object
	**************************************************/
	public HealthSimulator()
	{

		society = new DSAGraph();
		transferRate = 0;
		recoveryRate = 0;
		deathRate = 0;
		intervention = 0;
		date = new Date(1,1,2020);
		populationCount = 0;
		hospitalLimit = 0;
		hospitalCount = 0;
		infectedCount = 0;
		susceptibleCount = 0;
		recoveredCount = 0;
		deadCount = 0;

	}
	
	/*************************************************
	 *Alternate Constructor #1: 
	*IMPORT: inSociety, inTransferRate, inRecoveryRate, inDeathRate, inIntervention
	*EXPORT: address of new HealthSimulator object
	**************************************************/
	public HealthSimulator(double inTransferRate, double inRecoveryRate, double inDeathRate, int inIntervention)
	{
		society = new DSAGraph();
		setTransferRate(inTransferRate);
		setRecoveryRate(inRecoveryRate);
		setDeathRate(inDeathRate);
		setIntervention(inIntervention);
		populationCount = 0;
		hospitalLimit = 0;
		hospitalCount = 0;
		infectedCount = 0;
		susceptibleCount = 0;
		recoveredCount = 0;
		deadCount = 0;
		date = new Date (1,1,2020);
	}
	
	//GETTERS
	
    /*************************************************
 *  SUBMODULE: getSociety
 *  IMPORT: none
 *  EXPORT: society
 *  *************************************************/
	public DSAGraph getSociety()
	{
		return society;
	}
	
    /*************************************************
 *  SUBMODULE: getTransferRate
 *  IMPORT: none
 *  EXPORT: transferRate
 *  *************************************************/
	public double getTransferRate()
	{
		return transferRate;
	}
    /*************************************************
 *  SUBMODULE: getRecoveryRate
 *  IMPORT: none
 *  EXPORT: recoveryRate
 *  *************************************************/	
	public double getRecoveryRate()
	{
		return recoveryRate;
	}
    /*************************************************
 *  SUBMODULE: getDeathRate
 *  IMPORT: none
 *  EXPORT: deathRate
 *  *************************************************/	
	public double getDeathRate()
	{
		return deathRate;
	}
    /*************************************************
 *  SUBMODULE: getIntervention
 *  IMPORT: none
 *  EXPORT: intervention
 *  *************************************************/	
	public int getIntervention()
	{
		return intervention;
	}
    /*************************************************
 *  SUBMODULE: getPopulationCount
 *  IMPORT: none
 *  EXPORT: populationCount
 *  *************************************************/	
	public int getPopulationCount()
	{
		return populationCount;
	}
	
    /*************************************************
 *  SUBMODULE: getPopulationCount
 *  IMPORT: none
 *  EXPORT: populationCount
 *  *************************************************/	
	public int getHospitalLimit()
	{
		return hospitalLimit;
	}
	
    /*************************************************
 *  SUBMODULE: getPopulationCount
 *  IMPORT: none
 *  EXPORT: populationCount
 *  *************************************************/	
	public int getHospitalCount()
	{
		return hospitalCount;
	}
	//END OF GETTERS
	
	//SETTERS
    /*************************************************
 *  SUBMODULE: setSociety
 *  IMPORT: inSociety
 *  EXPORT: none
 *  *************************************************/	
	public void setSociety(DSAGraph inSociety)
	{
		society = inSociety;
	}

    /*************************************************
 *  SUBMODULE: setTransferRate
 *  IMPORT: inTransferRate
 *  EXPORT: none
 *  *************************************************/	
	public void setTransferRate(double inTransferRate)
	{
		if( (inTransferRate < 0) || (inTransferRate > 1) )
		{
			throw new IllegalArgumentException("invalid transfer rate.");
		}
		
		transferRate = inTransferRate;
	}

    /*************************************************
 *  SUBMODULE: setRecoveryRate
 *  IMPORT: inRecoveryRate
 *  EXPORT: none
 *  *************************************************/	
	public void setRecoveryRate(double inRecoveryRate)
	{
		if( (inRecoveryRate < 0) || (inRecoveryRate > 1) )
		{
			throw new IllegalArgumentException("invalid recovery rate.");
		}
		
		recoveryRate = inRecoveryRate;
	}
	
    /*************************************************
 *  SUBMODULE: setDeathRate
 *  IMPORT: inDeathRate
 *  EXPORT: none
 *  *************************************************/	
	public void setDeathRate(double inDeathRate)
	{
		if( (inDeathRate < 0) || (inDeathRate > 1) )
		{
			throw new IllegalArgumentException("invalid death rate.");
		}
		
		deathRate = inDeathRate;
	}
	
    /*************************************************
 *  SUBMODULE: setIntervention
 *  IMPORT: inIntervention
 *  EXPORT: none
 *  *************************************************/	
	public void setIntervention(int inIntervention)
	{
		if(inIntervention == 0)
		{
		}
		else if(inIntervention == 1)
		{
			//hand shaking contact disabled.
			
			/*when transferProbability method is called 
			 * inside HealthSimulator class,
			 * it will check if intervention 1 is defined
			 * and not allow for hand shakes to boost transfer rate.*/
			 
		}
		else if(inIntervention == 2)
		{
			removeGroup('S');
		}
		else if(inIntervention == 3)
		{
			removeGroup('S');
			removeGroup('W');
		}
		else
		{
			throw new IllegalArgumentException("invalid intervention level.");
		}
		
		intervention = inIntervention;
	}

    /*************************************************
 *  SUBMODULE: setDeathRate
 *  IMPORT: inDeathRate
 *  EXPORT: none
 *  *************************************************/	
	public void setPopulationCount(int inPopulationCount)
	{
		if( inPopulationCount < 0 )
		{
			throw new IllegalArgumentException("invalid population count.");
		}
		
		populationCount = inPopulationCount;
	}
	
    /*************************************************
 *  SUBMODULE: setDeathRate
 *  IMPORT: inDeathRate
 *  EXPORT: none
 *  *************************************************/	
	public void setHosptialLimit(int inHosptialLimit)
	{
		if( inHosptialLimit < 0 )
		{
			throw new IllegalArgumentException("invalid hospital limit.");
		}
		
		hospitalLimit = inHosptialLimit;
	}
    /*************************************************
 *  SUBMODULE: setDeathRate
 *  IMPORT: inDeathRate
 *  EXPORT: none
 *  *************************************************/	
	public void setHospitalCount(int inHospitalCount)
	{
		if( inHospitalCount < 0 )
		{
			throw new IllegalArgumentException("invalid hosptial count.");
		}
		
		hospitalCount = inHospitalCount;
	}
	//END OF SETTERS
	
    /*************************************************
 *  SUBMODULE: addSociety
 *  IMPORT: population
 *  EXPORT:none
 *  PURPOSE: to add person array objects into graph.
 *  *************************************************/
	
	public void addSociety(Person[] population)
	{
		
		//add population into HealthSimulator society graph. 
		for(int i = 0; i < population.length; i++)
		{
			society.addVertex(population[i]);
			if(population[i].getHealthStatus().equals("susceptible"))
			{
				susceptibleCount++;
			}
			if(population[i].getHealthStatus().equals("infected"))
			{
				infectedCount++;
			}
			if(population[i].getHealthStatus().equals("recovered"))
			{
				recoveredCount++;
			}
			if(population[i].getHealthStatus().equals("dead"))
			{
				deadCount++;
			}
			
			populationCount++;
			
		}
		
		hospitalLimit = populationCount/10;
		//call linkSociety to add edges between group.
		LinkSociety();
		
	}
	
    /*************************************************
 *  SUBMODULE: LinkSociety
 *  IMPORT: none
 *  EXPORT: none
 *  PURPOSE: to add edges between people of the same group.
 *  *************************************************/
	
	public void LinkSociety()
	{
		Iterator iterate1 =  society.graphIterator();
		Person person1 = null;
		Person person2 = null;
		
		
		//iterate through society graph.
		while(iterate1.hasNext())
		{
			//initialize person
			person1 = (Person) iterate1.next();
			if(person1.isInfected())
			{
				Iterator iterate2 = society.graphIterator();
				while(iterate2.hasNext())
				{
					person2 = (Person) iterate2.next();
					//if both group 1 match.
					if( person1.getGroup1() == person2.getGroup1() )
					{
						if(0.5 > Math.random())
						{
							society.addEdge(person1,person2,person1.getGroup1());
						}
					}
					//if group 1 and group 2 match.
					else if( person2.getGroup2() != 0 )
					{
						if( person1.getGroup1() == person2.getGroup2() )
						{
							
							if(0.5 > Math.random())
							{
								society.addEdge(person1,person2,person1.getGroup1());
							}
						}
					}
					//if group2 and group 1 match.
					else if(person1.getGroup2() != 0)
					{
						if( person1.getGroup2() == person2.getGroup1() )
						{
							if(0.5 > Math.random())
							{
								society.addEdge(person1,person2,person1.getGroup2());
							}
						}
					}
					//if both group2 match.
					else if(person1.getGroup2() != 0 && person2.getGroup2() != 0)
					{
						if( person1.getGroup2() == person2.getGroup2() )
						{
							if(0.5 > Math.random())
							{
								society.addEdge(person1,person2,person1.getGroup2());
							}
						}
					}
					//%5 chance to form some sort of relationship outside of group.
					else if(0.05 > Math.random())
					{
						society.addEdge(person1,person2,person1.getGroup1());
					}
						
				}
			}
			
		}

	}

    /*************************************************
 *  SUBMODULE: findPerson
 *  IMPORT: personName
 *  EXPORT: Person
 *  PURPOSE: to compare each person objects name with 
 *  personName.
 *  *************************************************/
	public Person findPerson(String personName)
	{
		//initialize iterator to loop through population
		Iterator iterate =  society.graphIterator();
		Person person = null;
		boolean stop = false;
		while(iterate.hasNext() && !stop)
		{
			//initialize person
			person = (Person)iterate.next();
			//if person's name equals personNae
			if(personName.equals(person.getName()))
			{
				//stop is set to true
				stop = true;
			}
		}
		//check if person object is valid
		if(!(person.getName().equals(personName)))
		{
			throw new IllegalArgumentException("could not find person");
		}
		//return person object
		return person;
	}

    /*************************************************
 *  SUBMODULE: insertPerson
 *  IMPORT: person
 *  EXPORT: none
 *  PURPOSE: to add person object into graph.
 *  *************************************************/
	public void insertPerson(Person person)
	{
		//add person object to graph
		society.addVertex(person);
		//check health status of person and increment relevant count
		if(person.getHealthStatus().equals("susceptible"))
		{
			susceptibleCount++;
		}
		if(person.getHealthStatus().equals("infected"))
		{
			infectedCount++;
		}
		if(person.getHealthStatus().equals("recovered"))
		{
			recoveredCount++;
		}
		if(person.getHealthStatus().equals("dead"))
		{
			deadCount++;
		}
		
		//increment population count
		populationCount++;
		//adjust hospitalLimit
		hospitalLimit = populationCount/10;
		
	}

    /*************************************************
 *  SUBMODULE: deletePerson
 *  IMPORT: personName
 *  EXPORT: none
 *  PURPOSE: to delete a person object using there name.
 *  *************************************************/	
	public void deletePerson(String personName)
	{
		Person person = null; 
		//call findPerson
		person = findPerson(personName);
		
		//if person could not be found
		if(person == null)
		{
			throw new IllegalArgumentException("could not find person.");
		}
		
		//call deleteVertex inside graph.
		society.deleteVertex(person);

	}

    /*************************************************
 *  SUBMODULE: addConnection
 *  IMPORT: name1, name2
 *  EXPORT: none
 *  PURPOSE: to add person1 and person 2 into their adjacency list.
 *  *************************************************/	
	public void addConnection(String name1, String name2,char group)
	{
		//get person objects, exception will be thrown if not found
		Person person1 = society.getVertex(name1);
		Person person2 = society.getVertex(name2);
		//add edge including group.
		society.addEdge(person1, person2,group);
	}

    /*************************************************
 *  SUBMODULE: removeConnection
 *  IMPORT: name1, name2
 *  EXPORT: none
 *  PURPOSE: to remove person1 and person 2 from their adjacency list.
 *  *************************************************/	
	public void removeConnection(String name1, String name2)
	{
		//get person objects, exception will be thrown if not found
		Person person1 = society.getVertex(name1);
		Person person2 = society.getVertex(name2);
		//call deleteConnectionFromList to delete objects
		person1.deleteConnectionFromList(person2);
		person2.deleteConnectionFromList(person1);
		//call deleteEdge method inside graph
		society.deleteEdge(person1,person2);
		
	}

    /*************************************************
 *  SUBMODULE: Statistics
 *  IMPORT: none
 *  EXPORT: none
 *  PURPOSE: to display overall statistics of the population.
 *  *************************************************/	
	public void Statistics()
	{

		DecimalFormat df = new DecimalFormat("#.##");
		double displayPercent;
		
		System.out.println("=+=+=+=+=+=+=+=+=+=+=+=+"+date.toString()+"=+=+=+=+=+=+=+=+=+=+=+");
		//get percentage of infected
		displayPercent = ((double)infectedCount/(double)populationCount)*100;
		//display percentage of infected
		System.out.println("infected: "+df.format(displayPercent)+"% / "+infectedCount);
		
		//get percentage of susceptible		
		displayPercent = ((double)susceptibleCount/(double)populationCount)*100;
		//display percentage of susceptible		
		System.out.println("susceptible: "+df.format(displayPercent)+"% / "+susceptibleCount);
		
		//get percentage of dead
		displayPercent = ((double)deadCount/(double)populationCount)*100;
		//display percentage of dead
		System.out.println("dead: "+df.format(displayPercent)+"% / "+deadCount);
		
		//get percentage of recovered
		displayPercent = ((double)recoveredCount/(double)populationCount)*100;
		//display percentage of recovered
		System.out.println("recovered: "+df.format(displayPercent)+"% / "+recoveredCount);
		
		System.out.println("+=+=+=+=+=+=+=+=+=Total Population:"+populationCount+"=+=+=+=+=+=+=+=+=+=");
	}

    /*************************************************
 *  SUBMODULE: runTimeStep
 *  IMPORT: none
 *  EXPORT: none
 *  PURPOSE: to run a time step. 
 *  *************************************************/	
	public DSALinkedList runTimeStep()
	{
		//if rates/population are invalid
		if(recoveryRate <= 0)
		{
			throw new IllegalArgumentException("recovery rate not set.");
		}
		if(deathRate <= 0)
		{
			throw new IllegalArgumentException("death rate not set.");
		}
		if(transferRate < 0)
		{
			throw new IllegalArgumentException("recovery rate not set.");
		}
		if(populationCount <= 0)
		{
			throw new IllegalArgumentException("society is empty.");
		}
		//initialize new events linked list
		DSALinkedList events = new DSALinkedList();

		//iterate through population
		Iterator population = society.graphIterator();
		
		while(population.hasNext())
		{
			//initalize person from iterator
			Person aPerson = (Person)population.next();
			if(aPerson.isInfected())
			{
				//First event in time step: 
				//aPerson's contact list have a possibility of getting infected.
				Iterator contacts = aPerson.getConnectionList();
				while(contacts.hasNext())
				{
					//initialize contact of aPerson
					Person bPerson = (Person) contacts.next();
					//check if susceptible
					if(bPerson.isSusceptible())
					{
						//get edge of aPerson and bPerson
						DSAGraphEdge edge = society.getEdge(aPerson, bPerson);
						//get a boolean from inContact method
						if(inContact(aPerson,bPerson,edge))
						{
							//get a boolean from method infectionProbability
							if(infectionProbability(edge))
							{
								//call infectionPerson method to infect person bPerson
								aPerson.infectPerson(bPerson);
								//add events to each persons contact linked list
								aPerson.addMessage(date.toString()+": Infected "+bPerson.getName()+"through type of contact:" +edge.getTypeOfContact());
								bPerson.addMessage(date.toString()+": Got Infected by "+aPerson.getName()+"through type of contact:" +edge.getTypeOfContact());
								//insert event into events linked list
								events.insertLast(bPerson.getName() +" "+edge.getTypeOfContact()+" with "+aPerson.getName()+" and gets infected!");
								//increment infection count
								infectedCount++;
								//decrement susceptible
								susceptibleCount--;
							}
							//if person is in contact, but does not get infected
							else
							{
								//store event in bPerson contacts
								bPerson.addMessage(date.toString()+": "+edge.getTypeOfContact()+" with "+aPerson.getName());
								//store event in events linked list
								events.insertLast(bPerson.getName()+" "+edge.getTypeOfContact()+" with "+aPerson.getName());
							}
						}
					}
				}
				//second event hospitalized:
				//if person is infected and not in a hospital.
				if(!aPerson.getInHospital())
				{
					//if number of people is less than limit.
					if(hospitalCount < hospitalLimit)
					{
						//add to hospital
						aPerson.hospitalize();
						//store events
						aPerson.addMessage(date.toString()+": admitted into a hospital.");
						events.insertLast(aPerson.getName()+" has admitted into a hospital.");
						//increment hospitalcount
						hospitalCount++;
					}
				}
				
				//third event:
				//person has a chance to recover from the virus.
				if(recoveryProbability(aPerson))
				{
					
					//call recovered method
					aPerson.recovered();
					//add events to aPerson contact linked list
					aPerson.addMessage(date.toString()+": recovered from infection.");
					//add events to events linked list
					events.insertLast(aPerson.getName()+" has recovered from the disease.");
					//call deleteAll edges to delete all edges that go from and to aPerson
					society.deleteAllEdges(aPerson);
					//increment recovered
					recoveredCount++;
					//decrement infected
					infectedCount--;

				
				}
				
				
				//third event in time step:
				//aPerson has a probability of death
				if(!aPerson.getInHospital())
				{
					//get death probability 
					if(deathProbability(aPerson))
					{
						//call death method to mark person as dead
						aPerson.death();
						//add events
						aPerson.addMessage(date.toString()+": passed away.");
						events.insertLast(aPerson.getName()+" has passed away after being infected for "+aPerson.getDaysInfected()+" days.");
						//call deleteAllEdges to remove all edges that go from to aPerson
						society.deleteAllEdges(aPerson);
						//increment dead count
						deadCount++;
						//decrement infected count
						infectedCount--;
					}
				}
				
				//if person does not recover or die
				//increment days infected
				aPerson.addDayInfected();
				
				//last event:
				//if person has been infected for more than 60 days
				if(aPerson.getDaysInfected() > 60)
				{
					//declare dead
					aPerson.death();
				}
				
			}
			
		}
		//increment day in Date
		date.addDay();
		//declare counter
		int i = 0;
		//get events iterator
		Iterator iterate = events.iterator();
		while(iterate.hasNext())
		{
			//output events
			System.out.println(iterate.next());
			//line breakage for events
			System.out.println("--------------------------------------------------------------------------------------");
			//increment counter
			i++;
		}
		//output total number of events
		System.out.println(i+" events.");
		//output statistics
		Statistics();
		
		/*to avoid having too many edges. otherwise
		stack over flow error may occur, when
		writing serialized object to file*/
		if(society.edgeCount() < 100)
		{
			LinkSociety();
		}
		
		return events;
		
	}

    /*************************************************
 *  SUBMODULE: deathProbability
 *  IMPORT: aPerson
 *  EXPORT: dead
 *  PURPOSE: to use the given probability of death to
 *  declare someone dead 
 *  *************************************************/	
	public boolean deathProbability(Person aPerson)
	{
		boolean dead = false;
		
		//check if person is infected
		if(aPerson.isInfected())
		{
			//get actual death rate
			double actualDeathRate = aPerson.getDeathRate(deathRate,date);
			//get probability
			if(actualDeathRate > Math.random())
			{
				//set dead boolean to true
				dead = true;
			}
			

		}
		return dead;
	}
    /*************************************************
 *  SUBMODULE: recoveryProbability
 *  IMPORT: aPerson
 *  EXPORT: recover
 *  PURPOSE: to use the given probability of the 
 *  recovery rate to decide if person object recovers.
 *  *************************************************/	
	public boolean recoveryProbability(Person aPerson)
	{
		boolean recover = false;
		//get actual recovery 
		double actualRecoveryRate = aPerson.getRecoveryRate(recoveryRate,date);

		//get probability
		if(actualRecoveryRate > Math.random())
		{
			//if person has been infected for 5 days, minimum time a person can recover after infection 
			if(aPerson.getDaysInfected() > 5)
			{
				//mark recover as true
				recover = true;
			}
		}
	
		
		return recover;
	}

    /*************************************************
 *  SUBMODULE: infectionProbability
 *  IMPORT: aPerson
 *  EXPORT: recover
 *  PURPOSE: to use the given transfer rate to find
 *  and then compare it against Math.random to return
 *  a true or false boolean variable.
 *  *************************************************/	
	public boolean infectionProbability(DSAGraphEdge edge)
	{
		boolean infect = false;
		
		double actualInfectionRate = 0;

		//if intervention level is 1
		if(edge.getTypeOfContact().equals("shakes hand") && intervention > 0)
		{
			actualInfectionRate = transferRate;
		}
		else
		{
			actualInfectionRate = transferRate + edge.getContactRate();
		}

		//get probability
		if(actualInfectionRate > Math.random())
		{
			//set infect as true
			infect = true;
		}
		return infect;
	}


	/*************************************************
 *  SUBMODULE: inContact
 *  IMPORT: aPerson, bPerson
 *  EXPORT: inContact
 *  PURPOSE: to find the probability of aPerson and bPerson
 *  being in the same location.
     * @param edge 
 *  *************************************************/	
	public boolean inContact(Person aPerson, Person bPerson, DSAGraphEdge edge)
	{
		//get location of a and b
		char locationA = aPerson.getLocation();
		char locationB = bPerson.getLocation();
		boolean inContact = false;
		
		//if location a and b happen to be in same location.
		if(locationA == locationB)
		{
			//if there are in infection distance
			if(inInfectionDistance(locationA,edge))
			{
				inContact = true;
			}
			
		}
		
		return inContact;
	}

    /*************************************************
 *  SUBMODULE: inInfectionDistance
 *  IMPORT: locationA
 *  EXPORT: inContact
 *  PURPOSE: if aPerson and bPerson are in the same location,
 *   check weather or not person a and b
 *  are within 1.5 distance of one another.
 *  *************************************************/	
	public boolean inInfectionDistance(char locationA, DSAGraphEdge edge)
	{
		Random random = new Random();
		boolean inContact = false;
		
		
		if(INFECTION_DISTANCE > random.nextInt(edge.getDistance()))
		{
			inContact = true;
		}
		
		return inContact;
	}
	
    /*************************************************
 *  SUBMODULE: newlyInfected
 *  IMPORT: none
 *  EXPORT: newlyInfected
 *  PURPOSE: return a LinkedList of newly infected
 *  person objects.
 *  *************************************************/	
	public void newlyInfected()
	{
		Iterator iterate = society.graphIterator();		
		while(iterate.hasNext())
		{
			Person person = (Person) iterate.next();
			if(person.isInfected())
			{
				if(person.getDaysInfected() == 0)
				{
				
					System.out.println(person.getName()+" and is currently "+person.isHospitalizedString());
				}
			}
		}

	}

    /*************************************************
 *  SUBMODULE: allInfected
 *  IMPORT: none
 *  EXPORT: allInfected
 *  PURPOSE: return a LinkedList of all infected
 *  person objects.
 *  *************************************************/	
	public void allInfected()
	{

		Iterator iterate = society.graphIterator();		
		while(iterate.hasNext())
		{
			Person person = (Person) iterate.next();
			if(person.isInfected())
			{

				System.out.println(person.getName()+" and is currently "+person.isHospitalizedString());
				
			}
		}

	}
	
    /*************************************************
 *  SUBMODULE: removeGroup
 *  IMPORT: group
 *  EXPORT: none
 *  PURPOSE: remove the all edges between imported char
 *  variable.
 *  *************************************************/	
	public void removeGroup(char group)
	{
		//get society iterator
		Iterator iterate = society.graphIterator();
		while(iterate.hasNext())
		{
			//initialize person object
			Person person = (Person)iterate.next();
			//check if group matches variable group
			if(person.getGroup1() == group || person.getGroup2() == group )
			{
				//call removeConnections which will remove connections within the person adjacency list
				removeConnections(person,group);
			}
		}
		
	}
	
    /*************************************************
 *  SUBMODULE: removeConnections
 *  IMPORT: person, group
 *  EXPORT: none
 *  PURPOSE: remove every group connection within persons
 *  adjacency list.
 *  *************************************************/	
	public void removeConnections(Person person, char group)
	{
		//get person adjacency list
		Iterator iterate = person.getConnectionList();
		while(iterate.hasNext())
		{
			//initialize person in adjacency list
			Person person2 = (Person) iterate.next();
			//if person matches group
			if(person2.getGroup1() == group || person2.getGroup2() == group)
			{
				//delete connections from each others adjacency list
				person.deleteConnectionFromList(person2);
				person2.deleteConnectionFromList(person);
			}
		}
	
	}

    /*************************************************
 *  SUBMODULE: getGroup
 *  IMPORT: group
 *  EXPORT: DSALinkedList
 *  PURPOSE: to return a linked list of person objects
 *  within the particular group.
 *  *************************************************/	
	public DSALinkedList getGroup(char group)
	{
		//initialize linked list
		DSALinkedList groupList = new DSALinkedList();
		//get graph iterator
		Iterator iterate = society.graphIterator();
		while(iterate.hasNext())
		{
			//initialize person
			Person person = (Person) iterate.next();
			//check if group matches variable group
			if(person.getGroup1() == group || person.getGroup2() == group)
			{
				//add to linked list
				groupList.insertLast(person);
			}
		}
		
		return groupList;
	}
	
    /*************************************************
 *  SUBMODULE: getHealthGroup
 *  IMPORT: healthStatus
 *  EXPORT: DSALinkedList
 *  PURPOSE:to return a linked list of person objects
 *  within the particular health group.
 *  *************************************************/	
	public DSALinkedList getHealthGroup(String healthStatus)
	{
		//initialize linked list
		DSALinkedList groupList = new DSALinkedList();
		//initialize graph iterator
		Iterator iterate = society.graphIterator();
		while(iterate.hasNext())
		{
			//get person object
			Person person = (Person) iterate.next();
			//check if health status matches variable healthStatus
			if( healthStatus.equals(person.getHealthStatus()) )
			{
				//add to linked list
				groupList.insertLast(person);
			}
		}
		
		return groupList;
	}
	
    /*************************************************
 *  SUBMODULE: infectedCount
 *  IMPORT: none
 *  EXPORT: none
 *  PURPOSE: to return an integer which is the number
 *  of infected objects within the population.
 *  *************************************************/	
	public int InfectedCount()
	{
		/*this method previous looped through graph iterator and used
		 * a counter, however the addition of infectedCount made
		 * this method algorithm inefficient, therefore this method
		 * returns infectedCount. */
		/*this method cannot be removed as its used by runAuto in FileManager*/ 
		return infectedCount;
	}
	
}
