import java.util.ArrayList;
import java.util.List;

public class main {

    public static void main(String[] args) {

        ArrayList<Object> arrayList = new ArrayList<>();

        arrayList.add("One");
        arrayList.add(2);
        arrayList.add("Three");
        arrayList.add(4);
        arrayList.add("One");
        arrayList.add(5.0);
        arrayList.add(6.0);
        arrayList.add("Seven");
        arrayList.add("Eight");
        arrayList.add("Nine");
        arrayList.add(10);
        arrayList.add(11);
        arrayList.add(12);
        arrayList.add("Thirteen");

        CircularArrayList circularArrayList = new CircularArrayList();

        System.out.println(arrayList);

        List<E> copy = new ArrayList<>(arrayList);
        List<E> removed = new ArrayList<>();


        for (int i = 0; i < 3; i++){
            E removeFrontList = arrayList.remove(0);
            E removeEndList  = arrayList.remove(arrayList.size() - 1);
            E removeMiddleList = arrayList.remove((int)(arrayList.size() / 2));
            E removeMiddleList = arrayList.remove((int)(arrayList.size() / 2));
            E removeFrontCopy = copy.remove(0);
            E removeEndCopy = copy.remove(copy.size() - 1);
            E removedMiddleCopy = copy.remove((int)(copy.size() / 2));

            assertEquals(removeFrontList, removeFrontCopy);
            assertEquals(removeMiddleList, removeEndCopy);
            assertEquals(removeMiddleList, removedMiddleCopy);

        }

        System.out.println(arrayList);


    }

    private static void assertEquals(E removeFrontList, E removeFrontCopy) {
    }
}
