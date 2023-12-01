/*Design of test harness is based on UnitTestLinkedList given on practical 4*/
public class UnitTestDSALinkedList
{
	public static void main (String[] args)
	{
		DSALinkedList list = null;
		
		int iNumPassed = 0;
        int iNumTests = 0;
        int i = 0;
        
        System.out.println("================================\nTesting default constructor:\n================================\n");
        try {
            iNumTests++;
            list = new DSALinkedList();
            if(list == null)
            {
            	throw new IllegalArgumentException("");
            }
            
            iNumPassed++;
            System.out.println("\n=======\npassed\n=======");
        }catch(Exception e) { System.out.println("FAILED"); }  
        
        System.out.println("================================\nTesting insertFirst method:\n================================\n");
        try {
            iNumTests++;
            
            Person person1 = new Person("john1","02/03/1999","infected",'S');
            Person person2 = new Person("johnFirst","02/03/1999","infected",'S');
            list.insertFirst(person1);
            list.insertFirst(person2);
            
            Iterator iterate = list.iterator();
            i = 1;
            while(iterate.hasNext())
            {
            	System.out.println(i+": "+iterate.next());
            	i++;
            }
            
            iNumPassed++;
            System.out.println("\n=======\npassed\n=======");
        }catch(Exception e) { System.out.println("FAILED"); }  
        
        System.out.println("================================\nTesting insertLast method:\n================================\n");
        try {
            iNumTests++;
            
            Person person3 = new Person("john3","02/03/1999","infected",'S');
            Person person4 = new Person("johnLast","02/03/1999","infected",'S');
            
            Iterator iterate = list.iterator();
            i = 1;
            while(iterate.hasNext())
            {
            	System.out.println(i+": "+iterate.next());
            	i++;
            }
            
            iNumPassed++;
            System.out.println("\n=======\npassed\n=======");
        }catch(Exception e) { System.out.println("FAILED"); } 
        
        System.out.println("================================\nTesting peekFirst method:\n================================\n");
        try {
            iNumTests++;
           
            System.out.println(list.peekFirst());
            
            iNumPassed++;
            System.out.println("\n=======\npassed\n=======");
        }catch(Exception e) { System.out.println("FAILED"); } 
        
        System.out.println("================================\nTesting peekLast method:\n================================\n");
        try {
            iNumTests++;
           
            System.out.println(list.peekLast());
            
            iNumPassed++;
            System.out.println("\n=======\npassed\n=======");
        }catch(Exception e) { System.out.println("FAILED"); } 
        
        System.out.println("================================\nTesting removeFirst method:\n================================\n");
        try {
            iNumTests++;
            
            list.removeFirst();
            
            Iterator iterate = list.iterator();
            i = 1;
            while(iterate.hasNext())
            {
            	System.out.println(i+": "+iterate.next());
            	i++;
            }
            
            iNumPassed++;
            System.out.println("\n=======\npassed\n=======");
        }catch(Exception e) { System.out.println("FAILED"); } 
        
        System.out.println("================================\nTesting removePerson method:\n================================\n");
        try {
            iNumTests++;
            
            Person person = new Person("john","02/03/1999","infected",'S');
            list.insertLast(person);
            
            Iterator iterate = list.iterator();
            i = 1;
            while(iterate.hasNext())
            {
            	System.out.println(i+": "+iterate.next());
            	i++;
            }
            
            list.deletePerson(person);
            iterate = list.iterator();
            i = 1;
            System.out.println("-------------------------------------");
            System.out.println("After removePerson");
            System.out.println("-------------------------------------");
            while(iterate.hasNext())
            {
            	System.out.println(i+": "+iterate.next());
            	i++;
            }
            
            iNumPassed++;
            System.out.println("\n=======\npassed\n=======");
        }catch(Exception e) { System.out.println("FAILED"); } 
        
        System.out.println("================================\nTesting removeEdge method:\n================================\n");
        try {
            iNumTests++;
            Person person1 = new Person("john1","02/03/1999","infected",'S');
            Person person2 = new Person("johnFirst","02/03/1999","infected",'S');
            DSAGraphEdge edge = new DSAGraphEdge(person1,person2,person1.getGroup1());
            DSALinkedList list2 = new DSALinkedList();
            list2.insertLast(edge);
            
            Iterator iterate = list2.iterator();
            i = 1;
            while(iterate.hasNext())
            {
            	System.out.println(i+": "+iterate.next());
            	i++;
            }
            
            list2.deleteEdge(edge);
            iterate = list2.iterator();
            i = 1;
            System.out.println("-------------------------------------");
            System.out.println("After removePerson");
            System.out.println("-------------------------------------");
            while(iterate.hasNext())
            {
            	System.out.println(i+": "+iterate.next());
            	i++;
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
