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
}
