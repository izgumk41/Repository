import java.util.*;
import java.io.*;

/*Previously submitted for practical, has since been modified to
 * include deletePerson,deleteEdge methods.*/
 
public class DSALinkedList implements Serializable
{
    
    //class fields:
    private DSAListNode head;
    private DSAListNode tail;
    
	/**************************************************
	 *Default Constructor:
	*IMPORT: none
	*EXPORT: address of new DSALinkedList object
	**************************************************/
    public DSALinkedList()
    {
        head = null;
        tail = null;
    }

    
    /*************************************************
 *  SUBMODULE: insertFirst
 *  IMPORT: none
 *  EXPORT: none
 *  *************************************************/
    public void insertFirst(Object newValue)
    {
        DSAListNode newNd = new DSAListNode(newValue); 
        if (isEmpty()) 
        {
            head = newNd;
            tail = newNd;
        }
        else
        {
            newNd.setNext(head); 
            head.setPrev(newNd);
            head = newNd;
        }
    }
    
    /*************************************************
 *  SUBMODULE: insertLast
 *  IMPORT: none
 *  EXPORT: none
 *  *************************************************/
    public void insertLast(Object newValue)
    {
        DSAListNode newNd;
        newNd = new DSAListNode(newValue);
        if (isEmpty()) 
        {
            head = newNd;
            tail = newNd;
        }
        else 
        {
            newNd.setPrev(tail); 
            tail.setNext(newNd); 
            tail = newNd; 
        }
    }
    
    /*************************************************
 *  SUBMODULE: isEmpty
 *  IMPORT: none
 *  EXPORT: none
 *  *************************************************/
    public boolean isEmpty()
    {
        boolean empty = false;
        if(head == null)
        {
        	empty = true;
        }
        return empty;
    }
    
    /*************************************************
     *  SUBMODULE: peekFirst
     *  IMPORT: none
     *  EXPORT: none
     *  *************************************************/
        public Object peekFirst()
        {
            Object nodeValue;
            if (isEmpty()) 
            {
                throw new IllegalArgumentException("empty");
            }
            else 
            {
                nodeValue = head.getValue();
            }
            return nodeValue;
        }

        /*************************************************
     *  SUBMODULE: peekLast
     *  IMPORT: none
     *  EXPORT: none
     *  *************************************************/
        public Object peekLast()
        {
            Object nodeValue;
            if (isEmpty()) 
            { 
                throw new IllegalArgumentException(" empty");
            }
            else 
            {
                nodeValue = tail.getValue();
            }
            return nodeValue;
        }

    /*************************************************
 *  SUBMODULE: removeFirst
 *  IMPORT: none
 *  EXPORT: none
 *  *************************************************/
    public Object removeFirst()
    {
        Object nodeValue;
        if (isEmpty()) 
        {
            throw new IllegalArgumentException("empty");
        }
        else if (head.getNext() == null)
        {
            nodeValue = head.getValue();
            head = null; 
            tail = null;
        }
        else 
        {
            nodeValue = head.getValue();
            head = head.getNext(); 
            head.setPrev(null); 
        }
        return nodeValue;
    }

    /*************************************************
 *  SUBMODULE: removeLast
 *  IMPORT: none
 *  EXPORT: none
 *  *************************************************/
    public Object removeLast()
    {

        Object nodeValue;
        if (isEmpty()) 
        {
            throw new IllegalArgumentException("empty");
        }
        else if (head.getNext() == null)
        {
            nodeValue = head.getValue();
            head = null;
            tail = null;
        }
        else 
        {
            nodeValue = tail.getValue();
            tail = tail.getPrev(); 
            tail.setNext(null); 
        }
        return nodeValue;
    }

    /*************************************************
 *  SUBMODULE: deletePerson
 *  IMPORT: none
 *  EXPORT: none
 *  PURPOSE: to delete person object from LinkedList
 *  *************************************************/
	public void deletePerson(Person person)
	{
		
		if(head != null)
		{
			//variables
			DSAListNode curNode = head;
			DSAListNode tempNode = null;
			Person tempPerson = null;
		
			do
			{
				//get person object from first node
				tempPerson = (Person) curNode.getValue();
				//if node is equal to head
				if(person.equals(tempPerson) && curNode.equals(head))
				{
					//call removefirst
					removeFirst();
					//set curNode as null to end loop
					curNode = null;
				}
				//if curNode is equal to tail
				else if(person.equals(tempPerson) && curNode.equals(tail))
				{
					//call removeLast
					removeLast();
					//set curNode to null to end loop
					curNode = null;
				}
				//if somewhere in between
				else if(tempPerson.equals(person))
				{
					//check for null
					if(curNode.getPrev() != null)
					{
						//get next node and set to tempNode
						tempNode = curNode.getNext();
						//set prev node next node to tempNode
						curNode.getPrev().setNext(tempNode);		
					}
					//check for null
					if(curNode.getNext() != null)
					{
						//get prev node and set to tempNode
						tempNode = curNode.getPrev();
						//set next node prev node to tempNode
						curNode.getNext().setPrev(tempNode);
					}
					
					//set curNode to null to end loop
					curNode = null;
				}
				//if node did not match
				else
				{
					//initialize curNode equal to next node
					curNode = curNode.getNext();
				}

			}while(curNode != null);
		}
	}
	
    /*************************************************
 *  SUBMODULE: deleteEdge
 *  IMPORT: none
 *  EXPORT: none
 *  PURPOSE: to delete DSAGraphEdge object from linked
 *  list
 *  *************************************************/
	public void deleteEdge(DSAGraphEdge edge)
	{
		if(head != null)
		{
			//variable declarations
			DSAListNode curNode = head;
			DSAListNode tempNode = null;
			DSAGraphEdge tempEdge = null;
			
			do
			{
				//get value inside curNode
				tempEdge = (DSAGraphEdge) curNode.getValue();
				//if curNode equals head
				if(tempEdge.equals(edge) && curNode.equals(head))
				{
					//call removeFirst
					removeFirst();
					//set curNode to null to end loop
					curNode = null;
				}
				//if curNode equals tail
				else if(tempEdge.equals(edge) && curNode.equals(tail))
				{
					//call removeLast
					removeLast();
					//set curNode to null to end loop
					curNode = null;
				}
				//if somewhere in between
				else if(tempEdge.equals(edge))
				{

					//check if prev not null
					if(curNode.getPrev() != null)
					{
						//initialize tempNode as next node of curNode
						tempNode = curNode.getNext();
						//set prev node next to tempNodde
						curNode.getPrev().setNext(tempNode);		
					}
					//check if next is not null
					if(curNode.getNext() != null)
					{
						//initalize tempNoe as prev node of curNode
						tempNode = curNode.getPrev();
						//set curNodes next nodes prev to tempNode :(
						curNode.getNext().setPrev(tempNode);
					}
					
					//set curNode to null to end loop
					curNode = null;
				}
				//if no matches
				else
				{
					//set curNode to next node
					curNode = curNode.getNext();
				}
				
			}while(curNode != null);
		}
	} 
    
    public Iterator iterator()
    {
        return new DSALinkedListIterator(this);
    }

    private class DSALinkedListIterator implements Iterator
    {
        private DSAListNode iterNext;
        
        public DSALinkedListIterator(DSALinkedList list)
        {
            iterNext = list.head;
        }

        public boolean hasNext()
        {
            return (iterNext != null);
        }


        public Object next()
        {
            Object value;
            if (iterNext == null)
            {
                value = null;
            }
            else
            {
                value = iterNext.getValue();
                iterNext = iterNext.getNext();
            }
            return value;
        }
        
        public void remove()
        {
            throw new UnsupportedOperationException("not supported.");
        }
    }


}
