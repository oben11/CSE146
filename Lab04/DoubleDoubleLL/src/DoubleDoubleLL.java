// Oliver Benjamin
// CSE146
// Lab04

public class DoubleDoubleLL<Double> {
    private class ListNode
    {
        double data;
        ListNode link;

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

    public DoubleDoubleLL()
    {
        head = current = previous = null;
        this.size = 0;
    }

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

    public void print()
    {
        ListNode temp = head;
        while(temp != null)
        {
            System.out.println(temp.data);
            temp = temp.link;
        }
    }

    public void addAfterCurrent(double aData)
    {
        if(current == null)
            return;
        ListNode newNode = new ListNode(aData, current.link);
        current.link = newNode;
        this.size++;
    }

    public double getCurrent()
    {

        return current.data;
    }

    public void setCurrent(double aData)
    {
        if(current == null)
            return;
        current.data = aData;
    }

    public void gotoNext()
    {
        if(current == null)
            return;
        previous = current;
        current = current.link;
    }

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

    public boolean hasMore()
    {
        return current != null;
    }

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

    public int getSize()
    {
        return this.size;
    }

    public double getAt(int index)
    {
        ListNode temp = head;
        for(int i = 0; i < index; i++)
            temp = temp.link;
        return temp.data;
    }

    public void setAt(int index, double aData)
    {
        if(index < 0 || index >= size)
            return;
        ListNode temp = head;
        for(int i = 0; i < index; i++)
            temp = temp.link;
        temp.data = aData;
    }

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

