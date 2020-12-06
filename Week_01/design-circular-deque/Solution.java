class Node{
    int value;
    Node pre;
    Node next;
    Node(int value){
        this.value = value;
        this.pre  = null;
        this.next = null;
    }
}

class MyCircularDeque {

    int count ;
    int cap;
    Node head, tail;

    /** Initialize your data structure here. Set the size of the deque to be k. */
    public MyCircularDeque(int k) {
        this.count = 0;
        this.cap = k;
    }

    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
        if(isFull()) return false;
        Node newN = new Node(value);
        if(isEmpty()){
            this.head = this.tail = newN;
        }else{
            this.head.pre = newN;
            newN.next = this.head;
            this.head = newN;
        }
        count++;
        return true;
    }

    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if(isFull()) return false;
        Node newN = new Node(value);
        if(isEmpty()){
            this.head = this.tail = newN;
        }else{
            this.tail.next = newN;
            newN.pre = tail;
            this.tail = newN;
        }
        count++;
        return true;
    }

    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if(isEmpty()) return false;
        if(count==1){
            this.tail = this.head = null;
        }else{
            Node next = this.head.next;
            this.head.next = null;
            this.head = next;
        }
        count--;
        return true;
    }

    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if(isEmpty()) return false;
        if(count==1){
            this.tail = this.head = null;
        }else{
            Node pre = this.tail.pre;
            this.tail = pre;
            this.tail.next = null;
        }
        count--;
        return true;
    }

    /** Get the front item from the deque. */
    public int getFront() {
        if(isEmpty()) return -1;
        return this.head.value;
    }

    /** Get the last item from the deque. */
    public int getRear() {
        if(isEmpty()) return -1;
        return this.tail.value;
    }

    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return this.count == 0;
    }

    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return this.count == this.cap;
    }
}

/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * MyCircularDeque obj = new MyCircularDeque(k);
 * boolean param_1 = obj.insertFront(value);
 * boolean param_2 = obj.insertLast(value);
 * boolean param_3 = obj.deleteFront();
 * boolean param_4 = obj.deleteLast();
 * int param_5 = obj.getFront();
 * int param_6 = obj.getRear();
 * boolean param_7 = obj.isEmpty();
 * boolean param_8 = obj.isFull();
 */