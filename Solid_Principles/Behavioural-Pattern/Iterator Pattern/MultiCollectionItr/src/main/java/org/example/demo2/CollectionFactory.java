package org.example.demo2;

/**
 * Factory that hides concrete collection types from the client.
 * Client only knows about MyCollection<Product>, not the implementation.
 */
public class CollectionFactory {

    public enum StorageType {
        LIST, ARRAY
    }

    public static MyCollection<Product> createCollection(StorageType type, int capacity) {
        switch (type) {
            case LIST:
                return new ProductListCollection();
            case ARRAY:
                return new ProductArrayCollection(capacity);
            default:
                throw new IllegalArgumentException("Unknown storage type: " + type);
        }
    }

    public static MyCollection<Product> createCollection(StorageType type) {
        return createCollection(type, 10);
    }
}

