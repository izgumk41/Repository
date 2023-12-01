
import java.util.InputMismatchException;
import java.util.Scanner;

/*************************************************
 * AUTHOR: Izan Muhammad
 * PURPOSE: methods which interact with user.
*************************************************/
public class UI
{
	/*SUBMODULE: inputString This method was previously submitted in OOPD assignment 2019_S2.
	IMPORT: none
	EXPORT: (String) output
	ALGORITHM:*/

	public static String inputString ()
	{
		Scanner sc = new Scanner (System.in);
	
		boolean valid;
		String input = "";
		
		valid = true;
		do
		{
			try
			{
				//gets input
				input = sc.nextLine();
				//changes valid to false
				valid = false;
			}	
			catch (InputMismatchException ex)
			{
				//clears buffer
				sc.nextLine();
				//outputs to the user to re-enter
				System.out.println("Enter a string, please");

			}
		}while (valid || (input).isEmpty());//checks
		return input;
	}
	
	/*SUBMODULE: inputInt (this method was previously submitted in OOPD assignment 2019_S2
	IMPORT: none
	EXPORT: (Integer) output
	ALGORITHM:*/

	public static int inputInt()
	{
		Scanner sc = new Scanner (System.in);
	
		boolean valid;
		int input = 0;
		
		valid = true;
	
		do
		{
			try
			{
				//gets input
				input = sc.nextInt();
				//changes valid to false
				valid = false;
			}	
			catch (InputMismatchException ex)
			{
				//clears buffer
				sc.nextLine();
				//outputs to the user to re-enter
				System.out.println("Enter a int, please");
			
			}
		}while (valid || input < 0);//checks
		return input;
	}
	
	/*SUBMODULE: inputDouble this method was previously submitted in OOPD assignment 2019_S2
	IMPORT: none
	EXPORT: (Real) output
	ALGORITHM:*/

	public static double inputDouble()
	{
		Scanner sc = new Scanner (System.in);
	
		boolean valid;
		double input = 0;
		
		valid = true;
	
		do
		{
			try
			{
				//gets input
				input = sc.nextDouble();
				//changes valid to false
				valid = false;
			}	
			catch (InputMismatchException ex)
			{
				//clears buffer
				sc.nextLine();
				//outputs to the user to re-enter
				System.out.println("Enter a int, please");
			
			}
		}while (valid || input < 0);//checks
		return input;
	}

    /*************************************************
 *  SUBMODULE: intputChar
 *  IMPORT: none
 *  EXPORT: none
 *  PURPOSE: display main menu and get input
 *  *************************************************/
	
	public static char inputChar()
	{
		Scanner sc = new Scanner (System.in);
	
		boolean valid;
		char input = 0;
		
		valid = true;
	
		do
		{
			try
			{
				//gets input
				input = sc.next().charAt(0);
				//changes valid to false
				valid = false;
			}	
			catch (InputMismatchException ex)
			{
				//clears buffer
				sc.nextLine();
				//outputs to the user to re-enter
				System.out.println("Enter a int, please");
			
			}
		}while (valid || input <= 0);//checks
		return input;
	}
	
    /*************************************************
 *  SUBMODULE: getMenuOption
 *  IMPORT: none
 *  EXPORT: none
 *  PURPOSE: display main menu and get input
 *  *************************************************/
	public static int getMenuOption()
	{
		int choice; 
		
		System.out.println("Main menu:");
		System.out.println("(1) Load network\n"+
				"(2) Set rates/interventions\n"+
				"(3) Node operations (find, insert, delete)\n"+
				"(4) Edge operations (add, remove, contacts)\n"+
				"(5) New infection\n"+
				"(6) Display network\n"+
				"(7) Display statistics\n"+
				"(8) Update (run a timestep)\n"+
				"(9) Save network\n"+
				"(0) exit");
		
		choice = inputInt();
		
		return choice;
	}

    /*************************************************
 *  SUBMODULE: getFileName
 *  IMPORT: none
 *  EXPORT: none
 *  PURPOSE: to get user to input a file.
 *  *************************************************/
	public static String getFileName()
	{
		Scanner sc = new Scanner (System.in);
		//variable declarations
		String fileName = null;
		//output to user
		System.out.print("Enter file name or enter (1) to use default file: ");
		fileName = inputString();
		//if default option selected
		if(fileName.equals("1"))
		{
			fileName = "datagenerated1.csv";
		}

		return fileName;
	}

    /*************************************************
 *  SUBMODULE: setRates
 *  IMPORT: healthSimulator
 *  EXPORT: none
 *  PURPOSE: to view/change/set rates of simulator
 *  *************************************************/
	public static void setRates(HealthSimulator healthSimulator)
	{
		int option;
		do
		{
			//output options
			System.out.println("(1). set rates\n(2). set intervention.\n(3). use default rates and intervention.\n(4). view rates and intervention.\n(0). exit");
			option = inputInt();
			
			switch(option)
			{
				case 1:
					//set rates by calling setPercentage method.
					healthSimulator.setTransferRate(setPercentage("transfer rate (0.00 - 1.00)"));
					healthSimulator.setRecoveryRate(setPercentage("recovery rate (0.00 - 1.00)"));
					healthSimulator.setDeathRate(setPercentage("death rate (0.00 - 1.00)"));
					viewRates(healthSimulator);
					
				break;
				
				case 2:
					
					setIntervention(healthSimulator);
				
				case 3:
					//set default rates.
					healthSimulator.setTransferRate(0.05);
					healthSimulator.setRecoveryRate(0.5);
					healthSimulator.setDeathRate(0.1);
					//view rates
					viewRates(healthSimulator);
				break;
			
				case 4:
					//view rates
					viewRates(healthSimulator);
				break;
			
				case 0:
				break;
			
				default:
					//if invalid option
					throw new IllegalArgumentException("invalid choice.");
			}
		}while(option != 0);
		
	}
	
    /*************************************************
 *  SUBMODULE: viewRates
 *  IMPORT: healthSimulator
 *  EXPORT: none
 *  PURPOSE: to view rates and intervention of simulator
 *  *************************************************/
	
	public static void viewRates(HealthSimulator healthSimulator)
	{
		//view rates and intervention
		System.out.println("Transfer Rate :"+healthSimulator.getTransferRate()+" / "+healthSimulator.getTransferRate()*100+"%");
		System.out.println("Recovery Rate :"+healthSimulator.getRecoveryRate()+" / "+healthSimulator.getRecoveryRate()*100+"%");
		System.out.println("Death Rate :"+healthSimulator.getDeathRate()+" / "+healthSimulator.getDeathRate()*100+"%");
		System.out.println("Intervention level :"+healthSimulator.getIntervention());
	}
	
	
	public static void setIntervention(HealthSimulator healthSimulator)
	{
		int intervention = 0;
		do
		{
			//output options.
			System.out.println("Enter intervention level (0-3): ");
			intervention = inputInt();
			//if invalid intervention
			if( (intervention < 0) || (intervention > 3) )
			{
				System.out.println("invalid intervention level.");
			}
			
		}while((intervention < 0) || (intervention > 3));	
		//set intervention
		healthSimulator.setIntervention(intervention);
	}
    /*************************************************
 *  SUBMODULE: setPercentage
 *  IMPORT: none
 *  EXPORT: none
 *  PURPOSE: to get user input.
 *  *************************************************/
	public static double setPercentage(String inString)
	{
		//variable declarations
		double percentage = 0.00;
		Scanner sc = new Scanner(System.in);
		boolean stop = true;
		//output to user
		
		do
		{
			try
			{
				//reset boolean, in case try catch has already looped.
				stop = true;
				
				//prompt user
				System.out.println("Enter "+inString+": ");
				//get input
				percentage = sc.nextDouble();
					
				//if invalid input
				if(percentage < 0 || percentage > 1)
				{
					System.out.println("Enter between 0.00-1.00");
				}
				

			}
			//catch wrong input 
			catch(InputMismatchException e)
			{
				//clear line
				sc.nextLine();
				//output to user
				System.out.println("invalid input.");
				//set stop to false, to loop while loop.
				stop = false;
			}
		}while(percentage < 0 || percentage > 1 || !stop);
		
		//return percentage
		return percentage;		
	}

    /*************************************************
 *  SUBMODULE: nodeOperations
 *  IMPORT: healthSimulator
 *  EXPORT: none
 *  PURPOSE: output a menu form which the user 
 *  can select 3 options - find, insert or delete.
 *  *************************************************/
	public static void nodeOperations(HealthSimulator healthSimulator)
	{
		//variable declarations
		int option = 0;
		String personName = null;
		Person person = null;
		
		do
		{
			//output options
			System.out.println("(1). find person\n(2). insert person\n(3). delete person\n(0). exit");
			option = inputInt();
			
			switch(option)
			{
				//find person
				case 1:
					//get name
					System.out.print("Enter name of person: ");
					personName = inputString();
					
					try
					{
						//call findPerson to return person object.
						person = healthSimulator.findPerson(personName);
					}
					//throw exception if person could not be found
					catch(IllegalArgumentException e)
					{
						System.out.println("Could not find person.");
					}
					
					//output toString of person
					System.out.println(person.toString());
					
				break;
				
				//insert person
				case 2:
					
					//call createPerson method to create a person object.
					person = createPerson();
					//insert person object in healthSimulator
					healthSimulator.insertPerson(person);
					
				break;
				//delete person
				case 3:
					
					//output name of person
					System.out.print("Enter name of person: ");
					personName = inputString();
					
					try
					{
						//call deletePerson method inside of healthSimulator
						healthSimulator.deletePerson(personName);
					}
					//if person could not be found or deleted
					catch(IllegalArgumentException e)
					{
						System.out.println("Could not find person.");
					}
					
				break;
				//exit
				case 0:
				break;
				
				default:
					throw new IllegalArgumentException("invalid choice.");
				
			}
			
		}while(option != 0);
		
	}

    /*************************************************
 *  SUBMODULE: createPerson
 *  IMPORT: healthSimulator
 *  EXPORT: none
 *  PURPOSE: provide a user interface to create 
 *  a person object.
 *  *************************************************/
	public static Person createPerson()
	{
		//variable declarations
		String name, dob, healthStatus;
		char group1 = 0, group2 = 0, charChoice = 0;
		int choice;
		Person person = null;
		
		//get name
		System.out.print("Enter name of person: ");
		name = inputString();
		
		//get DOB
		System.out.print("Enter date of birth in format DD/MM/YYYY: ");
		dob = inputString();
		
		//if invalid length of DOB string
		if(dob.length() != 10)
		{
			throw new IllegalArgumentException("invalid dob.");
		}
		
		//get health status
		System.out.println("Chose health status:\n(1). susceptible\n(2). infected\n(3). recovered\n(4). dead");
		choice = inputInt();
		
		if(choice == 1)
		{
			//status set.
			healthStatus = "susceptible";
		}
		else if(choice == 2)
		{
			//status set.
			healthStatus = "infected";
		}
		else if(choice == 3)
		{
			//status set.
			healthStatus = "recovered";
		}
		else if(choice == 4)
		{
			//status set.
			healthStatus = "dead";
		}
		else
		{
			throw new IllegalArgumentException("choice does not exist.");
		}
		
		//get group1
		System.out.println("Enter group - H / S / C / W");
		group1 = inputChar();
		if(group1 != 'H' || group1 != 'S' || group1 != 'C' || group1 != 'W' )
		{
			throw new IllegalArgumentException("group does not exist.");
		}
		
		//option to add another group
		System.out.println("Add another group? (y,n)");
		charChoice = inputChar();
		
		//if invalid choice
		if(charChoice != 'y' || charChoice != 'n')
		{
			throw new IllegalArgumentException("choice does not exist.");
		}
		

		if(charChoice == 'y' || charChoice == 'Y'  )
		{
			//get group2
			System.out.println("Enter group - H/S/C/W");
			group2 = inputChar();
			if(group1 != 'H' || group1 != 'S' || group1 != 'C' || group1 != 'W' )
			{
				throw new IllegalArgumentException("group does not exist.");
			}
			
			try
			{
				person = new Person (name,dob,healthStatus,group1,group2);
			}
			//if person object could not be created
			catch(IllegalArgumentException e)
			{
				throw new IllegalArgumentException("could not create person.");
			}
		}
		else if(charChoice ==  'n' || charChoice == 'N')
		{
			
			//initialize person
			try
			{
				person = new Person (name,dob,healthStatus,group1);
			}
			catch(IllegalArgumentException e)
			{
				throw new IllegalArgumentException("could not create person.");
			}
		}
		else
		{
			throw new IllegalArgumentException("choice does not exist.");
		}
		
		return person;
	}

    /*************************************************
 *  SUBMODULE: edgeOperations
 *  IMPORT: healthSimulator
 *  EXPORT: none
 *  PURPOSE: to output a menu to user from which they
 *  can select edge operations.
 *  *************************************************/
	public static void edgeOperations(HealthSimulator healthSimulator)
	{
		//variable declarations
		int nodeChoice = 0;
		String name1, name2;
		Person person = null;
		do
		{
			//output options
			System.out.println("(1). add Edge\n(2). remove Edge\n(3). view contact list of person.\n(0). exit");
			//get input
			nodeChoice = inputInt();
			
			//case statements
			switch (nodeChoice)
			{
				//add edge
				case 1:
					//get name of person 1
					System.out.println("1. enter first person's name: ");
					name1 = inputString();
					//get name of person2
					System.out.println("1. enter second person's name: ");
					name2 = inputString();
					
					//get type of connection
					System.out.println("type of group connection: W / H / S / C");
					char group = inputString().charAt(0);
					
					if(group != 'H' || group != 'S' || group != 'C' || group != 'W' )
					{
						throw new IllegalArgumentException("group does not exist.");
					}
					
					try
					{
						healthSimulator.addConnection(name1,name2,group);
					}
					catch(IllegalArgumentException e)
					{
						System.out.println("could not find person.");
					}
					
				break;
				//remove edge 
				case 2:
					
					//get name of person 1
					System.out.println("1. enter first person's name: ");
					name1 = inputString();
					//get name of person 2
					System.out.println("1. enter second person's name: ");
					name2 = inputString();
					
					try
					{
						//remove edge
						healthSimulator.removeConnection(name1,name2);
					}
					catch(IllegalArgumentException e)
					{
						System.out.println("could not delete edges.");
					}
					
				break;
				case 3:
					
					//get name of person
					System.out.println("enter person's name:");
					name1 = inputString();
					try
					{
						//call find person to return person object
						person = healthSimulator.findPerson(name1);
					}
					catch(IllegalArgumentException e)
					{
						System.out.println("could not find person.");
					}
					
					person.displayContacts();
					
				case 0:
				break;
				default:
					throw new IllegalArgumentException("invalid choice.");
			}
			
		}while(nodeChoice != 0);
		
	}

    /*************************************************
 *  SUBMODULE: newInfection
 *  IMPORT: healthSimulator
 *  EXPORT: none
 *  PURPOSE: to provide a menu from which the user
 *  can select options to: view new infections, view
 *  all infections or infect a person.
 *  *************************************************/
	public static void newInfection(HealthSimulator healthSimulator)
	{
		//variable declarations
		int nodeChoice = 0;
		String name;
		Person person = null;
		do
		{
			//output options
			System.out.println("(1).view new infected\n(2). view all infected.\n(3). infect person\n(0). exit");
			nodeChoice = inputInt();
			switch(nodeChoice)
			{
				case 1:
					
					//call newly infected function
					healthSimulator.newlyInfected();
					
				break;
				case 2:
					
					//call all infected function
					healthSimulator.allInfected();
					
				break;
				case 3:
					
					//get name of person
					System.out.println("Enter person's name: ");
					name = inputString();
					try
					{
						//call findPerson method to return person object
						person = healthSimulator.findPerson(name);
					}
					catch(IllegalArgumentException e)
					{
						System.out.println("Could not find person");
					}
					
					person.setHealthStatus("infected");
					
				break;
				case 0:
				break;
				default:
					throw new IllegalArgumentException("invalid option.");
						
			}
		}while(nodeChoice != 0);
		
	}

    /*************************************************
 *  SUBMODULE: getSimulationOption
 *  IMPORT: none
 *  EXPORT: none
 *  PURPOSE: to provide a menu from which the user
 *  can select simulation options.
 *  *************************************************/
	public static int getSimulationOption()
	{
		int choice = 0;
		do
		{
			//output to user
			System.out.println("(1). run simulation manually.");
			System.out.println("(2). run simulation until spread has stopped.");
			System.out.println("(3). run simulation a number of times.");
			System.out.println("(4). reload simulation.");
			System.out.println("(0). exit.");
			//get input
			choice = inputInt();
			//if invalid choice
			if(choice < 0 || choice > 4)
			{
				System.out.println("Chose between 1-4");
			}
		//loop if invalid choice
		}while(choice < 0 || choice > 4);
		
		//return choice
		return choice;
	}

    /*************************************************
 *  SUBMODULE: getSimulationOption
 *  IMPORT: none
 *  EXPORT: none
 *  PURPOSE: to provide a menu from which the user
 *  can select simulation options.
 *  *************************************************/
	public static void statistics(HealthSimulator healthSimulator,DSALinkedList messages)
	{
		int nodeChoice = 0;
		
		//output number of edges, to check if object is serializable
		System.out.println("Current number of connections: "+healthSimulator.getSociety().edgeCount());
		do
		{
			
			//output to user
			System.out.println("(1). output people in each health categories\n(2). output people in each social group\n(3). output previous timestep events\n(0). exit");
			//get input
			nodeChoice = inputInt();
			
		
			switch(nodeChoice)
			{
				//output list of health categories
				case 1:
				outputHealthCategory(healthSimulator);
				break;
				//output social group
				case 2:
				outputGroupCategory(healthSimulator);
				break;
				//output previous time step events
				case 3:
				outputEvents(messages);
				break;
					//exit
				case 0:								
				break;
				
				default:
					throw new IllegalArgumentException("invalid choice.");
			}

		}while(nodeChoice != 0);

	}

    /*************************************************
 *  SUBMODULE: outputEvents
 *  IMPORT: messages
 *  EXPORT: none
 *  PURPOSE: to output all objects within linked list.
 *  *************************************************/
	public static void outputEvents(DSALinkedList messages)
	{
		//get iterator of messages linked list
		Iterator iterate = messages.iterator();
		while(iterate.hasNext())
		{
			//output string
			System.out.println(iterate.next());
		}
		
	}
	
    /*************************************************
 *  SUBMODULE: displayPersonList
 *  IMPORT: perosnList
 *  EXPORT: none
 *  PURPOSE: to output name of each person object within
 *  the linked list.
 *  *************************************************/
	public static void displayPersonList(DSALinkedList personList)
	{
		//get adjacency list of person
		Iterator iterate = personList.iterator();
		//add counter
		int i = 1;
		//loop until end of list
		while(iterate.hasNext())
		{
			//initialize person
			Person person = (Person) iterate.next();
			//output person 
			System.out.println(i+": "+person.getName());
			//increment count
			i++;
		}
	}

    /*************************************************
 *  SUBMODULE: outputHealthCategory
 *  IMPORT: healthSimulator
 *  EXPORT: none
 *  PURPOSE: to output a menu so that a user
 *  can select one of the health categories to display.
 *  *************************************************/
	public static void outputHealthCategory(HealthSimulator healthSimulator)
	{
		int input = 0;
		do
		{
			//output to user
			System.out.println("(1). view infected group");
			System.out.println("(2). view susceptible group");
			System.out.println("(3). view recovered group");
			System.out.println("(4). view dead group");
			System.out.println("(0). exit");
			//get input
			input = inputInt();
			/*declare linked list, which will be passed to display method after
			person objects have been added.*/
			DSALinkedList group = null;
		
			switch(input)
			{
				//view infected 
				case 1: 
						//get infected group
						group = healthSimulator.getHealthGroup("infected");
						System.out.println("Infected group:\n-------------------------------");
						//pass linked list to be appended
						displayPersonList(group);
				break;
				//view susceptible
				case 2: 
						//get susceptible group
						group = healthSimulator.getHealthGroup("susceptible");
						System.out.println("susceptible group:\n-------------------------------");
						//pass linked list to be appended
						displayPersonList(group);				
				break;
				//view recovered group
				case 3:
						//get recovered group
						group = healthSimulator.getHealthGroup("recovered");
						System.out.println("recovered group:\n-------------------------------");
						//pass linked list to be appended
						displayPersonList(group);
				break;
				//view dead group
				case 4:
						//get dead group
						group = healthSimulator.getHealthGroup("dead");
						System.out.println("dead group:\n-------------------------------");
						//pass linked list to be appended
						displayPersonList(group);
						break;
				case 0:
					break;
			
					default:
						//if invalid choice
						throw new IllegalArgumentException("invalid choice.");
			}
		}while(input != 0);
		
	}

    /*************************************************
 *  SUBMODULE: outputGroupCategory
 *  IMPORT: healthSimulator
 *  EXPORT: none
 *  PURPOSE: to output a menu so that a user
 *  can select one of the group categories to display.
 *  *************************************************/
	public static void outputGroupCategory(HealthSimulator healthSimulator)
	{
		int input = 0;
		do
		{
			//output to user
			System.out.println("(1). view Home group");
			System.out.println("(2). view Work group");
			System.out.println("(3). view School group");
			System.out.println("(4). view Club group");
			System.out.println("(0). exit");
			//get input
			input = inputInt();
			//declare linked list
			DSALinkedList group = null;
			switch(input)
			{
				case 1: 
					//get Home group
					group = healthSimulator.getGroup('H');
					System.out.println("Home group:\n------------------------------");
					//output to user
					displayPersonList(group);
					break;
				case 2: 
					//get Work group
					group = healthSimulator.getGroup('W');
					System.out.println("Work group:\n------------------------------");
					//output to user
					displayPersonList(group);
					break;
				case 3:
					//get School group
					group = healthSimulator.getGroup('S');
					System.out.println("School group:\n------------------------------");
					//output to user
					displayPersonList(group);
					break;
				case 4:
					//get Club group
					group = healthSimulator.getGroup('C');
					System.out.println("Club group:\n------------------------------");
					//output to user
					displayPersonList(group);
					break;
				case 0:
					break;
			
				default:
					//if invalid choice
					throw new IllegalArgumentException("invalid choice.");
			}
		}while(input != 0);
	}

    /*************************************************
 *  SUBMODULE: saveNetwork
 *  IMPORT: healthSimulator
 *  EXPORT: none
 *  PURPOSE: display options to save network.
 *  *************************************************/
	public static void saveNetwork(HealthSimulator healthSimulator)
	{
		int choice = 0;
		do 
		{
			System.out.println("(1). save as normal file\n(2). save as serialized file\n(0).exit");
			choice = inputInt();
			
			switch(choice)
			{
				case 1:
					FileManager.saveNetworkFile(healthSimulator);
				break;
				case 2:
					FileManager.saveNetwork(healthSimulator);
				break;
				case 0:
				break;
				default:
					throw new IllegalArgumentException("invalid choice.");
			}
		} while (choice != 0);
		
	}
}
