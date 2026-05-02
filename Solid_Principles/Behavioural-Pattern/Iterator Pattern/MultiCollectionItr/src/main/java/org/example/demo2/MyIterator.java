package org.example.demo2;

/**
 * Generic Iterator interface - not coupled to any specific type.
 */
public interface MyIterator<T> {
    boolean hasNext();
    T next();
}

