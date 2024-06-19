package stack2;
/**
 * Brandon Logan
 * CSC 1351-02
 * Spring 2023
 * Lab 10
 */
public class Stack2<E> implements Stack2Interface<E> 
{
    private Node top;  // a pointer to the top node in the stack.
    private Node bottom;  // a pointer to the bottom node in the stack.
    private int length;  // the number of nodes in the stack.

    
    private class Node
    {
       public E data;  // the data value stored in the node.
       public Node next;  // a pointer to the next node that was added to the
                          // stack.
       public Node prev;  // a pointer to the previous node that was added to
                          // the stack.
    }

    
    /**
     * Creates an empty stack
     */
    public Stack2()
    {
        top = null;
        bottom = null;
        length = 0;
    }
    
    
    public int size()
    {
        return length;
    }
    

    /**
     * Determines whether the stack is empty.
     * @return true if the stack is empty; otherwise, false
     */
    public boolean isEmpty()
    {
        return length == 0;
    }
    
    
    /**
     * Adds a node at the top of the stack.
     * @param data the data value to be added.
     */
    public void push(E data)
    {
        Node newNode = new Node();
        newNode.data = data;
        newNode.next = null;
        if(length == 0){
            newNode.prev = null;
            top = newNode;
            bottom = newNode;
        }
        else{
            newNode.prev = top;
            top.next = newNode;
            top = newNode;
        }
        length++;
    }
    
    
    /**
     * @return the data value in the top node of a non-empty stack.
     * @throws Exception when the stack is empty.
     */
    public E top() throws Exception
    {
        if(length == 0){
            throw new Exception("Non-empty stack expected");
        }
        return top.data;
    }

    
    /**
     * Removes the top node of a non-empty stack.
     * @return the data value in the top node that was removed.
     * @throws Exception when the stack is empty.
     */
    public E pop() throws Exception
    {
        if(length == 0){
            throw new Exception("Non-empty stack expected");
        } 
        E data = top.data;
        if(length == 1){
            top = null;
            bottom = null;
        }
        else{
            top = top.prev;
            top.next = null;
        }
        length--;
        return data;
    }

    
    /**
     * Moves the top node of the stack to its bottom or does nothing
     * if the stack contains less than two elements.
     */
    public void moveTopNodeToBottom()
    {
        if(length >= 2){
            Node node = top;
            top = top.prev;
            top.next = null;
            node.prev = null;
            node.next = bottom;
            bottom.prev = node;
            bottom = node;
        }
    }

    
    /**
     * Moves the bottom node of the stack to its top or does nothing
     * if the stack contains less than two elements.
     */
    public void moveBottomNodeToTop()
    {
        if(length >= 2){
            Node node = bottom;
            bottom = bottom.next;
            bottom.prev = null;
            node.next = null;
            node.prev = top;
            top.next = node;
            top = node;
        }
    }

    
    /**
     * Returns a string [en-1, ..., e2, e1, e0] representing this stack, 
     * where e0 is the data item in the top node and en-1 is the data item
     * in the bottom node. It returns [] if this stack is empty.     
     */	 
    public String toString()
    {
        String string = "]";
        if(length > 0)
        {
            Node node = top;
            string = node.data + string;
            while(node.prev != null)
            {
                node = node.prev;
                string = node.data + ", " + string;
            }
        }
        string = "[" + string;
        return string;
    }    
}

