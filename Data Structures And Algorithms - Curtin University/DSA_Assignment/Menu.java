import java.io.Serializable;

/*************************************************
 * AUTHOR: Izan Muhammad
 * PURPOSE: provide menu for interaction mode.
*************************************************/
public class Menu implements Serializable
{
	
    /*************************************************
 *  SUBMODULE: menu
 *  IMPORT: none
 *  EXPORT: none
 *  PURPOSE: menu for interactive mode.
 *  *************************************************/
	public static void menu()
	{
		//variable declarations
		int option = 0;
		HealthSimulator healthSimulator = new HealthSimulator();
		DSALinkedList messages = new DSALinkedList();
		
		do
		{
			//interaction mode interface.	
			option = UI.getMenuOption();
		
			
			switch(option)
			{
				//load network
				case 1:	
					
					try
					{
						//get user to input filename
						String fileName = UI.getFileName();
						//call addSociety to load population array.
						healthSimulator.addSociety(FileManager.loadNetwork(fileName));
					}
					catch(Exception e)
					{
						System.out.println(e.getMessage());
					}
					
				break;
				
				//set rates/interventions
				case 2:
				
					try
					{
						UI.setRates(healthSimulator);
					}
					catch(Exception e)
					{
						//output to user.
						System.out.println(e.getMessage());
					}
				
				break;
				
				//node operations (find, insert, delete)
				case 3:
					try
					{
						UI.nodeOperations(healthSimulator);
					}
					catch(Exception e)
					{
						System.out.println(e.getMessage());
					}
				
				break;
				//edge operations ( add, remove)
				case 4:
					
					try
					{
						UI.edgeOperations(healthSimulator);
					}
					catch(Exception e)
					{
						System.out.println(e.getMessage());
					}

				break;
				// new infections (show newly infected)
				case 5:
					
					try
					{
						
						UI.newInfection(healthSimulator);
					}
					catch(Exception e)
					{
						System.out.println(e.getMessage());
					}
				break;
				//display network
				case 6:
					
					try			
					{
						
						UI.statistics(healthSimulator,messages);
					}
					catch(Exception e)
					{
						System.out.println(e.getMessage());
					}
				break;
				//display statistics
				case 7:
					
					try
					{
						
						healthSimulator.Statistics();
					}
					catch(Exception e)
					{
						System.out.println(e.getMessage());
					}
					
				break;
				//update(run a time step)
				case 8:
					
					try
					{
						messages = healthSimulator.runTimeStep();
					}
					catch(Exception e)
					{
						System.out.println("Rates are not set.");
					}
					
				break;
				//save network.
				case 9:
					
					try
					{
						UI.saveNetwork(healthSimulator);
						
					}
					catch(Exception e)
					{
						System.out.println("error: "+e.getMessage());
					}
					
				break;
				//exit
				case 0:
					System.out.println("goodbye.");
				break;
				//in case of invalid value.
				default:
					System.out.println("invalid option.");
				break;
			}
			
		}while(option != 0);
				
	}
}
