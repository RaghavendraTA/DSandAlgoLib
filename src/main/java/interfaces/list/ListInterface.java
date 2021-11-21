package interfaces.list;

public interface ListInterface<T> {

    T getValue();

    LLNode<T> getNode();

    void insertAtEnd(T value);

    void insertAllAtEnd(T...value);

    void insertAtFront(T value);

    void insertAllAtFront(T...value);

    T popAtEnd();

    T popAtFront();

    boolean isEmpty();

    default String toString(Iterable<T> iterable) {
        StringBuilder temp = new StringBuilder();
        temp.append("[");
        iterable.forEach(node -> temp.append(node).append(", "));
        if(temp.length() > 1) {
            temp.delete(temp.length() - 2, temp.length());
        }
        temp.append(']');
        return temp.toString();
    }
}
