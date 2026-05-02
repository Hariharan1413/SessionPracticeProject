package org.example.demo2;

/**
 * Array-based collection. The iterator is a private inner class —
 * no outside code can see or depend on it.
 */
public class ProductArrayCollection implements MyCollection<Product> {
    private Product[] products;
    private int count = 0;

    public ProductArrayCollection(int capacity) {
        products = new Product[capacity];
    }

    @Override
    public void add(Product product) {
        if (count < products.length) {
            products[count] = product;
            count++;
        } else {
            System.out.println("Array is full, cannot add: " + product);
        }
    }

    @Override
    public MyIterator<Product> createIterator() {
        return new ArrayIterator();
    }

    // Private inner class — hidden from outside
    private class ArrayIterator implements MyIterator<Product> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < count;
        }

        @Override
        public Product next() {
            Product product = products[index];
            index++;
            return product;
        }
    }
}
