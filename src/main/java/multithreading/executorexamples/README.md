# Executor Service:
 
### Different Executors

1. newScheduledThreadPool()
   - Explanation
2. newCachedThreadPool()
   - Explanation
3. newFixedThreadPool()
   - Explanation
4. newSingleThreadExecutor()
   - Explanation
5. newWorkStealingPool()
   - Explanation

# Synchronized Collections

### Different Queues

1. SynchronousQueue
   - put() and take() are the synchronized methods
   - Synchronous queues are similar to rendezvous channels used in CSP and Ada. They are well suited for handoff designs, in which an object running in one thread must sync up with an object running in another thread in order to hand it some information, event, or task.
2. BlockingQueue
   - it's an interface
3. LinkedBlockingQueue
   - Uses LinkedList in behind

# Java 8 Features

### Anonymous class, Anonymous function

### Lambda or Arrow function

### default and static method inside interface

### Functional Interface

1. Supplier<U>
   1. takes nothing returns value of type U
   2. get()
2. Consumer<T>
   1. takes argument of type T returns nothing
   2. accept(T arg)
3. Predicate<T>
   1. takes argument of type T returns boolean
   2. get(T arg)
4. Function<T, U>
   1. takes argument of type T and returns type of U
   2. apply(T arg)