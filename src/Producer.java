// import java.util.LinkedList;

public class Producer implements Runnable {
  private String  name;
  private Store   store;
  private int     capacity;

  public Producer(String name, Store store, int maxSize) {
    this.name = name;
    this.store = store;
    this.capacity = maxSize;
  }

  @Override
  public void run() {
    while(true) {
      int data = (int)(Math.random() * 100);
      try {
        // simulation for producing items
        Thread.sleep((int)((Math.random()) * 10000) % 3000);
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
      synchronized (store.laptops) {
        // while the store full wait for a consumer to use it
        while (store.laptops.size() >= capacity) {
          // full
          try {
            store.laptops.notifyAll();
            store.laptops.wait(); // release the lock and sleep
          } catch (InterruptedException e) {
            System.out.println(e.getMessage());
          }
        }
        store.laptops.add(new Product(data));
        System.out.println("\033[0;32m" + "Produced (" + name +") " + 
                            "-> List: " + store.laptops + "\033[0m");
        store.laptops.notifyAll(); // notify consumers that there is a data
      }
    }
  }

}
