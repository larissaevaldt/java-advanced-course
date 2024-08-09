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


