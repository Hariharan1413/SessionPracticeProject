package org.example.demo2;

/**
 * Generic Aggregate interface that can create an iterator.
 */
public interface MyCollection<T> {
    MyIterator<T> createIterator();
    void add(T item);
}

