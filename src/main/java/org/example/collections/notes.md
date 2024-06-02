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


