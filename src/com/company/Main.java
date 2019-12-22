package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
    }
}

// Dequeue
public class NestedIterator implements Iterator<Integer> {
    Deque<NestedInteger> s;

    public NestedIterator(List<NestedInteger> nestedList) {
        s = new LinkedList<>(nestedList == null ? Arrays.asList() : nestedList);
    }

    @Override
    public Integer next() {
        return s.pollFirst().getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!s.isEmpty() && !s.peekFirst().isInteger()) {
            List<NestedInteger> list = s.pollFirst().getList();
            for (int i=list.size()-1; i>=0; i--) s.addFirst(list.get(i));
        }
        return !s.isEmpty();
    }
}

// Stack
public class NestedIterator implements Iterator<Integer> {
    NestedInteger nextInt;
    Stack<Iterator<NestedInteger>> stack;

    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new Stack<Iterator<NestedInteger>>();
        stack.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nextInt != null ? nextInt.getInteger() : null; //Just in case
    }

    @Override
    public boolean hasNext() {
        while (!stack.isEmpty()) {
            if (!stack.peek().hasNext()) stack.pop();
            else if ((nextInt = stack.peek().next()).isInteger()) return true;
            else stack.push(nextInt.getList().iterator());
        }
        return false;
    }
}

// Stack2
public class NestedIterator implements Iterator<Integer> {
    Stack<NestedInteger> stack = new Stack<>();
    public NestedIterator(List<NestedInteger> nestedList) {
        for(int i = nestedList.size() - 1; i >= 0; i--) {
            stack.push(nestedList.get(i));
        }
    }

    @Override
    public Integer next() {
        return stack.pop().getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!stack.isEmpty()) {
            NestedInteger curr = stack.peek();
            if(curr.isInteger()) {
                return true;
            }
            stack.pop();
            for(int i = curr.getList().size() - 1; i >= 0; i--) {
                stack.push(curr.getList().get(i));
            }
        }
        return false;
    }
}