# Java Producer-Consumer Implementation

This project is a raw, low-level implementation of the classic **Producer-Consumer** concurrency problem in Java. It demonstrates how to manage shared resources between multiple threads without using high-level concurrent collections (like `BlockingQueue`), forcing a deep understanding of thread synchronization.

## 🧠 Core Concepts

This project implements the **Monitor Pattern** to ensure thread safety:

* **Mutual Exclusion:** Uses `synchronized` blocks to ensure only one thread accesses the shared `LinkedList` at a time.
* **Inter-Thread Communication:** Uses `wait()` to block threads when the buffer is full/empty and `notifyAll()` to wake them up when state changes.
* **Race Condition Prevention:** Guards against data corruption and "spurious wakeups" using `while` loops for state checking.
* **Deadlock Prevention:** Carefully placed notification calls ensure no thread is left sleeping indefinitely.

## 📂 Project Structure

* `Main.java`: The entry point. Initializes the shared storage and starts the threads.
* `Producer.java`: Generates data and adds it to the list. Waits if the list is full (Capacity: 10).
* `Consumer.java`: Removes data from the list. Waits if the list is empty.
* `ConsoleColors.java`: Helper class for ANSI color codes to visualize thread actions.

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
