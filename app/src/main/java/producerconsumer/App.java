package producerconsumer;

import java.lang.Thread;

public class App {
  /**
   * Handle the arguments for this program.
   *
   * @param args array of strings.
   * @return integer array of args.
   * @throws NumberFormatException if a non-integer is in args.
   * @throws IllegalArgumentException if args is not equal to size of 3.
   */
  public static int[] validateArgs(String[] args) {
    if (args.length != 3) {
      throw new IllegalArgumentException();
    }

    int[] intArgs = new int[3];

    intArgs[0] = Integer.parseInt(args[0]);
    intArgs[1] = Integer.parseInt(args[1]);
    intArgs[2] = Integer.parseInt(args[2]);

    return intArgs;
  }

  /**
   * Main driver for the producer consumer package.
   *
   * @param args CLI arguments.
   */
  public static void main(String[] args) throws InterruptedException {
    int[] intArgs = new int[3];
    try {
      intArgs = validateArgs(args);
    } catch (NumberFormatException e) {
      System.out.println("Non-integer number passed");
      System.exit(-1);
    } catch (IllegalArgumentException e) {
      System.out.println("Improper number of arguments passed");
      System.exit(-2);
    }

    Thread.sleep(intArgs[0]);
  }
}
