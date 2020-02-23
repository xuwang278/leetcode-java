// Solution 1: one queue
class MyStack {

    private Queue<Integer> q;
   
    /** Initialize your data structure here. */
    public MyStack() {
        q = new LinkedList<>();
    }
    
    // T: O(n)
    public void push(int x) {
        q.offer(x); 
        for (int i = 1; i < q.size(); i++) // rotate the queue
            q.add(q.poll());
    }
    
    // T: O(1)
    public int pop() {
        return q.poll();
    }
    
    // T: O(1)
    public int top() {
        return q.peek();
    }
    
    // T: O(1)
    public boolean empty() {
        return q.isEmpty();
    }

}

// Solution 2: list
// not use std q opts
class MyStack {
    private List<Integer> list;
    
    /** Initialize your data structure here. */
    public MyStack() {
        list = new LinkedList<>();
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        list.add(x);
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        int tail = list.get(list.size() - 1);
        list.remove(list.size() - 1);
        return tail;
    }
    
    /** Get the top element. */
    public int top() {
        return list.get(list.size() - 1);
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return list.isEmpty();
    }
}