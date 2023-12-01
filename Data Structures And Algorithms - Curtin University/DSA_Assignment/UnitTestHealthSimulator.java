/*Design of test harness is based on UnitTestLinkedList given on practical 4*/
public class UnitTestHealthSimulator
{
	public static void main(String[] args)
	{
		HealthSimulator simulator1 = null;
		HealthSimulator simulator2 = null;
		HealthSimulator healthsim3 = new HealthSimulator(0.5,0.5,0.1,0);
		Person person1 = new Person("john","02/04/2020","infected",'S');
    	Person person2 = new Person("john2.0","02/04/2020","susceptible",'S');
    	Person person3 = new Person("john3.0","02/04/2020","susceptible",'H');
		int iNumPassed = 0;
        int iNumTests = 0;
        
        //testing initialization
        System.out.println("================================\nTesting default constructor:\n================================\n");
        try {
            iNumTests++;
            
            simulator1 = new HealthSimulator();
            if(simulator1.equals(null))
            {
            	throw new IllegalArgumentException ("");
            }
           
            iNumPassed++;
            System.out.println("\n=======\npassed\n=======");
        }catch(Exception e) { System.out.println("FAILED"); }  
          
        System.out.println("================================\nTesting alternate constructor:\n================================\n");
        try {
            iNumTests++;
            
            simulator2 = new HealthSimulator(0.05,0.5,0.1,1);
            if(simulator2.equals(null))
            {
            	throw new IllegalArgumentException ("");
            }
           
            iNumPassed++;
            System.out.println("\n=======\npassed\n=======");
        }catch(Exception e) { System.out.println("FAILED"); }  
          
       
        System.out.println("================================\nTesting adddSociety method:\n================================\n");
        try {
            

        	Person[] population = {person1,person2};
        	simulator1.addSociety(population);

            iNumPassed++;
            System.out.println("\n=======\npassed\n=======");
        }catch(Exception e) { System.out.println("FAILED"); }  
        
        System.out.println("================================\nTesting adddSociety method:\n================================\n");
        try {
            

        	simulator1.LinkSociety();
        	
            iNumPassed++;
            System.out.println("\n=======\npassed\n=======");
        }catch(Exception e) { System.out.println("FAILED"); }  
         
        System.out.println("================================\nTesting findPerson method:\n================================\n");
        try {
            iNumTests++;
            
            Person temperson = simulator1.findPerson("john");
            System.out.println(temperson.toString());
            iNumPassed++;
            System.out.println("\n=======\npassed\n=======");
        }catch(Exception e) { System.out.println("FAILED"); }  
        
        System.out.println("================================\nTesting insertPerson method:\n================================\n");
        try {
            iNumTests++;
            
            simulator1.insertPerson(person3);
            iNumPassed++;
            System.out.println("\n=======\npassed\n=======");
        }catch(Exception e) { System.out.println("FAILED"); }  
        
        System.out.println("================================\nTesting deletePerson method:\n================================\n");
        try {
            iNumTests++;
            
            Iterator iterate = simulator1.getSociety().graphIterator();
            while(iterate.hasNext())
            {
            	System.out.println(((Person)iterate.next()).toString());
            }
            simulator1.deletePerson(person2.getName());
            System.out.println("--------------------------------------------");
            iterate = simulator1.getSociety().graphIterator();
            while(iterate.hasNext())
            {
            	System.out.println(((Person)iterate.next()).toString());
            }
            iNumPassed++;
            System.out.println("\n=======\npassed\n=======");
        }catch(Exception e) { System.out.println("FAILED"); }  
        
        
        System.out.println("================================\nTesting addConnection method:\n================================\n");
        try {
            iNumTests++;
            
            simulator1.addConnection(person1.getName(), person3.getName(), person1.getGroup1());
            iNumPassed++;
            System.out.println("\n=======\npassed\n=======");
        }catch(Exception e) { System.out.println("FAILED"); }  
        
        System.out.println("================================\nTesting removeConnection method:\n================================\n");
        try {
            iNumTests++;
            
            simulator1.removeConnection(person1.getName(),person3.getName());
            iNumPassed++;
            System.out.println("\n=======\npassed\n=======");
        }catch(Exception e) { System.out.println("FAILED"); }  
        
        System.out.println("================================\nTesting Statistics method:\n================================\n");
        try {
            iNumTests++;
            
            simulator1.Statistics();
            iNumPassed++;
            System.out.println("\n=======\npassed\n=======");
        }catch(Exception e) { System.out.println("FAILED"); } 
        
        
        System.out.println("================================\nTesting timeStep - 100% death rate method:\n================================\n");
        try {
            iNumTests++;
            System.out.println("this may not work 100% of the time, since death rate changes depending on person.");
            
            Person tempPerson1 = new Person ("John1","02/04/2020","infected",'S');
            Person tempPerson2 = new Person ("John2","02/04/2020","infected",'S');
            Person tempPerson3 = new Person ("John3","02/04/2020","infected",'S');
            healthsim3.insertPerson(tempPerson3);
            healthsim3.insertPerson(tempPerson2);
            healthsim3.insertPerson(tempPerson1);
            healthsim3.setRecoveryRate(0.001);
            healthsim3.setDeathRate(1);
            healthsim3.runTimeStep();
            iNumPassed++;
            System.out.println("\n=======\npassed\n=======");
        }catch(Exception e) { System.out.println("FAILED"); } 
        
        System.out.println("================================\nTesting getHealthGroup method:\n================================\n");
        try {
            iNumTests++;
            
            
            DSALinkedList group = healthsim3.getHealthGroup("dead");
            Iterator iterate = group.iterator();
            while(iterate.hasNext())
            {
            	System.out.println(((Person)iterate.next()).toString());
            }
            iNumPassed++;
            System.out.println("\n=======\npassed\n=======");
        }catch(Exception e) { System.out.println("FAILED"); } 
        
        System.out.println("================================\nTesting getGroup method:\n================================\n");
        try {
            iNumTests++;
            
            
            DSALinkedList group = healthsim3.getGroup('S');
            Iterator iterate = group.iterator();
            while(iterate.hasNext())
            {
            	System.out.println(((Person)iterate.next()).toString());
            }
            iNumPassed++;
            System.out.println("\n=======\npassed\n=======");
        }catch(Exception e) { System.out.println("FAILED"); } 
        
        System.out.println("================================\nTesting InfectedCount method:\n================================\n");
        try {
            iNumTests++;
            
            Person infectedPerson = new Person("Person","02/04/2020","infected",'S');
            healthsim3.insertPerson(infectedPerson);
            int count = healthsim3.InfectedCount();
            if(count != 1)
            {
            	throw new IllegalArgumentException("");
            }
            
            iNumPassed++;
            System.out.println("\n=======\npassed\n=======");
        }catch(Exception e) { System.out.println("FAILED"); } 
        
        //---------------------------------------------------------------------------

        // PRINT TEST SUMMARY
        System.out.print("\nNumber PASSED: " + iNumPassed + "/" + iNumTests);
        System.out.print(" -> " + (int)(double)iNumPassed/iNumTests*100 + "%\n");
    
//---------------------------------------------------------------------------
        
	}
}
