# Working with Arrays and Collections
* Use generics, including wildcards
* Use a Java array and List, Set, Map and Deque collections, including convenience methods
* Sort collections and arrays using Comparator and Comparable interfaces.

## Collections
* Differentiate the following:
  * collection - lowercase 'c'; represents any of the data structures in which objects are stores and iterated over.
  * Collection - capital 'C'; this is the *java.util.Collection* interface that the Set, List and Queue interfaces extend.
  * Collections - capital 'C' and ends with 's'; this is the *java.util.Collections* utility class which contains lots of *static* methods for use with collections


The top interface is Collection, then there is the Set, List and Queue interfaces all extending the Collection
* Set - has unique items, does NOT allow duplicates 
  * SortedSet interface extending Set 
    * NavigableSet interface extending SortedSet.
      * TreeSet: is sorted, and that implements the NavigableSet.
  * HashSet which implements the Set, it has no order.
    * LinkedHashSet: extends HashSet, it is a subtype of HashSet which maintains the insertion order.
* Queue - we can have various different types, two of them are FIFO queue (first in, first out). LIFO queue (Stack) (last in, first out)
  * Deque interface which is doubly ended queue extending Queue. Access both ends.
    * LinkedList: doubly-linked, implements Deque
    * ArrayDeque: expandable array, implements Deque
  * PriorityQueue: priority based, implements Queue
* List interface:
  * ArrayList: implements the List interface and provides index-order, maintains order and allows duplicates.
  * LinkedList: doubly-linked
  * Stack: legacy class; use Deque instead; access to top only.

Does not extend from Collection:
* Map interface - maps keys to values, duplicate keys are not allowed.
  * SortedMap
    * NavigableMap
      * TreeMap: sorted
  * HashMap: no order, not thread-safe, allows nulls
    * LinkedHashMap: insertion-order
  * Hashtable: thread-safe, nulls not allowed.
  

### Popular Collection Methods

| output  | method signature                 | description                                                 |
|---------|----------------------------------|-------------------------------------------------------------|
| boolean | add(E element)                   | adds the element to the end                                 |
| boolean | remove(Object o)                 | removes a single instance of the elements specified         |
| boolean | isEmpty()                        | returns true if the collection contains no elements         |
| int     | size()                           | returns the number of elements in the collection            |
| void    | clear()                          | removes all of the elements                                 |
| boolean | contains(Object o)               | does the collection contain the specified element           |
| boolean | removeIf(Predicate<? super E> p) | removes all elements that match the condition               |
| void    | forEach(Consumer<? super T> c)   | performs the given action on all elements in the collection |

**Note**: forEach method is a *default* method in the *Iterable* interface and *Collection* extends *Iterable*.

### List
* An ordered collection (sequence); provides precise control over access to an element using its integer index; duplicate elements are allowed
  * **ArrayList** - a growable array; fast iteration and fast random access; use when you are not likely to do much insertion/deletion (shuffling required).
  * **LinkedList** - elements are doubly-linked to each other; fast insertion/deletion.
  * **Stack** - represents a last-in-first-out (LIFO) stack of objects. The Deque interface and its implementations are more complete and should be used instead.
#### Popular Factory Methods
| output  | method signature                          | description                                                                                                                                                             |
|---------|-------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| List<T> | Arrays.asList(T... a)                     | returns a fixed-size list "backed" by the array i.e. changes to the list write through to the array and vice-versa; cannot add/delete elements but can replace elements |
| List<E> | List.of(E... elements)                    | returns an immutable list containing the elements specified                                                                                                             |
| List<E> | List.copyOf(Collection<? extends E> coll) | returns an immutable list containing the elements of the given collection                                                                                               |

#### Popular List Methods
| output | method signature                | description                                                                              |
|--------|---------------------------------|------------------------------------------------------------------------------------------|
| void   | add(int index, E element)       | adds the element at the index and moves the rest down one place                          |
| E      | get(int index)                  | returns the element at that index                                                        |
| E      | remove(int index)               | removed the element at that index and moves the rest up one place                        |
| void   | replaceAll(UnaryOperator<E> op) | replaces each element in the list by applying the operator                               |
| E      | set(int index, E e)             | replaces the element at that index with the specified element (the original is returned) |


### Set
* Collections with no duplicates
  * HashSet
    * unsorted, unordered Set; uses the hashcode of the object being inserted; the more efficient your hashCode() implementation, the better access performance you will get.
    * use this class when you want a collection with no duplicates, and you don't care about the order when you iterate through it.
  * LinkedHashSet
    * an ordered version of HashSet (insertion order)
    * elements are doubly-linked to each other
    * use this class instead of HashSet when you care about the iteration order.
  * TreeSet
    * a sorted collection ("Tree")
    * elements can be sorted according to their "natural order" - for String's, the natural order is alphabetic; for Integer's, the natural order is numeric.
    * elements can also be sorted according to a custom order by providing a comparator at creation time.

#### Popular Set Methods
| output | method signature                         | description                                                              |
|--------|------------------------------------------|--------------------------------------------------------------------------|
| Set<E> | Set.of(E... elements)                    | returns an immutable Set containing the elements specified               |
| Set<E> | Set.copyOf(Collection<? extends E> coll) | returns an immutable Set containing the elements of the given collection |

#### Hashing
* Hashing is about using memory more efficiently e.g. making searching faster.
* There are 2 steps:
  1. Find the bucket using hashCode()
  2. Find the object using equals()
* Thus, hashCode() and equals() are linked when using has based collections.
* Therefore, if you are using a collection with "hash" in its name and you override hashCode(), you must override equals() also (and vice versa).
* Briefly, equal objects should have the same hashCode, whether an object is equal is up to you, the developer.
* The whole idea is of you put an object into a bucket, you don't need to keep that exact same object around to be able to retrieve it later on, it could go out of scope and you don't mind, as long as you can recreate the object with the same instance variables then it's ok. You can recreate another object later on with the same instance variables and because equal objects should have the same hashCode, it will go in and find the object in the same bucket, so you should use the same instance variables that are used in the equals method in the calculation of the hashCode;  

##### In short: 
* *Set* allows no duplicates
* TreeSet - sorted.
* HashSet - hashing, unordered.
* LinkedHashSet - hashing, insertion order.


### Map
* Maps keys to values; keys are unique; each key can map to at most one value.
  * HashMap
    * unsorted, unordered Map.
    * uses the hashcode of the object being inserted; the more efficient your hashCode() implementation, the better access performance you will get.
    * use this class when you want a Map and you don't care about order when you iterate through it.
    * allows one *null* key and multiple *null* values.
  * LinkedHashMap
    * maintains insertion order
  * TreeMap
    * a sorted Map; sorted by natural order (i.e. alphabetical for strings) of its keys or by a custom order (via a comparator).
  * HashTable
    * similar to HashMap except Hashtable is thread-safe (slower as a result of the synchronized call that it's got to put in) and nulls are not allowed.


#### Popular Map Methods
| output             | method signature                 | description                                                                                                 |
|--------------------|----------------------------------|-------------------------------------------------------------------------------------------------------------|
| void               | clear()                          | removes all keys and values from the map                                                                    |
| boolean            | containsKey(Object key)          | is the key in the map                                                                                       |
| boolean            | containsKey(Object value)        | is this value in the map                                                                                    |
| Set<Map.Entry<K,V> | entrySet()                       | returns a Set view of the key/value pairs                                                                   |
| void               | forEach(BiConsumer(key, value))  | perform the given BiConsumer on each entry in the map                                                       |
| V                  | get(Object key)                  | returns the value for the specified key or null if no mapping exists                                        |
| boolean            | isEmpty()                        | is the map empty                                                                                            |
| Set<K>             | keySet()                         | returns a Set view of all the keys in the map                                                               |
| V                  | put(K key, V value)              | adds or replaces the key/value pair. Returns previous value or null.                                        |
| V                  | putIfAbsent(K key, V value)      | adds the key/value pair if the key is not already there and returns null; otherwise returns existing value. |
| V                  | remove(Object key)               | removes the value if the key exists and returns the value that was there; returns null if key not in map.   |
| V                  | replace(K key, V value)          | replaces the value for the key and returns the old value; return null if key not in map                     |
| void               | replaceAll(BiFunction<K,V,V> fn) | replaces each value with the results of the function.                                                       |
| int                | size()                           | how many key/value pairs in the map.                                                                        |
| Collection<V>      | values()                         | returns a Collection view of all the values.                                                                |


### Queue
* Queue - a collection that specifies the order in which the elements are to be processed.
  * Typically, the order is FIFO (First In First Out).
  * Exceptions are priority queues (order is natural ordering or according to a supplied comparator) and LIFO (Last In First Out) queues (stacks).
  * LinkedList
    * as LinkedList implements Queue; basic queues can be handled with a LinkedList.
  * PriorityQueue
    * orders the elements relative to each other such that "priority-in, priority-out" (as opposed to a FIFO or LIFO).
    * the elements are either compared by natural order or by a custom order via a comparator.
    * elements that are sorted first will be accessed first.
* Deque
  * "double ended queue" and is pronounced "deck"
  * access from both ends permitted.
  * can be used as both FIFO (queue) and LIFO (stack)
  * ArrayDeque
    * expandable-array implementation of the Deque interface (no capacity restrictions)
    * API: "likely to be faster than Stack when used as a stack, and faster than LinkedList when used as a queue"

#### Popular Queue Methods
|         | Throws exception | Returns special value |
|---------|------------------|-----------------------|
| Examine | element()        | peek()                |
| Insert  | add(e)           | offer(e)              |
| Remove  | remove()         | poll()                |

**Note:** the most common methods are peek(), offer() and poll() as they do not throw exceptions. POP is useful for remembering them.

#### Popular Deque Methods
* Head (First Element)

|         | Throws exception | Returns special value |
|---------|------------------|-----------------------|
| Examine | getFirst()       | peekFirst()           |
| Insert  | addFirst(e)      | offerFirst(e)         |
| Remove  | removeFirst()    | pollFirst()           |

* Tail (Last Element)

|         | Throws exception | Returns special value |
|---------|------------------|-----------------------|
| Examine | getLast()        | peekLast()            |
| Insert  | addLast(e)       | offerLast(e)          |
| Remove  | removeLast()     | pollLast()            |

##### Using Deque as a queue
* Examine:        peekFirst()
* Insert (end):   offerLast(e)
* Remove (front): pollFirst()

##### Using Deque as a stack - Beginning of deque is the "top" of the stack
* Examine:        peek()   is  **getFirst()**
* Insert (end):   push(e)  is  **addFirst()**
* Remove (front): pop()    is  **removeFirst()**

## Sorting - Comparable and Comparator
* Both collections and arrays can be sorted and searched using methods in the API.
* The *Collections* class is a utility class i.e. class which consists exclusively of static methods, used for operating on collections.
* The *Arrays* class is also a utility class; the *Arrays* class however, operates on native arrays only ([] syntax)
* One can convert an array (of reference types) to a *List* using the *Arrays.asList method. The returned *List* can then be passed to useful methods that exist in the *Collections* class.

### Comparable and Comparator interfaces
* The Comparable<T> and Comparator<T> interfaces are used for comparing objects of similar type
* Both are functional interfaces
* Sorting is a classic example where they are used.
* *java.lang.Comparable* because Comparable comes from java.lang you don't need an import for it
* *java.util.Comparator* do need to import it
* Note: if you add an object of a class to e.g. *TreeSet* and the class does NOT implement *Comparable*, you will get a *ClassCastException*.

### Comparable interface
* The *Comparable<T>* interface defines one method:
  * *int compareTo(T that)*
* Given that you implement *compareTo* method in the class itself, you already have access to its state using the "*this*" reference. Thus, you can compare "*this*" to the object passed in ("*that*" above).
* *Comparable* defines the "natural ordering". For *Integer* this is ascending numeric order(1, 2, 3, etc.); for *String* it is alphabetical order ("A","B","C", etc.)
  * Note: *TreeSet* would sort *Strings* according to Unicode: numbers before letters, uppercase letters before lowercase letters ("null" is an easy way to remember)
* *compareTo* logic: return an *int* value based on the following:
  * return a positive number if the current object is larger than the object passed in
  * return 0 if the current object is equivalent to the object passed in
  * return a negative number if the current object is smaller than the object passed in
* This logic can be delegated to existing types (String, Integer) that already have implemented *Comparable*. In other words, if you are comparing Integers you can delegate.
* when are 2 objects equal?
  * *compareTo* - returns 0
  * *equals* - returns true
* API: "The natural ordering for a class C is said to be *consistent* with equals if and only if e1.compareTo(e2) == 0 has the same boolean value as e1.equals(e2) for every e1 and e2 of class C".
* We are "strongly recommended" to keep our *Comparable* classes consistent with equals because "sorted sets (or sorted maps)... behave strangely" otherwise. [API]


### Comparator interface
* What if the objects we wanted to sort did not implement *Comparable* or if we wanted to sort in several different ways? Answer: *Comparator*
* *Comparator* is also a functional interface:
  * *int compareTo(T o1, T o2)*
* The logic internally is the same as for *compareTo*
* Typically, this is coded externally to the class whose objects we are comparing, so we need to compare 2 objects.
  * as *Comparable* is coded internally to the class, we just need the one/other object we want to compare to 'this' object

### How to remember the differences?
* Compara*t*or - "**T**wo out of three ain't bad"
  * "Compara**t**or takes **t**wo" args but not the "**T**o" method
    * int compare(T o1, T o2)
    * ORE = Comparat**or** and compar**e**() 
* Comparable
  * if *Comparator* takes 2 than this takes 1 (natural ordering)
  * as *Comparator* does not have the "To" method, it must be here
    * int compareTo(T o)
    * LEO = Comparab**l**e and compareT**o**

### binarySearch()
* binarySearch() requires a sorted *List*
* As with sort(), if you don't want natural order, you can pass in a comparator.

## equals() and hashCode()

### equals()
* Comparing two object references using the == operator evaluates to *true* only when both references refer to the same object i.e. == compares the bits in the reference variables themselves, and they are either equal or they are not.
* The *equals()* in *Object* behaves in the same way i.e. *equals()* in *Object* uses only the == operator for comparisons.
  * Person p1 = new Person();   p1 reference -> Person Object
  * Person p2 = new Person();   p2 reference -> Person Object
* The *String* class has overridden *equals()*, which it inherits from *Object*, so that you can compare two different *String* **objects** to see if their contents are meaningfully equivalent.
* When you need to know if two references are identical, use ==
* When you need to know if two objects themselves (not the references) are equal, use the *equals()* method
* Bear in mind that we will be storing objects (as keys) in Collections (such as HashMap) and searching/retrieving these objects again later.
* For example, assume we do **not** override *equals()* and we store an object of that type in a Collection and later attempt to retrieve it - we are in trouble unless we have a reference to the exact object we used when storing the key.
* This is because the search for the key/object will be based on Object::equals() which, as we know, uses == on the references for equality testing.
* Whereas, if you override *equals()*, when you need to search for an object X, you can just re-create a *new* instance that is meaningfully equivalent to X and use that instance for the search.
* If you want objects of your class to be used as elements in any data structure that uses equivalency for searching for, and/or retrieving an object, then you must override *equals()* **so that two different instances can be considered the same.**
* That way, you can use one instance when you **add** it to a Collection and essentially re-create an identical instance when you want to do a **search** based on that object as a key.
#### In summary:
* *Obejct::equals()* checks if two references are equal i.e. do they refer to the same object?
* typically, this is not what you want, so you must override equals()
* the *Obejct* passed in must be a downcast to the relevant type; to do this safely use *instaceof*.
* Remember that *toString()*, *equals()* and *hashCode()* are all **public**. Therefore, the following is an illegal override:
  * class Foo{ boolean equals(Object o){return true;}} // should be **public**
* The following is also an illegal override:
  * class Foo{ boolean equals(Foo f){return true;}} // parameter should be **Object** not **Foo**

### hashCode()
* If two objects are considered equal using *equals()* method, then they must have identical hashcode values. If you override *equals()*, override *hashCode()* as well.
* Hashcodes are used for improving performance in hash based collections e.g. *HashSet*, *HashMap*. The hashcode value is used to determine how the object should be *stored* in the collection and the hashcode is used again to help *locate* the object in the collection.
#### Understanding Hashing
* Hashing is similar to putting items in buckets:
  * the hashcode value determines which bucket the object is **stored** in and later on, the hashcode value determines which bucket is **searched** to locate the object.
  * hashcodes are not necessarily unique so several objects can land in the same bucket; this is where *equals()* comes into play - the correct object is then located by using the *equals()* method
1. find the right bucket (with *hashCode()*)
2. search the bucket for the right element (using *equals()*)

* Thus, for an object to be located, the search object and the object in the collection **must** have the same hashcode and return *true* for *equals()*
* The default *hashCode()* method in *Object* always comes up with a unique number for each object, even if the *equals()* method is overridden and states that two or more objects are equal.
* In other words, it does not matter how equal they are if their hashcodes do not reflect that (as you will be directed to the wrong bucket). Therefore, the *hashCode()* contract states that, **if two objects are equal, their hashcodes must be equal as well**.
* When calculating the hashcode in *hashCode()*, make sure to use the same instance variables that you used in *equals()*. Therefore, if two objects are equal (based on their instance variables), then the two objects will have the same hashcode value.
* **Note**: do not use *transient* instance variables in the hashcode calculations as these are not serialised.
* For example, if a *transient* variable has 10 as its value at the time you *store* the object in a *HashMap*, then you serialise the object to disk (*transient* not serialised); you then deserialize the object, the *transient* variable will get a default value e.g. 0 and therefore the hashcode value will be different when you go to *locate* that object (i.e. wrong bucket)

#### Example
* Assume we are storing names according to the following hashing calculation algorithm: A=1, B=2 etc...
* The numbers associated with each letter are added together to give the hashcode (bucket number)
  * Bob = B(2) + O(15) + B(2) = 19
  * Amy = A(1) + M(13) + Y(25) = 39
  * May  = M(13) + A(1) + Y(25) = 39
* 39 -> "Amy", "May"
* 19 -> "Bob"

#### Hashcode summary
* *hashCode()* contract:
  * the "contract" states that "*two equal objects must have the same hashCode()*"
* *public int hashCode()* :
  * by default, *hashCode()* in *Object* returns a unique integer for objects.
  * to be certain that your objects can be used in Collections that use hashing, you must override both *equals()* and *hashCode()* as both are used - *hashCode()* to find the bucket and *equals()* to find the object in the bucket.
  * the instance variables used in *equals()* must be the used in *hashCode()*; that way equal objects will return the same hashcode integer value.

