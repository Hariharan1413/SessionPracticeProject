package org.example;

public class Product {
    private String namepro;

    public void setNamePro(String namepro) {
        this.namepro = namepro;
    }

    public String getNamepro() {
        return namepro;
    }

    @Override
    public String toString() {
        return "Product{" +
                "namepro='" + namepro + '\'' +
                '}';
    }
}
