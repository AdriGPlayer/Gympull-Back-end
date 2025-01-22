package edu.unideh.GymPull.services;

import edu.unideh.GymPull.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.unideh.GymPull.repositories.ProductRepository;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(String name, Double price, String imagePath) throws IOException {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setImageUrl(imagePath);
        return productRepository.save(product);
    }
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public Product addProduct(Product product) { return productRepository.save(product); }

    public List<Product> getProductsByCategoryId(Long categoryId) {
        return productRepository.findByCategoriaId(categoryId);
    }


}


