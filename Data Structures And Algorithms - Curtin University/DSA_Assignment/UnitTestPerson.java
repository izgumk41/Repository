/*Design of test harness is based on UnitTestLinkedList given on practical 4*/
public class UnitTestPerson
{
	public static void main(String[] args)
	{
		Person person1 = null;
		Person person2  = null;
		int iNumPassed = 0;
        int iNumTests = 0;
        
        //testing initialization
        System.out.println("================================\nTesting alternate constructor 1:\n================================\n");
        try {
            iNumTests++;
            
            person1 = new Person("John","02/04/1999","infected",'S');
            if(person1.equals(null))
            {
            	throw new IllegalArgumentException ("");
            }
            System.out.println(person1.toString());
            iNumPassed++;
            System.out.println("\n=======\npassed\n=======");
        }catch(Exception e) { System.out.println("FAILED"); }  
          
        //testing initialization
        System.out.println("================================\nTesting alternate constructor 2:\n================================\n");
        try {
            iNumTests++;
            
            person2 = new Person("John 2.0","02/04/1999","susceptible",'S','H');
            if(person2.equals(null))
            {
            	throw new IllegalArgumentException ("");
            }
            System.out.println(person2.toString());
            iNumPassed++;
            System.out.println("\n=======\npassed\n=======");
        }catch(Exception e) { System.out.println("FAILED"); }  
          
       
        System.out.println("================================\nTesting equals method:\n================================\n");
        try {
            iNumTests++;
            System.out.println(person1.getName()+" = "+person2.getName());
            if(person1.equals(person2))
            {
            	throw new IllegalArgumentException ("");
            }
            
            iNumPassed++;
            System.out.println("\n=======\npassed\n=======");
        }catch(Exception e) { System.out.println("FAILED"); }  
         
        System.out.println("================================\nTesting convertToDate method:\n================================\n");
        try {
            iNumTests++;
            String date = "05/04/2020";
            person1.setDOB(date);
            if( !(person1.getDOB().toString().equals(date)) )
            {
            	throw new IllegalArgumentException ("");
            }
            
            System.out.println(person1.getDOB().toString());
            iNumPassed++;
            System.out.println("\n=======\npassed\n=======");
        }catch(Exception e) { System.out.println("FAILED"); }  
        
        System.out.println("================================\nTesting isInfected method:\n================================\n");
        try {
            iNumTests++;
            
            Person person = new Person("John","04/02/1942","infected",'S');
            if(!person.isInfected())
            {
            	throw new IllegalArgumentException ("");
            }
            iNumPassed++;
            System.out.println("\n=======\npassed\n=======");
        }catch(Exception e) { System.out.println("FAILED"); }  
        
        System.out.println("================================\nTesting isSusceptible method:\n================================\n");
        try {
            iNumTests++;
            
            Person person = new Person("John","04/02/1942","susceptible",'S');
            if(!person.isSusceptible())
            {
            	throw new IllegalArgumentException ("");
            }
            iNumPassed++;
            System.out.println("\n=======\npassed\n=======");
        }catch(Exception e) { System.out.println("FAILED"); }  
        
        System.out.println("================================\nTesting isRecovered method:\n================================\n");
        try {
            iNumTests++;
            
            Person person = new Person("John","04/02/1942","recovered",'S');
            if(!person.isRecovered())
            {
            	throw new IllegalArgumentException ("");
            }
            iNumPassed++;
            System.out.println("\n=======\npassed\n=======");
        }catch(Exception e) { System.out.println("FAILED"); }  
        
        System.out.println("================================\nTesting isDead method:\n================================\n");
        try {
            iNumTests++;
            
            Person person = new Person("John","04/02/1942","dead",'S');
            if(!person.isDead())
            {
            	throw new IllegalArgumentException ("");
            }
            iNumPassed++;
            System.out.println("\n=======\npassed\n=======");
        }catch(Exception e) { System.out.println("FAILED"); }  
        
        System.out.println("================================\nTesting addConnection method:\n================================\n");
        try {
            iNumTests++;
            
            Person tempPerson1 = new Person("John","04/02/1942","dead",'S');
            Person tempPerson2 = new Person("John1","04/02/1942","dead",'S');
            Person tempPerson3 = new Person("John2","04/02/1942","dead",'S');
      
            tempPerson1.addConnection(tempPerson2);
            Iterator iterate = tempPerson1.getConnectionList();
            while(iterate.hasNext())
            {
            	System.out.println(tempPerson1.getName()+": "+iterate.next().toString());
            }
            System.out.println("---------------------------------------------------------------------------\nAdding tempPerson3:\n---------------------------------------------------------------------------");
            tempPerson1.addConnection(tempPerson3);
            iterate = tempPerson1.getConnectionList();
            while(iterate.hasNext())
            {
            	System.out.println(tempPerson1.getName()+": "+iterate.next().toString());
            }
            
            iNumPassed++;
            System.out.println("\n=======\npassed\n=======");
        }catch(Exception e) { System.out.println("FAILED"); }  
        
        System.out.println("================================\nTesting havePeson method:\n================================\n");
    //    try {
            iNumTests++;
            
            person1.addConnection(person2);
            if(!(person1.havePerson(person2)))
            {
            	throw new IllegalArgumentException ("");
            }
            iNumPassed++;
            System.out.println("\n=======\npassed\n=======");
    //    }catch(Exception e) { System.out.println("FAILED"); } 
        
        System.out.println("================================\nTesting deleteConnectionFromList method:\n================================\n");
        try {
            iNumTests++;
            
            Iterator iterate = person1.getConnectionList();
            while(iterate.hasNext())
            {
            	System.out.println(person1.getName()+": "+iterate.next().toString());
            }
            
            person1.deleteConnectionFromList(person2);
           	System.out.println("-------------------------------------");
           	iterate = person1.getConnectionList();
           	while(iterate.hasNext())
           	{
        	   	System.out.println(person1.getName()+": "+iterate.next().toString());
           	}
            iNumPassed++;
            System.out.println("\n=======\npassed\n=======");
        }catch(Exception e) { System.out.println("FAILED"); }  
        
        System.out.println("================================\nTesting getRecoveryRate method:\n================================\n");
        try {
            iNumTests++;
            
            Date date = new Date();
            double recoveryRate = 0.5;
            Person tempPerson = new Person("test","01/01/2020","recovered",'S');
            double personRecoveryRate = tempPerson.getRecoveryRate(recoveryRate, date);
            if( !(Math.abs(recoveryRate - personRecoveryRate ) < 0.0001) )
            {   
            	throw new IllegalArgumentException ("");
            }
            
            iNumPassed++;
            System.out.println("\n=======\npassed\n=======");
        }catch(Exception e) { System.out.println("FAILED"); }  
        
        System.out.println("================================\nTesting getDeathRate method:\n================================\n");
        try {
            iNumTests++;
            
            Date date = new Date();
            double deathRate = 0.5;
            Person tempPerson = new Person("test","01/01/2020","recovered",'S');
            double personDeathRate = tempPerson.getDeathRate( deathRate, date);
            if( !(Math.abs( deathRate - personDeathRate ) < 0.0001) )
            {   
            	throw new IllegalArgumentException ("");
            }
            
            iNumPassed++;
            System.out.println("\n=======\npassed\n=======");
        }catch(Exception e) { System.out.println("FAILED"); } 
        
        System.out.println("================================\nTesting addMessage & displayContacts method:\n================================\n");
        try {
            iNumTests++;
            
            person1.addMessage("Test");
            person1.displayContacts();
            
            iNumPassed++;
            System.out.println("\n=======\npassed\n=======");
        }catch(Exception e) { System.out.println("FAILED"); }
        
        System.out.println("================================\nTesting toString & toFileString method:\n================================\n");
        try {
            iNumTests++;
            
            System.out.println(person1.toString());
            System.out.println(person1.toFileString());
            
            iNumPassed++;
            System.out.println("\n=======\npassed\n=======");
        }catch(Exception e) { System.out.println("FAILED"); }
        
        System.out.println("================================\nTesting infectPerson method:\n================================\n");
        try {
            iNumTests++;
            System.out.println(person2.toString());
            person1.infectPerson(person2);
            
            System.out.println(person2.toString());

            
            iNumPassed++;
            System.out.println("\n=======\npassed\n=======");
        }catch(Exception e) { System.out.println("FAILED"); }
        
        System.out.println("================================\nTesting recovered method:\n================================\n");
        try {
            iNumTests++;
            System.out.println(person2.toString());
            person2.recovered();
            
            System.out.println(person2.toString());

            
            iNumPassed++;
            System.out.println("\n=======\npassed\n=======");
        }catch(Exception e) { System.out.println("FAILED"); } 
        
        System.out.println("================================\nTesting death method:\n================================\n");
        try {
            iNumTests++;
            
            System.out.println(person2.toString());
            person2.death();
            
            System.out.println(person2.toString());


            
            iNumPassed++;
            System.out.println("\n=======\npassed\n=======");
        }catch(Exception e) { System.out.println("FAILED"); } 
        
        System.out.println("================================\nTesting hospitalize method:\n================================\n");
        try {
            iNumTests++;
            
            person2.hospitalize();
            if(!person2.getInHospital())
            {
            	throw new IllegalArgumentException("");
            }

            
            iNumPassed++;
            System.out.println("\n=======\npassed\n=======");
        }catch(Exception e) { System.out.println("FAILED"); } 
        
        System.out.println("================================\nTesting addDayInfected method:\n================================\n");
        try {
            iNumTests++;
            System.out.println(person2.toString());
            person2.addDayInfected();
            System.out.println(person2.toString());
            iNumPassed++;
            System.out.println("\n=======\npassed\n=======");
        }catch(Exception e) { System.out.println("FAILED"); } 
        
        System.out.println("================================\nTesting isHospitalizedString method:\n================================\n");
        try {
            iNumTests++;
            if(person2.isHospitalizedString().equals("hospitalized"))
            {
            }
            else
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
