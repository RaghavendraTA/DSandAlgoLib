package interfaces.list;

public interface LLNode<T> {
    T getValue();
    void setValue(T value);
    LLNode<T> getNext();
    void setNext(LLNode<T> next);
    LLNode<T> getPrev();
    void setPrev(LLNode<T> prev);
    boolean hasNext();
    boolean hasPrev();
}
