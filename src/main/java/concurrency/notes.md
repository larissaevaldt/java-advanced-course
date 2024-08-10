# Concurrency

* What is concurrency?
  * executing tasks **at the same time**
* Multiple CPU's (plus multi-core processors); true parallel processing
  * three chefs working at the same time on a meal
* Single CPU - multitasking, strictly not parallel processing
  * one chef on his/her own preparing a meal
* Multi-threading is also concurrency - separate parts of your program can use independent threads

### Advantages
* better performance
* better response time

### Disadvantages
* shared resources must be handled carefully
* data races, deadlock and livelock

### Definition of terms
* A *process* consists of one or more threads.
* A *thread* is the smallest unit of execution. A thread executes tasks.
* A *task* defines the work that the thread will execute e.g. this is often a lambda.
* The order of thread execution in *non-deterministic* i.e. not guaranteed

### Creating Threads
* extend *Thread*
* implement *Runnable*
* implement *Callable* (requires *ExecutorService*)

### ExecutorService interface
* The Concurrency API abstracts thread management for us i.e. it enables complex processing involving threads without us having to manage threads directly.
* The *ExecutorService* is an interface that provides services for the creation and management of threads.
* The *Executors* utility class provides static methods that return *ExecutorService* implementation.
* A "thread pool" is a set of reusable worker threads available to execute tasks.

#### Types of ExecutorService
* Single thread pool executor
  * a single thread is used; tasks are processed sequentially.
* Cached thread pool executor
  * creates new threads as needed and reuses threads that have become free.
  * care needed as the number of threads can become very large.
* Fixed thread pool executor
  * creates a fixed number of threads which is specified at the start.

#### Submitting tasks to an ExecutorService
* A Callable<V> is very similar to a Runnable except that a Callable can return a result and throw a checked Exception
* Both are asynchronous
* Both represent a task to be executed by thread 
* Both are functional interfaces

### Future<V> interface
* A Future<V> is used to obtain the results from a Callable's call() method.
* A Future<V> object represents the result of an asynchronous computation. Methods are provided to check if the computation is complete (isDone()) and to retrieve the result of that computation (get()).
* The result can only be retrieved using the V get() when the computation has completed, blocking if necessary until it is ready.
* 

