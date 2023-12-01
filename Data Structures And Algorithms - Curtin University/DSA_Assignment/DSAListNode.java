import java.io.*;

/*Previously submitted for practical*/
public class DSAListNode implements Serializable
{
    private Object value;
    private DSAListNode next;
    private DSAListNode prev;
    

    public DSAListNode(Object inValue)
    {
        value = inValue;
        next = null;
        prev = null;
    }

    //GETTERS
    public Object getValue()
    {
        return value;
    }


    public DSAListNode getNext()
    {
        return next;
    }


    public DSAListNode getPrev()
    {
        return prev;
    }

    //SETTERS
    public void setValue(Object inValue)
    {
        value = inValue;
    }


    public void setNext(DSAListNode inNext)
    {
        next = inNext;
    }
    

    public void setPrev(DSAListNode inPrev)
    {
        prev = inPrev;
    }
}
	
