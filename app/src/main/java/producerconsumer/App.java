package producerconsumer;

import java.util.ArrayList;
import java.util.Random;

/** Main class for the producer consumer package. */
public class App {
  /** Global variable to determine the start time of this program. */
  public static long startTime = System.nanoTime();

  /**
   * Handle the arguments for this program.
   *
   * @param args array of strings.
   * @param numOfArgs number of arguments expected.
   * @return integer array of args.
   * @throws NumberFormatException if a non-integer is in args.
   * @throws IllegalArgumentException if args is not equal to size of 3.
   */
  public static int[] validateArgs(String[] args, int numOfArgs) {
    if (args.length != numOfArgs) {
      throw new IllegalArgumentException();
    }

    int[] intArgs = new int[3];

    intArgs[0] = Integer.parseInt(args[0]);
    intArgs[1] = Integer.parseInt(args[1]);
    intArgs[2] = Integer.parseInt(args[2]);

    return intArgs;
  }

  /**
   * Create a specified number of producer threads.
   *
   * @param num number of producers.
   * @param time the length of the program or the max time for sleep.
   * @param buffer the buffer to produce to.
   * @return array list of threads.
   */
  public static ArrayList<Thread> createProducers(int num, int time, Buffer buffer) {
    ArrayList<Thread> producers = new ArrayList<>(num);
    for (int i = 0; i < num; i++) {
      producers.add(
          new Thread() {
            @Override
            public void run() {
              while (this.isAlive()) {
                Random rand = new Random();
                int sleep = rand.nextInt(time);
                int toAdd = rand.nextInt(100);

                try {
                  Thread.sleep(sleep);
                  if (buffer.insertItem(toAdd) != 0) {
                    System.out.printf("Error inserting occurred%n");
                    return;
                  }
                  System.out.printf(
                      "%d ms: Producer Thread %d inserts %d into buffer%n",
                          (System.nanoTime() - startTime) / 1000000, this.getId(), toAdd);
                } catch (InterruptedException e) {
                  throw new RuntimeException(e);
                }
              }
            }
          });
    }
    return producers;
  }

  /**
   * Create the specified number of consumer threads.
   *
   * @param num number of consumers.
   * @param time the length of the program of the max time to sleep.
   * @param buffer the buffer to remove from.
   * @return array list of threads.
   */
  public static ArrayList<Thread> createConsumers(int num, int time, Buffer buffer) {
    ArrayList<Thread> consumers = new ArrayList<>(num);
    for (int i = 0; i < num; i++) {
      consumers.add(
          new Thread() {
            @Override
            public void run() {
              while (this.isAlive()) {
                Random rand = new Random();
                int sleep = rand.nextInt(time);

                try {
                  Thread.sleep(sleep);
                  if (buffer.removeItem() != 0) {
                    System.out.printf("Error consuming occurred%n");
                    return;
                  }
                  System.out.printf(
                      "%d ms: Consumer Thread %d consumed%n",
                          (System.nanoTime() - startTime) / 1000000, this.getId());
                } catch (InterruptedException e) {
                  throw new RuntimeException(e);
                }
              }
            }
          });
    }
    return consumers;
  }

  /**
   * Main driver for the producer consumer package.
   *
   * @param args CLI arguments.
   */
  public static void main(String[] args) throws InterruptedException {
    int[] intArgs = new int[3];
    Buffer buffer = new Buffer();
    ArrayList<Thread> producers;
    ArrayList<Thread> consumers;

    try {
      intArgs = validateArgs(args, 3);
    } catch (NumberFormatException e) {
      System.out.println("Non-integer number passed");
      System.exit(-1);
    } catch (IllegalArgumentException e) {
      System.out.println("Improper number of arguments passed");
      System.exit(-2);
    }

    producers = createProducers(intArgs[1], intArgs[0], buffer);
    consumers = createConsumers(intArgs[2], intArgs[0], buffer);

    for (Thread thread : producers) {
      thread.start();
    }

    for (Thread thread : consumers) {
      thread.start();
    }

    Thread.sleep(intArgs[0]);
    System.exit(0);
  }
}
