package producerconsumer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BufferTest {
  Buffer buffer;

  @BeforeEach
  void init() {
    buffer = new Buffer();
  }

  @Test
  void testBufferSize() {
    assertEquals(5, buffer.getMaximumQuantity());
  }

  @Test
  void testInsertItem() throws InterruptedException {
    assertEquals(0, buffer.getNumOfContent());

    buffer.insertItem(7);
    assertEquals(1, buffer.getNumOfContent());

    buffer.insertItem(2);
    buffer.insertItem(3);
    buffer.insertItem(4);
    buffer.insertItem(5);
    assertEquals(5, buffer.getNumOfContent());
  }

  @Test
  void testRemoveItem() throws InterruptedException {
    assertEquals(0, buffer.getNumOfContent());

    buffer.insertItem(2);
    buffer.insertItem(3);

    assertEquals(0, buffer.removeItem());
    assertEquals(1, buffer.getNumOfContent());
  }
}
