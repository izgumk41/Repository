/*************************************************
 * AUTHOR: Izan Muhammad
 * PURPOSE: all file input and output methods
*************************************************/
import java.io.*;
import java.util.*;

public class FileManager implements Serializable
{
    /*************************************************
 *  SUBMODULE: loadNetwork
 *  IMPORT: fileName
 *  EXPORT: none
 *  PURPOSE: to read from file and store into person array
 *  *************************************************/
	public static Person[] loadNetwork (String fileName)
	{

		//store the number of lines in file.
		int filesize = filesize(fileName);
		//filesize is used to declare array.v
		Person[] population = new Person[filesize];
		//readfile is called to initialize array.
		ReadFile(fileName,population);

		return population;
	}
	
    /*************************************************
 *  SUBMODULE: fileSize
 *  IMPORT: filename
 *  EXPORT: count
 *  PURPOSE: to count the number of lines in file.
 *  *************************************************/
	public static int filesize(String fileName)
	{

		//variable declarations
	    FileInputStream fileStrm = null;
	    InputStreamReader rdr;
	    BufferedReader bufRdr;   
	    int count;
	    String line;
	    Scanner sc = new Scanner (System.in);
	    count = 0;

	    try
	    {
	    	//initializing variables
	        fileStrm = new FileInputStream (fileName);
	        rdr = new InputStreamReader (fileStrm);
	        bufRdr = new BufferedReader (rdr);
	        line = bufRdr.readLine();
	        //while not end of file.
	        while (line != null)
	        {
	        	//go to next line
	            line = bufRdr.readLine();
	            //increment count
	            count++;

	        }
	        //close file stream.
	        fileStrm.close();

	    }
	    catch (IOException e)
	    {
	        if(fileStrm != null)
	        {
	            try
	            {
	                fileStrm.close();
	            }
	            catch (IOException ex2) {}
	        }
	        System.out.println ("Error in file processing: "+e.getMessage());
	    }

	    return count;	        
	}
	
    /*************************************************
 *  SUBMODULE: ReadFile
 *  IMPORT: filename,population
 *  EXPORT: count
 *  PURPOSE: to read the file and store object into array.
 *  *************************************************/
	public static int ReadFile (String fileName, Person[] population)
	{
	    FileInputStream fileStrm = null;
	    InputStreamReader rdr;
	    BufferedReader bufRdr;
	    
	    int count;
	    String line;
	    Scanner sc = new Scanner (System.in);


	    count = 0;

	    try
	    {
	        fileStrm = new FileInputStream (fileName);
	        rdr = new InputStreamReader (fileStrm);
	        bufRdr = new BufferedReader (rdr);

	        line = bufRdr.readLine();

	        while (line != null)
	        {
	            //passes array on count to process line
	            ProcessLine(line, population ,count);
	            line = bufRdr.readLine();

	            count++;

	        }

	        fileStrm.close();

	    }
	    catch (IOException e)
	    {
	        if(fileStrm != null)
	        {
	            try
	            {
	                fileStrm.close();
	            }
	            catch (IOException ex2) {}
	        }
	        System.out.println ("Error in file processing: "+e.getMessage());
	    }

	    return count;
	}//end read file
	
    /*************************************************
 *  SUBMODULE: ProcessLine
 *  IMPORT: none
 *  EXPORT: none
 *  PURPOSE: to read line buffer, make a person object then store into array.
 *  *************************************************/
	public static void ProcessLine (String line, Person [] population, int count)
	{

		//variable declarations
		char group2 = 0;
		//lineArray initializes to line buffer in file.
		String[] lineArray;
		lineArray = line.split(",");
		//name is initialized
		String name = lineArray[0];
		//DOB is initialized
		String DOB = lineArray[1];
		//health status is initialized
		String healthStatus = lineArray[2];
		//first group is initialized.
		char group1 = lineArray[3].charAt(0);

		//checks if line array length is more than 5, therefore would have another group.
		if(lineArray.length >= 5)
		{
			//initialize group 2 variable.
			group2 = lineArray[4].charAt(0);
			//initialize person object using alternate constructor #2
			Person person = new Person(name,DOB,healthStatus,group1,group2);
			//add person to array.
			population[count] = person;
		}
		//else if person does not have a second group.
		else
		{

			//initialize person object using alternate constructor #1
			Person person = new Person(name,DOB,healthStatus,group1);
			//add person to array.
			population[count] = person;

		}

	}//end processline

    /*************************************************
 *  SUBMODULE: saveNetwork
 *  IMPORT: none
 *  EXPORT: none
 *  PURPOSE: to save time step events to a unique file.
 *  *************************************************/
	public static void saveNetwork(HealthSimulator healthSimulator)
	{
		System.out.println("Enter file name:");
		String fileName = UI.inputString();
		
		FileOutputStream fileStrm;
        ObjectOutputStream objStrm = null;
              
		
		
		try
		{
			fileStrm = new FileOutputStream (fileName);
			objStrm = new ObjectOutputStream(fileStrm);
			
			objStrm.writeObject(healthSimulator);
			
			objStrm.close();
			System.out.println("Serialized object saved to: "+fileName);

		}
		catch (IOException e)
		{
			throw new IllegalArgumentException("unable to save file."+e.getMessage());
		}
		catch(StackOverflowError ex2)
		{
			throw new IllegalArgumentException("Too many connections/edges : "+healthSimulator.getSociety().edgeCount());
		}
		catch (Exception ex3)
		{
			throw new IllegalArgumentException("unable to save file."+ex3.getMessage());
		}
		
	}
	
    /*************************************************
 *  SUBMODULE: saveNetwork
 *  IMPORT: none
 *  EXPORT: none
 *  PURPOSE: to save time step events to a unique file.
 *  *************************************************/
	public static void saveNetworkFile(HealthSimulator healthSimulator)
	{
		System.out.println("Enter file name:");
		String fileName = UI.inputString();
		
		FileOutputStream fileStrm = null;
		PrintWriter pw;
		
		try
		{
			fileStrm = new FileOutputStream (fileName);
			pw = new PrintWriter(fileStrm);
			Iterator population = healthSimulator.getSociety().graphIterator();
			while(population.hasNext())
			{
				Person person = (Person) population.next();
				pw.println(person.toFileString());
			}
			
			pw.close();
			System.out.println("File saved to: "+fileName);
		}
		catch (IOException e)
		{
			if(fileStrm!= null)
			{
				try
				{
					fileStrm.close();
				}
				catch(IOException ex2)
				{	
				}
			}
		}
		
	}

    /*************************************************
 *  SUBMODULE: simulation
 *  IMPORT: args (String array)
 *  EXPORT: none
 *  PURPOSE: to save time step events to a unique file.
 *  *************************************************/
	public static void simulation(String[] args)
	{
		double transferRate = Double.parseDouble(args[2]);
		double recoveryRate = Double.parseDouble(args[3]);
		double deathRate = Double.parseDouble(args[4]);
		int intervention = Integer.parseInt(args[5]);
		
		HealthSimulator healthSimulator = new HealthSimulator(transferRate, recoveryRate, deathRate, intervention);
		Person[] population = loadNetwork(args[1]);
		healthSimulator.addSociety(population);
		
		runSimulation(healthSimulator,args);
		
	}

    /*************************************************
 *  SUBMODULE: runSimulation
 *  IMPORT: healthSimulator
 *  EXPORT: none
 *  PURPOSE: to provide interface to select mode of simulation.
 *  *************************************************/
	public static void runSimulation(HealthSimulator healthSimulator,String[] args)
	{
		int simulationOption = 0;
		do
		{
			simulationOption = UI.getSimulationOption();
			
			switch(simulationOption)
			{
				//run iteratively
				case 1:
					try
					{
						runManual(healthSimulator);
					}
					catch(Exception e)
					{
						System.out.println("invalid choice.");
					}
					break;
					//run until last infected.
				case 2:
					try
					{
						runAuto(healthSimulator);
					}
					catch(Exception e)
					{
						System.out.println("invalid choice.");
					}
					break;
					//run limited number of times.
				case 3:
					try
					{
						runLimited(healthSimulator);
					}
					catch(Exception e)
					{
						System.out.println("invalid choice.");
					}
				break;
				case 4:
					simulation(args);
				break;
				//exit
				case 0:
					break;
				default:
					System.out.println("invalid choice.");
			}
		}while(simulationOption != 0);
		
		
	}

    /*************************************************
 *  SUBMODULE: runLimited
 *  IMPORT: healthSimulator
 *  EXPORT: none
 *  PURPOSE: to run time step a limited number of times.
 *  *************************************************/
	public static void runLimited(HealthSimulator healthSimulator)
	{
		System.out.println("Enter number of timesteps: ");
		int input = UI.inputInt();
		int timeStepCount = 1;	
		DSALinkedList message = null;
		for(int i = 0; i < input; i++)
		{
			message = healthSimulator.runTimeStep();
			writeToFile(message,timeStepCount);
			timeStepCount++;
		}
	}

    /*************************************************
 *  SUBMODULE: runAuto
 *  IMPORT: healthSimulator
 *  EXPORT: none
 *  PURPOSE: to run time step until end of spread.
 *  *************************************************/
	public static void runAuto(HealthSimulator healthSimulator)
	{
		int timeStepCount = 0;	
		DSALinkedList message = null;
		
		long start = System.currentTimeMillis();
		do
		{
			message = healthSimulator.runTimeStep();
			writeToFile(message,timeStepCount);
			timeStepCount++;
			
		}while(healthSimulator.InfectedCount() != 0);
		
		long stop = System.currentTimeMillis();
		
		System.out.println("Disease eradicated in: "+(stop-start)+" ms.");
		
	}

    /*************************************************
 *  SUBMODULE: runManual
 *  IMPORT: healthSimulator
 *  EXPORT: none
 *  PURPOSE: to run time step iterately.
 *  *************************************************/
	public static void runManual(HealthSimulator healthSimulator)
	{
		int input = 0;
		do
		{
			System.out.println("(1). run simulation\n(0). exit");
			input = UI.inputInt();
			DSALinkedList message = null;
			int timeStepCount = 0;
			switch(input)
			{
				case 1:
					message = healthSimulator.runTimeStep();
					writeToFile(message,timeStepCount);
					timeStepCount++;
				break;
				case 0:
				break;
				
				default:
				throw new IllegalArgumentException("invalid choice.");
			}
		}while(input != 0);
		
		
	}

    /*************************************************
 *  SUBMODULE: writeToFile
 *  IMPORT: message, timeStepCount
 *  EXPORT: none
 *  PURPOSE: imports LinkedList message which is a list
 *  of the events which occurred in time step. 
 *  *************************************************/
	public static void writeToFile(DSALinkedList message,int timeStepCount)
	{
		String fileName = "healthSimulator_TimeStep"+timeStepCount+".txt";
		

        FileOutputStream fileStrm = null;
        PrintWriter pw;
        
        try
        {
        	//initialize output stream
            fileStrm = new FileOutputStream (fileName);
            //initialize print writer.
            pw = new PrintWriter(fileStrm);
            Iterator iterate = (Iterator) message.iterator();
            while(iterate.hasNext())
            {
            	pw.println(iterate.next());
            }
            
            pw.close();
            System.out.println("Timestep events have been saved to file :"+fileName);
            
        }
        catch (IOException e)
        {
        	if (fileStrm != null)
        	{
        		try 
        		{
        			fileStrm.close();
        		}//end try
        		catch (IOException ex2)
        		{
        		}// end catch
        	}      	  
        	System.out.println("Error in writing to file: "+e.getMessage());           	
        }
	}

    /*************************************************
 *  SUBMODULE: loadSerializedFile
 *  IMPORT: healthSimulator
 *  EXPORT: none
 *  PURPOSE: load serialized file  
 *  *************************************************/
	public static void loadSerializedFile(HealthSimulator healthSimulator)
	{
		String fileName = UI.getFileName();
		
		 FileInputStream inFileStrm;
	     ObjectInputStream inObjStrm;
	     
	     try
	     {
	    	 inFileStrm = new FileInputStream(fileName);
	         inObjStrm = new ObjectInputStream(inFileStrm);
	         healthSimulator = (HealthSimulator) inObjStrm.readObject();
	         inObjStrm.close();
	     }
	     catch (ClassNotFoundException e)
	     {
	        System.out.println("Class HealthSimulator not found"+e.getMessage());
	     }
	     catch(Exception e)
	     {
	        throw new IllegalArgumentException("Unable to load object file");
	     }
	       	
	}
}
