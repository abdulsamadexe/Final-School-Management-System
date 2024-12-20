import java.util.*;

public class Repository<T> {
    private List<T> items;

    public Repository() {
        items = new ArrayList<>();
    }

    public void add(T item) {
        items.add(item);
        System.out.println(item + " added to the repository.");
    }

    public void remove(T item) {
        if (items.remove(item)) {
            System.out.println(item + " removed from the repository.");
        } else {
            System.out.println(item + " not found in the repository.");
        }
    }

    public List<T> getAll() {
        return new ArrayList<>(items);
    }
}
