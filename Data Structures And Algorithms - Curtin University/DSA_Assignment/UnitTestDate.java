
/*Design of test harness is based on UnitTestLinkedList given on practical 4*/
public class UnitTestDate
{
	public static void main(String[] args)
	{
		Date date = null;
		Date date2 = null;
		int iNumPassed = 0;
        int iNumTests = 0;
        
        //testing initialization
        System.out.println("================================\nTesting default constructor:\n================================\n");
        try {
            iNumTests++;
            
            date = new Date();
            if(date.equals(null))
            {
            	throw new IllegalArgumentException ("");
            }
            System.out.println(date.toString());
            iNumPassed++;
            System.out.println("\n=======\npassed\n=======");
        }catch(Exception e) { System.out.println("FAILED"); }  
          
        System.out.println("================================\nTesting alternate constructor:\n================================\n");
        try {
            iNumTests++;
            
            date2 = new Date(5,8,1999);
            if(date2.equals(null))
            {
            	throw new IllegalArgumentException ("");
            }
            System.out.println(date2.toString());
            iNumPassed++;
            System.out.println("\n=======\npassed\n=======");
        }catch(Exception e) { System.out.println("FAILED"); }  
          
       
        System.out.println("================================\nTesting equals method:\n================================\n");
        try {
            iNumTests++;
            System.out.println(date.toString()+" = "+date2.toString());
            if(date.equals(date2))
            {
            	throw new IllegalArgumentException ("");
            }
            
            iNumPassed++;
            System.out.println("\n=======\npassed\n=======");
        }catch(Exception e) { System.out.println("FAILED"); }  
         
        System.out.println("================================\nTesting addDay method:\n================================\n");
        try {
            iNumTests++;
            int prevDay = date.getDays();
            System.out.println(date.toString());
            date.addDay();
            
            if( (prevDay+1) != date.getDays())
            {
            	throw new IllegalArgumentException ("");
            }
            System.out.println(date.toString());
            iNumPassed++;
            System.out.println("\n=======\npassed\n=======");
        }catch(Exception e) { System.out.println("FAILED"); }  
        
        System.out.println("================================\nTesting year difference method:\n================================\n");
        try {
            iNumTests++;
            Date tempDate1 = new Date (7,5,2020);
            System.out.println(tempDate1.toString());
            Date tempDate2 = new Date (1,3,2000);
            System.out.println(tempDate2.toString());
            int yearsdifference = tempDate2.getYearDifference(tempDate1);
            if(yearsdifference != 20)
            {
            	throw new IllegalArgumentException ("");
            }
            System.out.println("Year difference: "+yearsdifference);
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
