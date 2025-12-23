
public class Main {
  public static void main(String[] args) {
    int maxSize = 10;

    Store store = new Store(maxSize);

    Producer p1 = new Producer("p1", store, maxSize);
    Producer p2 = new Producer("p2", store, maxSize);

    new Thread(p1).start();
    new Thread(p2).start();

    Consumer c1 = new Consumer("c1", store);
    Consumer c2 = new Consumer("c2", store);
    Consumer c3 = new Consumer("c3", store);
    Consumer c4 = new Consumer("c4", store);
    Consumer c5 = new Consumer("c5", store);
    Consumer c6 = new Consumer("c6", store);

    new Thread(c1).start();
    new Thread(c2).start();
    new Thread(c3).start();
    new Thread(c4).start();
    new Thread(c5).start();
    new Thread(c6).start();
  }
}
