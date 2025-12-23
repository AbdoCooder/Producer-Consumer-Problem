public class Product {
    int id;

    public Product(int data) {
        this.id = data;
    }

    @Override
    public String toString() {
        return "" + id;
    }
}
