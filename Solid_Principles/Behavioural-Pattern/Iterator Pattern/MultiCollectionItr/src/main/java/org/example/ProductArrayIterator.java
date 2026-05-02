package org.example;

import java.util.List;

public class ProductArrayIterator implements MultiIterator{
    private List<Product> productList;
    private int  index = 0;
    public ProductArrayIterator(List<Product> productList ) {
        this.productList = productList;
    }

    @Override
    public boolean hasNext() {
        return index < productList.size();
    }

    @Override
    public Product  next() {
        index = index + 1;
        Product product = productList.get(index);
        System.out.println(product);
       return product;
    }
}
