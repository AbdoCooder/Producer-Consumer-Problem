
public class Consumer implements Runnable {
  private String  name;
  private Store   store;

  public Consumer(String name, Store store) {
    this.name = name;
    this.store = store;
  }

  @Override
  public void run() {
    while(true) {
      try {
        Thread.sleep((int)(Math.random() * 10000));
      } catch (InterruptedException e) {
        System.err.println(e.getMessage());
      }
      synchronized (store.laptops) {
        // while the store full wait for a consumer to use it
        while (store.laptops.isEmpty()) {
          // empty
          try {
            store.laptops.notifyAll();
            store.laptops.wait(); // release the lock and sleep
          } catch (InterruptedException e) {
            System.out.println(e.getMessage());
          }
        }
        store.laptops.remove(0);
        System.out.println("\033[0;31m" + "Consumed (" + name + ") " +
                            "-> List: " + store.laptops + "\033[0m");
        // let the other threads know that the state has changed
        store.laptops.notifyAll();
      }
    }
  }

}
