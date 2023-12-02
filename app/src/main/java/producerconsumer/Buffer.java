package producerconsumer;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/** Stores the needed information for a bounded buffer. */
public class Buffer {
  /** The maximum size of this buffer. */
  private static final int BUFFER_SIZE = 5;

  /**
   * The mutex lock for this buffer.
   */
  private Semaphore mutex;

  /** Array of integer values. */
  private ArrayList<Integer> contents;

  /** Constructor for this buffer. */
  public Buffer() {
    mutex = new Semaphore(1);
    this.contents = new ArrayList<Integer>(BUFFER_SIZE);
  }

  /**
   * Return the maximum capacity of this buffer.
   *
   * @return maximum buffer size.
   */
  public int getMaximumQuantity() {
    return BUFFER_SIZE;
  }

  /**
   * Return the number of elements currently stored in this buffer.
   *
   * @return number of elements in content.
   */
  public int getNumOfContent() {
    return this.contents.size();
  }

  /**
   * Insert an item into the buffer.
   *
   * @return 0 if successful, -1 otherwise.
   */
  public int insertItem() throws InterruptedException {
    System.out.printf("Thread %d seeking entry%n", Thread.currentThread().getId());
    this.mutex.acquire();
    System.out.printf("Thread %d entering%n", Thread.currentThread().getId());

    System.out.printf("Thread %d leaving%n", Thread.currentThread().getId());
    this.mutex.release();
    return -1;
  }
}
