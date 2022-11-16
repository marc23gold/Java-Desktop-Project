package com.example.demo.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();

    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    public static Part lookupPart(int partId) {
        Part ok = null;
        for(Part part : Inventory.allParts){
            if(part.getId() == partId) {
                ok = part;
            }
        }
        return ok;
    }

    public static Product lookupProduct(int productId) {
        Product ok = null;
        for(Product product : Inventory.allProducts){
            if(product.getId() == productId) {
                ok = product;
            }
        }
       return ok;
    }

    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> search = FXCollections.observableArrayList();

        if(partName.length() == 0 ) {
            search = Inventory.allParts;
        }
        else {
            for(Part part: Inventory.allParts) {
                if (part.getName().contains(partName)) {
                    search.add(part);
                }
            }
            }
        return search;
    }

    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> search = FXCollections.observableArrayList();

        if(productName.length() == 0) {
            search = Inventory.allProducts;
        }
        else {
        for(Product product: Inventory.allProducts) {
            if (product.getName().contains(productName)) {
                search.add(product);
            }

        }
        }
       return search;
    }

    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }

    public static void updateProduct(int index, Product newProduct) {
        allProducts.set(index, newProduct);
    }

    public static boolean deletePart(Part selectedPart) {
        return allParts.remove(selectedPart);
    }

    public static boolean deleteProduct(Product selectedProduct) {
        return allParts.remove(selectedProduct);
    }

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}
