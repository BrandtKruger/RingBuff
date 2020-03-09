import com.sun.tools.hat.internal.model.JavaObjectArray;
import org.omg.CORBA.OBJ_ADAPTER;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;

public class CircularArrayList<E> extends AbstractList<E> {

    private static final int DEFAULT_INITIAL_CAPASITY = 10;
    private int size;
    private Object[] elements;
    private int head;

    public CircularArrayList(){
        this(DEFAULT_INITIAL_CAPASITY);
    }

    public CircularArrayList(int initialCapacity){
        elements = new Object[initialCapacity];
        size = 0;
    }

    public CircularArrayList(Collection<? extends E> c){
        elements = c.toArray();
        size = c.size();
    }

    public int size(){
        return size;
    }

    public E get(int index){
        checkIndexRange(index);
        int realIndex = calculateRealIndex(index);
        return (E) elements[index];
    }

    private int calculateRealIndex(int index) {
        return ( head + index) % elements.length;
    }

    private void checkIndexRange(int index) {
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
    }

    public E set(int index, Object element){
        checkIndexRange(index);
        E old = (E) elements[index];
        elements[index] = element;
        return old;
    }

    public void add(int index, Object element){

        if(index < 0 | index > size)
            throw new IndexOutOfBoundsException();

        ensureCapacity();

        int realIndex = calculateRealIndex(index);
        if (index != size){
            if (index == 0){
                moveHead(-1);
                realIndex = calculateRealIndex(index);
            } else if (realIndex > head && head != 0){
                System.arraycopy(elements, head, element, head - 1, index);
                realIndex = calculateRealIndex(index);
            } else {
                System.arraycopy(element, realIndex, elements, realIndex + 1, size - index);
            }
        }

        elements[realIndex] = element;
        size++;
        modCount++;
    }

    private void moveHead(int steps) {
        head += steps;
        head %= elements.length;

        if (head < 0)
            head += elements.length;
    }

    private void ensureCapacity() {
        if (size == elements.length){
            Object[] newElemenets = new Object[(elements.length + 1) * 3/2];
            System.arraycopy(elements, head, newElemenets, 0, elements.length - head);
            System.arraycopy(elements, 0, newElemenets, elements.length - head, head);
            elements = newElemenets;
            head = 0;
        }
    }

    public E remove(int index){
        checkIndexRange(index);
        int realIndex = calculateRealIndex(index);
        E old = (E) elements[index];

        if (index == size - 1){
            elements[realIndex] = null;
        } else if (index == 0){
            elements[realIndex] = null;
            moveHead(+1);
        } else if (realIndex > head){
            System.arraycopy(elements, head, elements, head + 1, index);
            elements[head] = null;
            moveHead(+1);
        } else {
            System.arraycopy(elements, realIndex + 1, elements, realIndex, size - index);
            elements[calculateRealIndex(size - 1)] = null;
        }

        elements[size - 1] = null;
        size--;
        return old;
    }


}
