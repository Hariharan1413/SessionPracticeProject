package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Product product = new Product();
        product.setNamePro("Rocket");
        Product product1 = new Product();
        product1.setNamePro("Bat");

        ProductArrayCollection collectionProd  = new ProductArrayCollection();
        collectionProd.addArrayIterator(product);
        collectionProd.addArrayIterator(product1);

        MultiIterator iterator = collectionProd.createIterator();

        if(iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }
}