/*Design of test harness is based on UnitTestLinkedList given on practical 4*/
public class UnitTestDSAGraphEdge
{
	public static void main(String[] args)
	{
		DSAGraphEdge edge = null;
		Person person1 = null;
		Person person2 = null;
		int iNumPassed = 0;
        int iNumTests = 0;

        
		//Testing addVertex:
        System.out.println("================================\nTesting alternate constructor:\n================================\n");
        try {
            iNumTests++;
            person1 = new Person("john","02/03/1999","infected",'S');
            person2 = new Person("john2","02/03/1999","infected",'S');
            
            edge = new DSAGraphEdge(person1,person2,person1.getGroup1());
            if(edge.equals(null))
            {
            	throw new IllegalArgumentException("");
            }
            
            iNumPassed++;
            System.out.println("\n=======\npassed\n=======");
        }catch(Exception e) { System.out.println("FAILED"); }  
        
        System.out.println("================================\nTesting setDistance & getDistance:\n================================\n");
        try {
        	iNumTests++;
        	
        	int distance = edge.getDistance();
        	
        	if(distance != 4)
        	{
        		throw new IllegalArgumentException("");
        	}
        	
            iNumPassed++;
            System.out.println("\n=======\npassed\n=======");
        }catch(Exception e) { System.out.println("FAILED"); } 
        
        System.out.println("================================\nTesting setTypeOfContact & getTypeOfContact:\n================================\n");
        try {
        	iNumTests++;
        	
        	String contact = edge.getTypeOfContact();
        	if(contact.equals("shakes hand"))
        	{
        	}
        	else if(contact.equals("converses"))
        	{	
        	}
        	else if(contact.equals("shares lunch"))
        	{	
        	}
        	else if(contact.equals("is in a group project"))
        	{	
        	}
        	else
        	{
        		throw new IllegalArgumentException("");
        	}
        	
            iNumPassed++;
            System.out.println("\n=======\npassed\n=======");
        }catch(Exception e) { System.out.println("FAILED"); } 
        
        System.out.println("================================\nTesting equals method:\n================================\n");
        try {
        	iNumTests++;
        	
        	DSAGraphEdge edge2 = new DSAGraphEdge(person2,person1,person1.getGroup1());
        	if(edge.equals(edge2))
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
