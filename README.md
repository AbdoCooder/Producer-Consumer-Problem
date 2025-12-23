# Java Producer-Consumer Implementation
<img width="2752" height="1536" alt="Image" src="https://github.com/user-attachments/assets/45107a63-3a67-4a81-831e-f66883aac8dc" />

## > !!! READ [Mastering_Java_Concurrency.pdf](https://github.com/AbdoCooder/Producer-Consumer-Problem/blob/main/Mastering_Java_Concurrency.pdf) FOR AN EXPANDED EXPLANATION !!!

# 💻 Laptop Store: Producer-Consumer Simulation

This project is a multi-threaded Java simulation of a **Laptop Store**, demonstrating the **Producer-Consumer** architectural pattern. It solves the concurrency challenges of managing a shared inventory (`Store`) accessed simultaneously by multiple suppliers (`Producers`) and customers (`Consumers`).

## 🧠 Core Architecture

This implementation uses the **Monitor Pattern** to ensure data integrity and thread safety without using high-level concurrent collections (like `BlockingQueue`).

### Key Concepts Implemented
* **Shared Resource:** A `Store` object containing an `ArrayList` of `Product`s (Laptops).
* **Mutual Exclusion:** Critical sections (adding/removing products) are guarded by `synchronized(store.laptops)`.
* **State Coordination:**
    * **Producers** wait via `wait()` when the store inventory hits capacity (10 units).
    * **Consumers** wait via `wait()` when the store is empty.
    * Both parties use `notifyAll()` to signal state changes (Full → Available Space, Empty → Stock Available).
* **Stress Testing:** The simulation runs **2 Producers** against **6 Consumers** to aggressively test the locking mechanism and race condition handling.

## 📂 Project Structure

* **`Main.java`**: The entry point. Initializes the `Store` and launches 2 Producer threads and 6 Consumer threads.
* **`Store.java`**: Represents the shared memory. Contains the `laptops` list (ArrayList) and the capacity limit.
* **`Product.java`**: A simple data class representing a specific laptop (with an ID).
* **`Producer.java`**: Simulates a factory worker. Generates random products, sleeps to simulate production time, and adds to the store.
* **`Consumer.java`**: Simulates a customer. Sleeps to simulate "browsing" time and removes products from the store.

## 🚀 How to Run

1.  Clone the repository.
2.  Compile the Java files:
    ```bash
    javac -d binary *.java
    ```
3.  Run the application:
    ```bash
    java -cp binary Main
    ```

## 📸 Sample Output
    ```bash
    Produced (p2) -> List: [62]
    Consumed (c5) -> List: []
    Produced (p2) -> List: [59]
    Consumed (c2) -> List: []
    Produced (p1) -> List: [60]
    Produced (p2) -> List: [60, 78]
    Consumed (c5) -> List: [78]
    Produced (p2) -> List: [78, 12]
    Consumed (c2) -> List: [12]
    Consumed (c6) -> List: []
    Produced (p1) -> List: [60]
    Produced (p2) -> List: [60, 32]
    Produced (p1) -> List: [60, 32, 70]
    Consumed (c4) -> List: [32, 70]
    Produced (p2) -> List: [32, 70, 51]
    Produced (p2) -> List: [32, 70, 51, 11]
    Consumed (c3) -> List: [70, 51, 11]
    Produced (p1) -> List: [70, 51, 11, 40]
    Produced (p1) -> List: [70, 51, 11, 40, 72]
    Produced (p1) -> List: [70, 51, 11, 40, 72, 63]
    Produced (p2) -> List: [70, 51, 11, 40, 72, 63, 69]
    Produced (p1) -> List: [70, 51, 11, 40, 72, 63, 69, 7]
    Consumed (c2) -> List: [51, 11, 40, 72, 63, 69, 7]
    Consumed (c5) -> List: [11, 40, 72, 63, 69, 7]
    Consumed (c1) -> List: [40, 72, 63, 69, 7]
    Consumed (c4) -> List: [72, 63, 69, 7]
    Produced (p1) -> List: [72, 63, 69, 7, 28]
    ```
The output uses ANSI colors to distinguish actors:
* 🟢 **Green**: Producer adding items.
* 🔴 **Red**: Consumer removing items.
