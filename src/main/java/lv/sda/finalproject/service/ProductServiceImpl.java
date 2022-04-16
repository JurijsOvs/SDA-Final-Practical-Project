package lv.sda.finalproject.service;

import lv.sda.finalproject.model.Product;
import lv.sda.finalproject.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void save(Product product){
      productRepository.save(product);
    }

    @Override
    public void delete(Long id){
        productRepository.deleteById(id);
    }

    @Override
    public void edit(Product product){
        productRepository.save(product);
    }

    @Override
    public Collection<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product findByProductName(String productName){return productRepository.findByProductName(productName);}
}
