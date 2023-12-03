# Producer Consumer
This program analyzes a producer-consumer problem through the usage of empty, full, and mutex structures.

## Contributors
* Tony Griffin

## Requirements
* [Java 11+](https://www.java.com/en/)
* [Gradle](https://gradle.org/) (optional)

## Running Instructions
This program can be run with either Gradle's build functionality or from the jar file. Both will require the following
arguments to be passed to them: time (length of the program in milloseconds), producers (number of producers), and
consumers (number of consumers).

### Run with Gradle
`./gradlew run --args="TIME PRODUCERS CONSUMERS`

### Run with a Jar File
`java -jar producerconsumer.jar TIME PRODUCERS CONSUMERS`
If a jar file is not presented, one can be generated using `./gradlew jar` while will place the `producerconsumer.jar`
file in the `app/build/libs` directory.

## Sample Execution
When run with `java -jar producerconsumer.jar 10000 5 5`, the following output is generated:
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