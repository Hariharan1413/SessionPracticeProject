package org.example.demo2;

import java.util.ArrayList;
import java.util.List;

/**
 * List-based collection. The iterator is a private inner class —
 * no outside code can see or depend on it.
 */
public class ProductListCollection implements MyCollection<Product> {
    private List<Product> products = new ArrayList<>();

    @Override
    public void add(Product product) {
        products.add(product);
    }

    @Override
    public MyIterator<Product> createIterator() {
        return new ListIterator();
    }

    // Private inner class — hidden from outside
    private class ListIterator implements MyIterator<Product> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < products.size();
        }

        @Override
        public Product next() {
            Product product = products.get(index);
            index++;
            return product;
        }
    }
}
