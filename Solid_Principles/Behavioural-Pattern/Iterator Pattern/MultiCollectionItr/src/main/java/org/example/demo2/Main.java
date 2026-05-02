package org.example.demo2;

/**
 * Client: Demonstrates Iterator Pattern.
 * Notice: The client NEVER sees ProductArrayIterator, ProductListIterator,
 * ProductListCollection, or ProductArrayCollection directly.
 * It only works through MyCollection and MyIterator interfaces.
 */
public class Main {
    public static void main(String[] args) {

        // --- List-based collection (client doesn't know it's a List internally) ---
        MyCollection<Product> collection1 = CollectionFactory.createCollection(CollectionFactory.StorageType.LIST);
        collection1.add(new Product("Rocket"));
        collection1.add(new Product("Bat"));
        collection1.add(new Product("Ball"));

        System.out.println("=== Iterating Collection 1 ===");
        printAll(collection1.createIterator());

        // --- Array-based collection (client doesn't know it's an Array internally) ---
        MyCollection<Product> collection2 = CollectionFactory.createCollection(CollectionFactory.StorageType.ARRAY, 5);
        collection2.add(new Product("Keyboard"));
        collection2.add(new Product("Mouse"));
        collection2.add(new Product("Monitor"));

        System.out.println("\n=== Iterating Collection 2 ===");
        printAll(collection2.createIterator());
    }

    /**
     * Uniform iteration — works with ANY MyIterator<Product>.
     * No knowledge of internal data structure needed.
     */
    private static void printAll(MyIterator<Product> iterator) {
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
