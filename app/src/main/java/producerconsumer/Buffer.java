package producerconsumer;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/** Stores the needed information for a bounded buffer. */
public class Buffer {
  /** The maximum size of this buffer. */
  private static final int BUFFER_SIZE = 5;

  /** The mutex lock for this buffer. */
  private Semaphore mutex;

  /** Semaphore for empty buffer area. */
  private Semaphore empty;

  /** Semaphore for full buffer area. */
  private Semaphore full;

  /** Array of integer values. */
  private ArrayList<Integer> contents;

  /** Constructor for this buffer. */
  public Buffer() {
    this.mutex = new Semaphore(1);
    this.empty = new Semaphore(BUFFER_SIZE);
    this.full = new Semaphore(0);

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
   * @param toAdd the integer to add to the buffer.
   * @return 0 if successful, -1 otherwise.
   */
  public int insertItem(int toAdd) throws InterruptedException {
    try {
      this.empty.acquire();
      this.mutex.acquire();
      System.out.printf("Thread %d entering%n", Thread.currentThread().getId());

      if (this.contents.size() == BUFFER_SIZE) {
        this.mutex.release();
        return -1;
      }
      this.contents.add(toAdd);

      System.out.printf("Thread %d leaving%n", Thread.currentThread().getId());
      this.mutex.release();
      this.full.release();
      return 0;
    } catch (Exception e) {
      return -1;
    }
  }

  /**
   * Remove an item from the buffer.
   *
   * @return the removed item.
   */
  public int removeItem() throws InterruptedException {
    try {
      this.full.acquire();
      this.mutex.acquire();
      System.out.printf("Thread %d entering%n", Thread.currentThread().getId());

      if (this.contents.isEmpty()) {
        this.mutex.release();
        return -1;
      }

      this.contents.remove(0);

      System.out.printf("Thread %d leaving%n", Thread.currentThread().getId());
      this.mutex.release();
      this.empty.release();
      return 0;
    } catch (Exception e) {
      return -1;
    }
  }
}
