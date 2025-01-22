package edu.unideh.GymPull.repositories;

import edu.unideh.GymPull.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoriaId( Long categoryId);
}



