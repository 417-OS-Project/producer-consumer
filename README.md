# Producer Consumer
This program analyzes a producer-consumer problem through the usage of empty, full, and mutex structures.

## Contributors
* Tony Griffin

## Requirements
* [Java 11+](https://www.java.com/en/)
* [Gradle](https://gradle.org/) (optional)

## Running Instructions
This program can be run with either Gradle's build functionality or from the jar file. Both will require the following
arguments to be passed to them: time (length of the program in milliseconds), producers (number of producers), and
consumers (number of consumers).

### Run with Gradle
`./gradlew run --args="TIME PRODUCERS CONSUMERS`

### Run with a Jar File
`java -jar producerconsumer.jar TIME PRODUCERS CONSUMERS`
If a jar file is not presented, one can be generated using `./gradlew jar` while will place the `producerconsumer.jar`
file in the `app/build/libs` directory.

## Sample Execution
When run with `java -jar producerconsumer.jar 10000 5 5`, the following output is generated to the console:
```
Producer Thread 16 inserts 64 into buffer
Consumer Thread 21 consumed
Producer Thread 18 inserts 0 into buffer
Consumer Thread 23 consumed
Producer Thread 14 inserts 99 into buffer
Consumer Thread 20 consumed
Producer Thread 17 inserts 51 into buffer
Producer Thread 18 inserts 94 into buffer
Producer Thread 16 inserts 74 into buffer
Consumer Thread 20 consumed
Consumer Thread 21 consumed
Producer Thread 15 inserts 88 into buffer
Consumer Thread 19 consumed
Consumer Thread 22 consumed
Producer Thread 15 inserts 71 into buffer
```
Additionally, this output will be placed in a text file found in the `report/` directory with a naming convention
as follows: `{time}-{producers}-{consumers}.txt`

## Results
In regards to turnaround time, this problem demonstrates the need for a comparable amount of producers and consumers. When the
number of producers outweighs the consumers, the turnaround time for the producers continues to grow as each thread will have
to wait in the semaphore queue for longer and longer. This is also true in the case of consumers outweighing the producers
as they will have to wait in their queue for longer and longer and the program continues.
