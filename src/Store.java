import java.util.ArrayList;

public class Store {
    int capacity;
    ArrayList<Product> laptops;

    public Store(int capacity) {
        this.capacity = capacity;
        laptops = new ArrayList<Product>();
    }
}
