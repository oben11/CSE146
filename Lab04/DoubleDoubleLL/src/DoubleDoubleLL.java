// Oliver Benjamin
// CSE146
// Lab04

public class DoubleDoubleLL<Double> {
    private class ListNode
    {
        double data;
        ListNode link;
        // node format
        public ListNode(double aData, ListNode aLink)
        {
            data = aData;
            link = aLink;
        }
    }
    private ListNode head;
    private ListNode current;
    private ListNode previous;
    private int size;
    // linked list init
    public DoubleDoubleLL()
    {
        head = current = previous = null;
        this.size = 0;
    }
    // add node to end
    public void add(double aData)
    {
        ListNode newNode = new ListNode(aData,null);
        if(head == null)
        {
            head = current = newNode;
            this.size = 1;
            return;
        }
        ListNode temp = head;
        while(temp.link != null)
            temp = temp.link;
        temp.link = newNode;
        this.size++;
    }
    // prints the list out
    public void print()
    {
        ListNode temp = head;
        while(temp != null)
        {
            System.out.println(temp.data);
            temp = temp.link;
        }
    }
    // adds a new node after current node
    public void addAfterCurrent(double aData)
    {
        if(current == null)
            return;
        ListNode newNode = new ListNode(aData, current.link);
        current.link = newNode;
        this.size++;
    }
    // get current
    public double getCurrent()
    {

        return current.data;
    }
    // set current
    public void setCurrent(double aData)
    {
        if(current == null)
            return;
        current.data = aData;
    }
    // goes to next index
    public void gotoNext()
    {
        if(current == null)
            return;
        previous = current;
        current = current.link;
    }
    // goes to previous index
    public void gotoPrev()
    {
    if (current == head || current == null)
        return;
    ListNode temp = head;
    previous = null;
    while (temp.link != current)
    {
        previous = temp;
        temp = temp.link;
    }
    current = previous;
    }
    // goes to end of the list
    public void gotoEnd()
    {
    if (head == null)
        return;
    previous = null;
    current = head;
    while (current.link != null)
    {
        previous = current;
        current = current.link;
    }
    }
    public void reset()
    {
        current = head;
        previous = null;
    }
    // is list at end.
    public boolean hasMore()
    {
        return current != null;
    }
    // Removes the current head
    public void removeCurrent()
    {
        if(current == head)
        {
            head = head.link;
            current = head;
        }
        else
        {
            previous.link = current.link;
            current = current.link;
        }
        if(this.size > 0)
            size--;
    }
    // gets current length of list
    public int getSize()
    {
        return this.size;
    }
    // gets data at list index
    public double getAt(int index)
    {
        ListNode temp = head;
        for(int i = 0; i < index; i++)
            temp = temp.link;
        return temp.data;
    }
    // sets index to aData
    public void setAt(int index, double aData)
    {
        if(index < 0 || index >= size)
            return;
        ListNode temp = head;
        for(int i = 0; i < index; i++)
            temp = temp.link;
        temp.data = aData;
    }
    // checks if aData is in list
    public boolean contains(double aData)
{
    ListNode temp = head;
    while (temp != null)
    {
        if (temp.data == aData )
            return true;
        temp = temp.link;
    }
    return false;
}
}

