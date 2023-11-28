/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package producerconsumer;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AppTest {
  @Test
  void testHandleArgs() {
    assertArrayEquals(new int[] {1, 2, 3}, App.validateArgs(new String[] {"1", "2", "3"}, 3));
    Assertions.assertThrows(
        NumberFormatException.class,
        () -> App.validateArgs(new String[] {"hello", "world", "bye"}, 3));
    Assertions.assertThrows(
        IllegalArgumentException.class, () -> App.validateArgs(new String[] {"2", "2"}, 3));
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> App.validateArgs(new String[] {"4", "4", "4", "4"}, 3));
  }

  @Test
  void testCreateProducers() {
    ArrayList<Thread> producers;

    producers = App.createProducers(4);
    assertEquals(4, producers.size());

    producers = App.createProducers(42);
    assertEquals(42, producers.size());
  }
}
