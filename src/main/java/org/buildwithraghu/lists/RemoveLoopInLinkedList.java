package linkedlist.linkedlists;

import arrays.cache.Node;

public class RemoveLoopInLinkedList {

    public void removeLoop(Node head)
    {
        Node low = head, high = head;

        while(low != null & high != null && high.next != null){
            low = low.next;
            high = high.next.next;
            if(low == high){
                break;
            }
        }

        if(low == head){
            while(high.next != low) high = high.next;
            high.next = null;
        }
        else if(low == high){
            low = head;
            while(low.next != high.next){
                low = low.next;
                high = high.next;
            }
            high.next = null;
        }
    }
}
