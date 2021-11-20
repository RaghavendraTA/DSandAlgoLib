package interfaces.list;

public interface LLNode<T> {

    void setValue(T value);

    void setNext(LLNode<T> node);

    boolean hasNext();

    LLNode<T> getNext();

    T getValue();
}
