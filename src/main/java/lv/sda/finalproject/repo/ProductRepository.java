package lv.sda.finalproject.repo;

import lv.sda.finalproject.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Product findByProductName(String productName);
}
