/*Design of test harness is based on UnitTestLinkedList given on practical 4*/
public class UnitTestDSAGraph
{
	public static void main(String [] args)
	{
		/*declaring DSAGraph & Variables*/
		DSAGraph graph = null;
		int iNumPassed = 0;
        int iNumTests = 0;
        Person person1 = null;
        Person person2 = null;
        
		//Testing addVertex:
        System.out.println("================================\nTesting default constructor:\n================================\n");
        try {
            iNumTests++;
            
            graph = new DSAGraph();
            
            if(graph.equals(null))
            {
            	throw new IllegalArgumentException("");
            }
            
            iNumPassed++;
            System.out.println("\n=======\npassed\n=======");
        }catch(Exception e) { System.out.println("FAILED"); }  
          
            
		//Testing addVertex:
        System.out.println("================================\nTesting addVertex Method:\n================================\n");
        try {
            iNumTests++;
            
            person1 = new Person("john","02/02/1999","infected",'S');
            graph.addVertex(person1);
            System.out.println("added person1");
            person2 = new Person("john1","02/02/1999","infected",'S');
            graph.addVertex(person2);
            System.out.println("added person2");
            
            Iterator iterate = graph.graphIterator();
            while(iterate.hasNext())
            {
            	System.out.println(((Person)iterate.next()).toString());
            }
            
            iNumPassed++;
            System.out.println("\n=======\npassed\n=======");
        }catch(Exception e) { System.out.println("FAILED"); }  
          
             
        
        //Testing addEdge:
        System.out.println("================================\nTesting addEdge Method:\n================================\n");          
        try {
            iNumTests++;
           
            graph.addEdge(person1,person2,'S');
            
            Iterator iterate = graph.getEdges().iterator();
            
            while(iterate.hasNext())
            {
            	System.out.println(((DSAGraphEdge)iterate.next()).toString());
            }
            
            iNumPassed++;
            System.out.println("=======\npassed\n=======");
        }catch(Exception e) { System.out.println("FAILED"); }  
        
      //Testing edgeCount:
        System.out.println("================================\nTesting getEdgeCount Method:\n================================\n");          
        try {
            iNumTests++;
            System.out.println("Number of edges in graph: "+graph.edgeCount());
            if(graph.edgeCount() != 1)
            {
            	throw new IllegalArgumentException("");
            }
            iNumPassed++;
            System.out.println("=======\npassed\n=======");
        }catch(Exception e) { System.out.println("FAILED"); }  
        
        
        System.out.println("================================\nTesting getEdge Method:\n================================\n");          
        try {
            iNumTests++;
            
            DSAGraphEdge edge2 = graph.getEdge(person1, person2);
            System.out.println(edge2.toString());
            iNumPassed++;
            System.out.println("=======\npassed\n=======");
        }catch(Exception e) { System.out.println("FAILED"); }  
        
        System.out.println("================================\nTesting hasVertex Method:\n================================\n");          
        try {
            iNumTests++;
            
            
            if(graph.hasVertex(person1))
            {
            	System.out.println(person1.toString());
            }
            else
            {
            	throw new IllegalArgumentException("");
            }
            
            iNumPassed++;
            System.out.println("=======\npassed\n=======");
        }catch(Exception e) { System.out.println("FAILED"); }  
        
        System.out.println("================================\nTesting getVertex Method:\n================================\n");          
        try {
            iNumTests++;
            
            Person person3 = graph.getVertex(person1.getName());
            if(person3.getName().equals(person1.getName()))
            {
            	System.out.println(person3.toString());
            }
            else
            {
            	throw new IllegalArgumentException("");
            }
            
            iNumPassed++;
            System.out.println("=======\npassed\n=======");
        }catch(Exception e) { System.out.println("FAILED"); }  
            
        System.out.println("================================\nTesting deleteVertex Method:\n================================\n");          
        try {
            iNumTests++;
            
            graph.deleteVertex(person1);
            
            if(graph.hasVertex(person1))
            {
            	throw new IllegalArgumentException("");
            }
            
            iNumPassed++;
            System.out.println("=======\npassed\n=======");
        }catch(Exception e) { System.out.println("FAILED"); }  
        
        System.out.println("================================\nTesting deleteConnections Method:\n================================\n");          
        try {
            iNumTests++;
            
            graph.addVertex(person1);
            graph.addEdge(person1, person2, person1.getGroup1());
            graph.deleteConnections(person1);
            
			Iterator iterate = person1.getConnectionList();
            while(iterate.hasNext())
            {
            	Person tempPerson = (Person) iterate.next();
            	System.out.println(tempPerson.getName());
            }
            
           
            iNumPassed++;
            System.out.println("=======\npassed\n=======");
        }catch(Exception e) { System.out.println("FAILED"); } 
        
        System.out.println("================================\nTesting deleteConnections Method:\n================================\n");          
        try {
            iNumTests++;
            
            graph.addEdge(person1, person2, person1.getGroup1());
            graph.deleteEdge(person1,person2);
            
			Iterator iterate = graph.getEdges().iterator();
            while(iterate.hasNext())
            {
            	DSAGraphEdge edge = (DSAGraphEdge) iterate.next();
            	System.out.println(edge.toString());
            }
            
           
            iNumPassed++;
            System.out.println("=======\npassed\n=======");
        }catch(Exception e) { System.out.println("FAILED"); } 
        
        System.out.println("================================\nTesting hasEdge Method:\n================================\n");          
        try {
            iNumTests++;
            
            graph.addEdge(person1, person2, person1.getGroup1());
            
			if(graph.hasEdge(person1, person2))
			{			
				System.out.println(graph.getEdge(person1, person2).toString());
			}
			else
			{
				throw new IllegalArgumentException("");
			}
            
           
            iNumPassed++;
            System.out.println("=======\npassed\n=======");
        }catch(Exception e) { System.out.println("FAILED"); } 
        
        System.out.println("================================\nTesting deleteEdge Method:\n================================\n");          
        try {
            iNumTests++;
            
            graph.deleteEdge(person1, person2);
            
			if(graph.hasEdge(person1, person2))
			{	
				
				throw new IllegalArgumentException("");
			}
			
            
           
            iNumPassed++;
            System.out.println("=======\npassed\n=======");
        }catch(Exception e) { System.out.println("FAILED"); } 
        
        System.out.println("================================\nTesting deleteAllEdges Method:\n================================\n");          
     //   try {
            iNumTests++;
            
            Person tempPerson1 = new Person("John","02/04/2020","infected",'S');
            Person tempPerson2 = new Person("John1","02/04/2020","infected",'S');
            Person tempPerson3 = new Person("John2","02/04/2020","infected",'S');
            graph.addVertex(tempPerson3);
            graph.addVertex(tempPerson2);
            graph.addVertex(tempPerson1);
            graph.addEdge(tempPerson1, tempPerson2, tempPerson1.getGroup1());
            graph.addEdge(tempPerson1, tempPerson3, tempPerson1.getGroup1());
			
            graph.deleteAllEdges(tempPerson1);
            
            Iterator iterate = graph.getEdges().iterator();
            while(iterate.hasNext())
            {
            	System.out.println(((DSAGraphEdge)iterate.next()).toString());
            }
            
           
            iNumPassed++;
            System.out.println("=======\npassed\n=======");
  //      }catch(Exception e) { System.out.println("FAILED"); } 
      //---------------------------------------------------------------------------

        // PRINT TEST SUMMARY
        System.out.print("\nNumber PASSED: " + iNumPassed + "/" + iNumTests);
        System.out.print(" -> " + (int)(double)iNumPassed/iNumTests*100 + "%\n");
    }
//---------------------------------------------------------------------------
	
}
