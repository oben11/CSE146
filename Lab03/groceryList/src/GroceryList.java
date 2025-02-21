// Oliver Benjamin
// CSE 146
// Lab03

public class GroceryList {
    // internal basic linked list class implementation.
    private class ListNode {
        GroceryItem data;
        ListNode link;

        public ListNode() {
            this.data = null;
            this.link = null;
        }

        public ListNode(GroceryItem data, ListNode link) {
            this.data = data;
            this.link = link;
        }
    }

    private ListNode head;
    private ListNode current;
    private ListNode previous;
    // default init for a linked list
    public GroceryList() {
        head = new ListNode();
        current = head;
        previous = head;
    }

    // functions to manipulate and read list
    public void gotoNext() {
        if (current != null && current.link != null) {
            previous = current;
            current = current.link;
        }
    }

    public GroceryItem getCurrent() {
        if (current != null) {
            return current.data;
        }
        return null;
    }

    public void setCurrent(GroceryItem item) {
        if (current != null && item != null) {
            current.data = item;
        }
    }

    public void addItem(GroceryItem item) {
        if (item == null) return;
        ListNode newNode = new ListNode(item, null);
        if (head.data == null) {
            head = newNode;
            current = head;
            previous = head;
        } else {
            ListNode temp = head;
            while (temp.link != null) {
                temp = temp.link;
            }
            temp.link = newNode;
        }
    }

    public void addItemAfterCurrent(GroceryItem item) {
        if (current == null || item == null) return;
        ListNode newNode = new ListNode(item, current.link);
        current.link = newNode;
    }

    public void removeCurrent() {
        if (current == null || head == null) return;
        if (current == head) {
            head = head.link;
            current = head;
        } else {
            previous.link = current.link;
            current = current.link;
        }
    }

    public void showList() {
        ListNode temp = head;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.link;
        }
    }

    public boolean contains(GroceryItem item) {
        ListNode temp = head;
        while (temp != null) {
            // had issues comparing objects so I switched it to compare the strings of the objects.
            if (temp.data != null && temp.data.toString().equals(item.toString())) {
                return true;
            }
            temp = temp.link;
        }
        return false;
    }
    // total cost function requested
    public double totalCost() {
        double total = 0.0;
        ListNode temp = head;
        while (temp != null) {
            if (temp.data != null) {
                total += temp.data.getValue();
            }
            temp = temp.link;
        }
        return total;
    }
}
