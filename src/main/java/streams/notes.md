# Stream

## What is a stream?
* Like lambdas and functional interfaces, streams were another new addition in Java 8.
* A *stream* is a sequence of data that can be processed with operations.
* Streams are **not** another way of organizing data, like an array or a *Collection*. Streams do not hold data; streams are all about processing data efficiently.
* While streams make code more concise, their big advantage is that streams, by using a pipeline, can, in certain situations, greatly improve the efficiency of data processing.
* The real power of streams come from the multiple intermediate operations you can perform on the stream.

## The pipeline
* A *stream pipeline* consists of the operations that run on a stream to produce a result.
* There are 3 parts to a stream pipeline:
  * a) Source - where the stream comes from e.g. array, collection or a file.
  * b) Intermediate operations - transforms the stream into another one. There can be as few or as many as required.
  * c) Terminal operation - required to start the whole process and produces the result. Streams can only be used once i.e. streams are no longer usable after a terminal operation completes (re-generate the stream if necessary).

## Common operations
* **filter()** - an intermediate operation and as such can filter the stream and pass on the filtered stream to the next operation (another intermediate or a terminal operation).
* **count()** and **forEach()** - are both terminal operations that end the stream.
* The pipeline operations are the way in which we specify how and what order we want the **data in the source** manipulated. Remember, **streams don't hold any data**.

## Streams are Lazy
* The principal of "lazy" evaluation is that you get what you need only when you need it. For example, if you were displaying 10,000 records to a user, the principal of lazy evaluation would be to retrieve 50 and while the user is viewing these, retrieve another 50 in the background.
* "Eager" evaluation would be to retrieve all 10,000 records in one go.
* With regard to streams, this means that nothing happens until the terminal operation occurs. You need a terminal operation for anything to occur in the stream.
* The pipeline specifies what operations we want performed (on the source) and in which order.
* This enables the JDK to reduce operations whenever possible.
* For example, why run an operation on a piece of data if the operation is not required:
  * we have found the data element we were looking for
  * we may have a limit set on the number of elements we want to operate on.

## Creating a Stream from an Array
* *Arrays.stream()* can be used to stream an array.

## Creating a Stream from a Collection
* The default Collection interface method *stream()* is used.

## Creating a Stream from a Collection
* *Stream.of()* is a static generically-typed utility method that accepts a varargs parameter and returns an ordered stream of those values.