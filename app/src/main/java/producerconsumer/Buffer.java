package producerconsumer;

/** Stores the needed information for a bounded buffer. */
public class Buffer {
  /** The maximum size of this buffer. */
  private static final int BUFFER_SIZE = 5;

  /** Array of integer values. */
  private int[] contents;

  /** Constructor for this buffer. */
  public Buffer() {
    this.contents = new int[BUFFER_SIZE];
  }

  /**
   * Return the maximum capacity of this buffer.
   *
   * @return maximum buffer size.
   */
  public int getMaximumQuantity() {
    return BUFFER_SIZE;
  }
}
