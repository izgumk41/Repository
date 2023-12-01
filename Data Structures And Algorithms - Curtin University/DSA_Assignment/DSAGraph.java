import java.io.Serializable;

/*************************************************
 * AUTHOR: Izan Muhammad
 * PURPOSE: a modified DSAGraph to simulate a population and connections.
*************************************************/
/*was previously submitted for practical, has since been modified to suit program*/
public class DSAGraph implements Serializable 
{
	//class fields:
	private DSALinkedList graph;
	private DSALinkedList edges;
	
	/**************************************************
	 *Default Constructor:
	*IMPORT: none
	*EXPORT: address of new DSAGraph object
	**************************************************/
	public DSAGraph()
	{
		graph = new DSALinkedList();
		edges = new DSALinkedList();
	}
	
	//GETTERS
    /************************************************
 *  SUBMODULE: getGraph
 *  IMPORT: none
 *  EXPORT:graph
 ***************************************************/	
	public DSALinkedList getGraph()
	{
		return graph;
	}
	
    /************************************************
 *  SUBMODULE: getEdges
 *  IMPORT: none
 *  EXPORT:graph
 ***************************************************/	
	public DSALinkedList getEdges()
	{
		return edges;
	}
	
	
	//SETTERS
    /************************************************
 *  SUBMODULE: setGraph
 *  IMPORT: inGraph
 *  EXPORT:none
 ***************************************************/
	public void setGraph(DSALinkedList inGraph)
	{
		graph = inGraph;
	}
	
    /************************************************
 *  SUBMODULE: setEdges
 *  IMPORT: inGraph
 *  EXPORT:none
 ***************************************************/
	public void setEdges(DSALinkedList inEdges)
	{
		edges = inEdges;
	}
	
    /*************************************************
 *  SUBMODULE: graphIterator
 *  IMPORT: none
 *  EXPORT: graph.iterator
 *  PURPOSE: to return iterator of LinkedList graph.
 *  *************************************************/
	public Iterator graphIterator()
	{
		return graph.iterator();
	}
	
    /*************************************************
 *  SUBMODULE: addVertex
 *  IMPORT: person
 *  EXPORT: none
 *  PURPOSE: to add person object into LinkedList
 *  *************************************************/
	public void addVertex(Person person)
	{
		//if person has already been added to LinkedList.
		if(hasVertex(person))
		{		
			throw new IllegalArgumentException("person already exists.");	
		}
		
		graph.insertLast(person);

	}
	
    /*************************************************
 *  SUBMODULE: addEdge
 *  IMPORT: none
 *  EXPORT: none
 *  PURPOSE: to add each person into the others person adjacency list.
 *  *************************************************/
	void addEdge(Person person1, Person person2, char inGroup)
	{
		//call checkGraph to check if graph is empty.
		checkGraph();
		
		//if person 1 or 2 don't exist.
		if (!hasVertex(person1) || !hasVertex(person2))
		{
			throw new IllegalArgumentException("person does not exist.");
		}
		//call addConnection inside each person.
		person1.addConnection(person2);
		person2.addConnection(person1);
		
		//create edge object and insert to edge linked list
		DSAGraphEdge edge = new DSAGraphEdge(person1,person2,inGroup);
		edges.insertLast(edge);
			
	}
	
    /*************************************************
 *  SUBMODULE: getEdge
 *  IMPORT: none
 *  EXPORT: none
 *  PURPOSE: to get DSAGraphEdge object from edge
 *  linked list which is the edge between person object
 *  1 and 2.
 *  *************************************************/
	public DSAGraphEdge getEdge(Person person1, Person person2)
	{
		//initialize iterator of edge linked list
		Iterator iterate = edges.iterator();
		boolean stop = false;
		DSAGraphEdge edge = null;
		while(iterate.hasNext() && !stop)
		{
			edge = (DSAGraphEdge) iterate.next();
			//since edges are undirected, 2 possibilites need to be checked.
			if(edge.getFrom().equals(person1) && edge.getTo().equals(person2))
			{
				stop = true;
			}
			else if(edge.getFrom().equals(person2) && edge.getTo().equals(person1))
			{
				stop = true;
			}
		}
		
		if(edge == null)
		{
			throw new IllegalArgumentException("could not find edge.");
		}
		
		return edge;
	}
    /*************************************************
 *  SUBMODULE: hasVertex
 *  IMPORT: person
 *  EXPORT: none
 *  PURPOSE: to check if person object already exist in LinkedList.
 *  *************************************************/
	public boolean hasVertex (Person person)
	{
		
		//variable declarations.
		boolean check = false;
		Person node = null;
		
		//declaring a iterator.
		Iterator iterate;
		iterate = graph.iterator();
		//while iterator has a vertex.
		while((iterate.hasNext()))
		{
			//initialize vertex.
			node = (Person)iterate.next();
			//checks if vertex matches label.
			if(node.equals(person))
			{
				check = true;
			}	
		}
	//returns boolean
	return check;		
	}
	
    /*************************************************
 *  SUBMODULE: getVertex
 *  IMPORT: name
 *  EXPORT: person
 *  PURPOSE: to find person by comparing name with persons name.
 *  *************************************************/
	
	public Person getVertex (String name)
	{
		//call checkGraph to check if graph is empty.
		checkGraph();
		
		//variable declarations.
		Person person = null;
		boolean stop = false;
		//initialize an iterator.
		Iterator iterate;
		iterate = graph.iterator();
		//while iterate has a next vertex.
		while(iterate.hasNext() && !stop)
		{
			//initialize the vertex.
			person = (Person) iterate.next();
			//if label matches a vertex label.
			if(name.equals(person.getName()))
			{
				stop = true;
			}
		}

		if(person == null)
		{
			throw new IllegalArgumentException("could not find person");
		}
		
		return person;
	}


    /*************************************************
 *  SUBMODULE: isAdjacent
 *  IMPORT: none
 *  EXPORT: check
 *  PURPOSE: to check if person 1 and 2 are connected.
 *  *************************************************/	
	/*public boolean isAdjancent (Person person1,Person person2)
	{
		//call checkGraph to check if graph is empty.
		checkGraph();
		
		//if person 1 or 2 don't exist.
		if (!hasVertex(person1) || !hasVertex(person2))
		{
			throw new IllegalArgumentException("person does not exist.");
		}
		
		//variable declarations.
		boolean check =  false;
		boolean stop = false;
		Person node = null;
		DSALinkedList vertices = null;
		
		//initialize the first vertex.
		//get adjacency list of node.
		vertices = person1.getConnections();
		//initialize iterator.
		Iterator iterate;
		iterate = vertices.iterator();
		//while there is a vertex in node's adjacency list.
		while(iterate.hasNext() && !stop)
		{
			//initialize vertex.
			node = (Person) iterate.next();
			//if label 2 exists in label 1 adjacency list..
			if(node.equals(person2))
			{
				//mark check as true.
				check = true;
				stop = true;
			}
		}		
					
		return check;
	}*/

    /*************************************************
 *  SUBMODULE: displayAsList
 *  IMPORT: none
 *  EXPORT: none
 *  PURPOSE: to display connections between person objects.
 *  *************************************************/
	/*public void displayAsList()
	{
		if(!graph.isEmpty())
		{
			Iterator iterate, iterate2;
			iterate = graph.iterator();
			Person person1 = null;
			Person person2 = null;
			DSALinkedList vertices = null;
			System.out.println("Adjacency List:\n---------------");
			
			while(iterate.hasNext())
			{
				person1 = (Person) iterate.next();
				System.out.print(person1.getName()+": ");
			    vertices = person1.getConnections();
				iterate2 =  vertices.iterator();
				if(!iterate2.hasNext())
				{
					System.out.println();
				}
				while(iterate2.hasNext())
				{
					person2 = (Person) iterate2.next();
					if(!iterate2.hasNext())
					{
						System.out.println(person2.getName());
					}
					else
					{
						System.out.print(person2.getName()+", ");
					}
				}
				
				
			}
		}
	}*/
	
    /*************************************************
 *  SUBMODULE: deleteVertex
 *  IMPORT: person
 *  EXPORT: none
 *  PURPOSE: to delete a person object from LinkedList
 *  *************************************************/
	public void deleteVertex(Person person)
	{
		if(hasVertex(person))
		{
			//call deleteConnections method
			deleteConnections(person);
			//call deletePerson method inside linked list.
			graph.deletePerson(person);
		}
		else
		{
			throw new IllegalArgumentException("person does not exist.");
		}
		
	}
	
    /*************************************************
 *  SUBMODULE: deleteConnections
 *  IMPORT: person
 *  EXPORT: none
 *  PURPOSE: to delete all connections in person adjacency list.
 *  *************************************************/
	public void deleteConnections(Person person)
	{
		Iterator iterate = person.getConnections().iterator();
		Person tempPerson = null;
		
		while(iterate.hasNext())
		{
			
			tempPerson = (Person) iterate.next();
			tempPerson.deleteConnectionFromList(person);
			
		}
		
	}

    /*************************************************
 *  SUBMODULE: check
 *  IMPORT: none
 *  EXPORT: none
 *  PURPOSE: to throw an exception if a graph is empty.
 *  *************************************************/
	public void checkGraph()
	{
		//if graph is empty exception will be thrown.
		if(graph.isEmpty())
		{
			throw new IllegalArgumentException("graph is empty.");
		}
		
	}

    /*************************************************
 *  SUBMODULE: deleteEdge
 *  IMPORT: person1, person2
 *  EXPORT: none
 *  PURPOSE: to delete DSAGraphEdge object which has
 *  object person1 and person2.
 *  *************************************************/
	public void deleteEdge(Person person1, Person person2)
	{
		//initialize iterator from edges linked list iterator
		Iterator iterate = edges.iterator();
		while(iterate.hasNext())
		{
			DSAGraphEdge edge = (DSAGraphEdge) iterate.next();
			//check if person1 and person2 objects match from and to
			//there can be 2 possibilities since only one edge is added between them.
			if(edge.getFrom().equals(person1) && edge.getTo().equals(person2))
			{
				edges.deleteEdge(edge);
			}
			else if(edge.getFrom().equals(person2) && edge.getTo().equals(person1))
			{
				edges.deleteEdge(edge);
			}
		}
		
	}

    /*************************************************
 *  SUBMODULE: hasEdge
 *  IMPORT: person1, person2
 *  EXPORT: boolean
 *  PURPOSE: to return a boolean to showcase if
 *  edge exists.
 *  *************************************************/
	public boolean hasEdge(Person person1, Person person2)
	{
		Iterator iterate = edges.iterator();
		boolean check = false;
		while(iterate.hasNext())
		{
			
			DSAGraphEdge edge = (DSAGraphEdge) iterate.next();
			//check if person1 and person2 objects match from and to
			//there can be 2 possibilities since only one edge is added between them.
			if(edge.getFrom().equals(person1) && edge.getTo().equals(person2))
			{
				check = true;
			}
			else if(edge.getFrom().equals(person2) && edge.getTo().equals(person1))
			{
				check = true;
			}
		}
		return check;
	}

    /*************************************************
 *  SUBMODULE: edgeCount
 *  IMPORT: none
 *  EXPORT: int
 *  PURPOSE: to return the number of edge objects
 *  within edges linked list
 *  *************************************************/
	public int edgeCount()
	{
		int count = 0;
		Iterator iterate = edges.iterator();
		while(iterate.hasNext())
		{
			iterate.next();
			count++;
		}
		return count;
	}
	
    /*************************************************
 *  SUBMODULE: deleteAllEdge
 *  IMPORT: person1
 *  EXPORT: none
 *  PURPOSE: to delete all edges of person object.
 *  *************************************************/
	public void deleteAllEdges(Person person)
	{
		//iterate to edges linkedlist
		Iterator iterate = edges.iterator();
		while(iterate.hasNext())
		{
			DSAGraphEdge edge = (DSAGraphEdge) iterate.next();
			//since edge is undirected person could be from or to,
			//so both possibilities need to be checked.
			if(edge.getFrom().equals(person))
			{
				edges.deleteEdge(edge);
			}
			else if(edge.getTo().equals(person))
			{
				edges.deleteEdge(edge);
			}
		}
	}
	
	
}

