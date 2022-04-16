package lv.sda.finalproject.service;

import lv.sda.finalproject.model.Product;

import java.util.Collection;
import java.util.Optional;

public interface ProductService {
    void save(Product product);
    void delete(Long id);
    void edit(Product product);
    Optional<Product> findById(Long id);
    Collection<Product> findAllProducts();

    Product findByProductName(String productName);
}
