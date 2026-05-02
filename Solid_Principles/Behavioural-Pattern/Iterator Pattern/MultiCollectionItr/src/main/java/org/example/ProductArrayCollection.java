package org.example;

import java.util.ArrayList;
import java.util.List;

public class ProductArrayCollection {
    private List<Product> productList = new ArrayList<Product>();

    public  void addArrayIterator(Product product) {
        System.out.println(product + "before adding in list");
        productList.add(product);
    }

    public MultiIterator createIterator() {
        return new ProductArrayIterator(productList);
    }
}
