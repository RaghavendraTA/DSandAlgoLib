import java.util.*;

class MinStack {

    LinkedList<Integer> stk, mstk;

    public MinStack() {
        stk = new LinkedList<>();
        mstk = new LinkedList<>();
    }
    
    public void push(int val) {
        stk.push(val);
        if (mstk.isEmpty() || val <= mstk.peek()) {
            mstk.push(val);
        }
    }
    
    public void pop() {
        if (stk.peek().equals(mstk.peek()))
            mstk.pop();
        stk.pop();
    }
    
    public int top() {
        return stk.peek();
    }
    
    public int getMin() {
        return mstk.peek();
    }
}